package com.teamhome.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.teamhome.dto.Message;
import com.teamhome.testdb.DBUtil;

public class MessageDao {

	public int add(Message m) {
		String insertSql = "insert into Message(content,contact) value(?,?)";
		Connection con = DBUtil.getConnection();
		PreparedStatement pre = DBUtil.prepare(con, insertSql);
		Connection con2 = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			pre.setString(1, m.getContent());
			pre.setString(2, m.getContact());
			// util的Date转sql的Date
			// 只需调用sql的 new Date(d.getTime())
			boolean success = pre.execute();
			if (!success) {
				DBUtil.close(con, pre);
				String getIdSql = "select id from Message where content='"
						+ m.getContent() + "' and contact='" + m.getContact()
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

	public Message get(int id) {
		String sql = "select * from Message where id = " + id;
		Message m = null;
		Connection con = DBUtil.getConnection();
		Statement stmt = DBUtil.getStatement(con);
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				String content = rs.getString("content");
				String contact = rs.getString("contact");
				m = new Message(content,contact);
				m.setId(id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(con, stmt, rs);
		}
		return m;
	}

	public boolean update(int id, Message afterM) {
		String updateSql = "update Message set content=?,contact=? where id=?";
		Connection con = DBUtil.getConnection();
		PreparedStatement pre = DBUtil.prepare(con, updateSql);
		try {
			pre.setString(1, afterM.getContent());
			pre.setString(2, afterM.getContact());
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

	public boolean delete(int id) {
		String deleteSql = "delete from Message where id = ?";
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

	public List<Message> list(int from, int to) {
		ArrayList<Message> list = new ArrayList<Message>();
		int num = to - from + 1;
		String listSql = "select * from Message limit ?,?";
		Connection con = DBUtil.getConnection();
		PreparedStatement pre = DBUtil.prepare(con, listSql);
		ResultSet rs = null;
		try {
			pre.setInt(1, from-1);
			pre.setInt(2, num);
			rs = pre.executeQuery();
			while(rs.next()){
				int id = rs.getInt("id");
				String content = rs.getString("content");
				String contact = rs.getString("contact");
				Message m = new Message(content, contact);
				m.setId(id);
				list.add(m);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(con, pre, rs);
		}
		return null;
	}
	
}
