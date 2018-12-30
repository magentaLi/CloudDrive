package com.clouddrive.dao;

import java.util.Vector;

import com.clouddrive.entity.Share;

public interface ShareDao {
	// 查找分享
	public int countShare();

	public int countShareByKey(String key);

	public int countShareByUuidName(String uuidName);

	public Share findShareByKey(String key);

	public Vector<Share> findShareByUser(String name);

	public Vector<Share> getHotShare();

	// 添加分享
	public int insert(Share share);

	// 更新分享
	public int updateDownloadByKey(String key);

	// 删除分享
	public int delShareByKey(String key);

	public int delShareByUuidName(String uuidName);

	public String findKeyByUuidName(String uuidName);

	// 统计下载总数
	public int countDownloads();
}
