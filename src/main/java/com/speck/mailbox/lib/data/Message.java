package com.speck.mailbox.lib.data;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Message {

    private long id;
    private String payload;
    private String type;
    private Date createdAt;
    private Date processedAt;
    private Date lockedUntil;

}
