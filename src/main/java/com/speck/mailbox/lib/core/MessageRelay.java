package com.speck.mailbox.lib.core;

import com.speck.mailbox.lib.data.UnitOfWork;
import com.speck.mailbox.lib.data.repositories.MessageDao;
import lombok.RequiredArgsConstructor;

import java.time.Instant;

@RequiredArgsConstructor
public class MessageRelay implements Runnable {

    private final MessageRelayConfiguration messageRelayConfiguration;
    private final MessageContextFactory messageContextFactory;
    private final MessageDao messageDao;
    private final MessagePipelineRegistry messagePipelineRegistry;
    private final UnitOfWork unitOfWork;

    @Override
    public void run() {
        var messages = unitOfWork.executeInTransaction(() -> {
            var innerMessages = messageDao.get(
                    messageRelayConfiguration.mailboxTable(),
                    messageRelayConfiguration.batchSize());

            messageDao.setLockedUntil(
                    messageRelayConfiguration.mailboxTable(),
                    Instant.now().plus(messageRelayConfiguration.lockWindow()),
                    innerMessages);

            return innerMessages;
        });

        for (var message : messages) {
            var messageContext = messageContextFactory.create(
                    message,
                    messageRelayConfiguration.mailboxTable());

            messagePipelineRegistry
                    .get(messageContext.getMessage().getClass())
                    .send(messageContext);
        }
    }

}
