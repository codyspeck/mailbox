package com.speck.mailbox.lib.core;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MessageContext {

    private final long messageId;
    private final String mailboxTable;
    private final Object message;

}
