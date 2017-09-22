package com.example.prueba.loan.service;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.prueba.book.dao.repository.IBookRepository;
import com.example.prueba.loan.dao.entity.LoanEntity;
import com.example.prueba.loan.dao.repository.ILoanRepository;
import com.example.prueba.loan.exception.LoanException;
import com.example.prueba.loan.model.LoanDTO;
import com.example.prueba.loan.model.LoanMapper;
import com.example.prueba.person.dao.repository.IPersonRepository;

@Service
public class LoanServiceImpl {

	@Autowired
	ILoanRepository loanRepository;

	@Autowired
	LoanMapper loanMapper;
	
	@Autowired
	IBookRepository bookRepository;
	
	@Autowired
	IPersonRepository personRepository; 

	@Transactional
	public List<LoanDTO> getLoans() {
		List<LoanEntity> listLoanEntities = (List<LoanEntity>) loanRepository.findAll();
		return loanMapper.toObject(listLoanEntities);
	}

	@Transactional
	public List<LoanDTO> getLoanByDniAndIsbn(String dni, Integer isbn) {
		List<LoanEntity> loanEntity = loanRepository.findByDniAndIsbn(dni, isbn);
		return loanMapper.toObject(loanEntity);
	}

	@Transactional
	public List<LoanDTO> getLoanByDni(String dni) {
		List<LoanEntity> loanEntity = loanRepository.findByDni(dni);
		return loanMapper.toObject(loanEntity);
	}

	@Transactional
	public List<LoanDTO> getLoanByIsbn(Integer isbn) {
		List<LoanEntity> loanEntity = loanRepository.findByIsbn(isbn);
		return loanMapper.toObject(loanEntity);
	}

	@Transactional
	public LoanDTO addLoan(LoanDTO loan) throws LoanException {
		if (bookRepository.findByIsbn(loan.getBook().getIsbn()) != null
			&& personRepository.findByDni(loan.getPerson().getDni()) != null) {
			List<LoanEntity> loansEntity = loanRepository.findByDniAndIsbn(loan.getPerson().getDni(), loan.getBook().getIsbn());
			if (CollectionUtils.isEmpty(loansEntity)) {
				LoanEntity loanEntity = loanMapper.toEntity(loan);
				loanEntity = loanRepository.save(loanEntity);
				return loanMapper.toObject(loanEntity);
			} else {
				throw new LoanException();
			}
		} else {
			throw new LoanException();
		}
	}

	@Transactional
	public void deleteLoan(String dni, Integer isbn) throws LoanException {
		List<LoanEntity> loansEntity = loanRepository.findByDniAndIsbn(dni, isbn);
		if (CollectionUtils.isNotEmpty(loansEntity)) {
			loanRepository.delete(loansEntity.get(0).getId());
		} else {
			throw new LoanException();
		}
	}
}
