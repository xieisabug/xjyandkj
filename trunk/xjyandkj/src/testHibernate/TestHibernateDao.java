package testHibernate;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import test.dao.IUserHibernateDao;
import test.dto.User;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("E://Workspaces/MyEclipse 8.5/xjyandkj/trunk/xjyandkj/src/beans.xml")
public class TestHibernateDao {
	@Resource(name="userHibernateDao")
	private IUserHibernateDao userHibernateDao;
	
	
	@Test
	public void testAdd() {
		User u = new User(1, "yujianjingjing","kuangjing", "¿ó¾®");
		try {
			userHibernateDao.add(u);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
