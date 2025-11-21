package com.speck.mailbox.lib.configuration;

import java.lang.reflect.Type;
import java.util.List;

public record MailboxProperties(
        String table,
        int batchSize,
        List<Type> messagesTypes) {}
