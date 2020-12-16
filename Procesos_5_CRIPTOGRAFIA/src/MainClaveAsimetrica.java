import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.util.ArrayList;
import java.util.Scanner;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;


import entidades.ClaveAsimetrica;
import entidades.Coche;
import entidades.GeneradorClave;


public class MainClaveAsimetrica {
	
	public static void mostrarMenu() {
		System.out.println("----------MENU----------");
		System.out.println("Encriptar frase. Opcion 1");
		System.out.println("Mostrar frase encriptada. Opcion 2");
		System.out.println("Desencriptar frase. Opcion 3");
		System.out.println("Encriptar coche. Opcion 4");
		System.out.println("Mostrar coche. Opcion 5");
		
		System.out.println("Salir del programa. Opcion salir");
	
	}
	
	//Metodo que devuelve los bytes de un objeto tipo Coche
	public static byte[] getBytes(Object auto) throws IOException{
	    ByteArrayOutputStream out = new ByteArrayOutputStream();
	    ObjectOutputStream os = new ObjectOutputStream(out);
			os.writeObject(auto); 
	
	    return out.toByteArray();
	}
		
	//Metodo que pasa bytes a formato Objeto	
	public static Object bytestoObject (byte[] data) throws IOException, ClassNotFoundException {
	    ByteArrayInputStream in = new ByteArrayInputStream(data);
	    ObjectInputStream is = new ObjectInputStream(in);
	    return is.readObject();
	}
	//metodo que con el scanner crea objeto Coche nuevo
	public static Coche nuevoCoche(Scanner sc) {
		System.out.println("Introduzca la matrícula del nuevo coche");
		String matricula = sc.nextLine();
		System.out.println("Introduzca la marca del nuevo coche");
		String marca = sc.nextLine();
		System.out.println("Introduzca el modelo del nuevo coche ");
		String modelo = sc.nextLine();
		System.out.println("Introduzca el precio del nuevo coche");
		String precio = sc.nextLine();
		double preciodoub = Double.valueOf(precio);
		
		Coche auto =new Coche(matricula, marca, modelo, preciodoub);
		return auto;
	}
		
