package com.speck.mailbox.lib.core;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class MessageTypeRegistry {

    private final Map<String, Type> messageTypes;
    private final Map<Type, String> messageTypeStrings;

    public MessageTypeRegistry(Map<String, Type> messageTypes) {
        this.messageTypes = messageTypes;
        messageTypeStrings = messageTypes.entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey));
    }

    public Type getMessageType(String messageTypeString) {
        return messageTypes.get(messageTypeString);
    }

    public String getMessageTypeString(Object message) {
        return messageTypeStrings.get(message.getClass());
    }

}
