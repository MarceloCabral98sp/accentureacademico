package com.accenture.academico.controller;
/**
 * @author Vinycius
 */
import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.accenture.academico.model.Agencia;
import com.accenture.academico.model.ContaCorrente;
import com.accenture.academico.model.Extrato;
import com.accenture.academico.repository.ExtratoRepository;
import com.accenture.academico.service.ExtratoService;

@RestController
@RequestMapping(value = "/movimentacoes")
public class ExtratoController {
	
	@Autowired
	private ExtratoService movimentacaoService;
	
	@Autowired
	private ExtratoRepository extratoRepository;

	//MÉTODO PARA FAZER DEPOSITO UTILIZANDO O ID DA CONTA
	@PostMapping("/deposito/{idConta}")
	@ResponseStatus(HttpStatus.CREATED)
	public void deposito(@RequestBody Extrato movimentacao, @PathVariable("idConta") Long idConta) {
		this.movimentacaoService.deposito(movimentacao, idConta);
	}
	
	//MÉTODO PARA FAZER SAQUE UTILIZANDO O ID DA CONTA
		@PostMapping("/saque/{idConta}")
		@ResponseStatus(HttpStatus.CREATED)
		public void saque(@RequestBody Extrato movimentacao, @PathVariable("idConta") Long idConta) {
			this.movimentacaoService.saque(movimentacao, idConta);
		}
	
	
	//MÉTODO PARA FAZER A TRANSFERENCIA ENTRE CONTAS
		@PostMapping("/transferencia/{idOrigem}/{idDestino}")
		@ResponseStatus(HttpStatus.CREATED)
		public void transferencia(@RequestBody Extrato movimentacao, @PathVariable("idOrigem") Long idOrigem, @PathVariable("idDestino") Long idDestino) {
			this.movimentacaoService.transferencia(movimentacao, idOrigem, idDestino);
		}
			
	//MÉTODO PARA VER O EXTRATO PELO ID DA CONTA
		@RequestMapping(method = RequestMethod.GET, value="/extrato/{idConta}")
		public ModelAndView extratoID (@PathVariable("idConta") Long idConta) {
			
			ModelAndView modelAndView = new ModelAndView("movimentacao/extrato");
			modelAndView.addObject("extratoobj", extratoRepository.listarExtrato(idConta));
			
			return modelAndView;
		}

}
