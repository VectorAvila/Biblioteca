package com.example.prueba.loan.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Component;

import com.example.prueba.loan.dao.entity.LoanEntity;

@Component
public class LoanMapper {
	
	public LoanDTO toObject(LoanEntity entity) {
		DozerBeanMapper mapper = new DozerBeanMapper();
		LoanDTO retorno = mapper.map(entity, LoanDTO.class);
		return retorno;
	}

	public List<LoanDTO> toObject(List<LoanEntity> entities) {
		List<LoanDTO> retorno = new ArrayList<>();
		if (CollectionUtils.isNotEmpty(entities)) {
			for (LoanEntity loan : entities) {
				retorno.add(toObject(loan));
			}
		}
		return retorno;
	}
	
	public LoanEntity toEntity(LoanDTO object) {
		DozerBeanMapper mapper = new DozerBeanMapper();
		LoanEntity retorno = mapper.map(object, LoanEntity.class);
		retorno.setDni(object.getPerson().getDni());
		retorno.setIsbn(object.getBook().getIsbn());
		return retorno;
	}
	
}
