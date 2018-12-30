package com.clouddrive.biz;

import com.clouddrive.entity.Share;

public interface ShareManageBiz {
	public String insertShare(String userName, String uuidName, String url, long size);

	public Share getShareByKey(String key);

	public boolean delShareByKey(String key);

	public int delShareByUuidName(String uuidName);
}
