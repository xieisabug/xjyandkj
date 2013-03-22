package com.teamhome.action;

import java.util.ArrayList;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import com.opensymphony.xwork2.ModelDriven;
import com.teamhome.dao.MenuDao;
import com.teamhome.dto.Menu;

@ParentPackage("json-default")
@Namespace("/menu")
public class MenuAction implements ModelDriven<Menu> {

	Menu menu = new Menu();//����ֵ�Ľ��ն���
	MenuDao dao = new MenuDao();//dao����
	boolean success = false;//���ص�json
	ArrayList<Menu> list = new ArrayList<Menu>();//���ص�json

	//��ȡ���в˵��б�
	@Action(value = "load", results = { @Result(name = "success", type = "json") })
	public String load() {
		list = dao.load();
		return "success";
	}
	
	//����һ���˵�
	@Action(value = "add", results = { @Result(name = "success", type = "json") })
	public String add(){
		dao.add(menu);
		return "success";
	}
	
	//ɾ��һ���˵�
	@Action(value = "delete", results = { @Result(name = "success", type = "json") })
	public String delete(){
		success = dao.delete(menu.getId());
		return "success";
	}
	
	//����һ���˵�
	@Action(value = "update", results = { @Result(name = "success", type = "json") })
	public String update(){
		success = dao.update(menu.getId(), menu);
		return "success";
	}
	
	public ArrayList<Menu> getList() {
		return list;
	}

	public void setList(ArrayList<Menu> list) {
		this.list = list;
	}
	
	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public Menu getModel() {
		return menu;
	}

}
