package com.clouddrive.biz.impl;

import java.util.LinkedHashMap;
import java.util.Vector;

import com.clouddrive.biz.FileListBiz;
import com.clouddrive.dao.impl.FileDaoImpl;
import com.clouddrive.entity.FileMessage;

public class FileListBizImpl implements FileListBiz {

	@Override
	public Vector<FileMessage> getFilesByTypeAndUser(String userName, String type) {
		String types[] = { "image", "document", "video", "music", "other" };

		for (String t : types) {
			if (type.equals(t)) {
				// 存储要下载的文件名
				FileDaoImpl fileDaoImpl = new FileDaoImpl();
				Vector<FileMessage> files;
				if (t.equals("document")) {
					files = fileDaoImpl.findFilesByTypeAndUser("doc", userName);
					for (FileMessage file : fileDaoImpl.findFilesByTypeAndUser("xls", userName)) {
						files.add(file);
					}
					for (FileMessage file : fileDaoImpl.findFilesByTypeAndUser("ppt", userName)) {
						files.add(file);
					}
					for (FileMessage file : fileDaoImpl.findFilesByTypeAndUser("pdf", userName)) {
						files.add(file);
					}
					for (FileMessage file : fileDaoImpl.findFilesByTypeAndUser("txt", userName)) {
						files.add(file);
					}
				} else {
					files = fileDaoImpl.findFilesByTypeAndUser(type, userName);
				}
				return files;
			}
		}
		return null;
	}

	@Override
	public Vector<FileMessage> getFilesByPathAndUser(String path, String userName) {
		FileDaoImpl fileDaoImpl = new FileDaoImpl();
		Vector<FileMessage> files = fileDaoImpl.findFilesByPathAndUser(path, userName);
		return files;
	}

	@Override
	public String getLastPath(String path) {
		String pathNames[] = path.split("/");
		int len = pathNames.length;
		String lastPath = "";
		for (int i = 0; i < len - 1; i++) {
			if (i == 0) {
				lastPath = pathNames[i];
			} else {
				lastPath = lastPath + "/" + pathNames[i];
			}
		}
		return lastPath;
	}

	@Override
	public LinkedHashMap<String, String> getPaths(String path) {
		LinkedHashMap<String, String> paths = new LinkedHashMap<>();
		String pathNames[] = path.split("/");
		String curPath = "";
		for (String name : pathNames) {
			if (curPath == "") {
				curPath = name;
				paths.put(name, curPath);
			} else {
				curPath = curPath + "/" + name;
				paths.put(name, curPath);
			}
		}
		return paths;
	}
}
