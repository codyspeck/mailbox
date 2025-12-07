package com.speck.mailbox.lib.core;

import com.speck.mailbox.lib.data.Message;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MessageContextFactory {

    private final MessageSerializer messageSerializer;
    private final MessageTypeRegistry messageTypeRegistry;

    public MessageContext create(Message message, String mailboxTable) {
        return MessageContext.builder()
                .messageId(message.getId())
                .mailboxTable(mailboxTable)
                .message(messageSerializer.deserialize(
                        message.getPayload(),
                        messageTypeRegistry.getMessageClass(message.getType())))
                .build();
    }

}
