package com.test;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.BeforeClass;
import org.junit.Test;

import com.db.DBHelper;
import com.entity.User;
import com.mysql.jdbc.Connection;

public class T {
	private static SessionFactory sessionFactory;

//	@Test
//	public void connection() {
//		Connection conn = (Connection) DBHelper.getConnection();
//		System.out.println(conn);
//	}

//	@BeforeClass
//	public static void setUpBeforeClass() throws Exception {
//		Configuration cfg = new Configuration();
//		cfg.configure(); // 重要
//		ServiceRegistry sr = new ServiceRegistryBuilder().applySettings(
//				cfg.getProperties()).buildServiceRegistry();
//		try {
//			sessionFactory = cfg.configure().addAnnotatedClass(User.class)
//					.buildSessionFactory(sr);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	@Test
	public void test() {
		
		Configuration cfg = new Configuration();
		cfg.configure(); // 重要
		ServiceRegistry sr = new ServiceRegistryBuilder().applySettings(
				cfg.getProperties()).buildServiceRegistry();
		try {
			sessionFactory = cfg.configure().addAnnotatedClass(User.class)
					.buildSessionFactory(sr);
		} catch (Exception e) {
			e.printStackTrace();
		}

		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		User user = new User();
		user.setUsername("kuangjing");
		user.setPassword("666666");

		session.persist(user);
		tx.commit();
		session.close();
		System.out.println("end");
	}

//	@Test
//	public void hibernate() {
//		User user = new User();
//		user.setUsername("kuangjing");
//		user.setPassword("666666");
//
//		// Configuration cfg = new AnnotationConfiguration();
//		// System.out.println(cfg.getProperties());
//		// ServiceRegistry serviceRegistry = new
//		// ServiceRegistryBuilder().applySettings(cfg.getProperties()).buildServiceRegistry();
//		// SessionFactory sf = cfg.buildSessionFactory(serviceRegistry);
//		SessionFactory sf = new AnnotationConfiguration().configure()
//				.buildSessionFactory();
//		Session session = (Session) sf.openSession();
//		session.beginTransaction();
//		session.save(user);
//		session.getTransaction().commit();
//		session.close();
//		sf.close();
//
//	}
}
