package com.example.prueba.person.model;

import java.util.Date;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Datos de Persona")
public class PersonDTO {

	@NotEmpty
	private String dni;
	
	@JsonProperty("nombre")
	@ApiModelProperty(value = "Nombre de la persona", required = true)
	private String name;
	
	@JsonProperty("apellidos")
	@ApiModelProperty(value = "Apellidos de la persona", required = true)
	private String surname;
	
	@JsonProperty("telefonos")
	@ApiModelProperty(value = "Telefonos de la persona", required = false)
	private String phoneNumber;
	
	@JsonProperty("direccion")
	private String address;
	
	@JsonProperty("fecNacimiento")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
    @JsonFormat(pattern = "dd/MM/yyyy")
	private Date birthDate;

	public PersonDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PersonDTO(String dni, String name, String surname, String phoneNumber, String address,
			Date birthDate) {
		super();
		this.dni = dni;
		this.name = name;
		this.surname = surname;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.birthDate = birthDate;
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

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
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
