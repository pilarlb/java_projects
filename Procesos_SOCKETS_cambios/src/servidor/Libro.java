package servidor;

import java.util.Arrays;

public class Libro {

	private String isbn;
	private String titulo;
	private String[] autor;
	private double[] precio;
	

	//CONSTRUCTORES
	public Libro() {
		
	}
	public Libro(String isbn, String titulo, String autor, double precio) {
		
		this.isbn = isbn;
		this.titulo = titulo;
		this.autor = new String[] {autor};
		this.precio = new double[] {precio};
	}
	
	public Libro(String isbn, String titulo, String[] autor, double precio) {
		
		this.isbn = isbn;
		this.titulo = titulo;
		this.autor = autor;
		this.precio = new double[] {precio};
	}
	
    public Libro(String isbn, String titulo, String autor, double[] precio) {
		
		this.isbn = isbn;
		this.titulo = titulo;
		this.autor = new String[] {autor};
		this.precio = precio;
	}
	public Libro(String isbn, String titulo, String[] autor, double[] precio) {
		super();
		this.isbn = isbn;
		this.titulo = titulo;
		this.autor = autor;
		this.precio = precio;
	}

	//GETTERS Y SETTERS
	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String[] getAutor() {
		return autor;
	}
	
	public void setAutor(String[] autor) {
		this.autor = autor;
	}
	//creo metodo para no tener que crear array 
	public void setAutorSinArray (String autor) {
		this.autor = new String[] {autor};
	}
	
	public double[] getPrecio() {
		return precio;
	}

	public void setPrecio(double[] precio) {
		this.precio = precio;
	}
	
	public void setPrecioArrayString(String[] precios) {
		
		double [] preciosD = {};
		
		for(int i = 0; i<precios.length; i++) {
			//convierto String a Double
			double num = Double.parseDouble(precios[i]);
			//meto en el Array Double
			preciosD[i] = num;
		}
		
		this.precio = preciosD;
	}
	
	public void setPrecioUnicoConString(String precio) {
		double num = Double.parseDouble(precio);
		//introduzco el double al array
		double [] preciosD = {num};	
		this.precio = preciosD;
	}
	
	@Override
	public String toString() {
		return "Libro ---> ISBN: " + isbn + " TITULO: " + titulo + " AUTOR: " + Arrays.toString(autor) + " PRECIO: "
				+ Arrays.toString(precio) + "<---";
	}

	


	
	
	
	
	
}
