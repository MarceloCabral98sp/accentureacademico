package com.accenture.academico.util;
/**
 * @author Vinycius
 */
import java.text.SimpleDateFormat;
import java.util.Date;


public class DataHoraUtil {
	
	static Date dataHoraAtual = new Date();
	
	
	
	public String retornarData() {
		String data = new SimpleDateFormat("dd/MM/yyyy").format(dataHoraAtual);
		return data;
		
	}
	
	public String retornarHora() {
		String hora = new SimpleDateFormat("HH:mm:ss").format(dataHoraAtual);
		return hora;
		
	}

}
