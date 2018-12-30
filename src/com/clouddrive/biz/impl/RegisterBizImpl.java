package com.clouddrive.biz.impl;

import com.clouddrive.biz.RegisterBiz;
import com.clouddrive.dao.impl.UserDaoImpl;
import com.clouddrive.entity.User;

public class RegisterBizImpl implements RegisterBiz {

	private UserDaoImpl userDaoImpl = new UserDaoImpl();

	@Override
	public boolean register(String username, String password) {
		int userCount = userDaoImpl.countUserByName(username);
		if (userCount == 0) {
			User user = new User(username, password);
			if (userDaoImpl.insert(user) != 0) {
				return true;
			}
			return false;
		}
		return false;
	}

}
