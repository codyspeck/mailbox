package com.speck.mailbox.lib.configuration;

import java.lang.reflect.Type;

public record MessageHandlerProperties(
        Type mailboxMessageHandlerType,
        Type mailboxMessageType,
        int boundedCapacity,
        int maxDegreeOfParallelism) {
}
