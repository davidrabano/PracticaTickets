package main.java;

import java.util.Scanner;

// Los siguientes import son para usar el objeto de tipo Logger
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger; 

public class Vagon {

	private int anchoVagon=4;
	private int largoVagon=10;
	char [][]vagon=new char[anchoVagon][largoVagon];
	
	// Hay que crear una instancia de tipo Logger en cada clase que queramos hacer un seguimiento de log
	private static Logger logger = LogManager.getLogger(Vagon.class);
	
// Métodos de la clase Vagon
	public void iniciaVagon() {
		// Inicia todos los asientos del vagón a la posición libre asignando una 'L'
		for (int i=0; i<anchoVagon; i++) {
			for (int j=0; j<largoVagon; j++) {
				vagon[i][j]='L';
			}
		}
	}
	
	public void mostrarEstadoVagon() {
		// Muestra por consola el estado de asientos libres 'L' y/o reservados 'R'
		System.out.println("Estado del vagón (L=Libre, R=Reservado):");
		for (int i=0; i<anchoVagon; i++) {
			for (int j=0; j<largoVagon; j++) {
				System.out.print(" " + vagon[i][j]);
			}
			System.out.println("\n");
		}
	}
	
	public int pedirEnteroIntervalo(int min, int max) {
		// Solicita un entero por consola que esté en el intervalo [min, max]
		int entero;		
		Scanner entrada=new Scanner(System.in);
		
		do {
			System.out.println("(entre " + min + " y " + max + "):");
			entero=entrada.nextInt();
		} while (entero<min || entero>max);
		//entrada.close();		
		return entero;
	}
	
	public int pedirPlazaFila() {
		// Solicita un entero por consola que esté en los límites del vagón (0, anchoVagon)
		int fila;
		
		System.out.print("Introduce la fila ");
		fila=pedirEnteroIntervalo(0, anchoVagon-1);
		return fila;
	}
	
	public int pedirPlazaColumna() {
		// Solicita un entero por consola que esté en los límites del vagón (0, largoVagon)
		int columna;
		
		System.out.print("Introduce la columna ");
		columna=pedirEnteroIntervalo(0, largoVagon-1);	
		return columna;
	}
	
	public void asignarPlaza(int fila, int columna) {
		// Reserva una plaza ('R') en la plaza (fila, columna)
		vagon[fila][columna]='R';
	}
	
	public boolean comprobarPlazaLibre(int fila, int columna) {
		// Devuelve true cuando la plaza (fila, columna) está libre (estado 'L') y false en caso contrario.
		boolean resultado=false;
		
		if(vagon[fila][columna]=='L') {
			resultado=true;
		}		
		return resultado;
	}	
	
	public boolean comprobarVagonLibre() {
		// Devuelve true cuando quedan plazas libres y false cuando el vagón está lleno
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
				}
				i++;				
			}			
			i=0;
			j++;
		}		
	}
	
} // fin class Vagon
