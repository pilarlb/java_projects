package paqueteclases;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

// Creamos una clase hilo a partir de la interfaz Runnable
public class HiloEsPrimo implements Runnable{
	//atributos con el que vamos a trabajar en el hilo
	private int number;
	//savedata sirve para saber si se guardan los datos o no
	private String savedata;
	
	public HiloEsPrimo(int number, String nombrehilo, String savedata) {
		this.number = number;
		this.savedata = savedata;
		//creamos objeto hilo
		Thread h1 = new Thread(this, nombrehilo);
		//inicializar el hilo
		h1.start();
	}
	
	
	@Override
	public void run() {
		//variables para calcular tiempo de procesamiento
		//hora de inicio, hora de fin, tiempo resultante
		long hinicio, hfin, tiempo;
		//hora de inicio
		hinicio = System.currentTimeMillis();
		
		
		//instancio objeto para guardar información
		//El objeto tiene el número que se introduce, y el nombre del hilo actual
		//con parametros default: tiempo de procesamiento (puesto en 0)
		// y String esPrimo (string vacio)
	
		NumeroProcesado N = new NumeroProcesado(number,Thread.currentThread().getName(),0,"");
		
		//Con los datos del objeto de tipo NumeroProcesado
		//llamo a un método de la clase NumeroProcesado --metodo esPrimo()
		//que devuelve un boolean
		if (N.esPrimo()) {
			//modifico el atributo del objeto de clase NumeroProcesado
			//según el resultado del método
			N.setEsPrimo("es PRIMO");
		}else {
			N.setEsPrimo("NO es PRIMO");
		}
		
		//hora fin
		hfin = System.currentTimeMillis();
		//calculo de tiempo de procesamiento transcurrido
		tiempo = hfin - hinicio;
		//introducimos el dato de tiempo de procesamiento en el objeto
		N.setTiempoProcesamiento(tiempo);
		
		//Llamo al método del objeto toString, que muestra el numero procesado, 
		//el nombre del hilo que lo ha procesado
		//el tiempo que ha tardado en procesar el número y si es primo o no
		System.out.println(N.toString());

		//En el caso de que se quiera guardar los datos del procesamiento
		if (savedata.equalsIgnoreCase("SI")){
			//Creo el acceso a fichero, ya sea para crear o modificar
			FileWriter fichero;
			
			try {
				//Creacion de fichero donde guardar los datos del hilo, o abrirlo si ya existe con el true
				//Se llama el fichero como el nombre del hilo actual thread.currentThread().getName()
				fichero = new FileWriter ("C:\\Users\\Pilar\\Documents\\"+Thread.currentThread().getName()+".txt",true);
				
				System.out.println("Archivo "+Thread.currentThread().getName()+".txt creado en C:\\Users\\Pilar\\Documents\\"
				+ "\n  Si ya existía el archivo, se abre para añadir más datos.");
				
			} catch (IOException e) {
				
				System.out.println("No se puede abrir el fichero");
				System.out.println(e.getMessage());
				return;
			}
			
			// instancio Buffer que sirve para escribir en el fichero mas eficientemente
			BufferedWriter buffer = new BufferedWriter(fichero);
			
			try {
				//escribir en el fichero el metodo toString del objeto tipo NumeroProcesado
				buffer.write(N.toString());
				//empezar nueva línea
				buffer.newLine();
				
			}catch (IOException e) {
				System.out.println("Error al escribir en el fichero");
				System.out.println(e.getMessage());
			}
			
			
			//Cerrar buffer y fichero
			try {
				buffer.close();
				fichero.close();
			}catch (IOException e){
				System.out.println("Error al escribir en el fichero");
				System.out.println(e.getMessage());
			}
			
		}
	
		
	}

}
