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

	// ��ȡConnection
	public static Connection getConnection() {
		Connection con = null;
		try {
			Context env = (Context) new InitialContext()
					.lookup("java:comp/env");
			DataSource ds = (DataSource) env.lookup("jdbc/mysql");
			if (ds == null) {
				throw new Exception("���ӳػ�ȡ����");
			} else {
				try {
					con = ds.getConnection();
					if (con == null) {
						throw new Exception("��ȡConnection����");
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

	// ͨ��Connection��ȡStatement
	public static Statement getStatement(Connection con) {
		Statement stmt = null;
		try {
			if (con != null) {
				stmt = con.createStatement();
				if (stmt == null) {
					throw new Exception("��ȡStatement����");
				} else {
					return stmt;
				}
			} else {
				throw new Exception("�����ConnectionΪnull");
			}
		} catch (Exception e) {
			e.printStackTrace();
			DBUtil.close(con, stmt);
		}
		return null;
	}

	// ��ȡһ��PreparedStatement
	public static PreparedStatement prepare(Connection con, String sql) {
		PreparedStatement pstmt = null;
		try {
			if (con == null) {
				throw new Exception("�����ConnectionΪ�գ�");
			} else {
				pstmt = con.prepareStatement(sql);
				if (pstmt == null) {
					throw new Exception("��ȡPreparedStatement����");
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

	// �ر�Connection��Statement
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

	// �ر�Connection��PreparedStatement
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

	// �ر�Connection��Statement��ResultSet
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

	// �ر�Connection��PreparedStatement��ResultSet
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
