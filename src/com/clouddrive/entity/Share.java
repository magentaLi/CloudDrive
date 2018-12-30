package com.clouddrive.entity;

public class Share {
	private String user;
	private String fileName;
	private String uuidName;
	private String url;
	private String shareTime;
	private String key;
	private int downloads;
	private long size;
	private String type;
	private String showSize;

	public Share(String user, String uuidName, String url, String shareTime, String key, int downloads, long size) {
		super();
		this.user = user;
		this.uuidName = uuidName;
		this.fileName = uuidName.substring(uuidName.indexOf("_") + 1);
		this.url = url;
		this.shareTime = shareTime;
		this.key = key;
		this.downloads = downloads;
		this.size = size;
		this.type = Type.getType(this.fileName);
		this.showSize = Conversion.conversion(size);
	}

	public String getShowSize() {
		return showSize;
	}

	public void setShowSize(String showSize) {
		this.showSize = showSize;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUuidName() {
		return uuidName;
	}

	public void setUuidName(String uuidName) {
		this.uuidName = uuidName;
	}

	public int getDownloads() {
		return downloads;
	}

	public void setDownloads(int downloads) {
		this.downloads = downloads;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getShareTime() {
		return shareTime;
	}

	public void setShareTime(String shareTime) {
		this.shareTime = shareTime;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

}
