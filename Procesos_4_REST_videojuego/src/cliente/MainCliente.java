package cliente;

import java.util.ArrayList;

import java.util.Scanner;

import entidades.Videojuego;
import persistencia.AccesoRest;

public class MainCliente {
	
	public static void mostrarMenu() {
		System.out.println("-------------MENU VIDEOJUEGOS--------------");
		System.out.println("Consultar videojuego por id. Opcion 1");
		System.out.println("Consultar videojuegos por compañia. Opcion 2");
		System.out.println("Alta de videojuego. Opcion 3");	
		System.out.println("Salir del programa. Opcion 4");
	
	}
	//metodo que introduce String del scanner y produce int array de notas
	public static int[] arraydeNotas(String respuesta){
		if(respuesta.contains("&")) {
			//array tipo String de las notas
			String [] aNotas = {};
			aNotas = respuesta.split("&");
			
			//array tipo int de las notas, vacio
			int intNotas[] = new int [aNotas.length];
			int num;
			for(int i = 0; i < aNotas.length; i++) {
				
				String stringNota = aNotas[i];
				
				num = Integer.parseInt(stringNota);
				
				intNotas[i] =  num;
					
			}//fin for
				return intNotas;
				
			}else {
				int nota = Integer.parseInt(respuesta);
				int intNota[] = {nota};
				return intNota;
				
		}
		
	}
	
	public static void main(String[] args) {
		
		String entrada = "";
		boolean continuar = true;
		AccesoRest accesoRest = new AccesoRest();
		//instancio scanner
		Scanner sc = new Scanner(System.in);
		
		
		while(continuar) {
			mostrarMenu();
			entrada = sc.nextLine();
		switch(entrada) {
			case "1"://CONSULTA VIDEOJUEGO POR ID
				Videojuego v = null;
				String idvj ="";
				int id;
				
				System.out.println("Introduzca ID del videojuego");
				idvj = sc.nextLine();
				
				id = Integer.valueOf(idvj);
				
				try {
					v = accesoRest.obtenerVid(id);
				} catch (Exception e) {
					
					e.printStackTrace();
				}
				if (v == null) {
					System.out.println("TAREA FINALIZADA------ID "+id+ " NO ENCONTRADO");
				}else {
					System.out.println(v);
				}
				
			break;
			case "2": //CONSULTA VIDEOJUEGO POR COMPAÑIA
				ArrayList<Videojuego> lsvjcompania = new ArrayList<Videojuego>();;
				String compania ="";
				
				System.out.println("Introduzca compañia de videojuegos");
				compania = sc.nextLine();
				
				try {
					lsvjcompania = accesoRest.obtenerVideojuego(compania);
					
				} catch (Exception e) {
					
					e.printStackTrace();
				}
				//si el arraylist es null
				if (lsvjcompania == null) {
					System.out.println("TAREA TERMINADA --- COMPAÑIA "+compania+ " NO ENCONTRADA");
				}else {
					
					System.out.println("--------LISTADO VIDEOJUEGOS "+compania+"------------");
					for( Videojuego vid : lsvjcompania) {
						System.out.println(vid);
					}
					System.out.println("----------FIN LISTADO VIDEOJUEGOS------------");
				}
				
			break;
			case "3":// ALTA DE VIDEOJUEGO
				Videojuego v1 = new Videojuego();
				Videojuego vRespuesta = null;
				String nombre = "";
				String com ="";
				
				System.out.println("Introduzca el nombre del videojuego");
				nombre = sc.nextLine();
				System.out.println("Introduzca la compañia del videojuego");
				com = sc.nextLine();
				System.out.println("Introduzca el ID del videojuego");
				entrada = sc.nextLine();
				int ident = Integer.parseInt(entrada);
				System.out.println("Introduzca la nota del videojuego.\nSi es más de una nota, separalas con & sin espacios");
				entrada = sc.nextLine();
				
				int arrayNotas [] = arraydeNotas(entrada);
				
				
				v1.setNombre(nombre);
				v1.setCompania(com);
				v1.setId(ident);
				v1.setListanotas(arrayNotas);
				
				try {
					//el método devuelve el Videojuego dado de Alta
					vRespuesta = accesoRest.altaVideojuego(v1);
				} catch (Exception e) {
					e.printStackTrace();
				}
				if(vRespuesta == null) {
					System.out.println("TAREA TERMINADA ----- VIDEOJUEGO NO DADO DE ALTA");
				}else {
					System.out.println("EL VIDEOJUEGO "+vRespuesta.toString()+" ---> AÑADIDO");
				}
				
			break;
			case "4"://SALIR
				System.out.println("--------CERRANDO APLICACION ---------");
				continuar = false;
			break;
			default:
				System.out.println("--------OPCION INCORRECTA ---------");
			break;
		}
		
		}//fin while
		
		sc.close();
	}

}
