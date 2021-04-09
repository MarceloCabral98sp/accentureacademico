package com.accenture.academico.model;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotEmpty;
/**
 * @author Vinycius
 */



@Entity
public class Agencia implements Serializable{
	
	
	private static final long serialVersionUID = 1L;


	@Id
	@SequenceGenerator(name = "agencia_seq", sequenceName = "agencia_seq", initialValue = 1, allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "agencia_seq")
	private Long idAgencia;
	
	@NotNull
	@Column(name = "descricao")
	private String nomeAgencia;
	
	@NotNull
	@Column(name = "endereco")
	private String enderecoAgencia;
	
	@NotNull
	@Column(name = "telefone")
	private String telefoneAgencia;	
	
	@NotNull
	@OneToMany(mappedBy = "agencia")
	private List<ContaCorrente> ContaCorrente = new ArrayList<ContaCorrente>();
		

	public String getTelefoneAgencia() {
		return telefoneAgencia;
	}

	public void setTelefoneAgencia(String telefoneAgencia) {
		this.telefoneAgencia = telefoneAgencia;
	}

	public Long getIdAgencia() {
		return idAgencia;
	}

	public void setIdAgencia(Long idAgencia) {
		this.idAgencia = idAgencia;
	}

	public String getNomeAgencia() {
		return nomeAgencia;
	}

	public void setNomeAgencia(String nomeAgencia) {
		this.nomeAgencia = nomeAgencia;
	}


	public String getEnderecoAgencia() {
		return enderecoAgencia;
	}

	public void setEnderecoAgencia(String enderecoAgencia) {
		this.enderecoAgencia = enderecoAgencia;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idAgencia == null) ? 0 : idAgencia.hashCode());
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
		Agencia other = (Agencia) obj;
		if (idAgencia == null) {
			if (other.idAgencia != null)
				return false;
		} else if (!idAgencia.equals(other.idAgencia))
			return false;
		return true;
	}
	
		
	

}
