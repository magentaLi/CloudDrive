package com.clouddrive.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import com.clouddrive.dao.BaseDao;
import com.clouddrive.dao.FileDao;
import com.clouddrive.dao.RSProcessor;
import com.clouddrive.entity.FileMessage;

public class FileDaoImpl extends BaseDao implements FileDao {

	@SuppressWarnings("unchecked")
	@Override
	public Vector<FileMessage> findFilesByPathAndUser(String path, String userName) {
		String sql = "select * from file where path = ? and user = ? order by updateTime desc";
		Object[] params = { path, userName };

		RSProcessor getResultProcessor = new RSProcessor() {

			public Object process(ResultSet rs) throws SQLException {
				Vector<FileMessage> files = new Vector<FileMessage>();

				while (rs.next()) {
					String fileName = rs.getString("fileName");
					String uuidName = rs.getString("uuidName");
					String updateTime = rs.getString("updateTime");
					String type = rs.getString("type");
					String path = rs.getString("path");
					String user = rs.getString("user");
					long size = rs.getLong("size");

					FileMessage fileMessage = new FileMessage(fileName, uuidName, updateTime, type, path, user, size);
					files.add(fileMessage);
				}
				return files;

			}
		};

		return (Vector<FileMessage>) this.executeQuery(getResultProcessor, sql, params);
	}

	@Override
	public int insert(FileMessage file) {
		System.out.println("yes");
		String sql = "insert file (fileName,uuidName,updateTime,type,path,user,size) values(?,?,?,?,?,?,?)";
		Object[] params = { file.getFileName(), file.getUuidName(), file.getUpdateTime(), file.getType(),
				file.getPath(), file.getUser(), file.getSize() };
		return this.executeUpdate(sql, params);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Vector<FileMessage> findFilesByTypeAndUser(String type, String user) {
		String sql = "select * from file where type = ? and user = ? order by updateTime desc";
		Object[] params = { type, user };

		RSProcessor getResultProcessor = new RSProcessor() {

			public Object process(ResultSet rs) throws SQLException {
				Vector<FileMessage> files = new Vector<FileMessage>();

				while (rs.next()) {
					String fileName = rs.getString("fileName");
					String uuidName = rs.getString("uuidName");
					String updateTime = rs.getString("updateTime");
					String type = rs.getString("type");
					String path = rs.getString("path");
					String user = rs.getString("user");
					long size = rs.getLong("size");

					FileMessage fileMessage = new FileMessage(fileName, uuidName, updateTime, type, path, user, size);
					files.add(fileMessage);
				}
				return files;
			}
		};

		return (Vector<FileMessage>) this.executeQuery(getResultProcessor, sql, params);
	}

	@Override
	public int delFileByUuidName(String uuidName) {
		String sql = "delete from file where uuidName = ?";
		Object[] params = { uuidName };
		return this.executeUpdate(sql, params);
	}

	@Override
	public int delFolder(String path, String fileName) {
		String sql = "delete from file where fileName = ? and type = 'folder' and path = ?";
		Object[] params = { fileName, path };
		return this.executeUpdate(sql, params);
	}

	@Override
	public int countFiles() {
		String sql = "select count(*) as file_count from file";

		RSProcessor getResultProcessor = new RSProcessor() {

			public Object process(ResultSet rs) throws SQLException {
				int count = 0;
				if (rs != null) {
					if (rs.next()) {
						count = rs.getInt("file_count");
					}
				}
				return new Integer(count);
			}

		};

		return (Integer) this.executeQuery(getResultProcessor, sql);
	}
}
