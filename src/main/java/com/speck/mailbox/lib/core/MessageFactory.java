package com.speck.mailbox.lib.core;

import com.speck.mailbox.lib.data.entities.Message;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;

@Component
public class MessageFactory {

    private final MessageSerializer messageSerializer;
    private final MessageTypeRegistry messageTypeRegistry;

    public MessageFactory(MessageSerializer messageSerializer, MessageTypeRegistry messageTypeRegistry) {
        this.messageSerializer = messageSerializer;
        this.messageTypeRegistry = messageTypeRegistry;
    }

    public Object create(String message) {
        return messageSerializer.deserialize(
                message,
                messageTypeRegistry.getMessageType(message));
    }

    public Message create(Object message) {
        return Message.builder()
                .payload(messageSerializer.serialize(message))
                .type(messageTypeRegistry.getMessageTypeString(message))
                .createdAt(Date.from(Instant.now()))
                .build();
    }

}
