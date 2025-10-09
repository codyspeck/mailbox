package com.speck.mailbox.lib.core;

import java.lang.reflect.Type;
import java.util.Map;

public class MessageTypeRegistry {

    private final Map<Type, String> messageTypeStrings;

    public MessageTypeRegistry(Map<Type, String> messageTypeStrings) {
        this.messageTypeStrings = messageTypeStrings;
    }

    public String getMessageTypeString(Object message) {
        return messageTypeStrings.get(message.getClass());
    }

}
