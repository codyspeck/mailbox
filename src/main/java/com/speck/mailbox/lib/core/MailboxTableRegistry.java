package com.speck.mailbox.lib.core;

import lombok.RequiredArgsConstructor;

import java.lang.reflect.Type;
import java.util.Map;

@RequiredArgsConstructor
public class MailboxTableRegistry {

    private final Map<Type, String> mailboxTables;

    public String getMailboxTable(Object message) {
        return mailboxTables.get(message.getClass());
    }

}
