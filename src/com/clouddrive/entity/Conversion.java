package com.clouddrive.entity;

public class Conversion {
	public static String conversion(long size) {
		String showSize;
		String unit;
		String units[] = { "B", "KB", "M", "G", "T" };
		double sizeNum = size;
		for (int i = 0; i < units.length; i++) {
			if (sizeNum < 1024) {
				unit = units[i];
				showSize = String.format("%.2f", sizeNum);
				return showSize + " " + unit;
			}
			sizeNum = sizeNum / 1024;
		}
		sizeNum = sizeNum * 1024;
		showSize = String.format("%.2f", sizeNum);
		unit = "T";
		return showSize + " " + unit;
	}
}
