package com.teamhome.dto;

/**
 * ������
 * ����һϵ�д����ݿ�ȡ�����Ĳ�������name��value�ļ�ֵ�Ա�ʾ
 * @author Administrator
 */
public class Attribute {
	//id
	private int id;
	//����������
	private String name;
	//������ֵ
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
