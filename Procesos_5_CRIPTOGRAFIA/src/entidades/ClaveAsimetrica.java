package entidades;

import java.security.KeyPair;
import java.security.KeyPairGenerator;

import javax.crypto.Cipher;

public class ClaveAsimetrica {
	
	private KeyPairGenerator generador;
	private KeyPair claves;
	private Cipher cifrador;
	
	
	public ClaveAsimetrica() {
	}
	public ClaveAsimetrica(KeyPairGenerator generador, KeyPair claves, Cipher cifrador) {
		
		this.generador = generador;
		this.claves = claves;
		this.cifrador = cifrador;
	}
	
	
	public KeyPairGenerator getGenerador() {
		return generador;
	}
	public void setGenerador(KeyPairGenerator generador) {
		this.generador = generador;
	}
	public KeyPair getClaves() {
		return claves;
	}
	public void setClaves(KeyPair claves) {
		this.claves = claves;
	}
	public Cipher getCifrador() {
		return cifrador;
	}
	public void setCifrador(Cipher cifrador) {
		this.cifrador = cifrador;
	}
	
	
	
	
}
