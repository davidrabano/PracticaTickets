package main.java;

import java.util.Scanner;

// Los siguientes import son para usar el objeto de tipo Logger
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger; 

public class Vagon {
	
	// Hay que crear una instancia de tipo Logger en cada clase que queramos hacer un seguimiento de log
	private static Logger logger = LogManager.getLogger(Vagon.class);
	
	private int anchoVagon=4;
	private int largoVagon=10;
	char [][]vagon=new char[anchoVagon][largoVagon];
		
// M�todos de la clase Vagon
	public void iniciaVagon() {
		// Inicia todos los asientos del vag�n a la posici�n libre asignando una 'L'
		for (int i=0; i<anchoVagon; i++) {
			for (int j=0; j<largoVagon; j++) {
				vagon[i][j]='L';
			}
		}
		
		logger.info("Se ha iniciado un nuevo vag�n con todas las plazas libres 'L'");
	}
	
	public void mostrarEstadoVagon() {
		// Muestra por consola el estado de asientos libres 'L' y/o reservados 'R'
		System.out.println("Estado del vag�n (L=Libre, R=Reservado):");
		for (int i=0; i<anchoVagon; i++) {
			for (int j=0; j<largoVagon; j++) {
				System.out.print(" " + vagon[i][j]);
			}
			System.out.println("\n");
		}
		
		logger.info("Se ha mostrado el estado actual del vag�n");
	}
	
	public int pedirEnteroIntervalo(int min, int max) {
		// Solicita un entero por consola que est� en el intervalo [min, max]
		int entero;		
		Scanner entrada=new Scanner(System.in);
		
		int contador=0; // Para el log
		
		do {
			System.out.println("(entre " + min + " y " + max + "):");
			entero=entrada.nextInt();
			
			contador++;
			logger.info("Se ha introducido una respuesta por consola.");
			
		} while (entero<min || entero>max);
		
		if (contador>1) { // Se ha introducido mal por consola la respuesta 'contador' veces
			logger.warn("Se ha introducido una respuesta err�nea por consola " + (contador-1) + ((contador-1)==1 ? " vez." : " veces."));
		}
		
		//entrada.close();		
		return entero;
	}
	
	public int pedirPlazaFila() {
		// Solicita un entero por consola que est� en los l�mites del vag�n (0, anchoVagon)
		int fila;
		
		System.out.print("Introduce la fila ");
		fila=pedirEnteroIntervalo(0, anchoVagon-1);
		return fila;
	}
	
	public int pedirPlazaColumna() {
		// Solicita un entero por consola que est� en los l�mites del vag�n (0, largoVagon)
		int columna;
		
		System.out.print("Introduce la columna ");
		columna=pedirEnteroIntervalo(0, largoVagon-1);	
		return columna;
	}
	
	public void asignarPlaza(int fila, int columna) {
		// Reserva una plaza ('R') en la plaza (fila, columna)
		vagon[fila][columna]='R';
		
		logger.info("Se ha asignado la plaza [" + fila + ", " + columna + "].");
	}
	
	public boolean comprobarPlazaLibre(int fila, int columna) {
		// Devuelve true cuando la plaza (fila, columna) est� libre (estado 'L') y false en caso contrario.
		boolean resultado=false;
		
		if(vagon[fila][columna]=='L') {
			resultado=true;
			
			logger.info("La plaza [" + fila + ", " + columna + "] est� libre");
			
		}		
		return resultado;
	}	
	
	public boolean comprobarVagonLibre() {
		// Devuelve true cuando quedan plazas libres y false cuando el vag�n est� lleno
		boolean resultado=false;
		int i=0, j=0;
		
		while (j<largoVagon && !resultado) {
			while (i<anchoVagon && !resultado) {
				if (comprobarPlazaLibre(i,j)) {
					resultado=true;
				}
				i++;				
			}			
			i=0;
			j++;
		}
		
		logger.info("Se ha comprobado el estado del vag�n");
		
		return resultado;
	}
	
	public void asignarPrimeraPlazaLibre() {
		// Reserva la primera plaza libre
		boolean asignacion=false;
		int i=0, j=0;
		
		while (j<largoVagon && !asignacion) {
			while (i<anchoVagon && !asignacion) {
				if (comprobarPlazaLibre(i,j)) {
					vagon[i][j]='R';
					asignacion=true;
					System.out.println("Se le ha asignado el ticket (fila, columna)=(" + i + ", " + j + ").");
					
					logger.info("Se ha asignado la plaza [" + i + ", " + j + "] autom�ticamente.");
				}
				i++;				
			}			
			i=0;
			j++;
		}		
	}
	
} // fin class Vagon
