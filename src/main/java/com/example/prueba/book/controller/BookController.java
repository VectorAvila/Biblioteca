package com.example.prueba.book.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.prueba.book.exception.BookException;
import com.example.prueba.book.model.BookDTO;
import com.example.prueba.book.service.BookServiceImpl;

import io.swagger.annotations.Api;

@RestController
@RequestMapping(value = "/v1")
@Api(value = "Servicio libro")
public class BookController {

	@Autowired
	BookServiceImpl bookServiceImpl;

	@RequestMapping(value = "/libro", method = RequestMethod.GET)
	public ResponseEntity<List<BookDTO>> getBooks() {
		List<BookDTO> data = bookServiceImpl.getBooks();
		if (CollectionUtils.isNotEmpty(data)) {
			return new ResponseEntity<List<BookDTO>>(data, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<BookDTO>>(data, HttpStatus.NO_CONTENT);
		}
		
	}

	@RequestMapping(value = "/libro/{isbn}", method = RequestMethod.GET)
	public ResponseEntity<BookDTO> getBook(@PathVariable(value = "isbn") Integer isbn) {
		BookDTO data = bookServiceImpl.getBook(isbn);
		if (data != null) {
			return new ResponseEntity<BookDTO>(data, HttpStatus.OK);
		} else {
			return new ResponseEntity<BookDTO>(data, HttpStatus.NO_CONTENT);
		}
	}

	@RequestMapping(value = "/libro", method = RequestMethod.POST)
	public ResponseEntity<BookDTO> addBook(@RequestBody BookDTO book) {
		try {
			return new ResponseEntity<BookDTO>(bookServiceImpl.addBook(book), HttpStatus.CREATED);
		} catch (BookException e) {
			return new ResponseEntity<BookDTO>(HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/libro/{isbn}", method = RequestMethod.PUT)
	public ResponseEntity<BookDTO> modifyBook(@PathVariable(value = "isbn") Integer isbn, @RequestBody @Valid BookDTO book) {
		if (isbn != book.getIsbn()) {
			return new ResponseEntity<BookDTO>(HttpStatus.BAD_REQUEST);
		} else {
			try {
				return new ResponseEntity<BookDTO>(bookServiceImpl.updateBook(book), HttpStatus.CREATED);
			} catch (BookException e) {
				return new ResponseEntity<BookDTO>(HttpStatus.BAD_REQUEST);
			}
		}
	}

	@RequestMapping(value = "/libro/{isbn}", method = RequestMethod.DELETE)
	public ResponseEntity<BookDTO> deleteBook(@PathVariable(value = "isbn") Integer isbn) {
		try {
			bookServiceImpl.deleteBook(isbn);
			return new ResponseEntity<BookDTO>(HttpStatus.OK);
		} catch (BookException e) {
			return new ResponseEntity<BookDTO>(HttpStatus.BAD_REQUEST);
		}
	}
}
