package com.accenture.academico.service;
/**
 * @author Vinycius
 */
import com.accenture.academico.util.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

import com.accenture.academico.enuns.TipoOperacao;
import com.accenture.academico.model.ContaCorrente;
import com.accenture.academico.model.Extrato;
import com.accenture.academico.repository.ContaCorrenteRepository;
import com.accenture.academico.repository.ExtratoRepository;
import org.springframework.data.domain.Pageable;
@Service
public class ExtratoService {
	
	@Autowired
	private ExtratoRepository movimentacaoRepository;
	
	@Autowired
	private ContaCorrenteRepository contaCorrenteRepository;
	
	//MÉTODO PARA FAZER DEPOSITO UTILIZANDO O ID DA CONTA
	public Extrato deposito(Extrato movimentacao, Long idConta) {
		Optional<ContaCorrente> contaBD = contaCorrenteRepository.findById(idConta);
		
		if (contaBD.isPresent() == false) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CONTA CORRENTE NÃO ENCONTRADA");
		
		}else {
			ContaCorrente conta = contaBD.get();

			movimentacao.setDataMovimentacao(new DataHoraUtil().retornarData());
			movimentacao.setHoraMovimentacao(new DataHoraUtil().retornarHora());
			movimentacao.setContaCorrente(conta);
			movimentacao.setTipoOperacao(TipoOperacao.DEPOSITO);
	
			conta.setContaCorrenteSaldo(conta.getContaCorrenteSaldo().add(movimentacao.getValor()));
			 contaCorrenteRepository.saveAndFlush(conta);
			 
			 this.movimentacaoRepository.saveAndFlush(movimentacao);
		}
		return movimentacao;	
		
		
	}
	
	
	//MÉTODO PARA FAZER SAQUE UTILIZANDO O ID DA CONTA
	public Extrato saque(Extrato movimentacao, Long idConta) {
		Optional<ContaCorrente> contaBD = contaCorrenteRepository.findById(idConta);
		BigDecimal valorConf = movimentacao.getValor();
		

		if (contaBD.isPresent() == false) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CONTA NÃO ENCONTRADA");
		
		}else {
			 ContaCorrente conta = contaBD.get();
			if(movimentacao.getValor().longValueExact() > conta.getContaCorrenteSaldo().longValueExact()) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "SAQUE INDISPONIVEL PARA ESSE VALOR - O SEU SALDO É: " + conta.getContaCorrenteSaldo().longValueExact());
			}
					
			movimentacao.setDataMovimentacao(new DataHoraUtil().retornarData());
			movimentacao.setHoraMovimentacao(new DataHoraUtil().retornarHora());
			movimentacao.setContaCorrente(conta);
			movimentacao.setTipoOperacao(TipoOperacao.SAQUE);

			conta.setContaCorrenteSaldo(conta.getContaCorrenteSaldo().subtract(movimentacao.getValor()));
			contaCorrenteRepository.saveAndFlush(conta);

			this.movimentacaoRepository.saveAndFlush(movimentacao);
		}
		return movimentacao;	
		
	}
	
	
	//M�TODO PARA FAZER A TRANSFERENCIA ENTRE CONTA - UTILIZANDO ID_ORIGEM E ID_DESTINO
		public Extrato transferencia(Extrato movimentacao, Long idOrigem, Long idDestino) {
			Optional<ContaCorrente> contaOrigem = contaCorrenteRepository.findById(idOrigem);
			Optional<ContaCorrente> contaDestino = contaCorrenteRepository.findById(idDestino);
			BigDecimal valorTransf = movimentacao.getValor();
			
			if(contaOrigem.isPresent() == false) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CONTA ORIGEM NÃO ENCONTRADA");
			
			}if(contaDestino.isPresent() == false) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CONTA DESTINO NÃO ENCONTRADA");
	
			}else {
				
				ContaCorrente contaOr = contaOrigem.get();
				ContaCorrente contaDt = contaDestino.get();
				
				if(movimentacao.getValor().longValueExact() > contaOr.getContaCorrenteSaldo().longValueExact()) {
					throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "TRANSFERENCIA INDISPONIVEL PARA ESSE VALOR - O SEU SALDO É: " + contaOr.getContaCorrenteSaldo().longValueExact());
				}
				
				movimentacao.setDataMovimentacao(new DataHoraUtil().retornarData());
				movimentacao.setHoraMovimentacao(new DataHoraUtil().retornarHora());
				movimentacao.setContaCorrente(contaOr);
				movimentacao.setTipoOperacao(TipoOperacao.TRANSFERENCIA_ENVIADA); //CONTA QUE ENVIOU	
				contaOr.setContaCorrenteSaldo(contaOr.getContaCorrenteSaldo().subtract(movimentacao.getValor())); //remover valor transferido
				contaCorrenteRepository.saveAndFlush(contaOr);
				this.movimentacaoRepository.saveAndFlush(movimentacao);
				
				
				movimentacao.setDataMovimentacao(new DataHoraUtil().retornarData());
				movimentacao.setHoraMovimentacao(new DataHoraUtil().retornarHora());
				movimentacao.setContaCorrente(contaDt);
				movimentacao.setTipoOperacao(TipoOperacao.TRANSFERENCIA_RECEBIDA); //CONTA QUE EST�O RECEBENDO
				contaDt.setContaCorrenteSaldo(contaDt.getContaCorrenteSaldo().add(movimentacao.getValor())); //soma valor na conta que recebe
				contaCorrenteRepository.saveAndFlush(contaDt);
				this.movimentacaoRepository.saveAndFlush(movimentacao);
				
			}
			
			return movimentacao;
		}
		
	
	//M�TODO FINANCEIRO
		private void RecalcularSaldo(ContaCorrente contaCorrente, Extrato extrato, Long idConta) {
			BigDecimal somaSaldo = contaCorrente.getContaCorrenteSaldo();
			
			
		}
		
	
	

}
