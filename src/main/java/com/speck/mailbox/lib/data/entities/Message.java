package com.speck.mailbox.lib.data.entities;

import java.util.Date;
import java.util.UUID;

public class Message {

    private long id;
    private String payload;
    private String type;
    private Date createdAt;
    private Date processedAt;
    private Date lockedUntil;

    public long getId() {
        return id;
    }

    public String getPayload() {
        return this.payload;
    }

    public String getType() {
        return type;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getProcessedAt() {
        return processedAt;
    }

    public Date getLockedUntil() {
        return lockedUntil;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setProcessedAt(Date processedAt) {
        this.processedAt = processedAt;
    }

    public void setLockedUntil(Date lockedUntil) {
        this.lockedUntil = lockedUntil;
    }

}
