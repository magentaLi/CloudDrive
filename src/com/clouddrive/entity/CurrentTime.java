package com.clouddrive.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CurrentTime {
	private Date date;
	private String dateString;

	public CurrentTime() {
		date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		dateString = format.format(date);
	}

	public String getDateString() {
		return dateString;
	}
}
