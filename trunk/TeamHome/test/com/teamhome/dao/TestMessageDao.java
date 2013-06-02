package com.teamhome.dao;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.teamhome.dto.Message;

public class TestMessageDao {
	MessageDao dao = null;
	static int id = 0;
	static int sId = 0;

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
		System.out.println("�����������");
		Message m = new Message("�������", "");
		id = dao.add(m);
		assertNotNull("�������ʧ�ܣ�", dao.get(id));
		System.out.println("������Գɹ���������Ե�idΪ��" + id);
	}

	@Test
	public void testUpdate() {
		System.out.println("׼�����Ը������ԣ�Ԥ������");
		Message beforeM = new Message("����ǰ", "123123");
		int i = dao.add(beforeM);
		beforeM.setId(i);
		System.out.println("Ԥ��������ɣ�Ԥ������Ϊ��");
		System.out.println(beforeM);
		Message afterM = new Message("�Ѹ��£�", "aaaa");
		dao.update(i, afterM);
		assertEquals("��������ʧ�ܣ�", afterM.getContent(), dao.get(i).getContent());
		System.out.println("�������Գɹ�����������Ϊ��");
		System.out.println(dao.get(i).toString());
		System.out.println("����Ԥ������");
		dao.delete(i);
		System.out.println("Ԥ����������������ϣ�");
	}

	@Test
	public void testDelete() {
		System.out.println("׼������ɾ������");
		dao.delete(id);
		assertNull("ɾ������ʧ�ܣ�������Ȼ����", dao.get(id));
		System.out.println("ɾ�����Գɹ���");
	}

	@Test
	public void testGet() {
		System.out.println("���Ի�ȡ����");
		assertNotNull(dao.get(1));
		System.out.println("��ȡ���Գɹ�������Ϊ��");
		System.out.println(dao.get(1));
	}

	@Test
	public void testList() {
		System.out.println("��ȡ��2��4����¼");
		List<Message> list = dao.list(2, 4);
		assertEquals("��ȡ�����б����", list.size(), 3);
		System.out.println("��ȡ���Գɹ�,��ȡ�����Էֱ��ǣ�");
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).toString());
		}
	}

	@Test
	public void testAddStarMessage() {
		System.out.println("���Ա������");
		Message msg = dao.get(1);
		sId = dao.starMessage(msg.getId());
		Message smsg = dao.getStarMessage(sId);
		assertNotNull("����Ǳ�����ʧ�ܣ�", smsg);
		System.out.println("���Ա�����Գɹ�");
	}

	@Test
	public void testListStarMessage() {
		System.out.println("���Ի�ȡ�Ǳ������б�");
		ArrayList<Message> list = dao.listStarMessage(1, 3);
		assertEquals("��ȡ�Ǳ����Դ���", list.size(), 3);
		System.out.println("��ȡ�Ǳ����Գɹ�����ȡ�ļ�¼Ϊ��");
		for(Message s : list) {
			System.out.println(s);
		}
	}

	@Test
	public void testGetStarMessage() {
		System.out.println("���Ի�ȡһ���Ǳ�����");
		Message smsg = dao.getStarMessage(1);
		assertNotNull("��ȡ�������ʧ�ܣ�",smsg);
		System.out.println("������Ի�ȡ�ɹ���Ϊ��");
		System.out.println(smsg);
	}

	@Test
	public void testDeleteStarMessage() {
		System.out.println("����ɾ���Ǳ�����");
		dao.deleteStarMessage(sId);
		assertNull("�Ǳ�������Ȼ���ڣ�",dao.getStarMessage(sId));
		System.out.println("ɾ���ɹ���ɾ��������Ϊ�� " + sId);
	}
}
