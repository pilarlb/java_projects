package cliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import com.google.gson.Gson;

import servidor.Libro;
/*
 * ESTE PROYECTO ES PORQUE ENTREGUE A FELIX, 
 * ESTE MISMO PROYECTO PERO PIDIENDO LAS RESPUESTAS DESDE SERVIDOR
 * Y AHORA YA LAS RESPUESTAS SE PIDEN Y SE PROCESAN EN CLIENTE
 * Y EL RESTO SE PROCESA EN EL SERVIDOR BIBLIOTECA
 */
public class Cliente {
	
	//metodo para llamar al menu
	public static void mostrarMenu() {
		System.out.println("-------------BIBLIOTECA -------------");
		System.out.println("Consulta libro por ISBN. Introduce 1");
		System.out.println("Consulta libro por título. Introduce 2.");
		System.out.println("Consulta libro por autor. Introduce 3.");
		System.out.println("Añadir libro a la biblioteca. Introduce 4");
		System.out.println("Salir de la aplicacion. Introduce EXIT.");
		System.out.println("--------------------------------------");
	}
	
	public static void respuestaServer(String string) {
		//si lo que llega de servidor contiene %
		if(string.contains("%")){
			
			String[] partesrespuesta = string.split("%");
			//vamos mostrando las particiones al servidor 
			for(int i=0; i<partesrespuesta.length; i++) {
				
				System.out.println("Servidor responde: "+ partesrespuesta[i]);
				
				if(partesrespuesta[i].contains("Tarea_terminada")) {
					mostrarMenu();
				}
				
			}
		}else {
			//Si la respuesta no contiene el caracter %, no se divide
			System.out.println("Servidor responde: "+ string);
			
		}
	}

