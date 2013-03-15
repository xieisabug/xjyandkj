package com.teamhome.dto;

/**
 * 留言类
 * 处理各种留言信息
 * @author Administrator
 */
public class Message {
	//id
	private int id;
	//留言内容
	private String content;
	//联系方式，可填可不填，填写的时候可以有多个，用空格隔开
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
