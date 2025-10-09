package com.speck.mailbox.app.handlers;

import com.speck.mailbox.app.messages.ProductCreated;
import com.speck.mailbox.lib.core.MessageHandler;

public class ProductCreatedHandler implements MessageHandler<ProductCreated> {

    @Override
    public void handle(ProductCreated productCreated) {
        System.out.println("Handling ProductCreated message");
    }

}
