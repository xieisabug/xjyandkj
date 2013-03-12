package test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import test.dao.IUserHibernateDao;
import test.dto.User;

@Service("userService")
public class UserServiceImpl implements IUserService {
	@Autowired
	@Qualifier("userHibernateDao")
	public IUserHibernateDao userDao;

	public void save(User user) {
		try {
			userDao.add(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public IUserHibernateDao getUserDao() {
		return userDao;
	}

	public void setUserDao(IUserHibernateDao userDao) {
		this.userDao = userDao;
	}

}
