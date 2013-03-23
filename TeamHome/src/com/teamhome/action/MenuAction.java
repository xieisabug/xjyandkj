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

	private Menu menu = new Menu();// 表单传值的接收对象
	private MenuDao dao = new MenuDao();// dao对象
	private boolean success;// 返回的json
	private ArrayList<Menu> list;// 返回的json

	// 获取所有菜单列表
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

	// 增加一个菜单
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

	// 删除一个菜单
	@Action(value = "delete")
	public String delete() {
		success = dao.delete(menu.getId());
		return "success";
	}

	// 更新一个菜单
	@Action(value = "update")
	public String update() {
		success = dao.update(menu.getId(), menu);
		return "success";
	}
	
	//获取一个菜单的属性
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
