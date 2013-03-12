package test.dao;

import java.util.List;

import test.dto.User;

public interface IUserHibernateDao {

	public void add(User user) throws Exception;
	public boolean delete(int userId) throws Exception;
	public boolean update(User user,int userId) throws Exception;
	public List<User> queryUsers(String userName) throws Exception;
	public User query(int userId) throws Exception;
}
