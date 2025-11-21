package com.speck.mailbox.lib.core;

import com.speck.mailbox.lib.data.entities.Message;

public interface MailboxTableRegistry {

    String getMailboxTable(Message message);

}
