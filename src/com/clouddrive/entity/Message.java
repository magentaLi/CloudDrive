package com.clouddrive.entity;

public class Message {
	private int id;
	private String userName;
	private String message;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Message(String userName, String message) {
		super();
		this.userName = userName;
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public Message() {
		super();
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
