package com.teamhome.dao;

import com.teamhome.dto.Admin;

public class LoginDao {
	
	public Admin login(String username,String password){
		if(username.equals("xjy..xjy") && password.equals("5842kj1314")){
			return new Admin(username,password);
		} else if(username.equals("kj..kj") && password.equals("kuangjing640310")){
			return new Admin(username,password);
		}
		
		return null;
	}

}
