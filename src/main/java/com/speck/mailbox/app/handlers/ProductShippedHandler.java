package com.speck.mailbox.app.handlers;

import com.speck.mailbox.app.messages.ProductShipped;
import com.speck.mailbox.lib.core.MessageHandler;

public class ProductShippedHandler implements MessageHandler<ProductShipped> {

    @Override
    public void handle(ProductShipped productCreated) {}

}
