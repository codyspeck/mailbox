package com.speck.mailbox.lib.configuration;

import java.lang.reflect.Type;

public class MessageTypeEntry {

    private final String messageTypeString;
    private final Type messageType;

    public MessageTypeEntry(String messageTypeString, Type messageType) {
        this.messageTypeString = messageTypeString;
        this.messageType = messageType;
    }

    public String getMessageTypeString() {
        return messageTypeString;
    }

    public Type getMessageType() {
        return messageType;
    }

}
