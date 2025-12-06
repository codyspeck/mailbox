package com.speck.mailbox.app.api;

import com.speck.mailbox.app.messages.ProductCreated;
import com.speck.mailbox.lib.core.Mailbox;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductsController {

    private final Mailbox mailbox;

    public ProductsController(Mailbox mailbox) {
        this.mailbox = mailbox;
    }

    @PostMapping("/api/v1/products")
    public void CreateProduct() {
        mailbox.insert(new ProductCreated());
    }

}
