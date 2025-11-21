package com.speck.mailbox.lib.core;

public interface MessageTypeRegistry {

    Class getMessageType(String messageTypeString);

    String getMessageTypeString(Object message);

}
