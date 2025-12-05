package com.speck.mailbox.lib.core;

public interface MessagePipeline {

    void send(MessageContext<Object> messageContext);

}
