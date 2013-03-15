package com.teamhome.testdb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {

	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager
					.getConnection("jdbc:mysql://localhost:3306/teamhome?user=root&password=640310");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	public static PreparedStatement prepare(Connection conn, String sql) {
		PreparedStatement pstmt = null;
		try {
			if (conn != null) {
				pstmt = conn.prepareStatement(sql);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pstmt;
	}

	public static PreparedStatement prepare(Connection conn, String sql,
			int autoGenereatedKeys) {
		PreparedStatement pstmt = null;
		try {
			if (conn != null) {
				pstmt = conn.prepareStatement(sql, autoGenereatedKeys);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pstmt;
	}

	public static Statement getStatement(Connection con) {
		Statement stmt = null;
		try {
			if (con != null) {
				stmt = con.createStatement();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return stmt;
	}

	public static ResultSet getResultSet(Statement stmt, String sql) {
		ResultSet rs = null;
		try {
			if (stmt != null) {
				rs = stmt.executeQuery(sql);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	public static void executeUpdate(Statement stmt, String sql) {
		try {
			if (stmt != null) {
				stmt.executeUpdate(sql);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
