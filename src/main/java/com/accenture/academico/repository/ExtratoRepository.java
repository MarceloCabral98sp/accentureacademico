package com.accenture.academico.repository;
/**
 * @author Vinycius
 */
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.accenture.academico.model.Extrato;

@Repository
@Transactional
public interface ExtratoRepository extends JpaRepository<Extrato, Long>{
	
 @Query(value = "SELECT * FROM extrato WHERE id_conta = ?1", nativeQuery = true)
 public List<Extrato> listarExtrato(Long idConta);
	
	

}
