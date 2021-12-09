package com.bilgiadam.boost.java.course01.lesson067.controller;

import java.util.ArrayList;

import javax.persistence.TypedQuery;

import org.hibernate.Session;

import com.bilgiadam.boost.java.course01.lesson067.entity.PersonEntity;

public class PersonController implements Controllable<PersonEntity> {

	@Override
	public void create(PersonEntity entity) {
		try {
			Session session = databaseConnectionHibernate();
			session.getTransaction().begin();
			System.out.println(session);
			session.persist(entity);
			session.getTransaction().commit();
			System.out.println("ekleme tamamd�r" + Controllable.class);
		} catch (Exception e) {
			System.out.println("ekleme an�nda hata meydana geldi !!!!! " + Controllable.class);
			e.printStackTrace();
		}
	}
	
	// silmek
	@Override
	public void delete(PersonEntity entity) {
		
		try {
			PersonEntity findEntity = find(entity.getId());
			if (findEntity != null) {
				Session session = databaseConnectionHibernate();
				session.getTransaction().begin();
				session.remove(findEntity);
				session.getTransaction().commit();
				System.out.println("Silme Ba�ar�l� " + PersonEntity.class);
			}
		} catch (Exception e) {
			System.out.println("silme an�nda hata meydana geldi !!!!! " + Controllable.class);
			e.printStackTrace();
		}
		
	}
	
	// g�ncellemek
	@Override
	public void update(PersonEntity entity) {
		try {
			PersonEntity findEntity = find(entity.getId());
			if (findEntity != null) {
				findEntity.setName(entity.getName());
				findEntity.setLastName(entity.getLastName());
				
				Session session = databaseConnectionHibernate();
				session.getTransaction().begin();
				session.merge(findEntity);
				session.getTransaction().commit();
				System.out.println("G�ncelleme Ba�ar�l� " + PersonEntity.class);
			}
			
		} catch (Exception e) {
			System.out.println("g�ncelleme an�nda hata meydana geldi !!!!! " + Controllable.class);
			e.printStackTrace();
		}
	}
	
	// listelemek
	@Override
	public ArrayList<PersonEntity> list() {
		Session session = databaseConnectionHibernate();
		
		// unutma: buradaki sorgulama entity sorgulamas� yani java class�na g�re
		// �a��raca��z.
		String hql = "select str from PersonEntity as str where str.id>=:key";
		TypedQuery<PersonEntity> typedQuery = session.createQuery(hql, PersonEntity.class);
		
		long id = 1L;
		typedQuery.setParameter("key", id);
		
		ArrayList<PersonEntity> arrayList = (ArrayList<PersonEntity>) typedQuery.getResultList();
		System.out.println("listelendi " + PersonEntity.class);
		return arrayList;
	}
	
	// find
	@Override
	public PersonEntity find(long id) {
		Session session = databaseConnectionHibernate();
		PersonEntity personEntity;
		try {
			personEntity = session.find(PersonEntity.class, id);
			
			if (personEntity != null) {
				System.out.println("bulundu... " + personEntity);
				return personEntity;
			} else {
				System.out.println("Arad���n�z kriterde sonu�lar bulunamad� ...");
				return null;
			}
		} catch (Exception e) {
			System.out.println("find an�nda hata meydana geldi !!!!! " + Controllable.class);
			e.printStackTrace();
		}
		return null;
	}
	
	// tek kay�t gonder
	@Override
	public PersonEntity singleResult(long id) {
		return Controllable.super.singleResult(id);
	}
}
