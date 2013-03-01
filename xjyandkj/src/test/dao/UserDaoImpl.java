package test.dao;

import javax.annotation.Resources;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import test.dto.User;


@Repository("userDao")
public class UserDaoImpl implements IUserDao {

	public void save(User user) {
	System.out.println(user.getName()+user.getUsename());
	}
	
	

}
