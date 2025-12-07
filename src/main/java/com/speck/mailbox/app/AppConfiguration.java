package com.speck.mailbox.app;

import com.speck.mailbox.app.handlers.ProductCreatedHandler;
import com.speck.mailbox.app.messages.ProductCreated;
import com.speck.mailbox.lib.configuration.MessageHandlerProperties;
import com.speck.mailbox.lib.configuration.MailboxProperties;
import com.speck.mailbox.lib.configuration.MessageTypeEntry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class AppConfiguration {

    @Bean
    public List<MailboxProperties> mailboxOptionsList() {
        return List.of(
                new MailboxProperties("inbox")
                        .withBatchSize(1000)
                        .withMessageType(ProductCreated.class));
    }

    @Bean
    public List<MessageHandlerProperties> mailboxMessageHandlerOptionsList() {
        return List.of(
                new MessageHandlerProperties(ProductCreatedHandler.class, ProductCreated.class)
                        .withBoundedCapacity(1000)
                        .withMaxDegreeOfParallelism(1000));
    }

    @Bean
    public List<MessageTypeEntry> messageTypeEntries() {
        return List.of(
                new MessageTypeEntry("product-created", ProductCreated.class));
    }

}
