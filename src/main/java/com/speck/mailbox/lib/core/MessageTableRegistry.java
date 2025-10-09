package com.speck.mailbox.lib.core;

import java.lang.reflect.Type;
import java.util.Map;

public class MessageTableRegistry {

    private final Map<Type, String> tables;

    public MessageTableRegistry(Map<Type, String> tables) {
        this.tables = tables;
    }

    public String getTable(Object message) {
        return tables.get(message.getClass());
    }

}
