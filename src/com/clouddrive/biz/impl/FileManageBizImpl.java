package com.clouddrive.biz.impl;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.clouddrive.biz.FileManageBiz;
import com.clouddrive.dao.impl.FileDaoImpl;
import com.clouddrive.entity.FileMessage;

public class FileManageBizImpl implements FileManageBiz {

	FileDaoImpl fileDaoImpl = new FileDaoImpl();
	ShareManageBizImpl shareManage = new ShareManageBizImpl();

	@Override
	public boolean delFile(String url, String path) {
		File file = new File(url);
		if (!file.exists()) {
			// 文件不存在
			return false;
		}
		if (file.isDirectory()) {
			// 如果是文件夹
			System.out.println("删除文件夹");
			boolean d = deleteDir(file, path);
			if (!d) {
				System.out.println("删除失败！");
				return false;
			}
		} else {
			System.out.println("删除文件");
			return deleteFile(file);
		}
		return false;
	}

	// 递归删除文件夹
	private boolean deleteDir(File dir, String path) {
		if (dir.isDirectory()) {
			String[] children = dir.list();
			// 递归删除目录中的子目录下
			for (int i = 0; i < children.length; i++) {
				boolean success = deleteDir(new File(dir, children[i]), path + "/" + dir.getName());
				if (!success) {
					return false;
				}
			}
			// 目录此时为空，可以删除
			String url = dir.getPath();
			System.out.println(url);
			String fileName = dir.getName();
			System.out.println("fileName:" + fileName);
			System.out.println("path:" + path);
			fileDaoImpl.delFolder(path, fileName);
			return dir.delete();
		}
		// 删除文件
		return deleteFile(dir);
	}

	// 删除文件
	private boolean deleteFile(File file) {
		String uuidName = file.getName();
		fileDaoImpl.delFileByUuidName(uuidName);
		shareManage.delShareByUuidName(uuidName);
		return file.delete();
	}

	@Override
	public boolean mkdirFolder(String savePath, String fileName, String userName) {
		File folder = new File(savePath);
		if (!folder.exists() || !folder.isDirectory()) {
			System.out.println(savePath + "目录不存在，需要创建");
			folder.mkdirs();
		}
		File newFolder = new File(savePath + "/" + fileName);
		newFolder.mkdirs();
		String type = "folder";
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String updateTime = format.format(date);
		System.out.println("fileName:" + fileName);
		FileMessage fileMessage = new FileMessage(fileName, updateTime, type, savePath, userName);

		FileDaoImpl fileDaoImpl = new FileDaoImpl();
		if (fileDaoImpl.insert(fileMessage) != 0) {
			return true;
		}
		return false;
	}

}
