import modelos.BufferMails;
import modelos.HiloConsumidor;
import modelos.HiloProductor;


public class Main {

	public static void main(String[] args) {
		//instancio objeto buffer
		BufferMails buffer = new BufferMails();
		
		//instancio objetos hilos productores
		HiloProductor p1 = new HiloProductor("Pontevedra",buffer);
		HiloProductor p2 = new HiloProductor("Pamplona",buffer);
		HiloProductor p3 = new HiloProductor("Paris",buffer);
		
		//instancio objetos hilos consumidores
		HiloConsumidor h1 = new HiloConsumidor("Cordoba",buffer);
		HiloConsumidor h2 = new HiloConsumidor("Cadiz",buffer);
		
		//inicio hilos productores
		p1.start();
		p2.start();
		p3.start();
		//inicio hilos consumidores
		h1.start();
		h2.start();
		
		
		
	}

}
