package main.java;

import java.net.URL;

//Los siguientes import son para usar el objeto de tipo Logger
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/* Enunciado: Subir un documento llamado tiempo.txt donde indiques cuanto es el tiempo, aunque sea aproximado, que has tardado en este ejercicio
 * Subir el proyecto que hayas realizado para esta actividad.
 * Recuerda incluir, si lo consideras, una clase que nos funcione de "librería" para hacer cosas propias del menú.
 * 
 * Componentes:
 * Vagon: es una matriz de caracteres de tamaño anchoVagon x largoVagon (4x10) que se inicializa a valores libre 'L'
 * 
 * Funcionalidad:
 * Permite al usuario, a través de la consola, a realizar la reserva de una plaza del vagón mediante su fila y su columna.
 * Puede ser por asignación de la primera plaza libre o una de su elección. En cada caso, muestra el estado de reservas del vagón y la plaza reservada. Si el vagón está lleno, no permite hacer más reservas.
 * 
 * Dudas: ¿Es necesario cerrar los objetos de tipo Scanner? ¿Cuándo y cómo?
 * ¿A qué se refiere con la clase "librería" para cosas propias del menú?
*/

public class SistemaReservaTickets {
	
	// Hay que crear una instancia de tipo Logger en cada clase que queramos hacer un seguimiento de log
	private static Logger logger = LogManager.getLogger(SistemaReservaTickets.class);

	public static void main(String[] args) {
		
		/* Esto solo en el main y una vez por proyecto */
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		URL url = loader.getResource("log4j.properties");
		PropertyConfigurator.configure(url);
		/**/
		
		Vagon wagon=new Vagon();
		MenuTickets menu=new MenuTickets();
		int fila, columna;
		boolean salir=false;

		System.out.println("Bienvenido al Sistema de Reserva de Tickets.");
		wagon.iniciaVagon();
		
		/*
		wagon.vagon[0][0]='R';
		wagon.vagon[1][0]='R';
		wagon.vagon[2][0]='R';
		wagon.vagon[3][9]='L';
		*/
		
		wagon.mostrarEstadoVagon();
		System.out.println("¿Desea reservar una plaza?");
		
		if (menu.pideRespuestaSiNo().equals("no")) { // Sale del programa
			System.out.println("Gracias por utilizar nuestro servicio.");
		}
		else { // menu.pideRespuestaSiNo()=="si"
			
			while (!salir && wagon.comprobarVagonLibre()) {
			
				System.out.println("¿Desea una posición concreta?");
				if (menu.pideRespuestaSiNo().equals("no")) { // Se asigna al usuario la primera plaza libre
					wagon.asignarPrimeraPlazaLibre();	
					wagon.mostrarEstadoVagon();					
				}
				else { // menu.pideRespuestaSiNo()=="si". Se permite al usuario elegir plaza
					System.out.println("Va a elegir plaza:");
					fila=wagon.pedirPlazaFila();
					columna=wagon.pedirPlazaColumna();			
					
					if (wagon.comprobarPlazaLibre(fila, columna)) {
						wagon.asignarPlaza(fila, columna);
						System.out.println("Se le ha asignado el ticket (fila, columna)=(" + fila + ", " + columna + ").");
						wagon.mostrarEstadoVagon();
					}
					else {
						System.out.println("Lo siento, la plaza ya está reservada.");
						wagon.mostrarEstadoVagon();
					}
					
				} // fin else (sí desea elegir plaza)				
			
				System.out.println("¿Desea reservar otra plaza?");
				if (menu.pideRespuestaSiNo().equals("no")) {
					salir=true;
					System.out.println("Gracias por utilizar nuestro servicio.");
				}				
				
			} // fin while
			
			if (!wagon.comprobarVagonLibre()) {
				System.out.println("Lo siento, el vagón está lleno y no se le puede reservar ticket.");
			}
			
		} // fin else (sí desea reservar ticket)
		
	} // fin main

} // fin class SistemaReservaTickets
