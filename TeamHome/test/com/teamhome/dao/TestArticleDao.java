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
		a = new Article("��������","��������",new Date(),"xjy..xjy");
		dao = new ArticleDao();
		System.out.println("\n");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAdd() {
		id = dao.add(a);
		System.out.println("����һ����¼��idΪ" + id);
		Article getA = dao.get(id);
		assertNotNull("�������²��ɹ���",getA);
		System.out.println("�������³ɹ�");
	}

	@Test
	public void testUpdate() {
		int n = dao.add(new Article("������", "������", new Date(), "xjy..xjy"));
		System.out.println("׼�����µ�" + n + "����¼");
		Article updateA = new Article("���Ը�������", "��������", new Date(), "kj..kj");
		boolean success = dao.update(n, updateA);
		assertTrue("��������ʧ�ܣ�", success);
		System.out.println("���µ�" + n + "����¼�ɹ������º�ļ�¼Ϊ��" + dao.get(n).toString());
		dao.delete(n);
		System.out.println("�������ºۼ���ϣ�");
	}
	
	@Test
	public void testDelete() {
		System.out.println("׼��ɾ����" + id + "����¼");
		dao.delete(id);
		assertNull("ɾ���û�ʧ�ܣ�",dao.get(id));
		System.out.println("ɾ����" + id + "����¼�ɹ�");
	}

	@Test
	public void testGet() {
		System.out.println("��ȡ���û�Ϊ:" + dao.get(1).toString());
		assertNotNull("��ȡ�û�ʧ�ܣ�",dao.get(1));
	}

	@Test
	public void testLoad() {
		System.out.println("��ȡ���µ�5����¼");
		List<Article> list = dao.load(5);
		assertEquals("��ȡ���´���", list.size(), 5);
		System.out.println("��ȡ���³ɹ�����ȡ�����·ֱ��ǣ�");
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).toString());
		}
	}

	@Test
	public void testList() {
		System.out.println("��ȡ��2��4����¼");
		List<Article> list = dao.list(2,4);
		assertEquals("��ȡ�����б����", list.size(), 3);
		System.out.println("��ȡ���³ɹ�,��ȡ�����·ֱ��ǣ�");
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).toString());
		}
	}

	@Test
	public void testSearch() {
		System.out.println("����������ʼ");
		List<Article> list = dao.search("��ȡ����", null, null, null, null, 1);
		assertEquals("��������ʧ�ܣ�", list.size(), 1);
		System.out.println("����������ϣ����������������£�");
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).toString());
		}
	}
}
