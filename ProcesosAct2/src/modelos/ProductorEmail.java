package modelos;
import java.util.concurrent.ThreadLocalRandom;
import java.util.ArrayList;
import java.util.List;

public class ProductorEmail {
	
	//metodo que instancia un objeto Email
	// y completa todos sus atributos
	//generando cada campo a través de su método correspondiente
	public Email crearEmail() {
		//instancia objeto Email sin parametros
		Email email = new Email();
		email.setId(generarId());
		email.setDestinatario(generarDestinatario());
		email.setRemitente(generarRemitente());
		email.setAsunto(generarAsunto());
		email.setMensaje(generarMensaje());
		return email;
	}
	
	//metodo que genera ID, un numero entre 10000 y 99999 (no incluido limite superior)
	public int generarId() {
		int numero = ThreadLocalRandom.current().nextInt(10000,100000);
		return numero;
	}
	
	//generar email Destinatario
	public String generarDestinatario() {
		List<String> listasus = new ArrayList<String>();
		
		listasus.add("Pantera");
		listasus.add("Muerte");
		listasus.add("pikachu");
		listasus.add("Barriga");
		listasus.add("Tormenta");
		listasus.add("Mascara");
		listasus.add("Gacela");
		listasus.add("Sombra");
		listasus.add("Bestia");
		listasus.add("Venganza");
		listasus.add("Florecilla");
		listasus.add("Aguila");
		listasus.add("Juventud");
		listasus.add("Serpiente");
		listasus.add("Masa");
		listasus.add("Niebla");
		listasus.add("Pesadilla");
		listasus.add("Medusa");
		listasus.add("Quimera");
		listasus.add("Gamba");
		listasus.add("Mirada");
		
		
		List<String> listaadj = new ArrayList<String>();
		
		listaadj.add("Dorada");
		listaadj.add("Sanguinaria");
		listaadj.add("");
		listaadj.add("Letal");
		listaadj.add("Demoniaca");
		listaadj.add("Implacable");
		listaadj.add("Audaz");
		listaadj.add("Destructora");
		listaadj.add("Rauda");
		listaadj.add("Fantasmal");
		listaadj.add("Suicida");
		listaadj.add("Bailonga");
		listaadj.add("Mugrienta");
		listaadj.add("Amorosa");
		listaadj.add("Infernal");
		listaadj.add("Sideral");
		listaadj.add("Salvaje");
		listaadj.add("Fornida");
		listaadj.add("Colosal");
		listaadj.add("Roedora");
		listaadj.add("Abismal"); 
		
		
		//De 0 a 19 porque en el ArrayList se empieza el contador
		//de elementos en 0 (hay 20 sustantivos y adjetivos)
		//en nextInt se introduce el limite superior(no incluido en la generacion de aleatorios)
		//Para probar el if de pikachu, establece el nextInt en 3
		int num1 = ThreadLocalRandom.current().nextInt(20);
		int num2 = ThreadLocalRandom.current().nextInt(20);
		
		String des = listasus.get(num1)+listaadj.get(num2)+ "@gmail.com";
		
		return des;
	}
	
	//Metodo que genera remitente a partir de la combinacion de 
	//dos Arraylist aleatoriamente
	public String generarRemitente() {
		
		List<String> listamit = new ArrayList<String>();
		
		listamit.add("ciclope");
		listamit.add("basilisco");
		listamit.add("centauro");
		listamit.add("fenix");
		listamit.add("gigante");
		listamit.add("pegaso");
		listamit.add("leviatan");
		listamit.add("fauno");
		listamit.add("dragon");
		
		List<String> listaadj = new ArrayList<String>();
		
		listaadj.add("dudoso");
		listaadj.add("intuitivo");
		listaadj.add("airoso");
		listaadj.add("jocoso");
		listaadj.add("dudoso");
		listaadj.add("ocurrente");
		listaadj.add("civico");
		listaadj.add("modosito");
		listaadj.add("agraciado");
		
		//Generar numero aleatorio del 0 al 8 (limite superior 9, introducido en nextInt), que son los elementos del ArrayList
		int num1 = ThreadLocalRandom.current().nextInt(9);
		int num2 = ThreadLocalRandom.current().nextInt(9);
		
		String rem = listamit.get(num1) + listaadj.get(num2) + "@mail.com";
		return rem;
		
		
	}
	
	//Método que genera Asunto del email seleccionando de un ArrayList aleatoriamente
	public String generarAsunto() {
		List<String> listaasun = new ArrayList<String>();
		
		
		listaasun.add("¿Esto no te lleva a ninguna parte?");
		listaasun.add("Estoy en tu ciudad ¡Conozcamonos!");
		listaasun.add("Reveladores hechos sobre Hilos que te haran pensar");
		listaasun.add("¡Termina esta noche! Tu ultima oportunidad");
		listaasun.add("Evita la muerte por enterramiento en buenas ideas");
		listaasun.add("La razón por la que perdi mi ultima apuesta");
		listaasun.add("Consejo de mi padre para quedar siempre bien");
		listaasun.add("Me hacen una pregunta un poco rara");
		listaasun.add("3 remedios inefectivos para el dolor de cabeza");
		listaasun.add("La receta mas absurda que he leído jamas");
		listaasun.add("Como ser mas libre");
		listaasun.add("Tienes pocos problemas.En serio");
		listaasun.add("Que hacer si tu 2020 no esta yendo como esperabas");
		listaasun.add("La enorme ventaja de tardar mucho en empezar algo");
		listaasun.add("Que hacer si has malgastado tu juventud");
		listaasun.add("Razones de peso para no comprarse un BMW");
		listaasun.add("Una de mis frases favoritas de todos los tiempos");
		
		//Generar numero aleatorio entre 0 y 17(sin incluir)
		int num = ThreadLocalRandom.current().nextInt(17);
		return listaasun.get(num);
	}
	
	//Metodo que genera mensaje aleatorio de una lista de Arraylist
	public String generarMensaje() {
		List<String> listamen = new ArrayList<String>();
		
		listamen.add("Cupcake ipsum dolor sit amet soufflé apple pie tootsie roll cotton candy.");
		listamen.add("My favorite word is I enjoy food Vampire Weekend my friends tell me they don't get why I'm single rock climbing.");
		listamen.add("I starred in my own reality show with morals with lots of self-respect no robots posing as real people everything destructive that I do crossfit.");
		listamen.add("Zombie ipsum reversus ab viral inferno, nam rick grimes malum cerebro. De carne lumbering animata corpora quaeritis. ");
		listamen.add("I value art snowboarding as friends. I hate lists listening to music Game of Thrones vegetarian ");
		
		//Generar numero entre 0 y 4, los elementos del ArrayList
		//nextInt()es con limite superior no incluido
		int num = ThreadLocalRandom.current().nextInt(5);
		
		return listamen.get(num);
		
	}
	
}
