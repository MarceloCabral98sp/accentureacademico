package com.accenture.academico.model;
/**
 * @author Vinycius
 */
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;

import com.accenture.academico.enuns.TipoOperacao;

@Entity
public class Extrato implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name = "extrato_seq", sequenceName = "extrato_seq", initialValue = 1, allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "extrato_seq")
	@Column(name = "id_movimentacao")
	private Long idMovimentacao;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "id_conta")
	private ContaCorrente contaCorrente;
	
	@NotNull
	@Column(name = "valor_movimentado")
	private BigDecimal valor;
	
	@NotNull
	@Column(name = "data_movimentacao")
	private String dataMovimentacao;
	
	@NotNull
	@Column(name = "hora_movimentacao")
	private String horaMovimentacao;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "tipo_operacao")
	private TipoOperacao tipoOperacao;
	
	


	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Long getIdMovimentacao() {
		return idMovimentacao;
	}

	public void setIdMovimentacao(Long idMovimentacao) {
		this.idMovimentacao = idMovimentacao;
	}

	public ContaCorrente getContaCorrente() {
		return contaCorrente;
	}

	public void setContaCorrente(ContaCorrente contaCorrente) {
		this.contaCorrente = contaCorrente;
	}

	

	public String getDataMovimentacao() {
		return dataMovimentacao;
	}

	public void setDataMovimentacao(String dataMovimentacao) {
		this.dataMovimentacao = dataMovimentacao;
	}

	public String getHoraMovimentacao() {
		return horaMovimentacao;
	}

	public void setHoraMovimentacao(String horaMovimentacao) {
		this.horaMovimentacao = horaMovimentacao;
	}

	public TipoOperacao getTipoOperacao() {
		return tipoOperacao;
	}

	public void setTipoOperacao(TipoOperacao tipoOperacao) {
		this.tipoOperacao = tipoOperacao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idMovimentacao == null) ? 0 : idMovimentacao.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Extrato other = (Extrato) obj;
		if (idMovimentacao == null) {
			if (other.idMovimentacao != null)
				return false;
		} else if (!idMovimentacao.equals(other.idMovimentacao))
			return false;
		return true;
	}
	
	

}
