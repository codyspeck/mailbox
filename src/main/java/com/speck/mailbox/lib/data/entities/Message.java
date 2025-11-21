package com.speck.mailbox.lib.data.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class Message {

    private long id;
    private String payload;
    private String type;
    private Date createdAt;
    private Date processedAt;
    private Date lockedUntil;

}
