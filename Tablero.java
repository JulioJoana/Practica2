
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
	// Matriz que representa el estado siguiente
	private int[][] estadoSiguiente = new int[DIMENSION][DIMENSION];

	/**
	 * Método utilizado para leer el estao inicial desde un fichero
	 * llamado 'matriz'.
	 */
	public void leerEstadoActual(){
		try{
			FileInputStream fis = new FileInputStream (NOMBREFICHERO);
			ObjectInputStream ois = new ObjectInputStream (fis);
			int i = 0;
			while(i<30){
				int j = 0;
				while(j<30){
					if (ois.readBoolean()==true){
						estadoActual[i][j]=1;
						//System.out.print("Elemento " + j + "leido, valor: " + estadoActual[i][j]);
						System.out.print("X");
					}
					else{
						estadoActual[i][j]=0;
						//System.out.print("Elemento " + j + "leido, valor:  " + estadoActual[i][j]);
						System.out.print("0");
					}
					j++;
				}
			//System.out.println("fila " + i + " leida");
			System.out.println();
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


	public void generarEstadoActualPorMontecarlo(){
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
					//System.out.print("Elemento " + j + "escrito ---");
					j++;
				}
				oos.writeByte(13);
				oos.writeByte(10);
				//System.out.println("Fila " + i + " escrita");
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

	public void transitarAlEstadoSiguiente(){

		int celdaEstadoActual;
		int celdaEstadoSiguiente;
		int cuentaCelulasVivas=0;

		// Compruebo la celda (0,0) ----------------------------------------------------------------------
		cuentaCelulasVivas=0;
		if(estadoActual[0][0]==1){
			if(estadoActual[0][1]==1){
				cuentaCelulasVivas++;
			}
			if(estadoActual[1][1]==1){
				cuentaCelulasVivas++;
			}
			if(estadoActual[1][0]==1){
				cuentaCelulasVivas++;
			}
			if(cuentaCelulasVivas ==2 || cuentaCelulasVivas == 3){
				estadoSiguiente[0][0]=1;
			}else{
				estadoSiguiente[0][0]=0;	
			}
			System.out.print(" Celula: (0,0)" + " Nº de celulas vivas alrededor: " + cuentaCelulasVivas);
			System.out.print(" Estado actual = 1");
			System.out.println(" Estado siguiente = " + estadoSiguiente[0][0]);
		}
		if(estadoActual[0][0]==0){
			if(estadoActual[0][1]==1){
				cuentaCelulasVivas++;
			}
			if(estadoActual[1][1]==1){
				cuentaCelulasVivas++;
			}
			if(estadoActual[1][0]==1){
				cuentaCelulasVivas++;
			}
			if(cuentaCelulasVivas == 3){
				estadoSiguiente[0][0]=1;
			}else{
				estadoSiguiente[0][0]=0;	
			}
			System.out.print(" Celula: (0,0)" + " Nº de celulas vivas alrededor: " + cuentaCelulasVivas);
			System.out.print(" Estado actual = 0");
			System.out.println(" Estado siguiente = " + estadoSiguiente[0][0]);
		}

		// Compruebo la celda (0,29) ----------------------------------------------------------------------
		cuentaCelulasVivas=0;
		if(estadoActual[0][29]==1){
			if(estadoActual[0][28]==1){
				cuentaCelulasVivas++;
			}
			if(estadoActual[1][28]==1){
				cuentaCelulasVivas++;
			}
			if(estadoActual[1][29]==1){
				cuentaCelulasVivas++;
			}
			if(cuentaCelulasVivas ==2 || cuentaCelulasVivas == 3){
				estadoSiguiente[0][29]=1;
			}else{
				estadoSiguiente[0][29]=0;	
			}
			System.out.print(" Celula: (0,29)" + " Nº de celulas vivas alrededor: " + cuentaCelulasVivas);
			System.out.print(" Estado actual = 1");
			System.out.println(" Estado siguiente = " + estadoSiguiente[0][29]);
		}
		
		if(estadoActual[0][29]==0){
			if(estadoActual[0][28]==1){
				cuentaCelulasVivas++;
			}
			if(estadoActual[1][28]==1){
				cuentaCelulasVivas++;
			}
			if(estadoActual[1][29]==1){
				cuentaCelulasVivas++;
			}
			if(cuentaCelulasVivas == 3){
				estadoSiguiente[0][29]=1;
			}else{
				estadoSiguiente[0][29]=0;	
			}
			System.out.print(" Celula: (0,29)" + " Nº de celulas vivas alrededor: " + cuentaCelulasVivas);
			System.out.print(" Estado actual = 0");
			System.out.println(" Estado siguiente = " + estadoSiguiente[0][29]);
		}

		// Compruebo la celda (29,29) ----------------------------------------------------------------------
		cuentaCelulasVivas=0;
		if(estadoActual[29][29]==1){
			if(estadoActual[29][28]==1){
				cuentaCelulasVivas++;
			}
			if(estadoActual[28][28]==1){
				cuentaCelulasVivas++;
			}
			if(estadoActual[28][29]==1){
				cuentaCelulasVivas++;
			}
			if(cuentaCelulasVivas ==2 || cuentaCelulasVivas == 3){
				estadoSiguiente[29][29]=1;
			}else{
				estadoSiguiente[29][29]=0;	
			}
			System.out.print(" Celula: (29,29)" + " Nº de celulas vivas alrededor: " + cuentaCelulasVivas);
			System.out.print(" Estado actual = 1");
			System.out.println(" Estado siguiente = " + estadoSiguiente[29][29]);
		}
		
		if(estadoActual[29][29]==0){
			if(estadoActual[29][28]==1){
				cuentaCelulasVivas++;
			}
			if(estadoActual[28][28]==1){
				cuentaCelulasVivas++;
			}
			if(estadoActual[28][29]==1){
				cuentaCelulasVivas++;
			}
			if(cuentaCelulasVivas == 3){
				estadoSiguiente[29][29]=1;
			}else{
				estadoSiguiente[29][29]=0;	
			}
			System.out.print(" Celula: (29,29)" + " Nº de celulas vivas alrededor: " + cuentaCelulasVivas);
			System.out.print(" Estado actual = 0");
			System.out.println(" Estado siguiente = " + estadoSiguiente[29][29]);
		}


		// Compruebo la celda (29,0) -----------------------------------------------------------------------------
		cuentaCelulasVivas=0;
		if(estadoActual[29][0]==1){
			if(estadoActual[28][0]==1){
				cuentaCelulasVivas++;
			}
			if(estadoActual[28][1]==1){
				cuentaCelulasVivas++;
			}
			if(estadoActual[29][1]==1){
				cuentaCelulasVivas++;
			}
			if(cuentaCelulasVivas ==2 || cuentaCelulasVivas == 3){
				estadoSiguiente[29][0]=1;
			}else{
				estadoSiguiente[29][0]=0;	
			}

			System.out.print(" Celula: (29,0)" + " Nº de celulas vivas alrededor: " + cuentaCelulasVivas);
			System.out.print(" Estado actual = 1");
			System.out.println(" Estado siguiente = " + estadoSiguiente[29][0]);
		}
		
		if(estadoActual[29][0]==0){
			if(estadoActual[28][0]==1){
				cuentaCelulasVivas++;
			}
			if(estadoActual[28][1]==1){
				cuentaCelulasVivas++;
			}
			if(estadoActual[29][1]==1){
				cuentaCelulasVivas++;
			}
			if(cuentaCelulasVivas == 3){
				estadoSiguiente[29][0]=1;
			}else{
				estadoSiguiente[29][0]=0;	
			}
			System.out.print(" Celula: (29,0)" + " Nº de celulas vivas alrededor: " + cuentaCelulasVivas);
			System.out.print(" Estado actual = 0");
			System.out.println(" Estado siguiente = " + estadoSiguiente[29][0]);
		}

		// Compruebo primera fila. Las comprobaciones (i-1) no se realizan -----------------------------------------
		for(int j=1; j<29; j++){
			cuentaCelulasVivas=0;
			if(estadoActual[0][j]==1){ //Célula está viva.
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

				if(cuentaCelulasVivas ==2 || cuentaCelulasVivas == 3){
					estadoSiguiente[0][j]=1;
					System.out.print("Estado actual celula (0" + "," + j + ")" + " = 1");
					System.out.print(" Celula: (0," + j + ")" + "Nº de celulas vivas alrededor: " + cuentaCelulasVivas);
					System.out.println(" Estadosiguiente = " + estadoSiguiente[0][j]);
				}else{
					estadoSiguiente[0][j]=0;	
					System.out.print("Estado actual celula (0" + "," + j + ")" + " = 1");
					System.out.print(" Celula: (0," + j + ")" + "Nº de celulas vivas alrededor: " + cuentaCelulasVivas);
					System.out.println(" Estadosiguiente = " + estadoSiguiente[0][j]);
				}
			}

			if(estadoActual[0][j]==0){ //Célula está muerta.
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
				if(cuentaCelulasVivas == 3){
					estadoSiguiente[0][j]=1;
					System.out.print("Estado actual celula (0" + "," + j + ")" + " = 0");
					System.out.print(" Celula: (0," + j + ")" + "Nº de celulas vivas alrededor: " + cuentaCelulasVivas);
					System.out.println(" Estadosiguiente = " + estadoSiguiente[0][j]);
				}else{
					estadoSiguiente[0][j]=0;	
					System.out.print("Estado actual celula (0" + "," + j + ")" + " = 0");
					System.out.print(" Celula: (0," + j + ")" + "Nº de celulas vivas alrededor: " + cuentaCelulasVivas);
					System.out.println(" Estadosiguiente = " + estadoSiguiente[0][j]);
				}
			}
		}

		// Compruebo ultima fila. Las comprobaciones (i+1) no se realizan --------------------------------------------------
		for(int j=1; j<29; j++){
			cuentaCelulasVivas=0;
			if(estadoActual[29][j]==1){
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
				if(cuentaCelulasVivas ==2 || cuentaCelulasVivas == 3){
					estadoSiguiente[29][j]=1;
				}else{
					estadoSiguiente[29][j]=0;	
					System.out.print("Estado actual celula (29" + "," + j + ")" + " = 1");
					System.out.print(" Celula: (29," + j + ")" + "Nº de celulas vivas alrededor: " + cuentaCelulasVivas);
					System.out.println(" Estadosiguiente = " + estadoSiguiente[29][j]);
				}
			}

			if(estadoActual[29][j]==0){
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
				if(cuentaCelulasVivas == 3){
					estadoSiguiente[29][j]=1;
				}else{
					estadoSiguiente[29][j]=0;	
					System.out.print("Estado actual celula (29" + "," + j + ")" + " = 0");
					System.out.print(" Celula: (29," + j + ")" + "Nº de celulas vivas alrededor: " + cuentaCelulasVivas);
					System.out.println(" Estadosiguiente = " + estadoSiguiente[29][j]);
				}
			}
		}

		// Compruebo primera columna. Las comprobaciones (j-1) no se realizan -----------------------------------------------
		for(int i=1; i<29; i++){
			cuentaCelulasVivas=0;
			if(estadoActual[i][0]==1){
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
				if(cuentaCelulasVivas ==2 || cuentaCelulasVivas == 3){
					estadoSiguiente[i][0]=1;
				}else{
					estadoSiguiente[i][0]=0;	
					System.out.print("Estado actual celula (" + i + ",0) = 1");
					System.out.print(" Celula: (" + i + ",0)" + "Nº de celulas vivas alrededor: " + cuentaCelulasVivas);
					System.out.println(" Estadosiguiente = " + estadoSiguiente[i][0]);
				}
			}
			
			if(estadoActual[i][0]==0){
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
				if(cuentaCelulasVivas == 3){
					estadoSiguiente[i][0]=1;
				}else{
					estadoSiguiente[i][0]=0;	
					System.out.print("Estado actual celula (" + i + ",0) = 0");
					System.out.print(" Celula: (" + i + ",0)" + "Nº de celulas vivas alrededor: " + cuentaCelulasVivas);
					System.out.println(" Estadosiguiente = " + estadoSiguiente[i][0]);
				}
			}
		}

		// Compruebo última columna. Las comprobaciones (j+1) no se realizan ----------------------------------------------
		for(int i=1; i<29; i++){
			cuentaCelulasVivas=0;
			if(estadoActual[i][29]==1){
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
				if(cuentaCelulasVivas ==2 || cuentaCelulasVivas == 3){
					estadoSiguiente[i][29]=1;
				}else{
					estadoSiguiente[i][29]=0;	
					System.out.print("Estado actual celula (" + i + ",0) = 1");
					System.out.print(" Celula: (" + i + ",0)" + "Nº de celulas vivas alrededor: " + cuentaCelulasVivas);
					System.out.println(" Estadosiguiente = " + estadoSiguiente[i][29]);
				}
			}

			if(estadoActual[i][29]==0){
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
				if(cuentaCelulasVivas == 3){
					estadoSiguiente[i][29]=1;
				}else{
					estadoSiguiente[i][29]=0;	
					System.out.print("Estado actual celula (" + i + ",0) = 0");
					System.out.print(" Celula: (" + i + ",0)" + "Nº de celulas vivas alrededor: " + cuentaCelulasVivas);
					System.out.println(" Estadosiguiente = " + estadoSiguiente[i][29]);
				}
			}
		}

		// Bucle para comprobar todas las celdas que no están en el contorno -----------------------------------------
		for(int i=1; i<29; i++){
			for(int j=1; j<29; j++){
				cuentaCelulasVivas=0;
				if(estadoActual[i][j]==1){
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
					if(cuentaCelulasVivas ==2 || cuentaCelulasVivas == 3){
						estadoSiguiente[i][j]=1;
						System.out.print("Estado actual celula (" + i + "," + j + ") = 1");
						System.out.print(" Celula: (" + i + "," + j + ")" + "Nº de celulas vivas alrededor: " + cuentaCelulasVivas);
						System.out.println(" Estadosiguiente = " + estadoSiguiente[i][j]);
					}else{
						estadoSiguiente[i][j]=0;	
						System.out.print("Estado actual celula (" + i + "," + j + ") = 1");
						System.out.print(" Celula: (" + i + "," + j + ")" + "Nº de celulas vivas alrededor: " + cuentaCelulasVivas);
						System.out.println(" Estadosiguiente = " + estadoSiguiente[i][j]);
						}
				}

				if(estadoActual[i][j]==0){
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
					if(cuentaCelulasVivas == 3){
						estadoSiguiente[i][j]=1;
						System.out.print("Estado actual celula (" + i + "," + j + ") = 0");
						System.out.print(" Celula: (" + i + "," + j + ")" + "Nº de celulas vivas alrededor: " + cuentaCelulasVivas);
						System.out.println(" Estadosiguiente = " + estadoSiguiente[i][j]);
					}else{
						estadoSiguiente[i][j]=0;
						System.out.print("Estado actual celula (" + i + "," + j + ") = 0");
						System.out.print(" Celula: (" + i + "," + j + ")" + "Nº de celulas vivas alrededor: " + cuentaCelulasVivas);
						System.out.println(" Estadosiguiente = " + estadoSiguiente[i][j]);
					}
				}
			}
		}

		// En el siguiente bucle hacemos que el array estadoActual se actualice con los valores del estadoSiguiente.
		for (int i=0; i<30; i++){
			for (int j=0; j<30; j++){
				// System.out.println("Estoy en el bucle ade actualización del estado Actual");
				estadoActual[i][j]=estadoSiguiente[i][j];
			}
		}

		int i=0;
		//System.out.println("He terminado de generar el nuevo estadoActual y ahora tengo que escribirlo");
		while(i<30){
			int j=0;
			while(j<30){
				//	System.out.println("Dentro del segundo bucle while");
				if (estadoActual[i][j]==1){
					// System.out.println("el valor encontrado de la celda es 1");
					System.out.print("X");
				}
				else{
					System.out.print("0");
				}
				j++;
			}
			//System.out.println("fila " + i + " leida");
			System.out.println();
			i++;
		}

	}

}
