package com.speck.mailbox.lib.core;

import com.speck.mailbox.lib.configuration.MailboxOptions;
import com.speck.mailbox.lib.data.repositories.MessageDao;

public class SequentialRelay implements MessageRelay {

    private final MailboxOptions mailboxOptions;
    private final MessageDao messageDao;
    private final MessagePipelineRegistry messagePipelineRegistry;

    public SequentialRelay(
            MailboxOptions mailboxOptions,
            MessageDao messageDao,
            MessagePipelineRegistry messagePipelineRegistry) {
        this.mailboxOptions = mailboxOptions;
        this.messageDao = messageDao;
        this.messagePipelineRegistry = messagePipelineRegistry;
    }

    @Override
    public void run() {
        var messages = messageDao.get(
                mailboxOptions.getTable(),
                mailboxOptions.getBatchSize());

        for (var message : messages) {
            messagePipelineRegistry
                    .get(message)
                    .send(message);
        }

        messageDao.setProcessedAt(mailboxOptions.getTable(), messages);
    }

}
