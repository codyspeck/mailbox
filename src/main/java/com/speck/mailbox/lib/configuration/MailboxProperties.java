package com.speck.mailbox.lib.configuration;

import lombok.Getter;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Getter
public class MailboxProperties {

    private final String table;
    private final List<Type> messagesTypes;
    private int batchSize;

    public MailboxProperties(String table) {
        this.table = table;
        this.batchSize = 100;
        this.messagesTypes = new ArrayList<>();
    }

    public MailboxProperties withBatchSize(int batchSize) {
        this.batchSize = batchSize;
        return this;
    }

    public MailboxProperties withMessageType(Type messageType) {
        messagesTypes.add(messageType);
        return this;
    }

}
