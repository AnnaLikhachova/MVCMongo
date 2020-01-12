package com.jcg.springmvc.mongo.models;

import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

@Document(collection = "groups")
public class Group implements Serializable {

    private String id, name;
    private User[] members = new User[0];

    public Group() {
        super();
    }

    public Group(String id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    public void addToGroup(User user) {
        List<User> updatedMembersList = Arrays.asList(this.members);
        updatedMembersList.add(user);
        this.members = updatedMembersList.toArray(new User[0]);
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

    public User[] getMembers() {
        return members;
    }

    public void setMembers(User[] members) {
        this.members = members;
    }
}
