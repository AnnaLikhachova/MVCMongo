package com.jcg.springmvc.mongo.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id, name;
	private Group[] groups = new Group[0];

	public User() {
		super();
	}

	public User(String id, String name, Group[] groups) {
		super();
		this.id = id;
		this.name = name;
		this.groups = groups;
	}

	public User(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Group[] getGroups() {
		return groups;
	}

	public void setGroups(Group[] groups) {
		this.groups = groups;
	}

	public void addToGroup(Group group) {
		List<Group> updatedGroupsList = new ArrayList<>();
		if(this.groups != null) {
			updatedGroupsList = new ArrayList<>(Arrays.asList(this.groups));
		}
		updatedGroupsList.add(group);
		this.groups = updatedGroupsList.toArray(new Group[0]);
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
