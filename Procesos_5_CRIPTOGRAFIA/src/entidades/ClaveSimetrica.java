package entidades;



import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;

import javax.crypto.SecretKey;
//CLAVE DE TIPO AES
public class ClaveSimetrica {
	
		private KeyGenerator generador;
		private SecretKey clave;
		private Cipher descifrador;
		
		public ClaveSimetrica () {
		}
		
		public ClaveSimetrica(KeyGenerator generador, SecretKey clave, Cipher descifrador) {
			
			this.generador = generador;
			this.clave = clave;
			this.descifrador = descifrador;
		}
		
		//GETTERS AND SETTERS
		public KeyGenerator getGenerador() {
			return generador;
		}

		public void setGenerador(KeyGenerator generador) {
			this.generador = generador;
		}

		public SecretKey getClave() {
			return clave;
		}

		public void setClave(SecretKey clave) {
			this.clave = clave;
		}

		public Cipher getDescifrador() {
			return descifrador;
		}

		public void setDescifrador(Cipher descifrador) {
			this.descifrador = descifrador;
		}
		
		
		
}
