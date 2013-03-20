package com.teamhome.dao;

import static org.junit.Assert.*;

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
		System.out.println("������Ӳ���");
		Attribute a = new Attribute("css","1");
		id = dao.add(a);
		assertNotNull("��Ӳ���ʧ�ܣ�",dao.get(id));
		System.out.println("��Ӳ����ɹ�����Ӳ�����idΪ��" + id);
	}
	
	@Test
	public void testUpdate(){
		System.out.println("׼�����Ը��²�����Ԥ������");
		Attribute beforeA = new Attribute("����ǰ","123123");
		int i = dao.add(beforeA);
		beforeA.setId(i);
		System.out.println("Ԥ��������ɣ�Ԥ������Ϊ��");
		System.out.println(beforeA);
		Attribute afterA = new Attribute("�Ѹ��£�", "#");
		dao.update(i,afterA);
		assertEquals("���²���ʧ�ܣ�", afterA.getName(), dao.get(i).getName());
		System.out.println("���²����ɹ�����������Ϊ��");
		System.out.println(dao.get(i).toString());
		System.out.println("����Ԥ������");
		dao.delete(i);
		System.out.println("Ԥ����������������ϣ�");
	}
	
	@Test
	public void testDelete(){
		System.out.println("׼������ɾ������");
		dao.delete(id);
		assertNull("ɾ������ʧ�ܣ�������Ȼ����",dao.get(id));
		System.out.println("ɾ�������ɹ���");
	}
	
	@Test
	public void testGet(){
		System.out.println("���Ի�ȡ����");
		assertNotNull(dao.get(1));
		System.out.println("��ȡ�����ɹ�������Ϊ��");
		System.out.println(dao.get(1));
	}
	
	@Test
	public void testList(){
		System.out.println("��ȡ��2��4����¼");
		List<Attribute> list = dao.list(2,4);
		assertEquals("��ȡ�����б����", list.size(), 3);
		System.out.println("��ȡ�����ɹ�,��ȡ�Ĳ����ֱ��ǣ�");
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).toString());
		}
	}
	
}
