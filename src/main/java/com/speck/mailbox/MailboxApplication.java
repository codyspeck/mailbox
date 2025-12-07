package com.speck.mailbox;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class MailboxApplication {

	public static void main(String[] args) { SpringApplication.run(MailboxApplication.class, args);	}

}
