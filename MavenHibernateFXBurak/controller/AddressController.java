package com.bilgiadam.boost.java.course01.lesson067.controller;

import java.util.ArrayList;

import javax.persistence.TypedQuery;

import org.hibernate.Session;

import com.bilgiadam.boost.java.course01.lesson067.entity.AddressEntity;

public class AddressController implements Controllable<AddressEntity> {

	@Override
	public void create(AddressEntity entity) {
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
	public void delete(AddressEntity entity) {
		
		try {
			AddressEntity findEntity = find(entity.getId());
			if (findEntity != null) {
				Session session = databaseConnectionHibernate();
				session.getTransaction().begin();
				session.remove(findEntity);
				session.getTransaction().commit();
				System.out.println("Silme Ba�ar�l� " + AddressEntity.class);
			}
		} catch (Exception e) {
			System.out.println("silme an�nda hata meydana geldi !!!!! " + Controllable.class);
			e.printStackTrace();
		}
		
	}
	
	// g�ncellemek
	@Override
	public void update(AddressEntity entity) {
		try {
			AddressEntity findEntity = find(entity.getId());
			if (findEntity != null) {
				findEntity.setStreet(entity.getStreet());
				findEntity.setDoorNumber(entity.getDoorNumber());
				
				Session session = databaseConnectionHibernate();
				session.getTransaction().begin();
				session.merge(findEntity);
				session.getTransaction().commit();
				System.out.println("G�ncelleme Ba�ar�l� " + AddressEntity.class);
			}
			
		} catch (Exception e) {
			System.out.println("g�ncelleme an�nda hata meydana geldi !!!!! " + Controllable.class);
			e.printStackTrace();
		}
	}
	
	// listelemek
	@Override
	public ArrayList<AddressEntity> list() {
		Session session = databaseConnectionHibernate();
		
		// unutma: buradaki sorgulama entity sorgulamas� yani java class�na g�re
		// �a��raca��z.
		String hql = "select str from AddressEntity as str where str.id>=:key";
		TypedQuery<AddressEntity> typedQuery = session.createQuery(hql, AddressEntity.class);
		
		long id = 1L;
		typedQuery.setParameter("key", id);
		
		ArrayList<AddressEntity> arrayList = (ArrayList<AddressEntity>) typedQuery.getResultList();
		System.out.println("listelendi " + AddressEntity.class);
		return arrayList;
	}
	
	// find
	@Override
	public AddressEntity find(long id) {
		Session session = databaseConnectionHibernate();
		AddressEntity addressEntity;
		try {
			addressEntity = session.find(AddressEntity.class, id);
			
			if (addressEntity != null) {
				System.out.println("bulundu... " + addressEntity);
				return addressEntity;
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
	public AddressEntity singleResult(long id) {
		return Controllable.super.singleResult(id);
	}
}
