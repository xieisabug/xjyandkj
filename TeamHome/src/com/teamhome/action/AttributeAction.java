package com.teamhome.action;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.teamhome.dao.AttributeDao;
import com.teamhome.dto.Attribute;

@ParentPackage("json-interceptor")
@Namespace("/attr")
@Results({ @Result(name = "success",type = "json",params = {"root","action"}) })
public class AttributeAction extends ActionSupport implements ModelDriven<Attribute> {

	private static final long serialVersionUID = 8105088880041737773L;

	private Attribute attr = new Attribute();// ����ֵ�Ľ��ն���
	private AttributeDao dao = new AttributeDao();// dao����
	private boolean success;// ���ص�json
	private ArrayList<Attribute> list;// ���ص�json
	private HashMap<String,String> map;


	//��ȡ���в�����תΪmap
	@Action(value = "map")
	public String map(){
		map = dao.map();
		return SUCCESS;
	}
	
	// ��ȡ���в����б�
	@Action(value = "load")
	public String load() {
		list = dao.list();
		if(list.size() > 0){
			success = true;
		} else {
			success = false;
		}
		return "success";
	}

	// ����һ������
	@Action(value = "add")
	public String add() {
		int i = dao.add(attr);
		if(i > 0) {
			success = true;
		} else {
			success = false;
		}
		return "success";
	}

	// ɾ��һ������
	@Action(value = "delete")
	public String delete() {
		success = dao.delete(attr.getId());
		return "success";
	}

	// ����һ������
	@Action(value = "update")
	public String update() {
		success = dao.update(attr.getId(), attr);
		return "success";
	}
	
	//��ȡһ������������
	@Action(value = "get")
	public String get(){
		attr = dao.get(attr.getId());
		return SUCCESS;
	}

	public Attribute getModel() {
		return attr;
	}

	public Attribute getAttr() {
		return attr;
	}

	public boolean isSuccess() {
		return success;
	}

	public ArrayList<Attribute> getList() {
		return list;
	}

	public HashMap<String, String> getMap() {
		return map;
	}

}
