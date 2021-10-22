package main.java;

import java.util.Scanner;

//Los siguientes import son para usar el objeto de tipo Logger
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class MenuTickets {
	
	// Hay que crear una instancia de tipo Logger en cada clase que queramos hacer un seguimiento de log
	private static Logger logger = LogManager.getLogger(MenuTickets.class);
	
	public String pideRespuestaSiNo() {
		// Pide por consola una respuesta de si/no, aceptando cualquier combinación con minúsculas y mayúsculas. Devuelve un string en minúsculas
		String respuesta;
		Scanner entry=new Scanner(System.in);
		
		int contador=0; // Para el log
		
		do {
			System.out.println("si/no");
			respuesta=entry.nextLine();
			
			contador++;
			logger.info("Se ha introducido una respuesta por consola.");
			
		} while (!respuesta.equalsIgnoreCase("si") && !respuesta.equalsIgnoreCase("no"));
		
		if (contador>1) { // Se ha introducido mal por consola la respuesta 'contador' veces
			logger.warn("Se ha introducido una respuesta errónea por consola " + (contador-1) + ((contador-1)==1 ? " vez." : " veces."));
		}
		
		//entrada.close();
		return respuesta.toLowerCase();
	}	

}
