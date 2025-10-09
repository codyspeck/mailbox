package com.speck.mailbox.lib.core;

import com.speck.mailbox.lib.configuration.MessageHandlerOptions;
import org.springframework.context.ApplicationContext;
import org.springframework.core.ResolvableType;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MessagePipelineRegistry {

    private final ApplicationContext applicationContext;
    private final List<MessageHandlerOptions> messageHandlerOptionsList;

    public MessagePipelineRegistry(
            ApplicationContext applicationContext,
            List<MessageHandlerOptions> messageHandlerOptionsList) {
        this.applicationContext = applicationContext;
        this.messageHandlerOptionsList = messageHandlerOptionsList;
    }

    public MessagePipeline get(Object message) {
        var messageHandlerType = ResolvableType.forClassWithGenerics(
                MessageHandler.class,
                message.getClass());

        var messageHandler = (MessageHandler) applicationContext
                .getBeanProvider(messageHandlerType)
                .getObject();

        return new DefaultMessagePipeline(messageHandler);
    }

}
