package com.teamhome.dto;

/**
 * 管理员类
 * 管理网站的管理员类
 * @author Administrator
 *
 */
public class Admin {

	//id
	private int id;
	//用户名
	private String username;
	//密码
	private String password;
	
	//带username和password的构造函数
	public Admin(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}
