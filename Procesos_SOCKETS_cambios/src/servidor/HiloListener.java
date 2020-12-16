package servidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.List;

import com.google.gson.Gson;
/*
 * ESTE PROYECTO ES PORQUE ENTREGUE A FELIX, 
 * ESTE MISMO PROYECTO PERO PIDIENDO LAS RESPUESTAS DESDE SERVIDOR
 * Y AHORA YA LAS RESPUESTAS SE PIDEN Y SE PROCESAN EN CLIENTE
 * Y EL RESTO SE PROCESA EN EL SERVIDOR BIBLIOTECA
 */
public class HiloListener implements Runnable{
	
	private Thread hilo;
	private static int numPeticionBiblioteca = 0;
	private Socket sockethilo;
	private List<Libro> catalogolibro;
	
	//Constructor HiloListener al que pasamos el objeto socket del cliente
	public HiloListener(Socket socketcliente, List<Libro> catalogolibro) {
		//sumamos uno al contador
		numPeticionBiblioteca++;
		//intanciar hilo
		hilo = new Thread(this, "PeticionBiblioteca_"+ numPeticionBiblioteca);
		this.sockethilo = socketcliente;
		this.catalogolibro = catalogolibro;
		//iniciar hilo
		hilo.start();
	}
	
	@Override
	public void run() {
		
		System.out.println("Comunicando con "+hilo.getName());
		//Creo referencias para los diferentes objetos que vamos a crear
		PrintStream salida = null;
		InputStreamReader entrada = null;
		BufferedReader entradaBuffer = null;
		
		try {
			//salida del servidor Biblioteca
			salida = new PrintStream(sockethilo.getOutputStream());
			//entrada del servidor Biblioteca
			entrada = new InputStreamReader(sockethilo.getInputStream());
			//buffer para leer de frase en frase la entrada
			entradaBuffer = new BufferedReader(entrada);
			
			//Creamos variables
			String texto = "";
			boolean continuar = true;
			//El servidor procesara las entradas de este hilo hasta que escriba EXIT
			
			
			while(continuar) {
				
				//servidor lee lo que le entra
				texto = entradaBuffer.readLine();
				texto = texto.toLowerCase();
				
				String [] jsonEntrada = texto.split("#");
				
				//la posicion 0 del array aparecera el texto que identifica la funcion
				switch (jsonEntrada[0]) {
								case "isbn": //CONSULTAR LIBRO POR ISBN
									//inicializo variable
									String isbncliente = jsonEntrada[1];
								    String libro="";
								    //recorres el arraylist del catalogo de libros
									    for( Libro lib : catalogolibro) {
									    	
									    	if(isbncliente.equalsIgnoreCase(lib.getIsbn())) {
									    		Gson gson = new Gson();
												//si el titulo coincide, se muestran los datos por pantalla
									    		libro = gson.toJson(lib);
												
											}
									    }
									  //SALIDAS DEL SERVIDOR
								    if(libro.equalsIgnoreCase("")) {
										salida.println("No está el libro de isbn: "+ isbncliente
														+"%"+"Tarea_terminada");
								    }else {
								    	//ENVIO RESPUESTA CON EL LIBRO
								    	salida.println(libro
												+"%"+"Tarea_terminada");
								    }
								    
								break;
								
								case "titulo": //CONSULTAR LIBRO POR TITULO
									
									String titulocliente = jsonEntrada[1];
									String librotitulo ="";
									//For para recorrerse Arraylist de objetos Libro
									for( Libro lib : catalogolibro) {
										
										if(titulocliente.equalsIgnoreCase(lib.getTitulo())) {
											Gson gson = new Gson();
											//si el titulo coincide, metemos en la variable toString
											librotitulo = gson.toJson(lib);
											
										}
									}
									//SALIDAS DEL SERVIDOR
									//Si no hemos metido la variable, es que no ha habido coincidencia
									if(librotitulo.equalsIgnoreCase("")) {
										salida.println("No está el libro de titulo: "+ titulocliente
														+"%"+"Tarea_terminada");
								    }else {
								    	
								    	//envio respuesta a Cliente
										salida.println(librotitulo
											       +"%"+"Tarea_terminada");
								    }
									
								break;
								
								case "autor": //consultar libro por autor
									
									//inicializo variables
									String autorcliente = jsonEntrada[1];
									String listalibros = "";
									int contador = 0;
									//For para recorrerse Arraylist de objetos Libro
									for( Libro lib : catalogolibro) {
										//recorrerse el array del atributo autor
										for(int i=0; i<lib.getAutor().length; i++) {
											//creo variable para recoger cada autor del array
											String autor = (lib.getAutor())[i];
											
											if(autorcliente.equalsIgnoreCase(autor)){
												contador ++;
												Gson gson = new Gson();
												//añado separador % para spilt
												//transformo objeto lib en json
												listalibros = listalibros + "%"+(gson.toJson(lib));
											}
							
										}
												
									}
									//SALIDAS PARA EL CLIENTE DEL SERVIDOR
									//si el contador es 0, es que no ha habido ninguna coincidencia
									if(contador == 0) {
										salida.println("No existe autores"
													+"%"+"Tarea_terminada");
									}else {
										salida.println("Total "+contador+" libros encontrados con autor "+ autorcliente 
														+ listalibros
														+"%"+"Tarea_terminada");
									}
									
									
								break;
								
								case "nuevolibro": //añadir libro a a la biblioteca
									String librojson = jsonEntrada[1];
									//transformo a objeto Libro
									Gson gson = new Gson();
									Libro lib = gson.fromJson(librojson,Libro.class);
									//añado a la biblioteca
									catalogolibro.add(lib);
									//SALIDA AL CLIENTE---------------------
									salida.println("El libro "+librojson+" añadido a BIBLIOTECA"
											+"%"+"Tarea_terminada");
									
								break;
								//----------------------------------------HASTA AQUI VOY
								case "exit":
									
									//Despedimos al cliente
									salida.println("Finalizo la petición al servidor Biblioteca");
									System.out.println(hilo.getName() + "cerró la comunicacion");
									//cambia el boolean para salir del while
									continuar = false;
								break;
								
				}
				
			}
		} catch (IOException e) {
			
			System.err.println("Error en el run "+e.getMessage());
			
		}finally { //Cerrar las conexiones del socket
			
			try {
				//si salida y entrada son diferentes de null
				if(salida != null && entrada != null) {
					salida.close();
					entrada.close();
					sockethilo.close();
					
				}
				
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			
		}
	
		
	}

	
	
}
