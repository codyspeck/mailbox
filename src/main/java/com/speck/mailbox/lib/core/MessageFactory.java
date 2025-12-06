package com.speck.mailbox.lib.core;

import com.speck.mailbox.lib.data.Message;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@RequiredArgsConstructor
public class MessageFactory {

    private final MessageSerializer messageSerializer;
    private final MessageTypeRegistry messageTypeRegistry;

    public Message createMessage(Object message) {
        return Message.builder()
                .payload(messageSerializer.serialize(message))
                .type(messageTypeRegistry.getMessageTypeString(message))
                .createdAt(new Date())
                .build();
    }

}
