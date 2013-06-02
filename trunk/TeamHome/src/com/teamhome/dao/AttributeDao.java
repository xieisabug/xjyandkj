package com.teamhome.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import com.teamhome.dto.Attribute;
import com.teamhome.testdb.DBUtil;

public class AttributeDao {

	public int add(Attribute a) {
		String insertSql = "insert into Attribute(name,value) value(?,?)";
		Connection con = DBUtil.getConnection();
		PreparedStatement pre = DBUtil.prepare(con, insertSql);
		Connection con2 = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			pre.setString(1, a.getName());
			pre.setString(2, a.getValue());
			// util的Date转sql的Date
			// 只需调用sql的 new Date(d.getTime())
			boolean success = pre.execute();
			if (!success) {
				DBUtil.close(con, pre);
				String getIdSql = "select id from Attribute where name='"
						+ a.getName() + "' and value='" + a.getValue()
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

	public Attribute get(int id) {
		String sql = "select * from Attribute where id = " + id;
		Attribute a = null;
		Connection con = DBUtil.getConnection();
		Statement stmt = DBUtil.getStatement(con);
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				String name = rs.getString("name");
				String value = rs.getString("value");
				a = new Attribute(name,value);
				a.setId(id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(con, stmt, rs);
		}
		return a;
	}

	public boolean update(int id, Attribute afterA) {
		String updateSql = "update Attribute set name=?,value=? where id=?";
		Connection con = DBUtil.getConnection();
		PreparedStatement pre = DBUtil.prepare(con, updateSql);
		try {
			pre.setString(1, afterA.getName());
			pre.setString(2, afterA.getValue());
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
		String deleteSql = "delete from Attribute where id = ?";
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

	public ArrayList<Attribute> list() {
		ArrayList<Attribute> list = new ArrayList<Attribute>();
		String listSql = "select * from Attribute";
		Connection con = DBUtil.getConnection();
		Statement stmt = DBUtil.getStatement(con);
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery(listSql);
			while(rs.next()){
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String value = rs.getString("value");
				Attribute a = new Attribute(name, value);
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
	
	public HashMap<String,String> map(){
		HashMap<String,String> map = new HashMap<String, String>();
		String listSql = "select * from Attribute";
		Connection con = DBUtil.getConnection();
		Statement stmt = DBUtil.getStatement(con);
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery(listSql);
			while(rs.next()){
				String name = rs.getString("name");
				String value = rs.getString("value");
				map.put(name, value);
			}
			return map;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(con, stmt, rs);
		}
		return map;
	}
	
}