	public static void main(String[] args) {
		
		//Scanner para pedir input
		Scanner lector = new Scanner(System.in);
		
		InputStreamReader entrada = null;
		PrintStream salida = null;
		Socket socketcliente = null;
		
		try {
			socketcliente = new Socket();
			InetSocketAddress direccionServidor = new InetSocketAddress("localhost",2001);
			System.out.println("Esperando que el servidor acepte la conexion");
			
			socketcliente.connect(direccionServidor);
			System.out.println("Comunicacion establecida con el servidor");
			
			entrada = new InputStreamReader(socketcliente.getInputStream());
			salida = new PrintStream(socketcliente.getOutputStream());
			BufferedReader entradaBuffer = new BufferedReader(entrada);
				
			mostrarMenu();
			
			
			String texto ="";
			
			while(!texto.equalsIgnoreCase("exit")) {
				
				texto = lector.nextLine(); //AQUI SERIA DONDE EMPEZAMOS A PEDIR RESPUESTAS EN CLIENTE
				
				switch(texto) {
					case "1": //consulta libro por ISBN
						System.out.println("Escribe el ISBN");
						texto = lector.nextLine(); //esta respuesta se mandaria al servidor
						
						if(texto.isEmpty()) {
							System.out.println("Has escrito un espacio en blanco");
							mostrarMenu();
						}else {
							//quitar los espacios en blanco
							texto = texto.replace(" ","");;
							//enviamos el texto al servidor
							salida.println("isbn#"+texto);
							
							System.out.println("Esperando respuesta ...");
							
							//recibimos respuesta del servidor 
							String respuesta1 = entradaBuffer.readLine();
							
							//FUNCION PARA GESTIONAR RESPUESTAS DEL SERVIDOR
							respuestaServer(respuesta1);
						}
						
					break;
					case "2": //consulta libro por titulo
						System.out.println("Escribe el titulo");
						texto = lector.nextLine(); //esta respuesta se mandaria al servidor
						
						if(texto.isEmpty()) {
							System.out.println("Has escrito un espacio en blanco");
							mostrarMenu();
						}else {
							//quitar los espacios delante y detras
							texto = texto.trim();
							//enviamos el texto al servidor
							salida.println("titulo#"+texto);
							
							System.out.println("Esperando respuesta ...");
							
							//recibimos respuesta del servidor 
							String respuesta1 = entradaBuffer.readLine();
							
							//FUNCION PARA GESTIONAR RESPUESTAS DEL SERVIDOR
							respuestaServer(respuesta1);
						}
						
					break;
					case "3"://CONSULTA LIBRO POR AUTOR
						System.out.println("Escribe el autor/a");
						texto = lector.nextLine(); //esta respuesta se mandaria al servidor
						
						if(texto.isEmpty()) {
							System.out.println("Has escrito un espacio en blanco");
							mostrarMenu();
						}else {
							//quitar los espacios delante y detras
							texto = texto.trim();
							//enviamos el texto al servidor
							salida.println("autor#"+texto);
							
							System.out.println("Esperando respuesta ...");
							
							//recibimos respuesta del servidor 
							String respuesta1 = entradaBuffer.readLine();
							
							//FUNCION PARA GESTIONAR RESPUESTAS DEL SERVIDOR
							respuestaServer(respuesta1);
						}
						
					break;
					case "4": //AÑADE NUEVO LIBRO A LA BIBLIOTECA
						//instancio objeto libro
						Libro nuevoLibro = new Libro();
						String isbn ="";
						String titulo="";
						String autor= null;
						String precio=null;
						
						System.out.println("Nuevo libro a añadir: escriba el ISBN");
						isbn = lector.nextLine(); 
						//Quitar espacios en blanco
						isbn = isbn.replace(" ","");
						//introduzco el isbn
						nuevoLibro.setIsbn(isbn);
						
						System.out.println("Nuevo libro a añadir: escriba el título");
						titulo = lector.nextLine(); 
						//introduzco el titulo al libro
						nuevoLibro.setTitulo(titulo);
						System.out.println("Nuevo libro a añadir: escriba el autor/a. Si hay varios autores, separalos con & sin dejar espacio.");
						autor = lector.nextLine(); 
							
						if(autor.contains("&")) {
							String [] arrayAutores = autor.split("&");
							nuevoLibro.setAutor(arrayAutores);
						}else {
							//metodo creado para solo un string
							nuevoLibro.setAutorSinArray(autor);
						}
						
						System.out.println("Nuevo libro a añadir: escriba el precio(s). Si hay más de un precio, separelo con el simbolo #");
						precio = lector.nextLine(); 
						//si los decimales del precio se separan con coma en vez de punto, los sustituimos
						if(precio.contains(",")) {
							precio = precio.replace(",",".");
						}
						//crear array String de precios y pasarlo a array Double
						if(precio.contains("#")) {
							String [] arrayPrecios = {};
							arrayPrecios = precio.split("#");
							
							//array Double
							double preciosD[] = new double [arrayPrecios.length];
							double num;
							for(int i = 0; i < arrayPrecios.length; i++) {
								
								String stringPrecios = arrayPrecios[i];
								
								num = Double.valueOf(stringPrecios);
								
								preciosD[i] =  num;
									
							}
							//introduzco el array Double
							nuevoLibro.setPrecio(preciosD);
							
						}else {//si sólo hay un precio y no hay #
							//metodo para introducir precio
							nuevoLibro.setPrecioUnicoConString(precio);
						}
						
						System.out.println("NUEVO "+nuevoLibro.toString() 
						+"\n"
						+"Si son correctos los datos, se añadirá a la biblioteca. Escriba ADD para confirmar.");
						texto = lector.nextLine();
						//SALIDA AL SERVIDOR
						if(texto.equalsIgnoreCase("add")) {
							//TRANSFORMAMOS EL OBJETO LIBRO A JSON
							Gson gson = new Gson();
							String newbook = gson.toJson(nuevoLibro);
							
							//enviamos el texto al servidor
							salida.println("nuevolibro#"+newbook);
							
							System.out.println("Esperando respuesta ...");
							
							//recibimos respuesta del servidor 
							String respuesta1 = entradaBuffer.readLine();
							
							//FUNCION PARA GESTIONAR RESPUESTAS DEL SERVIDOR
							respuestaServer(respuesta1);
						}else {
							System.out.println("No se ha añadido el libro a la biblioteca");
							mostrarMenu();
						}
						
					break;
					case "5"://mostrar todos los libros de la biblioteca
						//-------------------------AQUI ME HE QUEDADO
					case "exit": //exit
						//EXIT al servidor para que cierre
						salida.println("exit#quenosvamoosss");
						//recibimos respuesta del servidor 
						String respuesta1 = entradaBuffer.readLine();
						
						//FUNCION PARA GESTIONAR RESPUESTAS DEL SERVIDOR
						respuestaServer(respuesta1);
					break;
					default:
						String mensajeDefault = "Opcion incorrecta";
						System.out.println(mensajeDefault);
						mostrarMenu();
					break;
				}
				
			}
			
		}catch(UnknownHostException e) {
			System.out.println("No se puede establecer comunicacion con el servidor");
			System.out.println(e.getMessage());
			
		}catch(IOException e) {
			
			System.out.println("Error de E/S");
			System.out.println(e.getMessage());
			
			//Finally se ejecuta haya excepciones o no
		}finally {
			
			try {
				//si salida y entrada son diferentes de null
				if(salida != null && entrada != null) {
					salida.close();
					entrada.close();
					socketcliente.close();
					
				}
				
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			
		}
		
		//cerramos scanner
		lector.close();
		System.out.println("Comunicacion cerrada");
		
		
	}

}