	public static void main(String[] args) {
		//Inicializo variables
		String entrada ="";
		String frase = "";
		GeneradorClave generadorclave = null;
		ClaveAsimetrica llaveroclave;
		ClaveAsimetrica llaveroclavecoche = null;
		KeyPair clave = null;
		KeyPair clavecoche = null;
		Cipher descifrador = null;
		Cipher descifradorcoche = null;
		byte[] bytesFraseCif = null;
		byte[] bytesCocheCif = null;
		
		ArrayList<byte[]> listaCoches = null;
		
		
		boolean continuar = true;
		
		Scanner sc = new Scanner(System.in);
		mostrarMenu();
		entrada = sc.nextLine();
		
		while(continuar) {
			switch(entrada) {
				case "1": //Escribir frase y cifrarla
					System.out.println("Escriba frase para encriptarla");
					frase = sc.nextLine();
					
					try {
						
						//instancio un generador de claves
						generadorclave = new GeneradorClave();
						//Utilizo el metodo del generador para crear KeyGenerator, SecretKey y Cipher
						llaveroclave = generadorclave.crearclaveAsimetrica();
						//obtengo ambas claves
						clave = llaveroclave.getClaves();
						
						descifrador = llaveroclave.getCifrador();
						//inicio el descifrador con la clave publica
						descifrador.init(Cipher.ENCRYPT_MODE, clave.getPublic());
						
						//obtenemos bytes de la frase original
						byte[] bytesFrase = frase.getBytes();
						//cifra la frase
						bytesFraseCif = descifrador.doFinal(bytesFrase);
						
						System.out.println(frase + " ---> ENCRIPTADA");
						mostrarMenu();
						entrada = sc.nextLine();
						//clausulas añadidas según el tipo
					} catch (IllegalBlockSizeException | BadPaddingException | InvalidKeyException e) {
						
						e.printStackTrace();
					}
					
					
				break;
				case "2": //MUESTRA LA FRASE ORIGINAL Y LA CIFRADA
					if (bytesFraseCif == null) {
						System.out.println("No hay frase cifrada con encriptacion asimetrica, vaya a la opcion 1" );
					}else {
						String fraseCif = new String(bytesFraseCif);
						System.out.println("Frase original : "+ frase );
						System.out.println("Frase cifrada con encriptacion asimétrica :"+ fraseCif);
					}
					
					mostrarMenu();
					entrada = sc.nextLine();
				break;
				
				case "3": //DESCIFRAMOS LA FRASE CON LA CLAVE PRIVADA
					if (bytesFraseCif == null) {
						System.out.println("No hay frase cifrada con encriptacion asimetrica, vaya a la opcion 1" );
					}else {
						
						try {
							//desciframos con la clave Privada -- al haber encriptado con la publica
							descifrador.init(Cipher.DECRYPT_MODE, clave.getPrivate());
							byte[] bytesfraseDescif = descifrador.doFinal(bytesFraseCif);
							
							System.out.println("Descifrada con clave privada ---> "+ new String(bytesfraseDescif));
							
						} catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
						
					mostrarMenu();
					entrada = sc.nextLine();
				break;
				case "4": //ENCRIPTAR COCHE
					//metodo que usa el scanner para crear objeto Coche
					Coche auto = nuevoCoche(sc);
					System.out.println("CREADO "+auto.toString());
					
					//si no hay generador de Claves
					if (generadorclave == null) {
						//instancio un generador de claves
						generadorclave = new GeneradorClave();
						
					} //si no hay KeyGenerator, SecretKey y Cipher
					if(llaveroclavecoche == null) {
						//Utilizo el metodo del generador para crear KeyGenerator, SecretKey y Cipher
						llaveroclavecoche = generadorclave.crearclaveAsimetrica();
					}
					
					//obtengo el par de claves y el descifrador 
					clavecoche = llaveroclavecoche.getClaves();
					descifradorcoche = llaveroclavecoche.getCifrador();
					
					
					try {
						//inicializo el descifrador en modo encriptar, con clave publica
						descifradorcoche.init(Cipher.ENCRYPT_MODE, clavecoche.getPublic());
						
						//metodo para obtener bytes de un Objeto (Coche)
						byte[] bytesCoche = getBytes(auto);
						//Encriptando el Coche
						bytesCocheCif = descifradorcoche.doFinal(bytesCoche);
						
					} catch (IllegalBlockSizeException | BadPaddingException | IOException | InvalidKeyException e) {
						e.printStackTrace();
					}
						
					System.out.println(auto.toString() + " ---> ENCRIPTADO");
					
					if(listaCoches == null) { //si no esta inicializado el arraylist
						//el arrayList está cifrado, por eso es de byte
						listaCoches = new ArrayList<byte[]>();
					}
					//añado al ArrayList el Coche cifrado
					listaCoches.add(bytesCocheCif);
					
					System.out.println("El coche se ha añadido a la lista");
					
					mostrarMenu();
					entrada = sc.nextLine();
					
				break;
				case "5"://MOSTRAR COCHES DE LA LISTA
					
					Object autodes;
					byte[] bytesCocheDescif;
					int cont = 0;
					
					try {
						//cambio el modo del descifrador a Decript con clave Privada
						descifradorcoche.init(Cipher.DECRYPT_MODE, clavecoche.getPrivate());
						
						//for para recorrer el Arraylist de Coches Encriptados
						for(byte[] cochecif: listaCoches){
							cont++;
								
							//para cada coche, se aplica el descifrador, resulta en unos bytes
								bytesCocheDescif = descifradorcoche.doFinal(cochecif);
							//como son bytes de Objetos(Coche), hay que pasar de bytes a formato Objeto
								autodes = bytestoObject(bytesCocheDescif);
							
							//se imprime por pantalla la lista de coches
								System.out.println(cont +"-"+ autodes.toString()+ "-->DESCIFRADO");
						}//fin del for
						
					} catch (IllegalBlockSizeException | BadPaddingException 
							  | ClassNotFoundException | IOException |InvalidKeyException e) {
						e.printStackTrace();
					}

					//arrayde strings que cada string es un json
					System.out.println("--------- FIN LISTA DE COCHES --------");
					mostrarMenu();
					entrada = sc.nextLine();
						
				break; 
				
				case "salir":
					System.out.println("Se cierra el programa");
					continuar = false;
				break;
			
			}
			
			
			}
			//cerrar scanner
			sc.close();
		
		
		
		
		
	}//fin static void

}
