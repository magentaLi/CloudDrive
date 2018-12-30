package com.clouddrive.biz;

import com.clouddrive.entity.User;

public interface LoginBiz {
	public User login(String username, String password);
}
