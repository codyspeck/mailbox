package com.speck.mailbox.lib.core;

import com.speck.mailbox.lib.data.UnitOfWork;
import com.speck.mailbox.lib.data.MessageDao;
import lombok.RequiredArgsConstructor;

import java.time.Instant;

@RequiredArgsConstructor
public class MessagePipelineImpl<TMessage> implements MessagePipeline {

    private final Class<TMessage> clazz;
    private final MessageDao messageDao;
    private final MessageHandler<TMessage> messageHandler;
    private final UnitOfWork unitOfWork;

    public void send(MessageContext messageContext) {
        unitOfWork.executeInTransaction(() -> {
            var message = messageDao.get(
                    messageContext.getMailboxTable(),
                    messageContext.getMessageId());

            if (message.getProcessedAt() != null) {
                return;
            }

            messageHandler.handle(clazz.cast(messageContext.getMessage()));

            messageDao.setProcessedAt(
                    messageContext.getMailboxTable(),
                    Instant.now(),
                    message);
        });
    }

}
