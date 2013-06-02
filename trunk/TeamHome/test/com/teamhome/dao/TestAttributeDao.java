package com.teamhome.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.HashMap;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.teamhome.dto.Attribute;

public class TestAttributeDao {
	AttributeDao dao = null;
	static int id = 0;

	@Before
	public void setUp() throws Exception {
		dao = new AttributeDao();
		System.out.println("\n");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAdd() {
		System.out.println("测试添加参数");
		Attribute a = new Attribute("css","1");
		id = dao.add(a);
		assertNotNull("添加参数失败！",dao.get(id));
		System.out.println("添加参数成功！添加参数的id为：" + id);
	}
	
	@Test
	public void testUpdate(){
		System.out.println("准备测试更新参数，预备数据");
		Attribute beforeA = new Attribute("更新前","123123");
		int i = dao.add(beforeA);
		beforeA.setId(i);
		System.out.println("预备数据完成，预备数据为：");
		System.out.println(beforeA);
		Attribute afterA = new Attribute("已更新！", "#");
		dao.update(i,afterA);
		assertEquals("更新参数失败！", afterA.getName(), dao.get(i).getName());
		System.out.println("更新参数成功，更新数据为：");
		System.out.println(dao.get(i).toString());
		System.out.println("销毁预备数据");
		dao.delete(i);
		System.out.println("预备测试数据销毁完毕！");
	}
	
	@Test
	public void testDelete(){
		System.out.println("准备测试删除参数");
		dao.delete(id);
		assertNull("删除参数失败，参数任然存在",dao.get(id));
		System.out.println("删除参数成功！");
	}
	
	@Test
	public void testGet(){
		System.out.println("测试获取参数");
		assertNotNull(dao.get(1));
		System.out.println("获取参数成功！数据为：");
		System.out.println(dao.get(1));
	}
	
	@Test
	public void testList(){
		System.out.println("读取所有记录");
		List<Attribute> list = dao.list();
		assertEquals("获取参数列表错误！", list.size(), 3);
		System.out.println("读取参数成功,读取的参数分别是：");
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).toString());
		}
	}
	
	@Test
	public void testMap(){
		System.out.println("获取所有记录，并记录到Map中");
		HashMap<String, String> map = dao.map();
		assertNotEquals(map.size(), 0);
		System.out.println("读取记录成功，参数为：");
		System.out.println(map.toString());
	}
	
}
