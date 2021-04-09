package com.accenture.academico.model;
/**
 * @author Vinycius
 */
import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ContaCorrente implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name = "conta_seq", sequenceName = "conta_seq", initialValue = 1, allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "conta_seq")
	@Column(name = "id")
	private Long idContaCorrente;
	
	@NotNull
	@Column(name = "NumContaCorrente")
	private int contaCorrenteNumero;
	
	@NotNull
	@Column(name = "saldo")
	private BigDecimal contaCorrenteSaldo;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name="cliente_id")
	private Cliente cliente;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name="agencia_id")
	private Agencia agencia;
	
	

	public Agencia getAgencia() {
		return agencia;
	}

	public void setAgencia(Agencia agencia) {
		this.agencia = agencia;
	}

	public Long getIdContaCorrente() {
		return idContaCorrente;
	}

	public void setIdContaCorrente(Long idContaCorrente) {
		this.idContaCorrente = idContaCorrente;
	}


	public int getContaCorrenteNumero() {
		return contaCorrenteNumero;
	}

	public void setContaCorrenteNumero(int contaCorrenteNumero) {
		this.contaCorrenteNumero = contaCorrenteNumero;
	}

	

	public BigDecimal getContaCorrenteSaldo() {
		return contaCorrenteSaldo;
	}

	public void setContaCorrenteSaldo(BigDecimal contaCorrenteSaldo) {
		this.contaCorrenteSaldo = contaCorrenteSaldo;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idContaCorrente == null) ? 0 : idContaCorrente.hashCode());
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
		ContaCorrente other = (ContaCorrente) obj;
		if (idContaCorrente == null) {
			if (other.idContaCorrente != null)
				return false;
		} else if (!idContaCorrente.equals(other.idContaCorrente))
			return false;
		return true;
	}
	
	
	
	
}
