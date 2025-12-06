package com.speck.mailbox.lib.configuration;

import com.speck.mailbox.lib.core.MailboxTableRegistry;
import com.speck.mailbox.lib.core.MessageTypeRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
public class MailboxConfiguration {

    @Bean
    public MailboxTableRegistry getMailboxTableRegistry(List<MailboxProperties> mailboxPropertiesList) {
        var mailboxTables = new HashMap<Type, String>();

        // todo: use functional programming
        for (var mailboxProperties : mailboxPropertiesList) {
            for (var messageType : mailboxProperties.getMessagesTypes()) {
                mailboxTables.put(messageType, mailboxProperties.getTable());
            }
        }

        return new MailboxTableRegistry(mailboxTables);
    }

    @Bean
    public MessageTypeRegistry getMessageTypeRegistry(List<MessageTypeEntry> messageTypeEntries) {
        return new MessageTypeRegistry(
                messageTypeEntries
                        .stream()
                        .collect(Collectors.toMap(
                                MessageTypeEntry::getMessageTypeString,
                                MessageTypeEntry::getMessageType)));
    }

}
