package com.teamhome.dao;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.teamhome.dto.Article;

public class TestArticleDao {
	
	ArticleDao dao = null;
	Article a = null;
	static int id = 0;

	@Before
	public void setUp() throws Exception {
		a = new Article("测试文章","测试内容",new Date(),"xjy..xjy");
		dao = new ArticleDao();
		System.out.println("\n");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAdd() {
		id = dao.add(a);
		System.out.println("加入一条记录，id为" + id);
		Article getA = dao.get(id);
		assertNotNull("加入文章不成功！",getA);
		System.out.println("加入文章成功");
	}

	@Test
	public void testUpdate() {
		int n = dao.add(new Article("待更新", "待更新", new Date(), "xjy..xjy"));
		System.out.println("准备更新第" + n + "条记录");
		Article updateA = new Article("测试更新文章", "更新内容", new Date(), "kj..kj");
		boolean success = dao.update(n, updateA);
		assertTrue("更新文章失败！", success);
		System.out.println("更新第" + n + "条记录成功，更新后的记录为：" + dao.get(n).toString());
		dao.delete(n);
		System.out.println("消除更新痕迹完毕！");
	}
	
	@Test
	public void testDelete() {
		System.out.println("准备删除第" + id + "条记录");
		dao.delete(id);
		assertNull("删除用户失败！",dao.get(id));
		System.out.println("删除第" + id + "条记录成功");
	}

	@Test
	public void testGet() {
		System.out.println("获取的用户为:" + dao.get(1).toString());
		assertNotNull("获取用户失败！",dao.get(1));
	}

	@Test
	public void testLoad() {
		System.out.println("读取最新的5条记录");
		List<Article> list = dao.load(5);
		assertEquals("获取文章错误！", list.size(), 5);
		System.out.println("读取文章成功，读取的文章分别是：");
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).toString());
		}
	}

	@Test
	public void testList() {
		System.out.println("读取第2到4条记录");
		List<Article> list = dao.list(2,4);
		assertEquals("获取文章列表错误！", list.size(), 3);
		System.out.println("读取文章成功,读取的文章分别是：");
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).toString());
		}
	}

	@Test
	public void testSearch() {
		System.out.println("测试搜索开始");
		List<Article> list = dao.search("获取文章", null, null, null, null, 1);
		assertEquals("搜索文章失败！", list.size(), 1);
		System.out.println("搜索文章完毕，搜索到的文章如下：");
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).toString());
		}
	}
}
