package com.bilgiadam.boost.java.course01.lesson067.model;

public class Address {
	
	private long   id;
	private String street;
	private int    doorNumber;

	public Address(long id, String street, int doorNumber) {
		super();
		this.id         = id;
		this.street     = street;
		this.doorNumber = doorNumber;
	}

	@Override
	public String toString() {
		return "Address [street=" + this.street + ", doorNumber=" + this.doorNumber + "]";
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
}
