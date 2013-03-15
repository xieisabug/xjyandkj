package com.teamhome.testdb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestDBUtil {
	
	@Before
	public void setUp(){
		
	}
	
	@Test
	public void testGetConnection(){
		Connection con = DBUtil.getConnection();
		assertNotNull(con);
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetStatement(){
		Connection con = DBUtil.getConnection();
		Statement stmt = DBUtil.getStatement(con);
		assertNotNull(stmt);
		DBUtil.close(con,stmt);
	}
	
	@Test
	public void testPrepare(){
		Connection con = DBUtil.getConnection();
		PreparedStatement pre = DBUtil.prepare(con, "select * from Admin where username=?");
		ResultSet rs = null;
		try {
			pre.setString(1, "xjy..xjy");
			rs = pre.executeQuery();
			assertTrue(rs.next());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBUtil.close(con,pre,rs);
	}
}
