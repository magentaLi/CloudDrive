package com.clouddrive.entity;

public class FileMessage {
	private String fileName = "";
	private String uuidName = "";
	private String updateTime = "";
	private String type = "";
	private String path = "";
	private String user = "";
	private long size = 0;
	private String showSize;

	public FileMessage(String fileName, String uuidName, String updateTime, String type, String path, String user,
			long size) {
		super();
		this.fileName = fileName;
		this.uuidName = uuidName;
		this.updateTime = updateTime;
		this.type = type;
		this.path = path;
		this.user = user;
		this.size = size;
		showSize = Conversion.conversion(size);
	}

	public FileMessage(String fileName, String updateTime, String type, String path, String user) {
		super();
		this.fileName = fileName;
		this.updateTime = updateTime;
		this.type = type;
		this.path = path;
		this.user = user;
	}

	public String getShowSize() {
		return showSize;
	}

	public void setShowSize(String showSize) {
		this.showSize = showSize;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getUuidName() {
		return uuidName;
	}

	public void setUuidName(String uuidName) {
		this.uuidName = uuidName;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}
}
