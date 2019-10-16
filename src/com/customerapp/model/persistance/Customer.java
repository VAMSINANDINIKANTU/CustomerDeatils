package com.customerapp.model.persistance;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "customer_table165")
public class Customer {
	@Id
	@GeneratedValue
	private int id;
	@NotEmpty(message = "name can not left blank")
	private String name;
	@NotEmpty(message = "address can not left blank")
	private String address;
	@Email
	@NotEmpty(message = "email can left blank")
	private String email;

	private String phoneno;

	public Customer(String name, String address, String email, String phoneno) {

		this.name = name;
		this.address = address;
		this.email = email;
		this.phoneno = phoneno;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneno() {
		return phoneno;
	}

	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}

	public Customer() {

	}

}
