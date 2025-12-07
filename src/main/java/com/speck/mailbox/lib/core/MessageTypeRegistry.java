package com.speck.mailbox.lib.core;

import com.speck.mailbox.lib.configuration.MessageTypeEntry;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class MessageTypeRegistry {

    private final Map<String, Class<?>> messageClasses;
    private final Map<Type, String> messageTypes;

    public MessageTypeRegistry(List<MessageTypeEntry> messageTypeEntries) {
        this.messageClasses = messageTypeEntries.stream().collect(Collectors.toMap(
                MessageTypeEntry::getMessageType,
                MessageTypeEntry::getMessageClass));

        this.messageTypes = messageTypeEntries.stream().collect(Collectors.toMap(
                MessageTypeEntry::getMessageClass,
                MessageTypeEntry::getMessageType));
    }

    public Class<?> getMessageClass(String messageType) {
        return messageClasses.get(messageType);
    }

    public String getMessageType(Object message) {
        return messageTypes.get(message.getClass());
    }

}
