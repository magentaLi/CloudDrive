package com.clouddrive.entity;

public class Type {

	public static String getType(String name) {
		if (isImage(name)) {
			return "image";
		} else if (isVideo(name)) {
			return "video";
		} else if (isMusic(name)) {
			return "music";
		} else if (isDoc(name)) {
			return "doc";
		} else if (isXls(name)) {
			return "xls";
		} else if (isPpt(name)) {
			return "ppt";
		} else if (isTxt(name)) {
			return "txt";
		} else if (isZip(name)) {
			return "zip";
		} else if (isPdf(name)) {
			return "pdf";
		}
		return "other";
	}

	public static boolean isImage(String name) {
		String suffix = name.substring(name.lastIndexOf(".") + 1);
		String imageSuffixs[] = { "bmp", "jpg", "jpeg", "png", "gif" };

		for (String i : imageSuffixs) {
			if (i.equalsIgnoreCase(suffix)) {
				return true;
			}
		}

		return false;
	}

	public static boolean isVideo(String name) {
		String suffix = name.substring(name.lastIndexOf(".") + 1);
		String imageSuffixs[] = { "rm", "rmvb", "wmv", "avi", "mp4", "3gp", "mkv" };

		for (String i : imageSuffixs) {
			if (i.equalsIgnoreCase(suffix)) {
				return true;
			}
		}

		return false;
	}

	public static boolean isMusic(String name) {
		String suffix = name.substring(name.lastIndexOf(".") + 1);
		String imageSuffixs[] = { "WAV", "MP3", "AIF", "MIDI", "WMA" };

		for (String i : imageSuffixs) {
			if (i.equalsIgnoreCase(suffix)) {
				return true;
			}
		}

		return false;
	}

	public static boolean isDoc(String name) {
		String suffix = name.substring(name.lastIndexOf(".") + 1);
		String imageSuffixs[] = { "doc", "docx" };

		for (String i : imageSuffixs) {
			if (i.equalsIgnoreCase(suffix)) {
				return true;
			}
		}

		return false;
	}

	public static boolean isXls(String name) {
		String suffix = name.substring(name.lastIndexOf(".") + 1);
		String imageSuffixs[] = { "xls", "xlsx" };

		for (String i : imageSuffixs) {
			if (i.equalsIgnoreCase(suffix)) {
				return true;
			}
		}

		return false;
	}

	public static boolean isPpt(String name) {
		String suffix = name.substring(name.lastIndexOf(".") + 1);
		String imageSuffixs[] = { "ppt", "pptx" };

		for (String i : imageSuffixs) {
			if (i.equalsIgnoreCase(suffix)) {
				return true;
			}
		}

		return false;
	}

	public static boolean isZip(String name) {
		String suffix = name.substring(name.lastIndexOf(".") + 1);
		String imageSuffixs[] = { "rar", "tar", "zip", "gzip", "7z" };

		for (String i : imageSuffixs) {
			if (i.equalsIgnoreCase(suffix)) {
				return true;
			}
		}

		return false;
	}

	public static boolean isTxt(String name) {
		String suffix = name.substring(name.lastIndexOf(".") + 1);
		String imageSuffixs[] = { "txt" };

		for (String i : imageSuffixs) {
			if (i.equalsIgnoreCase(suffix)) {
				return true;
			}
		}

		return false;
	}

	public static boolean isPdf(String name) {
		String suffix = name.substring(name.lastIndexOf(".") + 1);
		String imageSuffixs[] = { "pdf" };

		for (String i : imageSuffixs) {
			if (i.equalsIgnoreCase(suffix)) {
				return true;
			}
		}

		return false;
	}
}
