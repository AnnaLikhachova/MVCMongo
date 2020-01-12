package com.jcg.springmvc.mongo.models;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id, name, email, password;
	private Group[] groups = new Group[0];

	public User(String id, String name, String email) {
		this.id = id;
		this.name = name;
		this.email = email;
	}

	public User() {
		super();
	}

	public User(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void addToGroup(Group group) {
		List<Group> updatedGroupsList = Arrays.asList(this.groups);
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
