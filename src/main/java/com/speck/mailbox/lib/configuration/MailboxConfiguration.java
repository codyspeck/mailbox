package com.speck.mailbox.lib.configuration;

import com.speck.mailbox.lib.core.*;
import com.speck.mailbox.lib.data.MessageDao;
import com.speck.mailbox.lib.data.UnitOfWork;
import org.springframework.boot.task.ThreadPoolTaskSchedulerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.lang.reflect.Type;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

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
    public ThreadPoolTaskScheduler mailboxThreadPoolTaskScheduler(
            List<MailboxProperties> mailboxPropertiesList,
            MessageContextFactory messageContextFactory,
            MessageDao messageDao,
            MessagePipelineRegistry messagePipelineRegistry,
            UnitOfWork unitOfWork) {

        var scheduler = new ThreadPoolTaskSchedulerBuilder()
                .poolSize(mailboxPropertiesList.size())
                .build();

        for (var mailboxProperties : mailboxPropertiesList) {
            var messageRelayConfiguration = new MessageRelayConfiguration(
                    mailboxProperties.getTable(),
                    mailboxProperties.getBatchSize(),
                    Duration.ofMinutes(5));

            var messageRelay = new MessageRelay(
                    messageRelayConfiguration,
                    messageContextFactory,
                    messageDao,
                    messagePipelineRegistry,
                    unitOfWork);

            scheduler.scheduleWithFixedDelay(messageRelay, Duration.ofSeconds(1));
        }

        return scheduler;
    }

}
