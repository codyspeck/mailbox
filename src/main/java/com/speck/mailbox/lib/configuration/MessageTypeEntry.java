package com.speck.mailbox.lib.configuration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MessageTypeEntry {

    private final String messageType;
    private final Class<?> messageClass;

}
