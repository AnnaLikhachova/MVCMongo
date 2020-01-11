package com.jcg.springmvc.mongo.models;

import java.io.Serializable;

public class UserEventStatus implements Serializable {
    private String id, userId;
    private EventStatus eventStatus;

    public UserEventStatus() {
        super();
    }

    public UserEventStatus(String id, String userId, EventStatus eventStatus) {
        super();
        this.id = id;
        this.userId = userId;
        this.eventStatus = eventStatus;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public EventStatus getEventStatus() {
        return eventStatus;
    }

    public void setEventStatus(EventStatus eventStatus) {
        this.eventStatus = eventStatus;
    }
}
