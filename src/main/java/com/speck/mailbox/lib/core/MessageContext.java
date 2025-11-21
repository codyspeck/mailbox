package com.speck.mailbox.lib.core;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MessageContext<TMessage> {

    private final long messageId;
    private final String mailboxTable;
    private final TMessage message;

}
