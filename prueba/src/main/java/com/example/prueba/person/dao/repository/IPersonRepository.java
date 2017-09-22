package com.example.prueba.person.dao.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.prueba.person.dao.entity.PersonEntity;

@Repository
public interface IPersonRepository extends CrudRepository<PersonEntity, Long> {

	/**
	 * @param dni
	 * @return
	 */
	public PersonEntity findByDni(String dni);
	
}
