package com.speck.mailbox.lib.core;

import java.lang.reflect.Type;

public interface MessagePipelineRegistry {

    MessagePipeline get(Type messageType);

}
