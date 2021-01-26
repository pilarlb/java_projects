import java.util.Scanner;
import paqueteclases.HiloEsPrimo;

public class Principal {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner (System.in);
		//inicializo variables numeros
		int num1, num2, num3, num4;
		//inicializo el string para saber si queremos guardar o no los datos
		String savedata;
		
		//guardo las respuestas en las variables int ya inicializadas
		System.out.println("Introduce 4 numeros. El primer numero es: ");
		num1 = sc.nextInt();
		System.out.println("El segundo numero es: ");
		num2 = sc.nextInt();
		System.out.println("El tercer numero es: ");
		num3 = sc.nextInt();
		System.out.println("El cuarto numero es: ");
		num4 = sc.nextInt();
		
		//Para validar la respuesta de si queremos guardar los resultados
		//mientras el string introducido no sea igual a SI o NO, se repetira la pregunta 
		//para escribir respuesta válida
		do {
			System.out.println("¿Quiere guardar los resultados en un fichero por cada número procesado? SI/NO");
			savedata = sc.next();
			//lo paso a mayúscula para que la funcion .equals no sufra
			savedata = savedata.toUpperCase();
			
		}while(!savedata.equals("NO") && !savedata.equals("SI")); 

		//Respuesta por pantalla si se introduce NO en savedata
		if (savedata.equalsIgnoreCase("NO")){
			System.out.println("Ha elegido no guardar los resultados");
			
		}
		
		//instancio un objeto hilo para procesar cada numero y saber si es primo
		//a cada hilo le meto la respuesta (que es igual para todos los hilos
		//para saber si guardar los datos o no
		new HiloEsPrimo(num1, "HiloEscarlata",savedata);
		new HiloEsPrimo(num2, "HiloRubi",savedata);
		new HiloEsPrimo(num3, "HiloTomate",savedata);
		new HiloEsPrimo(num4, "HiloAmapola",savedata);
		

		//Cerrar el scanner
		sc.close();
	}

}
