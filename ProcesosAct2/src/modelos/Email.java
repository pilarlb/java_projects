package modelos;

public class Email {
	
	private int id;
	private String destinatario;
	private String remitente;
	private String asunto;
	private String mensaje;
	
	
	public Email(int id, String destinatario, String remitente, String asunto, String mensaje) {
		super();
		this.id = id;
		this.destinatario = destinatario;
		this.remitente = remitente;
		this.asunto = asunto;
		this.mensaje = mensaje;
	}
	
	//constructor sin parametros
	public Email() {
		super();
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getDestinatario() {
		return destinatario;
	}


	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}


	public String getRemitente() {
		return remitente;
	}


	public void setRemitente(String remitente) {
		this.remitente = remitente;
	}


	public String getAsunto() {
		return asunto;
	}


	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}


	public String getMensaje() {
		return mensaje;
	}


	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}


	@Override
	public String toString() {
		return "Email [id=" + id + " TO " + destinatario + " FROM " + remitente + "\n--> " + asunto
				+ " mensaje=" + mensaje + "] \n";
	}
	
	
	

}
