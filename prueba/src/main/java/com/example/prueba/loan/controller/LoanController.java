package com.example.prueba.loan.controller;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.prueba.loan.exception.LoanException;
import com.example.prueba.loan.model.LoanDTO;
import com.example.prueba.loan.service.LoanServiceImpl;

import io.swagger.annotations.Api;

@RestController
@RequestMapping(value = "/v1")
@Api(value = "Servicio prestamo")
public class LoanController {

	@Autowired
	LoanServiceImpl loanServiceImpl;

	@RequestMapping(value = "/prestamo", method = RequestMethod.GET)
	public ResponseEntity<List<LoanDTO>> getLoad(@RequestParam(value = "persona", required = false) String dni, @RequestParam(value = "libro", required = false) Integer isbn) {
		List<LoanDTO> data = null;
		if (StringUtils.isEmpty(dni)) {
			if (isbn == null) {
				data = loanServiceImpl.getLoans();
			} else {
				data = loanServiceImpl.getLoanByIsbn(isbn);
			}
		} else {
			if (isbn == null) {
				data = loanServiceImpl.getLoanByDni(dni);
			} else {
				data = loanServiceImpl.getLoanByDniAndIsbn(dni, isbn);
			}
		}
		
		if (CollectionUtils.isNotEmpty(data)) {
			return new ResponseEntity<List<LoanDTO>>(data, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<LoanDTO>>(data, HttpStatus.NO_CONTENT);
		}
	}

	@RequestMapping(value = "/prestamo", method = RequestMethod.POST)
	public ResponseEntity<LoanDTO> addLoad(@RequestBody LoanDTO loan) {
		try {
			return new ResponseEntity<LoanDTO>(loanServiceImpl.addLoan(loan), HttpStatus.CREATED);
		} catch (LoanException e) {
			return new ResponseEntity<LoanDTO>(HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/prestamo/{dni}/{isbn}", method = RequestMethod.DELETE)
	public ResponseEntity<LoanDTO> deleteBook(@PathVariable(value = "dni") String dni, @PathVariable(value = "isbn") Integer isbn) {
		try {
			loanServiceImpl.deleteLoan(dni, isbn);
			return new ResponseEntity<LoanDTO>(HttpStatus.OK);
		} catch (LoanException e) {
			return new ResponseEntity<LoanDTO>(HttpStatus.BAD_REQUEST);
		}
	}
}
