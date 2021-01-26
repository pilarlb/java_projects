import com.itt.arte.*;
import java.util.Scanner;


public class Principal {
		
		 private static Scanner lector;

         private static Obra obra =

              new Obra("La cena de los idiotas", "Comedia", 95);

         private static Teatro teatro = new Teatro("C/ Sol, 45", 300, 2, obra, 30);
         
         //metodo main
         public static void main(String[] args) {

                       lector = new Scanner(System.in);

                       char opc;

                       do {

                                    opc = mostrarMenu();

                                    switch (opc) {

                                                  case '1':
													System.out.println(teatro.verProgramacion()+"\n");
													break;

                                                  case '2':
                                                	System.out.println(teatro.verLocalidades()+"\n");
  													break;
  													
                                                  case '3':
                                           
                                                	  System.out.println(teatro.verLocalidadesOcupadas()+"\n");
                                                	  break;
                                                	  
                                                  case '4': 
                                                	  //inicializar variables
                                                	  String respuesta;
                                                	  int fila;
                                                	  int butaca;
                                                	  //argumentos para crear Espectador
                                                	  String name;
                                                	  String tlf;
                                                	  int edad;
                                                	  //instancia de Espectador creada, los argumentos se modifican más adelante
                                                	  Espectador e = new Espectador("","",0);
                                                	  
                                                	  System.out.println("¿En qué fila quieres sentarte (0-4)?");
                                                	  //lector recibe Strings, asi que hay que traducir el dato a Integer
                                                	  respuesta = lector.nextLine();
                                                	  fila = Integer.parseInt(respuesta);
                                                	  //comprobar rango fila 0-4
                                                	  while(!(fila>=0 && fila<=4)) {
                                                		  System.out.println("Introduza fila (0-4)");
                                                		  respuesta = lector.nextLine();
                                                    	  fila = Integer.parseInt(respuesta);
                                                	  }
                                                	  
                                                	  System.out.println("¿Y en qué butaca (0-9)?");
                                                	  respuesta = lector.nextLine();
                                                	  butaca = Integer.parseInt(respuesta);
                                                	  //Comprobar rango butaca 0-9
                                                	  while(!(butaca>=0 && butaca<=9)) {
                                                		  System.out.println("Introduza butaca (0-9)");
                                                		  respuesta = lector.nextLine();
                                                    	  butaca = Integer.parseInt(respuesta);
                                                	  }
                                                	
                                                	  System.out.println("¿Cómo se llama?");
                                                	  
                                                	  name = lector.nextLine();
                                                	  name = name.toUpperCase();
                                                	  //modificamos la propiedad del objeto Espectador
                                                	  e.setNombre(name);
                                                	  
                                                	  System.out.println("¿Su teléfono?");
                                                	  tlf = lector.nextLine();
                                                	  //rango longitud del tlf
                                                	  while(!(tlf.length()==9)) {
                                                		  System.out.println("Introduzca teléfono válido");
                                                		  tlf = lector.nextLine();
                                                	  }
                                                	  //modificamos la propiedad del objeto Espectador
                                                	  e.setTlf(tlf);
                                                	  
                                                	  System.out.println("¿Cuántos años tiene?");
                                                	  respuesta = lector.nextLine();
                                                	  edad = Integer.parseInt(respuesta);
                                                	  
                                                	  while(!(edad>=0 && edad<=100)) {
                                                		  System.out.println("Introduzca edad válida");
                                                    	  respuesta = lector.nextLine();
                                                    	  edad = Integer.parseInt(respuesta);
                                                	  }
                                                	  //modificamos la propiedad del objeto Espectador 
                                                	  e.setEdad(edad);
                                                	  
                                                	  //pasamos los argumentos conseguidos con las respuestas al método
                                                	  System.out.println(teatro.venderLocalidad(fila, butaca, e)+"\n");
                                                	
                                                	 break;
                                                  case '5':
                                                	  System.out.println("¿Fila (0-4)?");
                                                	  respuesta = lector.nextLine();
                                                	  fila = Integer.parseInt(respuesta);
                                                	  
                                                	  System.out.println("¿Butaca (0-9)?");
                                                	  respuesta = lector.nextLine();
                                                	  butaca = Integer.parseInt(respuesta);
                                                	  
                                                	  System.out.println(teatro.cancelarLocalidad(fila, butaca)+"\n");
                                                	  break;
                                                	  
                                                  case '6':
                                                	  System.out.println("¿Fila (0-4)?");
                                                	  respuesta = lector.nextLine();
                                                	  fila = Integer.parseInt(respuesta);
                                                	  
                                                	  System.out.println("¿Butaca (0-9)?");
                                                	  respuesta = lector.nextLine();
                                                	  butaca = Integer.parseInt(respuesta);
                                                	  
                                                	  System.out.println(teatro.consultarLocalidad(fila, butaca)+"\n");
                                                	  break;
                                                  case '7':
                                                	  System.out.println("Recaudacion: "+teatro.calcularRecaudacion()+"\n");
                                                	  break;
                                                  case '8':
                                                	  //Se cierra el programa
                                                	  System.out.println("Hasta pronto\n");
                                                	  break;
                                                  default:
                                                	  System.out.println("Opción Incorrecta\n");
                                                	  break; 
                                     }

                         } while (opc!='8');

                         lector.close();

}

public static char mostrarMenu() {

         String opcion;

         System.out.println ("TEATRO LA BOMBILLA DE DON BLAS");

         System.out.println ("------------------------------");

         System.out.println ("1. Ver la programación actual");

         System.out.println ("2. Mostrar todas las localidades");

         System.out.println ("3. Mostrar localidades ocupadas");

         System.out.println ("4. Vender localidad");

         System.out.println ("5. Cancelar localidad");

         System.out.println ("6. Consultar localidad");

         System.out.println ("7. Calcular recaudación");

         System.out.println ("8. Terminar programa");

         System.out.println ("------------------------------");

         System.out.println ("¿Qué opción deseas?");

         opcion = lector.nextLine();

         return opcion.charAt(0); // Devuelvo el primer caracter tecleado.

      }

	}


