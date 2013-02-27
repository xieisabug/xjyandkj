package test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import test.dao.IUserDao;
import test.dto.User;
@Service("userService")
public class UserServiceImpl implements IUserService {
	@Autowired
	@Qualifier("userDao")
	public IUserDao userDao;
	public void save(User user) {
		userDao.save(user);
	}

}
