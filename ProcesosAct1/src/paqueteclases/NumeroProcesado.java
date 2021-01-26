package paqueteclases;
//Clase para crear objeto con cada número que introducimos
public class NumeroProcesado {
	//atributos: el numero que vamos a averiguar si es primo
	// un string con el nombre del hilo actual
	//un long para mostrar el tiempo de procesamiento del hilo
	//un String que indica si es primo o no el número
	private int numero;
	private String nombreHiloActual;
	private long tiempoProcesamiento;
	private String esPrimo;
	
	//Constructor
	public NumeroProcesado(int numero, String nombreHiloActual, long tiempoProcesamiento, String esPrimo) {
		super();
		this.numero = numero;
		this.nombreHiloActual = nombreHiloActual;
		this.tiempoProcesamiento = tiempoProcesamiento;
		this.esPrimo = esPrimo;
	}

	//Getters y setters
	public int getNumero() {
		return numero;
	}



	public void setNumero(int numero) {
		this.numero = numero;
	}



	public String getNombreHiloActual() {
		return nombreHiloActual;
	}



	public void setNombreHiloActual(String nombreHiloActual) {
		this.nombreHiloActual = nombreHiloActual;
	}



	public long getTiempoProcesamiento() {
		return tiempoProcesamiento;
	}



	public void setTiempoProcesamiento(long tiempoProcesamiento) {
		this.tiempoProcesamiento = tiempoProcesamiento;
	}



	public String getEsPrimo() {
		return esPrimo;
	}



	public void setEsPrimo(String esPrimo) {
		this.esPrimo = esPrimo;
	}

	//metodo toString
		@Override
	public String toString() {
		return "Este numero es " + numero + ", el hilo es " + nombreHiloActual 
				+ ", tiempoProcesamiento = "
				+ tiempoProcesamiento +" --> " +numero +" " + esPrimo +"\n";
	}

	//funcion para saber si el atributo numero es primo
	//retorna un boolean
	//un numero es primo cuando sólo es divisible entre sí mismo
	public boolean esPrimo(){
		
		if (numero == 0 || numero == 1 || numero == 4) {
			return false;
		} 
		for (int i=2; i < numero/2; i++) {
			//comprobamos si es divisible entre varios números
			//cuando el resto es 0, significa que es divisible
			//y si es divisible no es primo
			if(numero % i == 0) {
				return false;
			}
		}
		//si no es divisible entre ninguno de los anteriores números
		//entonces es primo
		return true;
	}
	
}
