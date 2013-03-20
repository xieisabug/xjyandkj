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
	 * 添加一篇文章
	 * 
	 * @param a
	 *            传入要添加的文章
	 * @return 返回插入后的id
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
			// util的Date转sql的Date
			// 只需调用sql的 new Date(d.getTime())
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
	 * 更新一篇文章
	 * 
	 * @param id
	 *            传入要更新的文章的id
	 * @param updateA
	 *            要更新的文章的新实体
	 * @return 返回更新成功或者失败
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
	 * 获取某一篇指定的文章
	 * 
	 * @param id
	 *            获取的文章的id
	 * @return 获取到的文章的实体
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
	 * 删除某一篇指定的文章
	 * 
	 * @param id
	 *            删除的文章的id
	 * @return 返回删除成功或失败
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
	 * 获取最新的指定篇数的文章
	 * 
	 * @param num
	 *            指定的篇数
	 * @return 获取到的文章列表
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
	 * 获取指定的从第几篇开始到第几篇结束的文章列表
	 * 
	 * @param from
	 *            从第几篇开始
	 * @param to
	 *            到第几篇结束
	 * @return 获取到的文章的列表
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
	 * 模糊搜索一些文章
	 * 
	 * @param title
	 *            搜索的标题
	 * @param content
	 *            搜索的内容
	 * @param from
	 *            搜索发表日期的开始
	 * @param to
	 *            搜索发表日期的结束
	 * @param authorName
	 *            搜索作者姓名
	 * @param num
	 *            搜索出的篇数
	 * @return 搜索到的文章的列表
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
