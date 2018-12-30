package com.clouddrive.biz.impl;

import com.clouddrive.biz.ShareManageBiz;
import com.clouddrive.dao.impl.ShareDaoImpl;
import com.clouddrive.entity.CreateKey;
import com.clouddrive.entity.CurrentTime;
import com.clouddrive.entity.Share;

public class ShareManageBizImpl implements ShareManageBiz {

	ShareDaoImpl shareDaoImpl = new ShareDaoImpl();

	@Override
	public String insertShare(String userName, String uuidName, String url, long size) {
		String currentTime = new CurrentTime().getDateString();
		String key = shareDaoImpl.findKeyByUuidName(uuidName);
		if (key == null) {
			key = new CreateKey().createPassWord(8);
			Share share = new Share(userName, uuidName, url, currentTime, key, 0, size);

			ShareDaoImpl shareImpl = new ShareDaoImpl();
			shareImpl.insert(share);
		} else {
			System.out.println("ÒÑ´æÔÚ");
		}
		return key;
	}

	@Override
	public Share getShareByKey(String key) {
		Share share = shareDaoImpl.findShareByKey(key);
		return share;
	}

	@Override
	public boolean delShareByKey(String key) {
		if (shareDaoImpl.delShareByKey(key) != 0) {
			return true;
		}
		return false;
	}

	@Override
	public int delShareByUuidName(String uuidName) {
		return shareDaoImpl.delShareByUuidName(uuidName);
	}

}
