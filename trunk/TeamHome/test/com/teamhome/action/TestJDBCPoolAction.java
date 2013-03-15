package com.teamhome.action;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

@Namespace("/test")
public class TestJDBCPoolAction {

	@Action(value = "JDBCPool", results = { @Result(name = "success", type = "dispatcher", location = "/index.jsp") })
	public String JDBCPool() {
		try {
			Context env = (Context) new InitialContext()
					.lookup("java:comp/env");
			DataSource ds = (DataSource) env.lookup("jdbc/mysql");
			Connection con = null;
			if (ds == null) {
				System.out.println("[DbPool]no pool");
			} else {
				try {
					con = ds.getConnection();
					if(con == null) {
						System.out.println("con is null");
					} else {
						Statement stmt = con.createStatement();
						ResultSet rs = stmt.executeQuery("select * from admin");
						while(rs.next()){
							System.out.println(rs.getString("username"));
							System.out.println(rs.getString("password"));
						}
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} catch (NamingException ne) {
			ne.printStackTrace();
		}
		return "success";
	}
}
