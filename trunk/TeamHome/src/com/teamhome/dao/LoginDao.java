package com.teamhome.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.teamhome.dto.Admin;
import com.teamhome.testdb.DBUtil;

public class LoginDao {
	
	public Admin login(String username,String password){
		if(username.equals("") || username == null){
			return null;
		}
		if(password.equals("") || password == null){
			return null;
		}
		Connection con = DBUtil.getConnection();
		PreparedStatement pre = DBUtil.prepare(con,"select * from Admin where username=?");
		ResultSet rs = null;
		try {
			pre.setString(1, username);
			rs = pre.executeQuery();
			if(rs.next()){
				if(password.equals(rs.getString("password"))){
					return new Admin(username,password);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
