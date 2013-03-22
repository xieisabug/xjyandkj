package com.teamhome.action;

import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.teamhome.dao.LoginDao;
import com.teamhome.dto.Admin;

@Namespace("/login")
public class LoginAction implements ModelDriven<Admin>{
	
	Admin admin = new Admin();
	LoginDao dao = new LoginDao();

	@Action(value = "login", results = { @Result(name = "success", type = "dispatcher", location = "/index.jsp") })
	public String login(){
		Admin loginUser = dao.login(admin.getUsername(), admin.getPassword());
		if(loginUser != null) {
			Map<String,Object> session=ActionContext.getContext().getSession();
			session.put("admin", loginUser);
			return "success";
		}
		return "false";
	}
	
	public Admin getModel() {
		return admin;
	}

}
