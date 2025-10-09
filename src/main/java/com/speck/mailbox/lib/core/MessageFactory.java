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

    public Message create(Object payload) {
        var message = new Message();
        message.setPayload(messageSerializer.serialize(payload));
        message.setType(messageTypeRegistry.getMessageTypeString(payload));
        message.setCreatedAt(Date.from(Instant.now()));
        return message;
    }

}
