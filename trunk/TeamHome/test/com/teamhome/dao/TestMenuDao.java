package com.teamhome.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.teamhome.dto.Menu;

public class TestMenuDao {

	MenuDao dao = null;
	static int id = 0;
	
	@Before
	public void setUp(){
		dao = new MenuDao();
		System.out.println("\n");
	}

	@Test
	public void testAdd() {
		System.out.println("测试添加菜单");
		Menu m = new Menu("01","测试菜单","#");
		id = dao.add(m);
		assertNotNull("添加菜单失败！",dao.get(id));
		System.out.println("添加菜单成功！添加菜单的id为：" + id);
	}
	
	@Test
	public void testDelete() {
		System.out.println("准备测试删除菜单");
		dao.delete(id);
		assertNull("删除菜单失败，菜单任然存在",dao.get(id));
		System.out.println("删除菜单成功！");
	}
	
	@Test
	public void testUpdate() {
		System.out.println("准备测试更新菜单，预备数据");
		Menu beforeM = new Menu("02", "更新前", "#");
		int i = dao.add(beforeM);
		beforeM.setId(i);
		System.out.println("预备数据完成，预备数据为：");
		System.out.println(beforeM);
		Menu afterM = new Menu("02", "已更新！", "#");
		dao.update(i,afterM);
		assertEquals("更新菜单失败！", afterM.getName(), dao.get(i).getName());
		System.out.println("更新菜单成功，更新数据为：");
		System.out.println(dao.get(i).toString());
		System.out.println("销毁预备数据");
		dao.delete(i);
		System.out.println("预备测试数据销毁完毕！");
	}
	
	@Test
	public void testGet() {
		System.out.println("测试获取菜单");
		assertNotNull(dao.get(1));
		System.out.println("获取菜单成功！数据为：");
		System.out.println(dao.get(1));
	}
	
	@Test
	public void testLoad() {
		System.out.println("测试加载菜单");
		List<Menu> list = dao.load();
		assertNotEquals("菜单加载错误！", list.size(), 0);
		System.out.println("菜单加载成功，数据如下：");
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).toString());
		}
	}
	
}
