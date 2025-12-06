package com.speck.mailbox.lib.core;

public interface MessagePipelineRegistry {

    MessagePipeline get(Object message);

}
