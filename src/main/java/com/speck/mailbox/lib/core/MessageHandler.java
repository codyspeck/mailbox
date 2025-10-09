package com.speck.mailbox.lib.core;

import org.springframework.stereotype.Component;

@Component
public interface MessageHandler<TMessage> {

    void handle(TMessage message);

}
