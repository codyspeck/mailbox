package com.speck.mailbox.lib.core;

import lombok.RequiredArgsConstructor;

import java.util.Map;

@RequiredArgsConstructor
public class MessagePipelineRegistry {

    private final Map<Class<?>, MessagePipeline> messagePipelines;

    public MessagePipeline get(Object message) {
        return messagePipelines.get(message.getClass());
    }

}
