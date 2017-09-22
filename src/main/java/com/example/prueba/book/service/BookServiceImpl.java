package com.example.prueba.book.service;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.prueba.book.dao.entity.BookEntity;
import com.example.prueba.book.dao.repository.IBookRepository;
import com.example.prueba.book.exception.BookException;
import com.example.prueba.book.model.BookDTO;
import com.example.prueba.book.model.BookMapper;
import com.example.prueba.loan.model.LoanDTO;
import com.example.prueba.loan.service.LoanServiceImpl;

@Service
public class BookServiceImpl {
	
	@Autowired
	IBookRepository bookRepository;
	
	@Autowired
	BookMapper bookMapper;
	
	@Autowired
	LoanServiceImpl loanServiceImpl;

	@Transactional
	public List<BookDTO> getBooks() {
		List<BookEntity> listBookEntities = (List<BookEntity>) bookRepository.findAll();
		return bookMapper.toObject(listBookEntities);
	}
	
	@Transactional
	public BookDTO getBook(Integer isbn) {
		BookEntity bookEntity = bookRepository.findByIsbn(isbn);
		if (bookEntity != null) {
			return bookMapper.toObject(bookEntity);
		} else {
			return null;
		}
	}
	
	@Transactional
	public BookDTO addBook(BookDTO book) throws BookException {
		BookEntity bookEntity = bookRepository.findByIsbn(book.getIsbn());
		if (bookEntity == null) {
			bookEntity = bookMapper.toEntity(book);
			bookEntity = bookRepository.save(bookEntity);
			return bookMapper.toObject(bookEntity);
		} else {
			throw new BookException();
		}
	}
	
	@Transactional
	public BookDTO updateBook(BookDTO book) throws BookException {
		BookEntity bookEntity = bookRepository.findByIsbn(book.getIsbn());
		if (bookEntity != null) {
			BookEntity bookEntitySave = bookMapper.toEntity(book);
			bookEntitySave.setId(bookEntity.getId());
			bookEntity = bookRepository.save(bookEntitySave);
			return bookMapper.toObject(bookEntity);
		} else {
			throw new BookException();
		}
	}
	
	@Transactional
	public void deleteBook(Integer isbn) throws BookException {
		BookEntity bookEntity = bookRepository.findByIsbn(isbn);
		List<LoanDTO> loans = loanServiceImpl.getLoanByIsbn(isbn);
		if (bookEntity != null
			&& CollectionUtils.isEmpty(loans)) {
			bookRepository.delete(bookEntity.getId());
		} else {
			throw new BookException();
		}
	}
}
