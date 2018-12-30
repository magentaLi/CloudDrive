package com.clouddrive.biz.impl;

import java.util.ArrayList;

import com.clouddrive.biz.CountDataBiz;
import com.clouddrive.dao.FileDao;
import com.clouddrive.dao.ShareDao;
import com.clouddrive.dao.UserDao;
import com.clouddrive.dao.impl.FileDaoImpl;
import com.clouddrive.dao.impl.ShareDaoImpl;
import com.clouddrive.dao.impl.UserDaoImpl;

public class CountDataBizImpl implements CountDataBiz {

	private UserDao userDao;
	private ShareDao shareDao;
	private FileDao fileDao;

	@Override
	public ArrayList<Integer> echarsData() {
		ArrayList<Integer> list = new ArrayList<>();
		userDao = new UserDaoImpl();
		shareDao = new ShareDaoImpl();
		fileDao = new FileDaoImpl();
		int countUsers = userDao.countUser();
		list.add(countUsers);
		int countFiles = fileDao.countFiles();
		list.add(countFiles);
		int countShares = shareDao.countShare();
		list.add(countShares);
		int countDownloads = shareDao.countDownloads();
		list.add(countDownloads);
		return list;
	}

}
