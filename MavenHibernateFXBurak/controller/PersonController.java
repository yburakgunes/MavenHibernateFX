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
			System.out.println("ekleme tamamdýr" + Controllable.class);
		} catch (Exception e) {
			System.out.println("ekleme anýnda hata meydana geldi !!!!! " + Controllable.class);
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
				System.out.println("Silme Baþarýlý " + PersonEntity.class);
			}
		} catch (Exception e) {
			System.out.println("silme anýnda hata meydana geldi !!!!! " + Controllable.class);
			e.printStackTrace();
		}
		
	}
	
	// güncellemek
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
				System.out.println("Güncelleme Baþarýlý " + PersonEntity.class);
			}
			
		} catch (Exception e) {
			System.out.println("güncelleme anýnda hata meydana geldi !!!!! " + Controllable.class);
			e.printStackTrace();
		}
	}
	
	// listelemek
	@Override
	public ArrayList<PersonEntity> list() {
		Session session = databaseConnectionHibernate();
		
		// unutma: buradaki sorgulama entity sorgulamasý yani java classýna göre
		// çaðýracaðýz.
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
				System.out.println("Aradýðýnýz kriterde sonuçlar bulunamadý ...");
				return null;
			}
		} catch (Exception e) {
			System.out.println("find anýnda hata meydana geldi !!!!! " + Controllable.class);
			e.printStackTrace();
		}
		return null;
	}
	
	// tek kayýt gonder
	@Override
	public PersonEntity singleResult(long id) {
		return Controllable.super.singleResult(id);
	}
}
