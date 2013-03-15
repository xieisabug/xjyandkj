package com.teamhome.dto;

/**
 * 属性类
 * 定义一系列从数据库取出来的参数，用name和value的键值对表示
 * @author Administrator
 */
public class Attribute {
	//id
	private int id;
	//参数的名称
	private String name;
	//参数的值
	private String value;
	
	public Attribute(String name, String value) {
		super();
		this.name = name;
		this.value = value;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
