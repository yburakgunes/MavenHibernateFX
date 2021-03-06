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

			// entity classlarımızı buraya ekleyeceğiz
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

	// dış dünyada bununla bu classa erişim sağlayabileceğim.

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}
