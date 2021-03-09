
package aplicacion;
import dominio.Tablero;
import java.util.concurrent.TimeUnit;
import java.lang.InterruptedException;

public class Principal{

	public static void main(String[] args){
		try{
			Tablero tablero = new Tablero();

			tablero.generarEstadoActualPorMontecarlo();
			tablero.leerEstadoActual();
			System.out.println();
			System.out.println();
			for (int i=0; i<100;i++){
				TimeUnit.SECONDS.sleep(1);
				tablero.transitarAlEstadoSiguiente();
				System.out.println();
				System.out.println();
			}
		}
		catch (InterruptedException e){
			System.out.println(e);
		}
	}

}
