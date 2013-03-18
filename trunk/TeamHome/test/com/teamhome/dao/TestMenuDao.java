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
		System.out.println("������Ӳ˵�");
		Menu m = new Menu("01","���Բ˵�","#");
		id = dao.add(m);
		assertNotNull("��Ӳ˵�ʧ�ܣ�",dao.get(id));
		System.out.println("��Ӳ˵��ɹ�����Ӳ˵���idΪ��" + id);
	}
	
	@Test
	public void testDelete() {
		System.out.println("׼������ɾ���˵�");
		dao.delete(id);
		assertNull("ɾ���˵�ʧ�ܣ��˵���Ȼ����",dao.get(id));
		System.out.println("ɾ���˵��ɹ���");
	}
	
	@Test
	public void testUpdate() {
		System.out.println("׼�����Ը��²˵���Ԥ������");
		Menu beforeM = new Menu("02", "����ǰ", "#");
		int i = dao.add(beforeM);
		beforeM.setId(i);
		System.out.println("Ԥ��������ɣ�Ԥ������Ϊ��");
		System.out.println(beforeM);
		Menu afterM = new Menu("02", "�Ѹ��£�", "#");
		dao.update(i,afterM);
		assertEquals("���²˵�ʧ�ܣ�", afterM.getName(), dao.get(i).getName());
		System.out.println("���²˵��ɹ�����������Ϊ��");
		System.out.println(dao.get(i).toString());
		System.out.println("����Ԥ������");
		dao.delete(i);
		System.out.println("Ԥ����������������ϣ�");
	}
	
	@Test
	public void testGet() {
		System.out.println("���Ի�ȡ�˵�");
		assertNotNull(dao.get(1));
		System.out.println("��ȡ�˵��ɹ�������Ϊ��");
		System.out.println(dao.get(1));
	}
	
	@Test
	public void testLoad() {
		System.out.println("���Լ��ز˵�");
		List<Menu> list = dao.load();
		assertNotEquals("�˵����ش���", list.size(), 0);
		System.out.println("�˵����سɹ����������£�");
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).toString());
		}
	}
	
}
