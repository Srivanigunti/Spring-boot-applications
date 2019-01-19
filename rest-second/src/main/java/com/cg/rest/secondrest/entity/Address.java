package com.cg.rest.secondrest.entity;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Address {
	private int flatNumber;
	private String city;
	private int pincode;

	public Address() {
		super();
		
	} 


	public Address(int flatNumber, String city, int pincode) {
		super();
		this.flatNumber = flatNumber;
		this.city = city;
		this.pincode = pincode;
	}


	public int getFlatNumber() {
		return flatNumber;
	}
	public void setFlatNumber(int flatNumber) {
		this.flatNumber = flatNumber;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public int getPincode() {
		return pincode;
	}
	public void setPincode(int pincode) {
		this.pincode = pincode;
	}
	@Override
	public String toString() {
		return "Address [flatNumber=" + flatNumber + ", city=" + city + ", pincode=" + pincode + "]";
	}
}
