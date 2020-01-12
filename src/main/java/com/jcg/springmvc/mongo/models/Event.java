package com.jcg.springmvc.mongo.models;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class Event implements Serializable {

    private String id, name, location;
    private Date timestamp;
    private String[] invitedList = new String[0];
    private UserEventStatus[] statuses = new UserEventStatus[0];

    public Event(String id, String name, String location, Date timestamp) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.timestamp = timestamp;
    }

    public void invite(String userId) {
        List<String> updatedInvitedList = Arrays.asList(this.invitedList);
        updatedInvitedList.add(userId);
        this.invitedList = updatedInvitedList.toArray(new String[0]);
    }


    public void updateInvitationStatus(UserEventStatus status) {
        List<String> invitedList = Arrays.asList(this.invitedList);
        if(!invitedList.contains(status.getUserId())){
            return; //can't accept invitation if not invited
        }
        List<UserEventStatus> updatedStatuses = Arrays.asList(this.statuses);
        Optional<UserEventStatus> existingUserStatus = updatedStatuses.stream().filter(o -> o.getUserId().equals(status.getUserId())).findFirst();
        if(existingUserStatus.isPresent()) {
            //update existing "going/not going" status
            existingUserStatus.get().setEventStatus(status.getEventStatus());
        } else {
            //set "going/not going status"
            updatedStatuses.add(status);
        }
        this.statuses = updatedStatuses.toArray(new UserEventStatus[0]);
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
