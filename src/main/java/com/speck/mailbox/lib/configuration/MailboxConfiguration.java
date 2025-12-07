package com.speck.mailbox.lib.configuration;

import com.speck.mailbox.lib.core.*;
import com.speck.mailbox.lib.data.MessageDao;
import com.speck.mailbox.lib.data.UnitOfWork;
import org.springframework.boot.task.ThreadPoolTaskSchedulerBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.lang.reflect.Type;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    public MessagePipelineRegistry getMessagePipelineRegistry(
            ApplicationContext applicationContext,
            MessageDao messageDao,
            UnitOfWork unitOfWork,
            List<MessageHandlerProperties> messageHandlerPropertiesList) {

        Map<Class<?>, MessagePipeline> map = messageHandlerPropertiesList
                .stream()
                .collect(Collectors.toMap(
                        MessageHandlerProperties::getMailboxMessageType,
                        messageHandlerProperties -> new MessagePipelineImpl(
                                messageHandlerProperties.getMailboxMessageType(),
                                messageDao,
                                (MessageHandler) applicationContext.getBean(messageHandlerProperties.getMailboxMessageHandlerType()),
                                unitOfWork)));

        return new MessagePipelineRegistry(map);
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
