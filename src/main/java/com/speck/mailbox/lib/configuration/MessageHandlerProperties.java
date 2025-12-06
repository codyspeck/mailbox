package com.speck.mailbox.lib.configuration;

import lombok.Getter;

import java.lang.reflect.Type;

@Getter
public class MessageHandlerProperties {

    private final Type mailboxMessageHandlerType;
    private final Type mailboxMessageType;
    private int boundedCapacity;
    private int maxDegreeOfParallelism;

    public MessageHandlerProperties(Type mailboxMessageHandlerType, Type mailboxMessageType) {
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
