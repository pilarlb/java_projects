package persistencia;

import java.io.IOException;
import java.io.PrintStream;
import java.net.HttpURLConnection;

import java.net.URL;
import java.util.ArrayList;

import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import entidades.Videojuego;

public class AccesoRest {
	
	private static final String IP = "http://localhost:8082/Procesos_4_REST_videojuego/rest/videojuegos";
	
	public String leerHTMl() throws IOException {
		
		/*
		 * Se solicita el servicio a través de una URL y un objeto HttpURLConnection.
		 * Esta peticion se envia a través del método GET.
		 */
							
		URL objURL = new URL("http://localhost:8082/Procesos_4_REST_videojuego/rest/videojuegos");
		HttpURLConnection conexion = (HttpURLConnection) objURL.openConnection();
		
		//Solicitud al servidor
		conexion.connect();
		
		/*
		 * Obtenemos codigo HTTP de la petición y lo mostramos en pantalla.
		 * Salvo que ocurra algún error, el código será 200(OK)
		 */
		int codigoHTTP = conexion.getResponseCode();
		System.out.println("Codigo: "+ codigoHTTP);
		
		/*
		 * El método conexiion.getInputStream() nos devuelve el flujo
		 * de datos que necesitaremos para leer el mensaje del servidor
		 * Dicho flujo de datos se lo pasamos a un objeto Scanner,
		 * que facilitara la lectura.
		 */
		Scanner sc = new Scanner(conexion.getInputStream());
		String respuesta = "";
		
		while(sc.hasNextLine()) {
			respuesta+= sc.nextLine();
		}
		
		sc.close();
		
		return respuesta;
		
		/*
		 * CONSULTAR VIDEOJUEGO POR ID
		 * CONSULTAR VIDEOJUEGO POR COMPAÑIA
		 * ALTA DE VIDEOJUEGO
		 * SALIR
		 */
		
	}
	//CONSULTAR VIDEOJUEGO POR ID
	public Videojuego obtenerVid(int id) throws Exception {
		String url = IP + "/" + id;
		Videojuego vRespuesta = null;
		
		System.out.println("Peticion a: "+ url);
		/*
		 * Se solicita el servicio a través de una URL y un objeto HttpURLConnection.
		 * Esta peticion se envia a través del método GET.
		 */
		URL objURL = new URL(url);
		HttpURLConnection conexion = (HttpURLConnection) objURL.openConnection();
		conexion.setRequestMethod("GET");
		
		//solicitud al servidor
		conexion.connect();
		/*
		 * Obtenemos codigo HTTP de la petición y lo mostramos en pantalla.
		 * Salvo que ocurra algún error, el código será 200(OK)
		 */
		int codigoHTTP = conexion.getResponseCode();
		
		if (codigoHTTP == 404) {
			System.out.println("Codigo "+codigoHTTP + "-> No encontrado videojuego con id "+id);
		}else {
			
			System.out.println("Codigo: "+codigoHTTP);
			
			/*
			 * El método conexiion.getInputStream() nos devuelve el flujo
			 * de datos que necesitaremos para leer el mensaje del servidor
			 * Dicho flujo de datos se lo pasamos a un objeto Scanner,
			 * que facilitara la lectura.
			 */
			Scanner sc = new Scanner (conexion.getInputStream());
			String respuesta = sc.nextLine();
			
			Gson gson = new Gson();
			vRespuesta = gson.fromJson(respuesta, Videojuego.class);
			
			sc.close();
		}
		
		
		conexion.disconnect();
		
		return vRespuesta;
	}
	
	//ALTA DE VIDEOJUEGO
	public Videojuego altaVideojuego(Videojuego vj) throws Exception {
		System.out.println("Peticion a: "+ IP);
		URL objURL = new URL(IP);
		Videojuego vRespuesta = null;
		HttpURLConnection conexion = (HttpURLConnection) objURL.openConnection();
		conexion.setRequestMethod("POST");
		/*
		 * poner cabecera Content-Type con valor application/json
		 * pues el servidor solo consume mensaje json
		 */
		conexion.setRequestProperty("Content-type","application/json");
		conexion.setDoOutput(true);
		
		//Parseo el cliente a formato json para enviar en ese formato al servidor
		Gson gson = new Gson();
		String jsonAEnviar = gson.toJson(vj);
		System.out.println("Json a enviar: "+ jsonAEnviar);
		
		//PrintStream sirve para escribir en el body del HTTP Request
		PrintStream salida = new PrintStream(conexion.getOutputStream());
		salida.println(jsonAEnviar);
		
		//solicitud al servidor
		conexion.connect();
		
		//leemos la respuesta
		Scanner sc = new Scanner (conexion.getInputStream());
		
		int codigoHTTP = conexion.getResponseCode();
		//400 es badrequest, no se ha añadido el Videojuego a la lista
		if(codigoHTTP == 400) {
			System.out.println(codigoHTTP+" --> Videojuego con ID o nombre repetido en la base de datos. No se ha añadido");
		}else {//si se acepta y añade el Videojuego
			System.out.println("Codigo: "+ codigoHTTP);
			
			String jsonRespuesta = sc.nextLine();
			vRespuesta = gson.fromJson(jsonRespuesta, Videojuego.class);
		}
		
		
		sc.close();
		salida.close();
		conexion.disconnect();
		
		return vRespuesta;
	}
	
	//Retornar lista de videojuegos por compañia, que es una List
	public ArrayList<Videojuego> obtenerVideojuego(String compania) throws Exception{
		ArrayList<Videojuego> vidcompania = null;
		String url = null;
		
		if(compania == "") {
			url = IP;
		}else  {
			url = IP + "/query?compania=" + compania;
		}
		
		System.out.println("Peticion a: "+ url);
		
		URL objURL = new URL(url);
		HttpURLConnection conexion = (HttpURLConnection) objURL.openConnection();
		conexion.setRequestMethod("GET");
		
		//solicitud al servidor
		conexion.connect();
		
		int codigoHTTP = conexion.getResponseCode();
		//QUE HACER CON LA RESPUESTA EN FUNCION DEL CODIGO DE RESPUESTA
		if (codigoHTTP == 404) {
			System.out.println("Codigo "+codigoHTTP + " --> no hay videojuegos de la compañia "+ compania);
			
		}else {
			
			System.out.println("Código: " + codigoHTTP);
			
			Scanner sc = new Scanner (conexion.getInputStream());
			
			//leer el json que manda el servidor
			String jsonRespuesta = sc.nextLine();
			
			//convierto json en lista de objetos Videojuego
			Gson gson = new Gson(); //cambiado List por ArrayList
			vidcompania = gson.fromJson(jsonRespuesta, new TypeToken<ArrayList<Videojuego>>(){}.getType());
			
			sc.close();
		}
		
		conexion.disconnect();	
		
		return vidcompania;
		
		
	}
}
