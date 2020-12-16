package entidades;

import java.io.Serializable;
import java.util.Arrays;

public class Videojuego implements Serializable {

	
	private static final long serialVersionUID = -56604463373635131L;
	private int id;
	private String nombre;
	private String compania;
	private int[] listanotas;
	
	//CONSTRUCTORES
	public Videojuego() {
		
	}
	
	public Videojuego(int id, String nombre, String compania, int[] listanotas) {
		
		this.id = id;
		this.nombre = nombre;
		this.compania = compania;
		this.listanotas = listanotas;
	}


	//GETTERS Y SETTERS
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCompania() {
		return compania;
	}

	public void setCompania(String compania) {
		this.compania = compania;
	}

	public int[] getListanotas() {
		return listanotas;
	}

	public void setListanotas(int[] listanotas) {
		this.listanotas = listanotas;
	}

	@Override
	public String toString() {
		return "Videojuego [id=" + id + ", nombre=" + nombre + ", compania=" + compania + ", listanotas="
				+ Arrays.toString(listanotas) + "]";
	}

	
	
}
