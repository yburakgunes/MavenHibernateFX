package com.bilgiadam.boost.java.course01.lesson067.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.bilgiadam.boost.java.course01.lesson067.model.Person;

@Entity
@Table(name = "person")
public class PersonEntity {

	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "name")
	private String name;

	@Column(name = "last_name")
	private String lastName;

	@ManyToOne
	@JoinColumn(name = "addresss_id", referencedColumnName = "id")
	private AddressEntity address;

	public PersonEntity() {
		super();
	}

	public PersonEntity(String name, String lastName) {
		super();
		this.name     = name;
		this.lastName = lastName;
	}

	public PersonEntity(Person person) {
//		this.id       = person.getId();   // id auto increment olduðu için hibernate üzerinden set edilmemeli 
		this.name     = person.getName();
		this.lastName = person.getLastName();
//		this.address  = new AddressEntity(person.getAddress());
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return "PersonEntity [id=" + this.id + ", name=" + this.name + ", lastName=" + this.lastName + ", address="
				+ this.address + "]";
	}

	public AddressEntity getAddress() {
		return this.address;
	}

	public void setAddress(AddressEntity address) {
		this.address = address;
	}

}
