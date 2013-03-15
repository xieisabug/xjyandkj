package com.teamhome.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBUtil {

	// 获取Connection
	public static Connection getConnection() {
		Connection con = null;
		try {
			Context env = (Context) new InitialContext()
					.lookup("java:comp/env");
			DataSource ds = (DataSource) env.lookup("jdbc/mysql");
			if (ds == null) {
				throw new Exception("连接池获取出错！");
			} else {
				try {
					con = ds.getConnection();
					if (con == null) {
						throw new Exception("获取Connection出错！");
					} else {
						return con;
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// 通过Connection获取Statement
	public static Statement getStatement(Connection con) {
		Statement stmt = null;
		try {
			if (con != null) {
				stmt = con.createStatement();
				if (stmt == null) {
					throw new Exception("获取Statement出错！");
				} else {
					return stmt;
				}
			} else {
				throw new Exception("传入的Connection为null");
			}
		} catch (Exception e) {
			e.printStackTrace();
			DBUtil.close(con, stmt);
		}
		return null;
	}

	// 获取一个PreparedStatement
	public static PreparedStatement prepare(Connection con, String sql) {
		PreparedStatement pstmt = null;
		try {
			if (con == null) {
				throw new Exception("传入的Connection为空！");
			} else {
				pstmt = con.prepareStatement(sql);
				if (pstmt == null) {
					throw new Exception("获取PreparedStatement出错！");
				} else {
					return pstmt;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// 关闭Connection和Statement
	public static void close(Connection con, Statement stmt) {
		try {
			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stmt != null) {
				stmt = null;
			}
			if (con != null) {
				con = null;
			}
		}
	}

	// 关闭Connection和PreparedStatement
	public static void close(Connection con, PreparedStatement pre) {
		try {
			pre.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pre != null) {
				pre = null;
			}
			if (con != null) {
				con = null;
			}
		}
	}

	// 关闭Connection、Statement和ResultSet
	public static void close(Connection con, Statement stmt, ResultSet rs) {
		try {
			rs.close();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				rs = null;
			}
			if (stmt != null) {
				stmt = null;
			}
			if (con != null) {
				con = null;
			}
		}
	}

	// 关闭Connection、PreparedStatement和ResultSet
	public static void close(Connection con, PreparedStatement pre, ResultSet rs) {
		try {
			rs.close();
			pre.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				rs = null;
			}
			if (pre != null) {
				pre = null;
			}
			if (con != null) {
				con = null;
			}
		}
	}
}
