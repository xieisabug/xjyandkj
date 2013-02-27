package test.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import test.dto.User;
import test.service.IUserService;

import com.opensymphony.xwork2.ActionSupport;

@Controller("loginAction")
@Scope("prototype")
@Namespace("/action")
public class LoginAction extends ActionSupport{    
	
	
   
	@Autowired
	@Qualifier("userService")
	public IUserService userService;
	/**
	 * 
	 */
	private static final long serialVersionUID = 3784420166877017365L;

	
	
	@Action(value = "login", results = { @Result(name = "success", type = "dispatcher",location="/login.jsp") })
	public String execute() throws Exception {
		
		User user = new User();
		user.setName("kuangjing");
		user.setPassword("231234");
		user.setUsename("yuanjian");
		userService.save(user);
		
        return SUCCESS;
    }
    
}
