package com.example.prueba.loan.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.prueba.loan.dao.entity.LoanEntity;

@Repository
public interface ILoanRepository extends CrudRepository<LoanEntity, Long> {
	
	@Query("SELECT t FROM LoanEntity t, PersonEntity p, BookEntity b WHERE t.dni = p.dni AND p.dni = :dni AND t.isbn = b.isbn AND b.isbn=:isbn")
	public List<LoanEntity> findByDniAndIsbn(@Param("dni") String dni, @Param("isbn") Integer isbn);
	
	@Query("SELECT t FROM LoanEntity t, PersonEntity p WHERE t.dni = p.dni AND p.dni = :dni")
	public List<LoanEntity> findByDni(@Param("dni") String dni);
	
	@Query("SELECT t FROM LoanEntity t, BookEntity b WHERE t.isbn = b.isbn AND b.isbn=:isbn")
	public List<LoanEntity> findByIsbn(@Param("isbn") Integer isbn);
	
}
