package com.speck.mailbox.app;

import com.speck.mailbox.app.handlers.ProductCreatedHandler;
import com.speck.mailbox.app.handlers.ProductDeliveredHandler;
import com.speck.mailbox.app.handlers.ProductShippedHandler;
import com.speck.mailbox.app.messages.ProductCreated;
import com.speck.mailbox.app.messages.ProductDelivered;
import com.speck.mailbox.app.messages.ProductShipped;
import com.speck.mailbox.lib.configuration.MessageHandlerOptions;
import com.speck.mailbox.lib.configuration.MailboxOptions;
import com.speck.mailbox.lib.configuration.MessageTypeEntry;
import com.speck.mailbox.lib.configuration.RelayStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class AppConfiguration {

    @Bean
    public List<MailboxOptions> mailboxOptionsList() {
        return List.of(
                new MailboxOptions("inbox")
                        .withBatchSize(1000)
                        .withMessageType(ProductCreated.class)
                        .withMessageType(ProductDelivered.class)
                        .withMessageType(ProductShipped.class));
    }

    @Bean
    public List<MessageHandlerOptions> mailboxMessageHandlerOptionsList() {
        return List.of(
                new MessageHandlerOptions(ProductCreatedHandler.class, ProductCreated.class)
                        .withBoundedCapacity(1000)
                        .withMaxDegreeOfParallelism(1000),

                new MessageHandlerOptions(ProductDeliveredHandler.class, ProductDelivered.class)
                        .withBoundedCapacity(1000)
                        .withMaxDegreeOfParallelism(1000),

                new MessageHandlerOptions(ProductShippedHandler.class, ProductShipped.class)
                        .withBoundedCapacity(1000)
                        .withMaxDegreeOfParallelism(1000));
    }

    @Bean
    public List<MessageTypeEntry> messageTypeEntries() {
        return List.of(
                new MessageTypeEntry("product-created", ProductCreated.class),
                new MessageTypeEntry("product-delivered", ProductDelivered.class),
                new MessageTypeEntry("product-shipped", ProductShipped.class));
    }

}
