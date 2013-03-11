package com.teamhome.action;

import java.util.Date;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

@Namespace("/test")
public class TestStrutsAction {

	@Action(value = "struts", results = { @Result(name = "success", type = "dispatcher", location = "/index.jsp") })
	public String strutsTest() {
		System.out.println(new Date()+"------>" + "strutsÅäÖÃ³É¹¦");
		return "success";
	}

}
