package modelos;

//Hilo que produce objetos Emails y los introduce en el buffer
public class HiloProductor extends Thread{
	//Atributos nombre del hilo y el buffer donde guardamos Emails
	public String nombrehilo;
	public BufferMails buffer;
	
	//metodo constructor
	public HiloProductor(String nombrehilo, BufferMails buffer) {
		super();
		this.nombrehilo = nombrehilo;
		this.buffer = buffer;
	}

	@Override
	public void run() {
		//instancio objeto productor de emails
		ProductorEmail pe = new ProductorEmail();
		
		//hilo produce 10 emails y los introduce en el buffer
		for(int i=1; i<=10; i++) {
			//creo un objeto email a partir del metodo crearEmail del productor
			 Email email = pe.crearEmail();
			 
			//obtengo el destinatario del email y con el metodo .equals
			 //comparamos los Strings del campo destinatario emails
			if ((email.getDestinatario()).equals("pikachu@gmail.com")){
				System.out.println("El email ID: "+email.getId() +" no se ha enviado a destinatario "+email.getDestinatario()+"\n");
			}else{
			 
				 try {
						//se duerme la ejecucion del hilo 0,5 seg
						Thread.sleep(500);
						//se añade el objeto email al buffer de emails
						buffer.addEmail(email);
						//se imprime por pantalla
						System.out.println("El hilo "+ nombrehilo + " ha introducido el email ID: "+ email.getId());
					
				}catch (InterruptedException e) {
					e.printStackTrace();
				}
		}
				
		}
	}

}
