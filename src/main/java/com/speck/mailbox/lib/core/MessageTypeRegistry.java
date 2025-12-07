package com.speck.mailbox.lib.core;

import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class MessageTypeRegistry {

    private final Map<String, Class<?>> messageClasses;
    private final Map<Type, String> messageTypes;

    public MessageTypeRegistry(Map<String, Class<?>> messageClasses) {
        this.messageClasses = messageClasses;
        this.messageTypes = messageClasses.entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey));
    }

    public Class<?> getMessageClass(String messageType) {
        return messageClasses.get(messageType);
    }

    public String getMessageType(Object message) {
        return messageTypes.get(message.getClass());
    }

}
