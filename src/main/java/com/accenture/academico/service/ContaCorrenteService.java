package com.accenture.academico.service;
/**
 * @author Vinycius
 */
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.accenture.academico.model.ContaCorrente;
import com.accenture.academico.repository.ContaCorrenteRepository;

@Service
public class ContaCorrenteService {
	
	@Autowired
	private ContaCorrenteRepository contaCorrenteRepository;
	
	//MÉTODO PARA BUSCAR TODAS AS CONTAS CORRENTES
	public List<ContaCorrente> buscarContaCorrente(Pageable pageable){
		return this.contaCorrenteRepository.findAll(pageable).getContent();
	}
	
	//MÉTODO PARA SALVAR CONTA CORRENTE
	public void salvarContaCorrente(ContaCorrente contaCorrente) {
		//String contaCorrenteAgencia = contaCorrente.getContaCorrenteAgencia();
		
		
		this.contaCorrenteRepository.save(contaCorrente);
	}
	
	//MÉTODO PARA ALTERAR CONTA CORRENTE
	public void alterarContaCorrente(ContaCorrente contaCorrente, Long id) {
		ContaCorrente CC = this.contaCorrenteRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Agencia não encontrada"));
	    BeanUtils.copyProperties(contaCorrente, CC, "id");
	    this.salvarContaCorrente(CC);
	}
	
	//MÉTODO PARA EXCLUIR CONTA CORRENTE
	public void excluirContaCorrente(Long id) {
		this.contaCorrenteRepository.deleteById(id);
	}
	
	//MÉTODO PARA BUSCAR CONTA CORRENTE POR ID
	public ContaCorrente buscarContaCorrenteID(Long id) {
		Optional<ContaCorrente> CC = contaCorrenteRepository.findById(id);
		return CC.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Conta Corrente não encontrada!"));
	}

}
