package com.clouddrive.biz.impl;

import com.clouddrive.biz.LoginBiz;
import com.clouddrive.dao.impl.UserDaoImpl;
import com.clouddrive.entity.User;

public class LoginBizImpl implements LoginBiz {
	private UserDaoImpl userDao = new UserDaoImpl();

	@Override
	public User login(String userName, String pwd) {
		System.out.println("loginbiz:");
		System.out.println("userName:" + userName);
		User user = userDao.findUserByName(userName);
		if (user != null && user.getPwd().equals(pwd)) {
			System.out.println(user.getPwd());
			System.out.println(pwd);
			return user;
		}
		return null;
	}

}
