
package dominio;
import java.io.*;
import java.util.*;

/**
 * Esta clase es responsable de leer el tablero de un fichero en forma
 * de ceros y unos, ir transitando de estados e ir mostrando dichos
 * estados.
 */

public class Tablero{

	private static int DIMENSION = 30;
	private final String NOMBREFICHERO = "./src/matriz";
	// Matriz que representa el estado actual
	private int[][] estadoActual = new int[DIMENSION][DIMENSION];
	private String representacionCelulas;
	// Matriz que representa el estado siguiente
	private int[][] estadoSiguiente = new int[DIMENSION][DIMENSION];

	/**
	 * Método utilizado para leer el estao inicial desde un fichero
	 * llamado 'matriz'.
	 */

	public void leerEstadoActual(){ // ----------------------------------Método que lee el archivo con el estado inicial --------
		try{
			FileInputStream fis = new FileInputStream (NOMBREFICHERO);
			ObjectInputStream ois = new ObjectInputStream (fis);
			int i = 0;
			while(i<30){
				int j = 0;
				while(j<30){
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

	public void generarEstadoActualPorMontecarlo(){ // ------------------Método que genera el archivo con el estado inicial ------
		double numeroAleatorio;
		for(int i=0; i<30; i++){
			for(int j=0; j<30; j++){
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
			oos.flush();
			int i = 0;
			while(i<30){
				int j = 0;
				while(j<30){
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

	public void transitarAlEstadoSiguiente(){ //------------------------Método que realiza la transición de estados--------------
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

		// Compruebo la celda (0,29) ----------------------------------------------------------------------
		cuentaCelulasVivas=0;
		if(estadoActual[0][28]==1){
			cuentaCelulasVivas++;
		}
		if(estadoActual[1][28]==1){
			cuentaCelulasVivas++;
		}
		if(estadoActual[1][29]==1){
			cuentaCelulasVivas++;
		}
		if(estadoActual[0][29] == 1 && (cuentaCelulasVivas ==2 || cuentaCelulasVivas == 3)){
			estadoSiguiente[0][29]=1;
		}else if(estadoActual[0][29] == 0 && cuentaCelulasVivas == 3){
			estadoSiguiente[0][29]=1;
		}else{
			estadoSiguiente[0][29]=0;	
		}

		// Compruebo la celda (29,29) ----------------------------------------------------------------------
		cuentaCelulasVivas=0;
		if(estadoActual[29][28]==1){
			cuentaCelulasVivas++;
		}
		if(estadoActual[28][28]==1){
			cuentaCelulasVivas++;
		}
		if(estadoActual[28][29]==1){
			cuentaCelulasVivas++;
		}
		if(estadoActual[29][29] == 1 && (cuentaCelulasVivas ==2 || cuentaCelulasVivas == 3)){
			estadoSiguiente[29][29]=1;
		}else	if(estadoActual[29][29] == 0 && cuentaCelulasVivas == 3){
			estadoSiguiente[29][29]=1;
		}else{
			estadoSiguiente[29][29]=0;	
		}

		// Compruebo la celda (29,0) -----------------------------------------------------------------------------
		cuentaCelulasVivas=0;
		if(estadoActual[28][0]==1){
			cuentaCelulasVivas++;
		}
		if(estadoActual[28][1]==1){
			cuentaCelulasVivas++;
		}
		if(estadoActual[29][1]==1){
			cuentaCelulasVivas++;
		}
		if(estadoActual[29][0] == 1 && (cuentaCelulasVivas ==2 || cuentaCelulasVivas == 3)){
			estadoSiguiente[29][0]=1;
		}else if(estadoActual[29][0] == 0 && cuentaCelulasVivas == 3){
			estadoSiguiente[29][0]=1;
		}else{
			estadoSiguiente[29][0]=0;	
		}

		// Compruebo primera fila. Las comprobaciones (i-1) no se realizan -----------------------------------------
		for(int j=1; j<29; j++){
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

		// Compruebo ultima fila. Las comprobaciones (i+1) no se realizan --------------------------------------------------
		for(int j=1; j<29; j++){
			cuentaCelulasVivas=0;
				if(estadoActual[28][j-1]==1){
				cuentaCelulasVivas++;
			}
			if(estadoActual[28][j]==1){
				cuentaCelulasVivas++;
			}
			if(estadoActual[28][j+1]==1){
				cuentaCelulasVivas++;
			}
			if(estadoActual[29][j-1]==1){
				cuentaCelulasVivas++;
			}
			if(estadoActual[29][j+1]==1){
				cuentaCelulasVivas++;
			}
			if(estadoActual[29][j] == 1 && (cuentaCelulasVivas ==2 || cuentaCelulasVivas == 3)){
				estadoSiguiente[29][j]=1;
			}else if (estadoActual[29][j] == 0 && cuentaCelulasVivas == 3){
				estadoSiguiente[29][j]=1;
			}else{
				estadoSiguiente[29][j]=0;
			}
		}

		// Compruebo primera columna. Las comprobaciones (j-1) no se realizan -----------------------------------------------
		for(int i=1; i<29; i++){
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

		// Compruebo última columna. Las comprobaciones (j+1) no se realizan ----------------------------------------------
		for(int i=1; i<29; i++){
			cuentaCelulasVivas=0;
			if(estadoActual[i-1][28]==1){
				cuentaCelulasVivas++;
			}
			if(estadoActual[i-1][29]==1){
				cuentaCelulasVivas++;
			}
			if(estadoActual[i][28]==1){
				cuentaCelulasVivas++;
			}
			if(estadoActual[i+1][28]==1){
				cuentaCelulasVivas++;
			}
			if(estadoActual[i+1][29]==1){
				cuentaCelulasVivas++;
			}
			if(estadoActual[i][29] == 1 && (cuentaCelulasVivas ==2 || cuentaCelulasVivas == 3)){
				estadoSiguiente[i][29]=1;
			}else if (estadoActual[i][29] == 0 && cuentaCelulasVivas == 3){
				estadoSiguiente[i][29]=1;
			}else{
				estadoSiguiente[i][29]=0;
			}
		}

		// Bucle para comprobar todas las celdas que no están en el contorno -----------------------------------------
		for(int i=1; i<29; i++){
			for(int j=1; j<29; j++){
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

		// En el siguiente bucle hacemos que el array estadoActual se actualice con los valores del estadoSiguiente.
		for (int i=0; i<30; i++){
			for (int j=0; j<30; j++){
				estadoActual[i][j]=estadoSiguiente[i][j];
			}
		}
	}
	
// Método para mostrar las células en pantalla
	public String toString(Tablero tablero){
		int i=0;
		while(i<30){
			int j=0;
			representacionCelulas="";
			while(j<30){
				if (estadoActual[i][j]==1){
					representacionCelulas.concat("X"); 
				}
				else{
					representacionCelulas.concat("0");
				}
				j++;
			}
			representacionCelulas.concat("/n");
			i++;
		}
		return representacionCelulas;
	}
}
