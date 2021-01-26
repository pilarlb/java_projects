 package modelos;
 
import java.util.LinkedList;
import java.util.Queue;

public class BufferMails {
	
	
	//el máximo del emails que almacena el buffer es 5
	public final static int MAX_ELEMENTOS = 5;
	
	//El buffer es un almacen, queremos usar FIFO
	//FIFO: first in, first out
	//Para ello usaremos una lista circular al que meteremos objetos Email
	private Queue<Email> buffer = new LinkedList<>();
	
	//metodo por el que se añaden Email al buffer
	//sincronizado para que no esten dos metodos 
	//simultaneamente tocando el buffer
	public synchronized void addEmail (Email email) {
		
		//Cuando se haya alcanzado el num maximo 
		//de Emails en el buffer, no añadir mas Emails
		while(buffer.size() == MAX_ELEMENTOS) {
			try {
				// wait es una parada indeterminada de tiempo
				// asociada a bloque(s) sincronizado(s)
				//tiene que estar dentro de un while
				wait();
			}catch (InterruptedException e) {
				e.printStackTrace();
			}	
		}
		
		//Si no se ha alcanzado el num max de Emails
		//se añade un email al buffer
		buffer.offer(email);
		
		//notify avisa a todos los hilos status=wait()
		//que despierten (tanto de este método como del resto de métodos
		//que tengan wait())
		notify();
	}
	
	//metodo por el que se sacan Email del buffer
	//sincronizado para que no esten dos metodos 
	//simultaneamente tocando el buffer
    public synchronized Email getEmail(){
    	//Variable em de tipo Email
    	//que es el objeto que vamos a sacar del buffer
    	Email em = null;
    	
    	//Mientras el buffer no tenga Emails
    	//manda al método esperar
    	while(buffer.size() == 0) {
    		try {
    			wait();
    		}catch(InterruptedException e){
    			e.printStackTrace();
    		}
    	}
    	
    	//sacar del buffer un objeto Email
    	em = buffer.poll();
    	
    	notify();
    	//devuelve objeto Email sacado del buffer
    	return em;
    		
    }
    
}
    

