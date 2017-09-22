package com.example.prueba.person.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.prueba.person.exception.PersonException;
import com.example.prueba.person.model.PersonDTO;
import com.example.prueba.person.service.PersonServiceImpl;

import io.swagger.annotations.Api;

@RestController
@RequestMapping(value = "/v1")
@Api(value = "Servicio personas")
public class PersonController {
	
	@Autowired
	PersonServiceImpl personServiceImpl;

	@RequestMapping(value = "/persona", method = RequestMethod.GET)
	public ResponseEntity<List<PersonDTO>> getPersons() {
		List<PersonDTO> data = personServiceImpl.getPersons();
		if (CollectionUtils.isNotEmpty(data)) {
			return new ResponseEntity<List<PersonDTO>>(data, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<PersonDTO>>(data, HttpStatus.NO_CONTENT);
		}
		
	}

	@RequestMapping(value = "/persona/{dni}", method = RequestMethod.GET)
	public ResponseEntity<PersonDTO> getPerson(@PathVariable(value = "dni") String dni) {
		PersonDTO data = personServiceImpl.getPerson(dni);
		if (data != null) {
			return new ResponseEntity<PersonDTO>(data, HttpStatus.OK);
		} else {
			return new ResponseEntity<PersonDTO>(data, HttpStatus.NO_CONTENT);
		}
	}

	@RequestMapping(value = "/persona", method = RequestMethod.POST)
	public ResponseEntity<PersonDTO> addPerson(@RequestBody PersonDTO persona) {
		try {
			return new ResponseEntity<PersonDTO>(personServiceImpl.addPerson(persona), HttpStatus.CREATED);
		} catch (PersonException e) {
			return new ResponseEntity<PersonDTO>(HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/persona/{dni}", method = RequestMethod.PUT)
	public ResponseEntity<PersonDTO> modifyPerson(@PathVariable(value = "dni") String dni, @RequestBody @Valid PersonDTO persona) {
		if (!StringUtils.equals(dni, persona.getDni())) {
			return new ResponseEntity<PersonDTO>(HttpStatus.BAD_REQUEST);
		} else {
			try {
				return new ResponseEntity<PersonDTO>(personServiceImpl.updatePerson(persona), HttpStatus.CREATED);
			} catch (PersonException e) {
				return new ResponseEntity<PersonDTO>(HttpStatus.BAD_REQUEST);
			}
		}
	}

	@RequestMapping(value = "/persona/{dni}", method = RequestMethod.DELETE)
	public ResponseEntity<PersonDTO> deletePerson(@PathVariable(value = "dni") String dni) {
		try {
			personServiceImpl.deletePerson(dni);
			return new ResponseEntity<PersonDTO>(HttpStatus.OK);
		} catch (PersonException e) {
			return new ResponseEntity<PersonDTO>(HttpStatus.BAD_REQUEST);
		}
	}
}
