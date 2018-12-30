package com.clouddrive.dao;

import java.util.Vector;

import com.clouddrive.entity.User;

public interface UserDao {

	public int countUser();

	public int countUserByName(String name);

	public User findUserByName(String name);

	public Vector<User> findUsersByName(String name);

	public int insert(User user);
}
