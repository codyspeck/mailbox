package com.speck.mailbox.lib.core;

import com.speck.mailbox.lib.data.MessageDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MailboxImpl implements Mailbox {

    private final MessageDao messageDao;
    private final MailboxTableRegistry mailboxTableRegistry;
    private final MessageFactory messageFactory;

    @Override
    public void insert(Object message) {
        messageDao.insert(
                mailboxTableRegistry.getMailboxTable(message),
                messageFactory.createMessage(message));
    }

}
