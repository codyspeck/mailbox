package com.speck.mailbox.lib.core;

import java.lang.reflect.Type;

public interface MessagePipelineRegistry {

    MessagePipelineImpl get(Type messageType);

}
