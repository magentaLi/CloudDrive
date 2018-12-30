package com.clouddrive.dao.impl;

import com.clouddrive.dao.BaseDao;
import com.clouddrive.dao.MessageDao;
import com.clouddrive.entity.CurrentTime;
import com.clouddrive.entity.Message;

public class MessageDaoImpl extends BaseDao implements MessageDao {

	@Override
	public int insert(Message message) {
		String sql = "insert into message (username, message, time) values(?,?,?)";
		Object[] params = { message.getUserName(), message.getMessage(), new CurrentTime().getDateString() };
		return this.executeUpdate(sql, params);
	}
}
