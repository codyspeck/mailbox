package com.speck.mailbox.lib.core;

import com.speck.mailbox.lib.data.entities.Message;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MessageContextFactory {

    private final MessageFactory messageFactory;
    private final MessageSerializer messageSerializer;
    private final MessageTypeRegistry messageTypeRegistry;

    public MessageContext create(Message message, String mailboxTable) {
        return MessageContext.builder()
                .messageId(message.getId())
                .mailboxTable(mailboxTable)
                .message(messageSerializer.deserialize(
                        message.getPayload(),
                        messageTypeRegistry.getMessageType(message.getType())))
                .build();
    }

}
