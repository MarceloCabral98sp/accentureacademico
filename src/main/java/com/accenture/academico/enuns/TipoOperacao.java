package com.accenture.academico.enuns;
/**
 * @author Vinycius
 */
public enum TipoOperacao {
	DEPOSITO(0, "Deposito"), 
	SAQUE(1, "Saque"), 
	TRANSFERENCIA_ENVIADA(2, "Transferencia_Enviada"),
	TRANSFERENCIA_RECEBIDA(3, "Transferencia_Recebida");
	
	public int codigo;
    public String descricao;
    

    private TipoOperacao(int codigo, String descricao) {
    	this.codigo = codigo;
        this.descricao = descricao;
	}
        
    

	
	

}
