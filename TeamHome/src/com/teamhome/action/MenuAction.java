package com.teamhome.action;

import java.util.ArrayList;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.teamhome.dao.MenuDao;
import com.teamhome.dto.Menu;

@ParentPackage("json-interceptor")
@Namespace("/menu")
@Results({ @Result(name = "success",type = "json",params = {"root","action"}) })
public class MenuAction extends ActionSupport implements ModelDriven<Menu> {

	private static final long serialVersionUID = 8105088880041737773L;

	private Menu menu = new Menu();// ����ֵ�Ľ��ն���
	private MenuDao dao = new MenuDao();// dao����
	private boolean success;// ���ص�json
	private ArrayList<Menu> list;// ���ص�json

	// ��ȡ���в˵��б�
	@Action(value = "load")
	public String load() {
		list = dao.load();
		if(list.size() > 0){
			success = true;
		} else {
			success = false;
		}
		return "success";
	}

	// ����һ���˵�
	@Action(value = "add")
	public String add() {
		int i = dao.add(menu);
		if(i > 0) {
			success = true;
		} else {
			success = false;
		}
		return "success";
	}

	// ɾ��һ���˵�
	@Action(value = "delete")
	public String delete() {
		success = dao.delete(menu.getId());
		return "success";
	}

	// ����һ���˵�
	@Action(value = "update")
	public String update() {
		success = dao.update(menu.getId(), menu);
		return "success";
	}
	
	//��ȡһ���˵�������
	@Action(value = "get ")
	public String get(){
		menu = dao.get(menu.getId());
		return SUCCESS;
	}

	public ArrayList<Menu> getList() {
		return list;
	}

	public boolean isSuccess() {
		return success;
	}
	
	public Menu getModel() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

}
