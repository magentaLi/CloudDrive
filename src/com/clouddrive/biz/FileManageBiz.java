package com.clouddrive.biz;

public interface FileManageBiz {
	// 删除文件
	public boolean delFile(String url, String path);

	// 新建文件夹
	public boolean mkdirFolder(String savePath, String fileName, String userName);

}
