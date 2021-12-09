package com.bilgiadam.boost.java.course01.lesson067.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.bilgiadam.boost.java.course01.lesson067.entity.AddressEntity;
import com.bilgiadam.boost.java.course01.lesson067.entity.PersonEntity;

public class HibernateUtils {
	private static final SessionFactory sessionFactory = sessionFactory();

	private static SessionFactory sessionFactory() {

		try {

			// instance
			Configuration configuration = new Configuration();

			// entity classlarýmýzý buraya ekleyeceðiz
			configuration.addAnnotatedClass(PersonEntity.class);
			configuration.addAnnotatedClass(AddressEntity.class);

			SessionFactory factory = configuration.configure("hibernate.cfg.xml").buildSessionFactory();

			return factory;

		}
		catch (Exception e) {

			e.printStackTrace();

		}

		return null;

	}

	// dýþ dünyada bununla bu classa eriþim saðlayabileceðim.

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}
