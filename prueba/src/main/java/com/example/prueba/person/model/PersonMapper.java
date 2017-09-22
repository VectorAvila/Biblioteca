package com.example.prueba.person.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Component;

import com.example.prueba.person.dao.entity.PersonEntity;

@Component
public class PersonMapper {
	
	public PersonDTO toObject(PersonEntity entity) {
		DozerBeanMapper mapper = new DozerBeanMapper();
		return mapper.map(entity, PersonDTO.class);
	}

	public List<PersonDTO> toObject(List<PersonEntity> entities) {
		List<PersonDTO> retorno = new ArrayList<>();
		if (CollectionUtils.isNotEmpty(entities)) {
			for (PersonEntity person : entities) {
				retorno.add(toObject(person));
			}
		}
		return retorno;
	}
	
	public PersonEntity toEntity(PersonDTO object) {
		DozerBeanMapper mapper = new DozerBeanMapper();
		return mapper.map(object, PersonEntity.class);
	}
	
}
