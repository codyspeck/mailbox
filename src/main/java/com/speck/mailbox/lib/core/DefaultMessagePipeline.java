package com.speck.mailbox.lib.core;

public class DefaultMessagePipeline implements MessagePipeline {

    private final MessageHandler messageHandler;

    public DefaultMessagePipeline(MessageHandler messageHandler) {
        this.messageHandler = messageHandler;
    }

    @Override
    public void send(Object message) {
        messageHandler.handle(message);
    }

}
