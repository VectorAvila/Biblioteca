package com.example.prueba.loan.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.example.prueba.book.model.BookDTO;
import com.example.prueba.person.model.PersonDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class LoanDTO {

	@JsonProperty("persona")
	private PersonDTO person;
	
	@JsonProperty("libro")
    private BookDTO book;
	
	@JsonProperty("fecPrestamo")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date fecPrestamo;
    
	@JsonProperty("fecDevolucion")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date fecDevolucion;
    
	public LoanDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LoanDTO(PersonDTO person, BookDTO book, Date fecPrestamo, Date fecDevolucion) {
		super();
		this.person = person;
		this.book = book;
		this.fecPrestamo = fecPrestamo;
		this.fecDevolucion = fecDevolucion;
	}

	public PersonDTO getPerson() {
		return person;
	}

	public void setPerson(PersonDTO person) {
		this.person = person;
	}

	public BookDTO getBook() {
		return book;
	}

	public void setBook(BookDTO book) {
		this.book = book;
	}

	public Date getFecPrestamo() {
		return fecPrestamo;
	}

	public void setFecPrestamo(Date fecPrestamo) {
		this.fecPrestamo = fecPrestamo;
	}

	public Date getFecDevolucion() {
		return fecDevolucion;
	}

	public void setFecDevolucion(Date fecDevolucion) {
		this.fecDevolucion = fecDevolucion;
	}

}
