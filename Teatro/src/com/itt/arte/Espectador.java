package com.itt.arte;

public class Espectador {
	
	private String nombre;
	private String tlf;
	private int edad;
	
	//constructor
	public Espectador(String nombre, String tlf, int edad) {
		
		this.nombre = nombre;
		this.tlf = tlf;
		this.edad = edad;
	}
	
	//metodos propios
	public String rangoEdad() {
		if(edad<=12)
			return "INFANTIL";
		else if (edad>=13 && edad<18)
			return "MENOR";
		else if (edad>=18 && edad<65)
			return "MAYOR";
		else
			return "JUBILADO";
				
/*
 * 0 a 12 años: INFANTIL
13 a 17 años: MENOR
18 a 64 años: MAYOR
>= 65: JUBILADO
 */
	}
	
	@Override
	public String toString() {
		return "Espectador [nombre=" + nombre + ", tlf=" + tlf + ", edad=" + edad + "]";
	}

	//get and set de atributos
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getTlf() {
		return tlf;
	}
	public void setTlf(String tlf) {
		this.tlf = tlf;
	}
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
	
	
}
