package com.example.prueba.book.dao.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.prueba.book.dao.entity.BookEntity;

@Repository
public interface IBookRepository extends CrudRepository<BookEntity, Long> {
	
	public BookEntity findByIsbn(Integer isbn);
}
