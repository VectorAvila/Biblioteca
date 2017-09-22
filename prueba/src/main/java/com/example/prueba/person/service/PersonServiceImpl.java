package com.example.prueba.person.service;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.prueba.loan.model.LoanDTO;
import com.example.prueba.loan.service.LoanServiceImpl;
import com.example.prueba.person.dao.entity.PersonEntity;
import com.example.prueba.person.dao.repository.IPersonRepository;
import com.example.prueba.person.exception.PersonException;
import com.example.prueba.person.model.PersonDTO;
import com.example.prueba.person.model.PersonMapper;

@Service
public class PersonServiceImpl {
	
	@Autowired
	IPersonRepository personRepository;
	
	@Autowired
	PersonMapper personMapper;
	
	@Autowired
	LoanServiceImpl loanServiceImpl;

	@Transactional
	public List<PersonDTO> getPersons() {
		List<PersonEntity> listPersonsEntities = (List<PersonEntity>) personRepository.findAll();
		return personMapper.toObject(listPersonsEntities);
	}
	
	@Transactional
	public PersonDTO getPerson(String dni) {
		PersonEntity personsEntity = personRepository.findByDni(dni);
		return personMapper.toObject(personsEntity);
	}
	
	@Transactional
	public PersonDTO addPerson(PersonDTO person) throws PersonException {
		PersonEntity personsEntity = personRepository.findByDni(person.getDni());
		if (personsEntity == null) {
			personsEntity = personMapper.toEntity(person);
			personsEntity = personRepository.save(personsEntity);
			return personMapper.toObject(personsEntity);
		} else {
			throw new PersonException();
		}
	}
	
	@Transactional
	public PersonDTO updatePerson(PersonDTO person) throws PersonException {
		PersonEntity personsEntity = personRepository.findByDni(person.getDni());
		if (personsEntity != null) {
			PersonEntity personsEntitySave = personMapper.toEntity(person);
			personsEntitySave.setId(personsEntity.getId());
			personsEntity = personRepository.save(personsEntitySave);
			return personMapper.toObject(personsEntity);
		} else {
			throw new PersonException();
		}
	}
	
	@Transactional
	public void deletePerson(String dni) throws PersonException {
		PersonEntity personsEntity = personRepository.findByDni(dni);
		List<LoanDTO> loans = loanServiceImpl.getLoanByDni(dni);
		if (personsEntity != null
			&& CollectionUtils.isEmpty(loans)) {
			personRepository.delete(personsEntity.getId());
		} else {
			throw new PersonException();
		}
	}
}
