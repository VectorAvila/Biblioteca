package com.example.prueba.person.dao.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="persona")
public class PersonEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
	
	@Column(unique=true)
	private String dni;
	
	@Column(name="nombre")
	private String name;
	
	@Column(name="apellidos")
	private String surname;
	
	@Column(name="telefonos")
	private String phoneNumbers;
	
	@Column(name="direccion")
	private String address;
	
	@Column(name="fecha_nacimiento")
	private Date birthDate;

	public PersonEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PersonEntity(String dni, String name, String surname, String phoneNumbers, String address,
			Date birthDate) {
		super();
		this.dni = dni;
		this.name = name;
		this.surname = surname;
		this.phoneNumbers = phoneNumbers;
		this.address = address;
		this.birthDate = birthDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPhoneNumbers() {
		return phoneNumbers;
	}

	public void setPhoneNumbers(String phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

}
