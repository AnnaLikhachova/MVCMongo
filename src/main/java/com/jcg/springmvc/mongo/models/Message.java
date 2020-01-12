package com.jcg.springmvc.mongo.models;

import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

@Document(collection = "messages")
public class Message implements Serializable {

    private String id, fromUserId, text;
    private Date timestamp;

    public Message() {
        super();
    }

    public Message(String id, String fromUserId, String toUserId, String text, Date timestamp) {
        super();
        this.id = id;
        this.fromUserId = fromUserId;
        this.text = text;
        this.timestamp = timestamp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(String fromUserId) {
        this.fromUserId = fromUserId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
