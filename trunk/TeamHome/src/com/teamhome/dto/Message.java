package com.teamhome.dto;

/**
 * ������
 * �������������Ϣ
 * @author Administrator
 */
public class Message {
	//id
	private int id;
	//��������
	private String content;
	//��ϵ��ʽ������ɲ����д��ʱ������ж�����ÿո����
	private String contact;
	
	public Message(String content, String contact) {
		this.content = content;
		this.contact = contact;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	
	
}
