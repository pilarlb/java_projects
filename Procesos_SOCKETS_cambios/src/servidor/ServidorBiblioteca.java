package servidor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
/*
 * ESTE PROYECTO ES PORQUE ENTREGUE A FELIX, 
 * ESTE MISMO PROYECTO PERO PIDIENDO LAS RESPUESTAS DESDE SERVIDOR
 * Y AHORA YA LAS RESPUESTAS SE PIDEN Y SE PROCESAN EN CLIENTE
 * Y EL RESTO SE PROCESA EN EL SERVIDOR BIBLIOTECA
 */
public class ServidorBiblioteca {
	
	public static final int PUERTO = 2001;
	public static final String IP_SERVER = "localhost";
	
	
	public static void main(String[] args) {
		
		System.out.println("SERVIDOR DE LA BIBLIOTECA");
		
		//creamos referencia para objeto serversocket
		ServerSocket servidor = null;
		
 		//Arraylist del catalogo de biblioteca
		List<Libro> catalogolibro = new ArrayList<Libro>();
		
		
		//Añado 5 libros al servidor inicializados
		catalogolibro.add(new Libro("111111","El primer libro", new String[]{"Maria Lopez", "Pedro Bernat"}, new double[]{5.9,4.9}));
		catalogolibro.add(new Libro ("222222", "El libro de la selva", new String[] {"Pedro Bernat", "Ursula Le Guin", "Maria Lopez"}, 8.99));
		catalogolibro.add(new Libro ("333333","El calendario quemado", "Pedro Bernat", new double[] {14.88,13.77}));
		catalogolibro.add(new Libro ("444444", "Los portales", new String[] {"Laura Gallego", "Ursula Le Guin"}, 16.99));
		catalogolibro.add(new Libro ("555555","Tres palos y medio", new String[] {"Ursula Le Guin","Laura Gallego", "Maria Lopez"}, 21.99));
		
		try {
			servidor = new ServerSocket();
			InetSocketAddress direccion = new InetSocketAddress(IP_SERVER,PUERTO);
			
			//el servidor ahora escucha peticiones desde IP_server y Puerto
			servidor.bind(direccion);
			System.out.println("Servidor Biblioteca creado por puerto: "+ PUERTO);
			System.out.println("Serviodor Biblioteca creado por la IP local: "+ direccion.getAddress());
			
			while(true) {
				Socket sockethilo = servidor.accept();
				System.out.println("Comunicacion entrante");
				//instanciamos un nuevo hilo, liberando el hilo principal
				new HiloListener(sockethilo,catalogolibro);
			}
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println("Main servidor "+ e.getMessage());
		} finally {
			//cerrar todas las conexiones
			if(servidor != null) {
				try {
					servidor.close();
				}catch(IOException e){
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		
	}

}
