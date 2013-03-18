package com.teamhome.dto;

import java.util.Date;

/**
 * 文章类
 * 用于发表文章的类
 * @author Administrator
 */
public class Article {
	
	//id
	private int id;
	//标题
	private String title;
	//内容
	private String content;
	//发布时间
	private Date date;
	//发布作者
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
