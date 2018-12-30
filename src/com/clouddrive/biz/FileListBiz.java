package com.clouddrive.biz;

import java.util.LinkedHashMap;
import java.util.Vector;

import com.clouddrive.entity.FileMessage;

public interface FileListBiz {
	public Vector<FileMessage> getFilesByTypeAndUser(String userName, String type);

	public Vector<FileMessage> getFilesByPathAndUser(String path, String userName);

	public String getLastPath(String path);

	public LinkedHashMap<String, String> getPaths(String path);
}
