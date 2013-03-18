package com.teamhome.dto;

import java.util.Date;

/**
 * ������
 * ���ڷ������µ���
 * @author Administrator
 */
public class Article {
	
	//id
	private int id;
	//����
	private String title;
	//����
	private String content;
	//����ʱ��
	private Date date;
	//��������
	private String authorName;
	
	public Article(String title, String content, Date date, String authorName) {
		super();
		this.title = title;
		this.content = content;
		this.date = date;
		this.authorName = authorName;
	}
	
	@Override
	public String toString() {
		return "Article [id=" + id + ", title=" + title + ", content="
				+ content + ", date=" + date + ", authorName=" + authorName
				+ "]";
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	
	
}
