package com.teamhome.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.teamhome.dto.Article;
import com.teamhome.testdb.DBUtil;

public class ArticleDao {

	/**
	 * ���һƪ����
	 * 
	 * @param a
	 *            ����Ҫ��ӵ�����
	 * @return ���ز�����id
	 */
	public int add(Article a) {
		String insertSql = "insert into Article(title,content,date,authorName) value(?,?,?,?)";
		Connection con = DBUtil.getConnection();
		PreparedStatement pre = DBUtil.prepare(con, insertSql);
		Connection con2 = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			pre.setString(1, a.getTitle());
			pre.setString(2, a.getContent());
			// util��Dateתsql��Date
			// ֻ�����sql�� new Date(d.getTime())
			pre.setDate(3, new java.sql.Date(a.getDate().getTime()));
			pre.setString(4, a.getAuthorName());
			boolean success = pre.execute();
			if (!success) {
				DBUtil.close(con, pre);
				String getIdSql = "select id from Article where title='"
						+ a.getTitle() + "' and content='" + a.getContent()
						+ "'";
				con2 = DBUtil.getConnection();
				stmt = DBUtil.getStatement(con2);
				rs = stmt.executeQuery(getIdSql);
				if (rs.next()) {
					return rs.getInt(1);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(con2, stmt, rs);
		}
		return 0;
	}

	/**
	 * ����һƪ����
	 * 
	 * @param id
	 *            ����Ҫ���µ����µ�id
	 * @param updateA
	 *            Ҫ���µ����µ���ʵ��
	 * @return ���ظ��³ɹ�����ʧ��
	 */
	public boolean update(int id, Article updateA) {
		String updateSql = "update Article set title=?,content=? where id=?";
		Connection con = DBUtil.getConnection();
		PreparedStatement pre = DBUtil.prepare(con, updateSql);
		try {
			pre.setString(1, updateA.getTitle());
			pre.setString(2, updateA.getContent());
			pre.setInt(3, id);
			int num = pre.executeUpdate();
			if (num == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(con, pre);
		}
		return false;
	}

	/**
	 * ��ȡĳһƪָ��������
	 * 
	 * @param id
	 *            ��ȡ�����µ�id
	 * @return ��ȡ�������µ�ʵ��
	 */
	public Article get(int id) {
		String sql = "select * from Article where id = " + id;
		Article a = null;
		Connection con = DBUtil.getConnection();
		Statement stmt = DBUtil.getStatement(con);
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				String title = rs.getString("title");
				String content = rs.getString("content");
				Date date = rs.getDate("date");
				String authorName = rs.getString("authorName");
				a = new Article(title, content, date, authorName);
				a.setId(id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(con, stmt, rs);
		}
		return a;
	}

	/**
	 * ɾ��ĳһƪָ��������
	 * 
	 * @param id
	 *            ɾ�������µ�id
	 * @return ����ɾ���ɹ���ʧ��
	 */
	public boolean delete(int id) {
		String deleteSql = "delete from Article where id = ?";
		Connection con = DBUtil.getConnection();
		PreparedStatement pre = DBUtil.prepare(con, deleteSql);
		try {
			pre.setInt(1, id);
			pre.execute();
			boolean success = pre.getUpdateCount()==1?true:false;
			return success;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(con, pre);
		}
		return false;
	}

	/**
	 * ��ȡ���µ�ָ��ƪ��������
	 * 
	 * @param num
	 *            ָ����ƪ��
	 * @return ��ȡ���������б�
	 */
	public ArrayList<Article> load(int num) {
		ArrayList<Article> list = new ArrayList<Article>();
		String loadSql = "select * from Article order by id desc limit ?";
		Connection con = DBUtil.getConnection();
		PreparedStatement pre = DBUtil.prepare(con, loadSql);
		ResultSet rs = null;
		try {
			pre.setInt(1, num);
			rs = pre.executeQuery();
			while(rs.next()){
				int id = rs.getInt("id");
				String title = rs.getString("title");
				String content = rs.getString("content");
				Date date = rs.getDate("date");
				String authorName = rs.getString("authorName");
				Article a = new Article(title, content, date, authorName);
				a.setId(id);
				list.add(a);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(con, pre, rs);
		}
		return null;
	}

	/**
	 * ��ȡָ���Ĵӵڼ�ƪ��ʼ���ڼ�ƪ�����������б�
	 * 
	 * @param from
	 *            �ӵڼ�ƪ��ʼ
	 * @param to
	 *            ���ڼ�ƪ����
	 * @return ��ȡ�������µ��б�
	 */
	public ArrayList<Article> list(int from, int to) {
		ArrayList<Article> list = new ArrayList<Article>();
		int num = to - from + 1;
		String listSql = "select * from Article limit ?,?";
		Connection con = DBUtil.getConnection();
		PreparedStatement pre = DBUtil.prepare(con, listSql);
		ResultSet rs = null;
		try {
			pre.setInt(1, from-1);
			pre.setInt(2, num);
			rs = pre.executeQuery();
			while(rs.next()){
				int id = rs.getInt("id");
				String title = rs.getString("title");
				String content = rs.getString("content");
				Date date = rs.getDate("date");
				String authorName = rs.getString("authorName");
				Article a = new Article(title, content, date, authorName);
				a.setId(id);
				list.add(a);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(con, pre, rs);
		}
		
		return null;
	}

	/**
	 * ģ������һЩ����
	 * 
	 * @param title
	 *            �����ı���
	 * @param content
	 *            ����������
	 * @param from
	 *            �����������ڵĿ�ʼ
	 * @param to
	 *            �����������ڵĽ���
	 * @param authorName
	 *            ������������
	 * @param num
	 *            ��������ƪ��
	 * @return �����������µ��б�
	 */
	public ArrayList<Article> search(String title, String content, Date from,
			Date to, String authorName, int num) {
		ArrayList<Article> list = new ArrayList<Article>();
		String sql = "select * from article ";
		String where = " where 1=1 ";
		if(title != null) {
			where += " and title like '%" + title + "%' ";
		}
		if(content != null) {
			where += " and content like '%" + content + "%' ";
		}
		if(from == null) {
			Calendar c = Calendar.getInstance();
			c.set(1970, 1, 1);
			from = new java.sql.Date(c.getTime().getTime());
		}
		if(to == null) {
			to = new java.sql.Date(new Date().getTime());
		}
		if(from != null) {
			where += " and date >= '"+ from + "' and date <= '" + to + "' ";
		}
		if(authorName != null) {
			authorName += "and authorName like '%" + authorName + "%' ";
		}
		sql += where;
		Connection con = DBUtil.getConnection();
		Statement stmt = DBUtil.getStatement(con);
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				int id = rs.getInt("id");
				String _title = rs.getString("title");
				String _content = rs.getString("content");
				Date _date = rs.getDate("date");
				String _authorName = rs.getString("authorName");
				Article a = new Article(_title, _content, _date, _authorName);
				a.setId(id);
				list.add(a);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(con, stmt, rs);
		}
		return null;
	}

}
