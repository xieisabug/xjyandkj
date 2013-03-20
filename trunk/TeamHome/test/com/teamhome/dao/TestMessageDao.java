package com.teamhome.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.teamhome.dto.Message;

public class TestMessageDao {
	MessageDao dao = null;
	static int id = 0;

	@Before
	public void setUp() throws Exception {
		dao = new MessageDao();
		System.out.println("\n");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAdd() {
		System.out.println("测试添加留言");
		Message m = new Message("添加留言","");
		id = dao.add(m);
		assertNotNull("添加留言失败！",dao.get(id));
		System.out.println("添加留言成功！添加留言的id为：" + id);
	}
	
	@Test
	public void testUpdate(){
		System.out.println("准备测试更新留言，预备数据");
		Message beforeM = new Message("更新前","123123");
		int i = dao.add(beforeM);
		beforeM.setId(i);
		System.out.println("预备数据完成，预备数据为：");
		System.out.println(beforeM);
		Message afterM = new Message("已更新！", "#");
		dao.update(i,afterM);
		assertEquals("更新留言失败！", afterM.getContent(), dao.get(i).getContent());
		System.out.println("更新留言成功，更新数据为：");
		System.out.println(dao.get(i).toString());
		System.out.println("销毁预备数据");
		dao.delete(i);
		System.out.println("预备测试数据销毁完毕！");
	}
	
	@Test
	public void testDelete(){
		System.out.println("准备测试删除留言");
		dao.delete(id);
		assertNull("删除留言失败，留言任然存在",dao.get(id));
		System.out.println("删除留言成功！");
	}
	
	@Test
	public void testGet(){
		System.out.println("测试获取留言");
		assertNotNull(dao.get(1));
		System.out.println("获取留言成功！数据为：");
		System.out.println(dao.get(1));
	}
	
	@Test
	public void testList(){
		System.out.println("读取第2到4条记录");
		List<Message> list = dao.list(2,4);
		assertEquals("获取留言列表错误！", list.size(), 3);
		System.out.println("读取留言成功,读取的留言分别是：");
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).toString());
		}
	}
	
}
