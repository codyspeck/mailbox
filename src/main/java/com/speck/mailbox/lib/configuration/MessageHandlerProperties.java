package com.speck.mailbox.lib.configuration;

import lombok.Getter;

@Getter
public class MessageHandlerProperties {

    private final Class<?> mailboxMessageHandlerType;
    private final Class<?> mailboxMessageType;
    private int boundedCapacity;
    private int maxDegreeOfParallelism;

    public MessageHandlerProperties(Class<?> mailboxMessageHandlerType, Class<?> mailboxMessageType) {
        this.mailboxMessageHandlerType = mailboxMessageHandlerType;
        this.mailboxMessageType = mailboxMessageType;
    }

    public MessageHandlerProperties withBoundedCapacity(int boundedCapacity) {
        this.boundedCapacity = boundedCapacity;
        return this;
    }

    public MessageHandlerProperties withMaxDegreeOfParallelism(int maxDegreeOfParallelism) {
        this.maxDegreeOfParallelism = maxDegreeOfParallelism;
        return this;
    }

}
