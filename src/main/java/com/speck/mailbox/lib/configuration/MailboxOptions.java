package com.speck.mailbox.lib.configuration;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class MailboxOptions {

    private final String table;

    private int batchSize;
    private List<Type> messagesTypes;
    private RelayStrategy relayStrategy;

    public MailboxOptions(String table) {
        this.table = table;
        this.messagesTypes = new ArrayList<>();
        this.relayStrategy = RelayStrategy.Sequential;
    }

    public String getTable() {
        return table;
    }

    public int getBatchSize() {
        return batchSize;
    }

    public List<Type> getMessagesTypes() {
        return messagesTypes;
    }

    public MailboxOptions withBatchSize(int batchSize) {
        this.batchSize = batchSize;
        return this;
    }

    public MailboxOptions withMessageType(Type messageType) {
        messagesTypes.add(messageType);
        return this;
    }

    public MailboxOptions withRelayStrategy(RelayStrategy relayStrategy) {
        this.relayStrategy = relayStrategy;
        return this;
    }

}
