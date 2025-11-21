package com.speck.mailbox.lib.core;

import com.speck.mailbox.lib.data.UnitOfWork;
import com.speck.mailbox.lib.data.repositories.MessageDao;
import lombok.RequiredArgsConstructor;

import java.time.Instant;

@RequiredArgsConstructor
public class MessagePipeline<TMessage> {

    private final MessageDao messageDao;
    private final MessageHandler<TMessage> messageHandler;
    private final UnitOfWork unitOfWork;

    public void send(MessageContext<TMessage> messageContext) {
        unitOfWork.executeInTransaction(() -> {
            var message = messageDao.get(
                    messageContext.getMailboxTable(),
                    messageContext.getMessageId());

            if (message.getProcessedAt() != null) {
                return;
            }

            messageHandler.handle(messageContext.getMessage());

            messageDao.setProcessedAt(
                    messageContext.getMailboxTable(),
                    Instant.now(),
                    message);
        });
    }

}
