package com.clouddrive.dao;

import java.util.Vector;

import com.clouddrive.entity.FileMessage;

public interface FileDao {
	// 统计文件总数
	public int countFiles();

	// 查找文件
	public Vector<FileMessage> findFilesByPathAndUser(String path, String userName);

	public Vector<FileMessage> findFilesByTypeAndUser(String type, String user);

	// 添加文件
	public int insert(FileMessage file);

	// 删除文件
	public int delFileByUuidName(String uuidName);

	// 删除文件夹
	public int delFolder(String path, String fileName);
}
