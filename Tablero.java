/**
 * El 'Juego de la vida'
 *
 * Esta clase es responsable de leer el tablero de un fichero en forma
 * de ceros y unos, ir transitando de estados e ir mostrando dichos estados.
 *
 * @author Jesús Joana Azuara
 * @version 11.03.2021
 */

package dominio;
import java.io.*;
import java.util.*;

public class Tablero{

	private static int DIMENSION = 30; // Tamaño del array
	private final String NOMBREFICHERO = "./src/matriz";	// Archivo de 0 y 1 en binario
	private int[][] estadoActual = new int[DIMENSION][DIMENSION]; // Matriz que representa el estado actual
	private String representacionCelulas; // Variable endonde almacenamos el String de toString()
	private int[][] estadoSiguiente = new int[DIMENSION][DIMENSION]; // Matriz que representa el estado siguiente

	/**
	 * Lee el estado inicial de un fichero llamado 'matriz'.
	 */
	public void leerEstadoActual(){
	//La secuencia de ceros y unos del fichero es guardada en 'estadoActual[][]' y, utilizando las reglas
	//del juego de la vida, se insertan los ceros y unos corresponientes en 'estadoSiguiente[][]'.

		try{
			FileInputStream fis = new FileInputStream (NOMBREFICHERO);
			ObjectInputStream ois = new ObjectInputStream (fis);
			int i = 0;
			while(i<DIMENSION){
				int j = 0;
				while(j<DIMENSION){
					if (ois.readBoolean()==true){
						estadoActual[i][j]=1;
					}
					else{
						estadoActual[i][j]=0;
					}
					j++;
				}
			i++;
		}
		ois.close();
		fis.close();
		}catch (FileNotFoundException e){
			System.out.println("No ha sido posible acceder a la información");
		}catch (IOException e){
			System.out.println("No ha sido posible acceder a la información");
		}
	}

	/**
	 * Genera un estado inicial aleatorio, para cada delda genera un número aleatorio en el intervalo [0,1)
	 * Si el número es es menor que 0.5, entonces la célula esta inicialmente viva En caso contrario
	 * esta muerta.
	 */
	public void generarEstadoActualPorMontecarlo(){
	//La secuencia de ceros y unos generadas es guardada en 'estadoActual[][]' y, utilizando las reglas
	//del juego de la vida, se insertan ceros y unos corresponientes en 'estadoSiguiente[][].

		double numeroAleatorio;
		for(int i=0; i<DIMENSION; i++){
			for(int j=0; j<DIMENSION; j++){
				numeroAleatorio = Math.random();
				if (numeroAleatorio<0.5){
					estadoActual[i][j]=0;
				}else{
					estadoActual[i][j]=1;
				}
			}
		}
			
		try{
			FileOutputStream fos = new FileOutputStream (NOMBREFICHERO);
			ObjectOutputStream oos = new ObjectOutputStream (fos);
			int i = 0;
			while(i<DIMENSION){
				int j = 0;
				while(j<DIMENSION){
					if(estadoActual[i][j]==0){
						oos.writeBoolean(false);
					}else{
						oos.writeBoolean(true);
					}
					j++;
				}
				oos.writeByte(13);
				oos.writeByte(10);
				i++;
			}
			oos.close();
			fos.close();
		}catch (FileNotFoundException e){
			System.out.println("No ha sido posible acceder a la información");
		}catch (IOException e){
			System.out.println("No ha sido posible acceder a la información");
		}
	}

