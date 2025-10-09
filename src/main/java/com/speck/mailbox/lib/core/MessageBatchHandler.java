package com.speck.mailbox.lib.core;

public interface MessageBatchHandler<TMessage> {

    void handle(TMessage[] messages);

}
