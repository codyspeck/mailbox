package com.speck.mailbox.lib.data;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.Callable;

@Component
public class UnitOfWork {

    @Transactional
    public void executeInTransaction(Runnable runnable) {
        runnable.run();
    }

    @Transactional
    public <T> T executeInTransaction(Callable<T> callable) {
        try {
            return callable.call();
        }
        catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

}