	/**
	 * Transita al estado siguiente según las reglas del juego de la vida
	 */
	public void transitarAlEstadoSiguiente(){
	//La variable 'estadoActual[][]' pasa a tener el contenido de 'estadoSiguiente[][]' y, este
	//último atributo pasa a reflejar el estado siguiente.
		int celdaEstadoActual;
		int celdaEstadoSiguiente;
		int cuentaCelulasVivas;

		// Compruebo la celda (0,0) ----------------------------------------------------------------------
		cuentaCelulasVivas=0;
		if(estadoActual[0][1]==1){
			cuentaCelulasVivas++;
		}
		if(estadoActual[1][1]==1){
			cuentaCelulasVivas++;
		}
		if(estadoActual[1][0]==1){
			cuentaCelulasVivas++;
		}
		if(estadoActual[0][0] == 1 && (cuentaCelulasVivas == 2 || cuentaCelulasVivas == 3)){
			estadoSiguiente[0][0]=1;
		}else if(estadoActual[0][0] == 0 && cuentaCelulasVivas == 3){
			estadoSiguiente[0][0]=1;
		}else{
			estadoSiguiente[0][0]=0;	
		}

		// Compruebo la celda (0,29) ------------------------------------------------------------------
		cuentaCelulasVivas=0;
		if(estadoActual[0][DIMENSION-2]==1){
			cuentaCelulasVivas++;
		}
		if(estadoActual[1][DIMENSION-2]==1){
			cuentaCelulasVivas++;
		}
		if(estadoActual[1][DIMENSION-1]==1){
			cuentaCelulasVivas++;
		}
		if(estadoActual[0][DIMENSION-1] == 1 && (cuentaCelulasVivas ==2 || cuentaCelulasVivas == 3)){
			estadoSiguiente[0][DIMENSION-1]=1;
		}else if(estadoActual[0][DIMENSION-1] == 0 && cuentaCelulasVivas == 3){
			estadoSiguiente[0][DIMENSION-1]=1;
		}else{
			estadoSiguiente[0][DIMENSION-1]=0;	
		}

		// Compruebo la celda (29,29) -----------------------------------------------------------------
		cuentaCelulasVivas=0;
		if(estadoActual[DIMENSION-1][DIMENSION-2]==1){
			cuentaCelulasVivas++;
		}
		if(estadoActual[DIMENSION-2][DIMENSION-2]==1){
			cuentaCelulasVivas++;
		}
		if(estadoActual[DIMENSION-2][DIMENSION-1]==1){
			cuentaCelulasVivas++;
		}
		if(estadoActual[DIMENSION-1][DIMENSION-1] == 1 && (cuentaCelulasVivas ==2 || cuentaCelulasVivas == 3)){
			estadoSiguiente[DIMENSION-1][DIMENSION-1]=1;
		}else	if(estadoActual[DIMENSION-1][DIMENSION-1] == 0 && cuentaCelulasVivas == 3){
			estadoSiguiente[DIMENSION-1][DIMENSION-1]=1;
		}else{
			estadoSiguiente[DIMENSION-1][DIMENSION-1]=0;	
		}

		// Compruebo la celda (29,0) -----------------------------------------------------------------
		cuentaCelulasVivas=0;
		if(estadoActual[DIMENSION-2][0]==1){
			cuentaCelulasVivas++;
		}
		if(estadoActual[DIMENSION-2][1]==1){
			cuentaCelulasVivas++;
		}
		if(estadoActual[DIMENSION-1][1]==1){
			cuentaCelulasVivas++;
		}
		if(estadoActual[DIMENSION-1][0] == 1 && (cuentaCelulasVivas ==2 || cuentaCelulasVivas == 3)){
			estadoSiguiente[DIMENSION-1][0]=1;
		}else if(estadoActual[DIMENSION-1][0] == 0 && cuentaCelulasVivas == 3){
			estadoSiguiente[DIMENSION-1][0]=1;
		}else{
			estadoSiguiente[DIMENSION-1][0]=0;	
		}

		// Compruebo primera fila. Las comprobaciones (i-1) no se realizan ------------------------
		for(int j=1; j<(DIMENSION-1); j++){
			cuentaCelulasVivas=0;
			if(estadoActual[0][j-1]==1){
				cuentaCelulasVivas++;
			}
			if(estadoActual[0][j+1]==1){
				cuentaCelulasVivas++;
			}
			if(estadoActual[1][j-1]==1){
				cuentaCelulasVivas++;
			}
			if(estadoActual[1][j]==1){
				cuentaCelulasVivas++;
			}
			if(estadoActual[1][j+1]==1){
				cuentaCelulasVivas++;
			}
			if(estadoActual[0][j] ==1 && (cuentaCelulasVivas ==2 || cuentaCelulasVivas == 3)){
				estadoSiguiente[0][j]=1;
			}else if (estadoActual[0][j] == 0 && cuentaCelulasVivas == 3){
				estadoSiguiente[0][j]=1;	
			}else{
				estadoSiguiente[0][j]=0;
			}
		}

		// Compruebo ultima fila. Las comprobaciones (i+1) no se realizan -----------------------
		for(int j=1; j<(DIMENSION-1); j++){
			cuentaCelulasVivas=0;
				if(estadoActual[DIMENSION-2][j-1]==1){
				cuentaCelulasVivas++;
			}
			if(estadoActual[DIMENSION-2][j]==1){
				cuentaCelulasVivas++;
			}
			if(estadoActual[DIMENSION-2][j+1]==1){
				cuentaCelulasVivas++;
			}
			if(estadoActual[DIMENSION-1][j-1]==1){
				cuentaCelulasVivas++;
			}
			if(estadoActual[DIMENSION-1][j+1]==1){
				cuentaCelulasVivas++;
			}
			if(estadoActual[DIMENSION-1][j] == 1 && (cuentaCelulasVivas ==2 || cuentaCelulasVivas == 3)){
				estadoSiguiente[DIMENSION-1][j]=1;
			}else if (estadoActual[DIMENSION-1][j] == 0 && cuentaCelulasVivas == 3){
				estadoSiguiente[DIMENSION-1][j]=1;
			}else{
				estadoSiguiente[DIMENSION-1][j]=0;
			}
		}

		// Compruebo primera columna. Las comprobaciones (j-1) no se realizan ----------------
		for(int i=1; i<(DIMENSION-1); i++){
			cuentaCelulasVivas=0;
			if(estadoActual[i-1][0]==1){
				cuentaCelulasVivas++;
			}
			if(estadoActual[i-1][1]==1){
				cuentaCelulasVivas++;
			}
			if(estadoActual[i][1]==1){
				cuentaCelulasVivas++;
			}
			if(estadoActual[i+1][0]==1){
				cuentaCelulasVivas++;
			}
			if(estadoActual[i+1][1]==1){
				cuentaCelulasVivas++;
			}
			if(estadoActual[i][0] == 1 && (cuentaCelulasVivas ==2 || cuentaCelulasVivas == 3)){
				estadoSiguiente[i][0]=1;
			}else if (estadoActual[i][0] ==0 && cuentaCelulasVivas == 3){
				estadoSiguiente[i][0] = 1;
			}else{
				estadoSiguiente[i][0]=0;
			}
		}

		// Compruebo última columna. Las comprobaciones (j+1) no se realizan ------------------
		for(int i=1; i<(DIMENSION-1); i++){
			cuentaCelulasVivas=0;
			if(estadoActual[i-1][DIMENSION-2]==1){
				cuentaCelulasVivas++;
			}
			if(estadoActual[i-1][DIMENSION-1]==1){
				cuentaCelulasVivas++;
			}
			if(estadoActual[i][DIMENSION-2]==1){
				cuentaCelulasVivas++;
			}
			if(estadoActual[i+1][DIMENSION-2]==1){
				cuentaCelulasVivas++;
			}
			if(estadoActual[i+1][DIMENSION-1]==1){
				cuentaCelulasVivas++;
			}
			if(estadoActual[i][DIMENSION-1] == 1 && (cuentaCelulasVivas ==2 || cuentaCelulasVivas == 3)){
				estadoSiguiente[i][DIMENSION-1]=1;
			}else if (estadoActual[i][DIMENSION-1] == 0 && cuentaCelulasVivas == 3){
				estadoSiguiente[i][DIMENSION-1]=1;
			}else{
				estadoSiguiente[i][DIMENSION-1]=0;
			}
		}

		// Bucle para comprobar todas las celdas que no están en el contorno ------------------
		for(int i=1; i<(DIMENSION-1); i++){
			for(int j=1; j<(DIMENSION-1); j++){
				cuentaCelulasVivas=0;
				if(estadoActual[i-1][j-1]==1){
					cuentaCelulasVivas++;
				}
				if(estadoActual[i-1][j]==1){
					cuentaCelulasVivas++;
				}
				if(estadoActual[i-1][j+1]==1){
					cuentaCelulasVivas++;
				}
				if(estadoActual[i][j-1]==1){
					cuentaCelulasVivas++;
				}
				if(estadoActual[i][j+1]==1){
					cuentaCelulasVivas++;
				}
				if(estadoActual[i+1][j-1]==1){
					cuentaCelulasVivas++;
				}
				if(estadoActual[i+1][j]==1){
					cuentaCelulasVivas++;
				}
				if(estadoActual[i+1][j+1]==1){
					cuentaCelulasVivas++;
				}
				if(estadoActual[i][j] == 1 && (cuentaCelulasVivas ==2 || cuentaCelulasVivas == 3)){
					estadoSiguiente[i][j]=1;
				}else if (estadoActual[i][j] == 0 && cuentaCelulasVivas == 3){
					estadoSiguiente[i][j]=0;
				}else{
					estadoSiguiente[i][j]=0;
				}
			}
		}

		// El array estadoActual[][] se actualiza con los valores del estadoSiguiente[][].
		for (int i=0; i<DIMENSION; i++){
			for (int j=0; j<DIMENSION; j++){
				estadoActual[i][j]=estadoSiguiente[i][j];
			}
		}
	}
	
	/** Devuelve, en modo texto, el estadoActual[][]
	 * @return el estado actual.
	 */
	public String toString(){
		int i=0;
		representacionCelulas="";
		while(i<DIMENSION){
			int j=0;
			while(j<DIMENSION){
				if (estadoActual[i][j]==1){
					representacionCelulas = representacionCelulas.concat("X"); 
				}
				else{
					representacionCelulas = representacionCelulas.concat("0");
				}
				j++;
			}
			representacionCelulas = representacionCelulas.concat("\n");
			i++;
		}
		return representacionCelulas;
	}
}
