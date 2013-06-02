package com.teamhome.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.teamhome.dao.MessageDao;
import com.teamhome.dto.Message;

@ParentPackage("json-interceptor")
@Namespace("/message")
@Results({ @Result(name = "success",type = "json",params = {"root","action"}) })
public class MessageAction extends ActionSupport implements ModelDriven<Message> {

	private static final long serialVersionUID = -6939025987256177071L;
	
	private MessageDao dao = new MessageDao();
	private Message message = new Message();
	int from = 1;
	int to = 10;
	boolean success;
	private List<Message> messages;

	//获取留言列表
	@Action(value="list")
	public String list() {
		messages = dao.list(from, to);
		if(messages.size() == 0) {
			success = false;
		} else {
			success = true;
		}
		return SUCCESS;
	}
	
	//增加一条留言
	@Action(value="add")
	public String add(){
		int id = dao.add(message);
		message.setId(id);
		if(id>0){
			success = true;
		} else {
			success = false;
		}
		return SUCCESS;
	}
	
	//修改一条留言
	@Action(value="update")
	public String update(){
		success = dao.update(message.getId(), message);
		return SUCCESS;
	}
	
	//删除一条留言
	@Action(value="delete")
	public String delete(){
		success = dao.delete(message.getId());
		return SUCCESS;
	}
	
	//获取一条留言
	@Action(value="get")
	public String message(){
		message = dao.get(message.getId());
		return SUCCESS;
	}
	
	//将一条留言标记为星标留言
	@Action(value="starMessage")
	public String addStarMessage(){
		int id = dao.starMessage(message.getId());
		if(id>0){
			success = true;
		} else {
			success = false;
		}
		return SUCCESS;
	}
	
	//删除一条星标留言(不影响原有的留言)
	@Action(value="deleteStarMessage")
	public String deleteStarMessage(){
		success = dao.deleteStarMessage(message.getId());
		return SUCCESS;
	}
	
	//获取星标留言的列表
	@Action(value="listStarMessage")
	public String listStarMessage(){
		messages = dao.listStarMessage(from, to);
		return SUCCESS;
	}
	
	//获取一条星标留言
	@Action(value="getStarMessage")
	public String starMessage(){
		message = dao.getStarMessage(message.getId());
		return SUCCESS;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public boolean isSuccess() {
		return success;
	}

	public List<Message> getMessages() {
		return messages;
	}

	public Message getModel() {
		return message;
	}

	public void setFrom(int from) {
		this.from = from;
	}

	public void setTo(int to) {
		this.to = to;
	}
}
