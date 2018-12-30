package com.clouddrive.biz.impl;

import com.clouddrive.biz.MessageBiz;
import com.clouddrive.dao.MessageDao;
import com.clouddrive.dao.impl.MessageDaoImpl;
import com.clouddrive.entity.Message;

public class MessageBizImpl implements MessageBiz {

	private MessageDao messageDao;

	@Override
	public boolean leaveMessage(Message message) {
		System.out.println("messageBiz:");
		messageDao = new MessageDaoImpl();
		int res = messageDao.insert(message);
		if (res != 0) {
			return true;
		} else {
			return false;
		}
	}

}
