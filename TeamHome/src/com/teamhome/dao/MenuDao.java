package com.teamhome.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.teamhome.dto.Menu;
import com.teamhome.testdb.DBUtil;

public class MenuDao {

	/**
	 * ����һ���˵�
	 * @param m ���ӵĲ˵�
	 * @return �������ӵĲ˵���id
	 */
	public int add(Menu m) {
		String addSql = "insert into menu(code,name,link) value(?,?,?)";
		Connection con = DBUtil.getConnection();
		PreparedStatement pre = DBUtil.prepare(con, addSql);
		Connection con2 = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			pre.setString(1, m.getCode());
			pre.setString(2, m.getName());
			pre.setString(3, m.getLink());
			boolean success = pre.execute();
			if (!success) {
				DBUtil.close(con, pre);
				String getIdSql = "select id from menu where code='"
						+ m.getCode() + "' and name='" + m.getName()
						+ "' and link='" + m.getLink() + "' ";
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
	 * ��ȡһ���˵�
	 * @param id ��ȡ�˵���id
	 * @return ���ػ�ȡ���Ĳ˵�ʵ��
	 */
	public Menu get(int id) {
		String sql = "select * from Menu where id = " + id;
		Menu m = null;
		Connection con = DBUtil.getConnection();
		Statement stmt = DBUtil.getStatement(con);
		try {
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				String code = rs.getString("code");
				String name = rs.getString("name");
				String link = rs.getString("link");
				m = new Menu(code, name, link);
				m.setId(id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return m;
	}

	/**
	 * ����һ���˵�
	 * @param id ��Ҫ���µĲ˵���id
	 * @param afterM ���²˵�������
	 * @return ���ظ����Ƿ�ɹ�
	 */
	public boolean update(int id, Menu afterM) {
		String updateSql = "update Menu set code=?,name=?,link=? where id=?";
		Connection con = DBUtil.getConnection();
		PreparedStatement pre = DBUtil.prepare(con, updateSql);
		try {
			pre.setString(1, afterM.getCode());
			pre.setString(2, afterM.getName());
			pre.setString(3, afterM.getLink());
			pre.setInt(4, id);
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
	 * ɾ��һ���˵�
	 * @param id ɾ���Ĳ˵���id
	 * @return ����ɾ���Ƿ�ɹ�
	 */
	public boolean delete(int id) {
		String deleteSql = "delete from Menu where id = ?";
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
	 * �������еĲ˵�
	 * @return ���ؼ��ص��Ĳ˵�list
	 */
	public ArrayList<Menu> load() {
		ArrayList<Menu> list = new ArrayList<Menu>();
		String loadSql = "select * from Menu order by code";
		Connection con = DBUtil.getConnection();
		Statement stmt = DBUtil.getStatement(con);
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery(loadSql);
			while(rs.next()){
				int id = rs.getInt("id");
				String code = rs.getString("code");
				String name = rs.getString("name");
				String link = rs.getString("link");
				Menu m = new Menu(code, name, link);
				m.setId(id);
				list.add(m);
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
