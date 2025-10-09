package com.speck.mailbox.lib.core;

import com.speck.mailbox.lib.data.repositories.MessageDao;
import org.springframework.stereotype.Component;

@Component
public class MailboxImpl implements Mailbox {

    private final MessageDao messageDao;
    private final MessageFactory messageFactory;
    private final MessageTableRegistry messageTableRegistry;

    public MailboxImpl(
            MessageDao messageDao,
            MessageFactory messageFactory,
            MessageTableRegistry messageTableRegistry) {
        this.messageDao = messageDao;
        this.messageFactory = messageFactory;
        this.messageTableRegistry = messageTableRegistry;
    }

    @Override
    public void insert(Object message) {
        messageDao.insert(
                messageTableRegistry.getTable(message),
                messageFactory.create(message));
    }

}
