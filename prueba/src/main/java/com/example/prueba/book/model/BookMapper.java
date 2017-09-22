package com.example.prueba.book.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Component;

import com.example.prueba.book.dao.entity.BookEntity;

@Component
public class BookMapper {
	
	public BookDTO toObject(BookEntity entity) {
		DozerBeanMapper mapper = new DozerBeanMapper();
		return mapper.map(entity, BookDTO.class);
	}

	public List<BookDTO> toObject(List<BookEntity> entities) {
		List<BookDTO> retorno = new ArrayList<>();
		if (CollectionUtils.isNotEmpty(entities)) {
			for (BookEntity book : entities) {
				retorno.add(toObject(book));
			}
		}
		return retorno;
	}
	
	public BookEntity toEntity(BookDTO object) {
		DozerBeanMapper mapper = new DozerBeanMapper();
		return mapper.map(object, BookEntity.class);
	}
	
}
