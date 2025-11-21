package com.speck.mailbox.lib.core;

import java.time.Duration;

public record MessageRelayConfiguration(
        String mailboxTable,
        int batchSize,
        Duration lockWindow) {}
