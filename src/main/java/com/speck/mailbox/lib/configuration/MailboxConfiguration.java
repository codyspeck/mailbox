package com.speck.mailbox.lib.configuration;

import com.speck.mailbox.lib.core.MessagePipelineRegistry;
import com.speck.mailbox.lib.core.SequentialRelay;
import com.speck.mailbox.lib.core.MessageTableRegistry;
import com.speck.mailbox.lib.core.MessageTypeRegistry;
import com.speck.mailbox.lib.data.repositories.MessageDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
@EnableScheduling
public class MailboxConfiguration {

    @Bean
    public ThreadPoolTaskScheduler relayTaskScheduler(
            MessageDao messageDao,
            MessagePipelineRegistry messagePipelineRegistry,
            List<MailboxOptions> mailboxOptionsList) {

        var scheduler = new ThreadPoolTaskScheduler();
        scheduler.setPoolSize(mailboxOptionsList.size());
        scheduler.initialize();

        for (var mailboxOptions : mailboxOptionsList) {
            scheduler.schedule(
                    new SequentialRelay(mailboxOptions, messageDao, messagePipelineRegistry),
                    new CronTrigger("*/3 * * * * *"));
        }

        return scheduler;
    }

    @Bean
    public MessageTableRegistry messageTableRegistry(List<MailboxOptions> mailboxOptionsList) {
        var map = mailboxOptionsList
                .stream()
                .flatMap(mailboxOptions -> mailboxOptions
                        .getMessagesTypes()
                        .stream()
                        .map(messageType -> Map.entry(messageType, mailboxOptions.getTable())))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue));

        return new MessageTableRegistry(map);
    }

    @Bean
    public MessageTypeRegistry messageTypeRegistry(List<MessageTypeEntry> messageTypeEntries) {
        return new MessageTypeRegistry(
                messageTypeEntries.stream().collect(
                        Collectors.toMap(
                                MessageTypeEntry::getMessageType,
                                MessageTypeEntry::getMessageTypeString)));
    }

}
