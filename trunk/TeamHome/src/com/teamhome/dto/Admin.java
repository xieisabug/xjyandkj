package com.teamhome.dto;

/**
 * ����Ա��
 * ������վ�Ĺ���Ա��
 * @author Administrator
 *
 */
public class Admin {

	//id
	private int id;
	//�û���
	private String username;
	//����
	private String password;
	
	//��username��password�Ĺ��캯��
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
