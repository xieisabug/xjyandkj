package com.teamhome.dao;

import org.junit.Before;
import org.junit.Test;

import com.teamhome.dto.Admin;

import static org.junit.Assert.*;

public class TestLoginDao {
	
	LoginDao login = null;

	@Before
	public void setUp(){
		login = new LoginDao();
	}
	
	@Test
	public void testLogin(){
		Admin a = login.login("xjy..xjy", "xjy..xjy");
		assertNotNull("用户登陆失败！",a);
	}
	
	@Test
	public void testSQLLogin(){
		Admin a = login.login("(1=1)", "(1=1)");
		assertNull("用户SQL注入成功！",a);
	}
	
	@Test
	public void testNoUsernameLogin(){
		Admin a = login.login("aaaa", "aaaaa");
		assertNull("无效用户登陆成功！",a);
	}
	
	@Test
	public void testNoPasswordLogin(){
		Admin a = login.login("xjy..xjy", "");
		assertNull("无密码登陆成功！",a);
	}
	
}
