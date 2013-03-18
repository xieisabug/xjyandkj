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
		assertNotNull("�û���½ʧ�ܣ�",a);
	}
	
	@Test
	public void testSQLLogin(){
		Admin a = login.login("(1=1)", "(1=1)");
		assertNull("�û�SQLע��ɹ���",a);
	}
	
	@Test
	public void testNoUsernameLogin(){
		Admin a = login.login("aaaa", "aaaaa");
		assertNull("��Ч�û���½�ɹ���",a);
	}
	
	@Test
	public void testNoPasswordLogin(){
		Admin a = login.login("xjy..xjy", "");
		assertNull("�������½�ɹ���",a);
	}
	
}
