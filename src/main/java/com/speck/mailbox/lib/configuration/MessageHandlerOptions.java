package com.speck.mailbox.lib.configuration;

import com.speck.mailbox.lib.core.MessageHandler;

import java.lang.reflect.Type;

public class MessageHandlerOptions {

    private final Type mailboxMessageHandlerType;
    private final Type mailboxMessageType;

    private int boundedCapacity;
    private int maxDegreeOfParallelism;

    public <TMessage, THandler extends MessageHandler<TMessage>> MessageHandlerOptions(
            Class<THandler> mailboxMessageHandlerType,
            Class<TMessage> mailboxMessageType)
    {
        this.mailboxMessageType = mailboxMessageType;
        this.mailboxMessageHandlerType = mailboxMessageHandlerType;
    }

    public MessageHandlerOptions withBoundedCapacity(int boundedCapacity) {
        this.boundedCapacity = boundedCapacity;
        return this;
    }

    public MessageHandlerOptions withMaxDegreeOfParallelism(int maxDegreeOfParallelism) {
        this.maxDegreeOfParallelism = maxDegreeOfParallelism;
        return this;
    }

}
