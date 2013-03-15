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
		assertNotNull("ÓÃ»§µÇÂ½Ê§°Ü£¡",a);
	}
	
	@Test
	public void testLogout(){
		fail();
	}
	
	@Test
	public void testSQLLogin(){
		fail();
	}
	
	@Test
	public void testNoUsernameLogin(){
		fail();
	}
	
	@Test
	public void testNoPasswordLogin(){
		fail();
	}
	
	@Test
	public void testNotLogin(){
		fail();
	}
}
