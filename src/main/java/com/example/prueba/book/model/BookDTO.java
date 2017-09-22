package com.example.prueba.book.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BookDTO {

	
	private Integer isbn;
	
	@JsonProperty("titulo")
    private String title;
	
	@JsonProperty("autor")
    private String author;
	
	@JsonProperty("fecPublicacion")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date publicationDate;
	
	@JsonProperty("disponible")
    private Boolean available;
    
	public BookDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BookDTO(Integer isbn, String title, String author, Date publicationDate, Boolean available) {
		super();
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.publicationDate = publicationDate;
		this.available = available;
	}

	public Integer getIsbn() {
		return isbn;
	}

	public void setIsbn(Integer isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Date getPublicationDate() {
		return publicationDate;
	}

	public void setPublicationDate(Date publicationDate) {
		this.publicationDate = publicationDate;
	}

	public Boolean getAvailable() {
		return available;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
	}
	
}
