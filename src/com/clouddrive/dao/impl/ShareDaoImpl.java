package com.clouddrive.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import com.clouddrive.dao.BaseDao;
import com.clouddrive.dao.RSProcessor;
import com.clouddrive.dao.ShareDao;
import com.clouddrive.entity.Share;

public class ShareDaoImpl extends BaseDao implements ShareDao {

	@Override
	public int countShare() {
		String sql = "select count(*) as share_count from share";
		RSProcessor getResultProcessor = new RSProcessor() {
			public Object process(ResultSet rs) throws SQLException {
				int count = 0;
				if (rs != null) {
					if (rs.next()) {
						count = rs.getInt("share_count");
					}
				}
				return new Integer(count);
			}
		};
		return (Integer) this.executeQuery(getResultProcessor, sql);
	}

	@Override
	public int countShareByKey(String key) {
		String sql = "select count(*) as share_count from share where keyword=?";
		Object[] params = { key };

		RSProcessor getResultProcessor = new RSProcessor() {

			public Object process(ResultSet rs) throws SQLException {
				int count = 0;
				if (rs != null) {
					if (rs.next()) {
						count = rs.getInt("share_count");
					}
				}

				return new Integer(count);
			}

		};

		return (Integer) this.executeQuery(getResultProcessor, sql, params);
	}

	@Override
	public Share findShareByKey(String key) {
		String sql = "select * from share where keyword = ?";
		Object[] params = { key };

		RSProcessor getResultProcessor = new RSProcessor() {

			public Object process(ResultSet rs) throws SQLException {
				Share share = null;

				if (rs != null) {
					if (rs.next()) {
						String user = rs.getString("user");
						String uuidName = rs.getString("uuidName");
						String url = rs.getString("url");
						String shareTime = rs.getString("shareTime");
						String key = rs.getString("keyword");
						int downloads = rs.getInt("downloads");
						long size = rs.getLong("size");
						share = new Share(user, uuidName, url, shareTime, key, downloads, size);
					}
				}

				return share;

			}
		};

		return (Share) this.executeQuery(getResultProcessor, sql, params);
	}

	@Override
	public int insert(Share share) {
		String sql = "insert share (user, uuidName, url, shareTime, keyword, downloads, size) values(?,?,?,?,?,?,?)";
		Object[] params = { share.getUser(), share.getUuidName(), share.getUrl(), share.getShareTime(), share.getKey(),
				0, share.getSize() };
		return this.executeUpdate(sql, params);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Vector<Share> findShareByUser(String name) {
		String sql = "select * from share where user = ? order by shareTime desc";
		Object[] params = { name };

		RSProcessor getResultProcessor = new RSProcessor() {

			public Object process(ResultSet rs) throws SQLException {
				Vector<Share> shares = new Vector<Share>();

				while (rs.next()) {

					String user = rs.getString("user");
					String uuidName = rs.getString("uuidName");
					String url = rs.getString("url");
					String shareTime = rs.getString("shareTime");
					String key = rs.getString("keyword");
					int downloads = rs.getInt("downloads");
					long size = rs.getLong("size");
					Share share = new Share(user, uuidName, url, shareTime, key, downloads, size);
					shares.add(share);
				}

				return shares;

			}
		};

		return (Vector<Share>) this.executeQuery(getResultProcessor, sql, params);
	}

	@Override
	public int updateDownloadByKey(String key) {
		String sql = "update share \r\n" + "set downloads = downloads+1\r\n" + "where keyword = ?";
		Object[] params = { key };
		return this.executeUpdate(sql, params);
	}

	@Override
	public int delShareByKey(String key) {
		String sql = "delete from share\r\n" + "where keyword = ?";
		Object[] params = { key };
		return this.executeUpdate(sql, params);
	}

	@Override
	public int delShareByUuidName(String uuidName) {
		String sql = "delete from share\r\n" + "where uuidName = ?";
		Object[] params = { uuidName };
		return this.executeUpdate(sql, params);
	}

	@Override
	public int countShareByUuidName(String uuidName) {
		String sql = "select count(*) as share_count from share where uuidName=?";
		Object[] params = { uuidName };

		RSProcessor getResultProcessor = new RSProcessor() {
			public Object process(ResultSet rs) throws SQLException {
				int count = 0;
				if (rs != null) {
					if (rs.next()) {
						count = rs.getInt("share_count");
					}
				}
				return new Integer(count);
			}
		};
		return (Integer) this.executeQuery(getResultProcessor, sql, params);
	}

	@Override
	public String findKeyByUuidName(String uuidName) {
		String sql = "select keyword from share where uuidName = ?";
		Object[] params = { uuidName };

		RSProcessor getResultProcessor = new RSProcessor() {

			public Object process(ResultSet rs) throws SQLException {
				String key = null;

				if (rs != null) {
					if (rs.next()) {
						key = rs.getString("keyword");
					}
				}
				return key;
			}
		};

		return (String) this.executeQuery(getResultProcessor, sql, params);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Vector<Share> getHotShare() {
		String sql = "select * from share order by downloads desc limit 12";
		Object[] params = {};
		RSProcessor getResultProcessor = new RSProcessor() {
			public Object process(ResultSet rs) throws SQLException {
				Vector<Share> shares = new Vector<Share>();
				while (rs.next()) {

					String user = rs.getString("user");
					String uuidName = rs.getString("uuidName");
					String url = rs.getString("url");
					String shareTime = rs.getString("shareTime");
					String key = rs.getString("keyword");
					int downloads = rs.getInt("downloads");
					long size = rs.getLong("size");
					Share share = new Share(user, uuidName, url, shareTime, key, downloads, size);
					shares.add(share);
				}
				return shares;
			}
		};

		return (Vector<Share>) this.executeQuery(getResultProcessor, sql, params);
	}

	@Override
	public int countDownloads() {
		String sql = "select sum(downloads) as sumDowmloads from share";
		RSProcessor getResultProcessor = new RSProcessor() {
			public Object process(ResultSet rs) throws SQLException {
				int count = 0;
				if (rs != null) {
					if (rs.next()) {
						count = rs.getInt("sumDowmloads");
					}
				}
				return new Integer(count);
			}
		};
		return (Integer) this.executeQuery(getResultProcessor, sql);
	}

}
