package com.bilgiadam.boost.java.course01.lesson067.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.bilgiadam.boost.java.course01.lesson067.model.Address;

@Entity
@Table(name = "address")
public class AddressEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column (name = "id")
	private long   id;
	@Column(name = "street")
	private String street;
	@Column(name = "door_number")
	private int    doorNumber;

//	@OneToMany(mappedBy = "address", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//	private List<PersonEntity> people;

//	public void addPerson(PersonEntity person) {
//		if (this.people == null) {
//			this.people = new ArrayList<PersonEntity>();
//		}
//		this.people.add(person);
//	}

//	public List<PersonEntity> getPeople() {
//		return this.people;
//	}

	public AddressEntity(long id, String street, int doorNumber) {
		super();
//		this.id         = id;
		this.street     = street;
		this.doorNumber = doorNumber;
	}

	public AddressEntity(Address address) {
//		this.id         = address.getId();
		this.street     = address.getStreet();
		this.doorNumber = address.getDoorNumber();
	}

	public String getStreet() {
		return this.street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public int getDoorNumber() {
		return this.doorNumber;
	}

	public void setDoorNumber(int doorNumber) {
		this.doorNumber = doorNumber;
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "AddressEntity [id=" + this.id + ", street=" + this.street + ", doorNumber=" + this.doorNumber + "]";
	}
}
