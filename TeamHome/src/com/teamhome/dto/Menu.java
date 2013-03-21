package com.teamhome.dto;

/**
 * �˵���
 * ���ڲ˵��Ķ�ȡ����ʾ��
 * @author Administrator
 */
public class Menu {
	//id
	private int id;
	//�˵����룬���ڶ���˵���ʾ˳������¼���
	private String code;
	//�˵���
	private String name;
	//�˵�����,��|�ָǰ����ͼ�����ӣ���������ת����
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
