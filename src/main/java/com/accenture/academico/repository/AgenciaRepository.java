package com.accenture.academico.repository;
/**
 * @author Vinycius
 */
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accenture.academico.model.Agencia;



@Repository
public interface AgenciaRepository extends JpaRepository<Agencia, Long>{

}
