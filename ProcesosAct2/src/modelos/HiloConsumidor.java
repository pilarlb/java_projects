package modelos;

//hilo que extrae objetos Emails del buffer
public class HiloConsumidor extends Thread{
	//
	public String nombrehilo;
	public BufferMails buffer;
	
	//metodo constructor del hilo
	public HiloConsumidor(String nombrehilo, BufferMails buffer) {
		
		this.nombrehilo = nombrehilo;
		this.buffer = buffer;
		
	}

	@Override
	public void run() {
		//Hilo consumidor, como vamos a instanciar 2
		//y cada hilo productor produce 10 emails (y son 3)
		//ponemos 15 extracciones de email
		for(int i=1; i<=15; i++) {
			//Extraemos un objeto Email del buffer mediante el metodo getEmail()
			Email email = buffer.getEmail();
			//aparece por pantalla nombre del hilo y datos del obj Email extraido del buffer
			System.out.println("El hilo "+nombrehilo+" ha extraido "+ email.toString());
		
		}
	}

}
