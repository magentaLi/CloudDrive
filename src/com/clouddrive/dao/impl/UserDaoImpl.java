package com.clouddrive.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import com.clouddrive.dao.BaseDao;
import com.clouddrive.dao.RSProcessor;
import com.clouddrive.dao.UserDao;
import com.clouddrive.entity.User;

public class UserDaoImpl extends BaseDao implements UserDao {

	@Override
	public int countUserByName(String name) {
		String sql = "select count(*) as user_count from user where userName=?";
		Object[] params = { name };

		RSProcessor getResultProcessor = new RSProcessor() {

			public Object process(ResultSet rs) throws SQLException {
				int count = 0;
				if (rs != null) {
					if (rs.next()) {
						count = rs.getInt("user_count");
					}
				}
				return new Integer(count);
			}

		};

		return (Integer) this.executeQuery(getResultProcessor, sql, params);
	}

	@Override
	public User findUserByName(String name) {
		String sql = "select * from user where userName = ?";
		Object[] params = { name };

		RSProcessor getResultProcessor = new RSProcessor() {

			public Object process(ResultSet rs) throws SQLException {
				User user = null;

				if (rs != null) {
					if (rs.next()) {
						String userName = rs.getString("userName");
						String pwd = rs.getString("pwd");
						user = new User(userName, pwd);
					}
				}

				return user;

			}
		};

		return (User) this.executeQuery(getResultProcessor, sql, params);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Vector<User> findUsersByName(String name) {
		String sql = "select * from user where userName = ?";
		Object[] params = { name };

		RSProcessor getResultProcessor = new RSProcessor() {

			public Object process(ResultSet rs) throws SQLException {
				Vector<User> users = new Vector<User>();

				while (rs.next()) {
					String userName = rs.getString("userName");
					String pwd = rs.getString("pwd");

					User user = new User(userName, pwd);
					users.add(user);
				}

				return users;

			}
		};

		return (Vector<User>) this.executeQuery(getResultProcessor, sql, params);
	}

	@Override
	public int insert(User user) {
		String sql = "insert user (userName, pwd) values(?,?)";
		Object[] params = { user.getUserName(), user.getPwd() };
		return this.executeUpdate(sql, params);
	}

	@Override
	public int countUser() {
		String sql = "select count(*) as user_count from user";

		RSProcessor getResultProcessor = new RSProcessor() {

			public Object process(ResultSet rs) throws SQLException {
				int count = 0;
				if (rs != null) {
					if (rs.next()) {
						count = rs.getInt("user_count");
					}
				}
				return new Integer(count);
			}

		};

		return (Integer) this.executeQuery(getResultProcessor, sql);
	}

}
