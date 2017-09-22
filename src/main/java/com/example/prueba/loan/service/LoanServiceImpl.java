package com.example.prueba.loan.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.prueba.book.service.BookServiceImpl;
import com.example.prueba.loan.dao.entity.LoanEntity;
import com.example.prueba.loan.dao.repository.ILoanRepository;
import com.example.prueba.loan.exception.LoanException;
import com.example.prueba.loan.model.LoanDTO;
import com.example.prueba.loan.model.LoanMapper;
import com.example.prueba.person.service.PersonServiceImpl;

@Service
public class LoanServiceImpl {

	@Autowired
	ILoanRepository loanRepository;

	@Autowired
	LoanMapper loanMapper;
	
	@Autowired
	BookServiceImpl bookServiceImpl;
	
	@Autowired
	PersonServiceImpl personServiceImpl;

	@Transactional
	public List<LoanDTO> getLoans() {
		List<LoanEntity> listLoanEntities = (List<LoanEntity>) loanRepository.findAll();
		return fillDatas(listLoanEntities);
	}

	@Transactional
	public List<LoanDTO> getLoanByDniAndIsbn(String dni, Integer isbn) {
		List<LoanEntity> listLoanEntities = loanRepository.findByDniAndIsbn(dni, isbn);
		return fillDatas(listLoanEntities);
	}

	@Transactional
	public List<LoanDTO> getLoanByDni(String dni) {
		List<LoanEntity> listLoanEntities = loanRepository.findByDni(dni);
		return fillDatas(listLoanEntities);
	}

	@Transactional
	public List<LoanDTO> getLoanByIsbn(Integer isbn) {
		List<LoanEntity> listLoanEntities = loanRepository.findByIsbn(isbn);
		return fillDatas(listLoanEntities);
	}

	@Transactional
	public LoanDTO addLoan(LoanDTO loan) throws LoanException {
		if (bookServiceImpl.getBook(loan.getBook().getIsbn()) != null
			&& personServiceImpl.getPerson(loan.getPerson().getDni()) != null) {
			List<LoanEntity> loansEntity = loanRepository.findByDniAndIsbn(loan.getPerson().getDni(), loan.getBook().getIsbn());
			if (CollectionUtils.isEmpty(loansEntity)) {
				LoanEntity loanEntity = loanMapper.toEntity(loan);
				loanEntity = loanRepository.save(loanEntity);
				return fillData(loanEntity);
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
	
	private List<LoanDTO> fillDatas(List<LoanEntity> entities) {
		List<LoanDTO> retorno = new ArrayList<>();
		if (CollectionUtils.isNotEmpty(entities)) {
			for (LoanEntity entity : entities) {
				retorno.add(fillData(entity));
			}
		}
		return retorno;
	}
	
	private LoanDTO fillData(LoanEntity entity) {
		LoanDTO loanDTO = loanMapper.toObject(entity);
		loanDTO.setBook(bookServiceImpl.getBook(entity.getIsbn()));
		loanDTO.setPerson(personServiceImpl.getPerson(entity.getDni()));
		return loanDTO;
	}
	
}
