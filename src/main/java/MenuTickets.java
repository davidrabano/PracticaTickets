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
		
		do {
			System.out.println("si/no");
			respuesta=entry.nextLine();
		} while (!respuesta.equalsIgnoreCase("si") && !respuesta.equalsIgnoreCase("no"));
		//entrada.close();
		return respuesta.toLowerCase();
	}	

}
