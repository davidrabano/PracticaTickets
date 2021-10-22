package main.java;

import java.net.URL;

//Los siguientes import son para usar el objeto de tipo Logger
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/*
# Root logger option
# Level/rules TRACE < DEBUG < INFO < WARN < ERROR < FATAL.
# FATAL: shows messages at a FATAL level only
# ERROR: Shows messages classified as ERROR and FATAL
# WARNING: Shows messages classified as WARNING, ERROR, and FATAL
# INFO: Shows messages classified as INFO, WARNING, ERROR, and FATAL
# DEBUG: Shows messages classified as DEBUG, INFO, WARNING, ERROR, and FATAL
# TRACE: Shows messages classified as TRACE, DEBUG, INFO, WARNING, ERROR, and FATAL
# ALL: Shows messages classified as TRACE, DEBUG, INFO, WARNING, ERROR, and FATAL
# OFF: No log messages display

Tipos de logs que se pueden crear:

logger.fatal(Object message)
logger.error(Object message)
logger.warn(Object message) // Ejemplo: logger.warn("Esto es un aviso");
logger.info(Object message) // Ejemplo (a�adiendo URL) logger.info("Este es el fichero de configuraci�n: " + url);
logger.debug(Object message) // Ejemplo: logger.debug("Esto es un debug");
		
*/

/* Enunciado: Subir un documento llamado tiempo.txt donde indiques cuanto es el tiempo, aunque sea aproximado, que has tardado en este ejercicio
 * Subir el proyecto que hayas realizado para esta actividad.
 * Recuerda incluir, si lo consideras, una clase que nos funcione de "librer�a" para hacer cosas propias del men�.
 * 
 * Componentes:
 * Vagon: es una matriz de caracteres de tama�o anchoVagon x largoVagon (4x10) que se inicializa a valores libre 'L'
 * 
 * Funcionalidad:
 * Permite al usuario, a trav�s de la consola, a realizar la reserva de una plaza del vag�n mediante su fila y su columna.
 * Puede ser por asignaci�n de la primera plaza libre o una de su elecci�n. En cada caso, muestra el estado de reservas del vag�n y la plaza reservada. Si el vag�n est� lleno, no permite hacer m�s reservas.
 * 
 * Dudas: �Es necesario cerrar los objetos de tipo Scanner? �Cu�ndo y c�mo?
 * �A qu� se refiere con la clase "librer�a" para cosas propias del men�?
*/

public class SistemaReservaTickets {
	
	// Hay que crear una instancia de tipo Logger en cada clase que queramos hacer un seguimiento de log
	private static Logger logger = LogManager.getLogger(SistemaReservaTickets.class);
	//private static Logger logger = LogManager.getLogger(NOMBRE_CLASE.class);

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
		
		logger.info("Se inicia la aplicaci�n.");
		
		wagon.iniciaVagon();
		
		/*
		wagon.vagon[0][0]='R';
		wagon.vagon[1][0]='R';
		wagon.vagon[2][0]='R';
		wagon.vagon[3][9]='L';
		*/
		
		wagon.mostrarEstadoVagon();
		System.out.println("�Desea reservar una plaza?");
		
		if (menu.pideRespuestaSiNo().equals("no")) { // Sale del programa
			System.out.println("Gracias por utilizar nuestro servicio.");
			
			logger.info("Se sale de la aplicaci�n.");
		}
		else { // menu.pideRespuestaSiNo()=="si"
			
			while (!salir && wagon.comprobarVagonLibre()) {
			
				System.out.println("�Desea una posici�n concreta?");
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
						System.out.println("Lo siento, la plaza ya est� reservada.");
						wagon.mostrarEstadoVagon();
					}
					
				} // fin else (s� desea elegir plaza)				
			
				System.out.println("�Desea reservar otra plaza?");
				if (menu.pideRespuestaSiNo().equals("no")) {
					salir=true;
					System.out.println("Gracias por utilizar nuestro servicio.");
					
					logger.info("Se sale de la aplicaci�n.");
				}				
				
			} // fin while
			
			if (!wagon.comprobarVagonLibre()) {
				System.out.println("Lo siento, el vag�n est� lleno y no se le puede reservar ticket.");
				
				logger.error("No se pueden reservar m�s plazas. Vag�n completo. Iniciar un nuevo vag�n.");
			}
			
		} // fin else (s� desea reservar ticket)
		
	} // fin main

} // fin class SistemaReservaTickets
