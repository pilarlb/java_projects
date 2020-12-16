package entidades;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

public class GeneradorClave {
	
	
	public ClaveSimetrica crearclaveSimetrica() {
		ClaveSimetrica llaveroclave = new ClaveSimetrica();
		KeyGenerator generador = generarKeyGenerator();
		llaveroclave.setGenerador(generador);
		llaveroclave.setClave(generarSecretKey(generador));
		llaveroclave.setDescifrador(generarCipher());
		return llaveroclave ;
	};

	//metodo para generar KeyGenerator
	public KeyGenerator generarKeyGenerator() {
		KeyGenerator generador = null;
		try {
			generador = KeyGenerator.getInstance("AES");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return generador;
	}
	//metodo para generar SecretKey
	public SecretKey generarSecretKey(KeyGenerator generador) {
		SecretKey clave = generador.generateKey();
		return clave;
	}
	
	//metodo para generar Cipher
	public Cipher generarCipher() {
		Cipher descifrador = null;
		try {
			descifrador = Cipher.getInstance("AES");
		} catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return descifrador;
	}
	
	//metodo que genera Clave Asimetrica
	public ClaveAsimetrica crearclaveAsimetrica() {
		ClaveAsimetrica llaveroclaves = new ClaveAsimetrica();
		KeyPairGenerator generador = null;
		KeyPair claves = null;
		Cipher cifrador = null;
		try {
			generador = KeyPairGenerator.getInstance("RSA");
			claves = generador.generateKeyPair();
			cifrador = Cipher.getInstance("RSA");
		} catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		llaveroclaves.setGenerador(generador);
		llaveroclaves.setClaves(claves);
		llaveroclaves.setCifrador(cifrador);
		return llaveroclaves;
		
	}
	
}
