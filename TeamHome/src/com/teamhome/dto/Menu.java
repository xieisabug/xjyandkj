package com.teamhome.dto;

/**
 * 菜单类
 * 用于菜单的读取与显示等
 * @author Administrator
 */
public class Menu {
	//id
	private int id;
	//菜单编码，用于定义菜单显示顺序和上下级等
	private String code;
	//菜单名
	private String name;
	//菜单链接,用|分割，前面是图标链接，后面是跳转链接
	private String link;
	
	public Menu() {
		super();
	}
	
	public Menu(String code, String name, String link) {
		super();
		this.code = code;
		this.name = name;
		this.link = link;
	}
	
	@Override
	public String toString() {
		return "Menu [id=" + id + ", code=" + code + ", name=" + name
				+ ", link=" + link + "]";
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
}
