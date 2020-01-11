package com.jcg.springmvc.mongo.models;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class Chat implements Serializable {

    private String id, name;
    private String[] participantsIds;
    private Message[] messages = new Message[]{};

    public Chat() {
        super();
    }

    public Chat(String id, String name, String[] participantsIds) {
        super();
        this.id = id;
        this.name = name;
        this.participantsIds = participantsIds;
    }

    public void addParticipant(String userId) {
        List<String> updatedParticipantsList = Arrays.asList(this.participantsIds);
        updatedParticipantsList.add(userId);
        this.participantsIds = updatedParticipantsList.toArray(new String[0]);
    }

    public void addMessage(Message message) {
        List<Message> updatedMessagesList = Arrays.asList(this.messages);
        updatedMessagesList.add(message);
        this.messages = updatedMessagesList.toArray(new Message[0]);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
