package com.itt.arte;

public class Teatro extends Local implements Sala {
	
	private Obra obra;
	private double precio;
	private Espectador[][] localidades;
	
	//Obra es agregada, por lo tanto Obra obra como parametro esta bien
	public Teatro(String domicilio, int metros, int accesos, Obra obra, double precio) {
		super(domicilio, metros, accesos);
		this.obra = obra;
		this.precio = precio;
		//Espectador es componente, relacion fuerte
		this.localidades = new Espectador[5][10];
	}
	//HECHO
	public String verProgramacion() {
		return "Hoy representamos "+ this.getObra().getTitulo()
				+", genero: "+this.getObra().getGenero()
				+", Duracion: "+ this.getObra().getMinutosDuracion()
				+ "\nEn "+ this.getDomicilio()
				+ ", local de "+ this.getMetros()
				+" metros, con "+this.getAccesos()
				+" accesos\nPrecio: "+this.getPrecio();
	}
	
	//HECHO
	public String verLocalidades() {
		//creamos variable texto
		String texto="";
		texto = texto.concat("\n");
		
		// Recorremos el array mostrando los valores en pantalla.
		for (int f=0; f<this.localidades.length; f++) {
			for (int c=0; c<this.localidades[f].length; c++) {
				
				texto = texto.concat(f+"."+c+" ");
				
				//Si la plaza está libre u ocupada
				String estado = (this.localidades[f][c]==null) ? "Libre  " : "Ocupada";
				estado = estado.concat(" "); 
				texto = texto.concat(estado);	
					}
				//al cambiar de fila, retorno de carro
				texto = texto.concat("\n");
					
					}return texto;
	}
	
	//HECHO
	public String verLocalidadesOcupadas() {

		String texto ="";
		//este string se modifica si hay ocupación
		for (int f=0; f<this.localidades.length; f++) {
			for (int c=0; c<this.localidades[f].length; c++) {
				
			//Si la plaza está ocupada...
				if (!(this.localidades[f][c]==null)) {
					
					texto = texto.concat( f+"."+c+" "
					+this.localidades[f][c].getNombre()
					+", tlf: "+this.localidades[f][c].getTlf()
					+", tipo: "+this.localidades[f][c].rangoEdad()
					+"\n");
				}
			}
			}return texto;
	}
	//HECHO
	public String venderLocalidad(int fila, int butaca, Espectador e) {
		String texto; 
		if (this.localidades[fila][butaca]==null) {
			//asociar plaza libre a espectador
			this.localidades[fila][butaca]= e;
			
			//Calcular precio entrada con descuento de edad
			double precioEntrada = this.precioFinalEntrada(fila, butaca);
			
			texto = "Se ha vendido la localidad "+fila+"."+butaca+
					" a "+ e.getNombre()+ " por "+ precioEntrada +" euros";
			
		}else {
			texto ="Esta localidad "+fila+"."+butaca+" está ocupada";
		}
		
		return texto;	
	}
	//COMPROBAR
	public String cancelarLocalidad(int fila, int butaca) {
		
		String texto;
		if (this.localidades[fila][butaca]!=null) {
			String nombre = this.localidades[fila][butaca].getNombre();
			texto=nombre +" ha cancelado su reserva";
			//se cancela la asociacion butaca nombre
			this.localidades[fila][butaca]=null;
			
		}else {
			texto= "Error, la localidad "+fila+"."+butaca+" no está reservada.";
		}return texto+"\n";
	}
	//HECHO
	public String consultarLocalidad(int fila, int butaca) {
		
		String texto;
		//ver si la plaza está ocupada
		if (this.localidades[fila][butaca]!=null) {
		
		//informacion sobre Espectador
		String nombre =this.localidades[fila][butaca].getNombre();
		String tlf = this.localidades[fila][butaca].getTlf();
		String tipo = this.localidades[fila][butaca].rangoEdad();
		
		//llamo al metodo para calcular el precio de la entrada con descuento
		double precioEntrada = this.precioFinalEntrada(fila, butaca);
		
			texto = "Localidad ocupada por "+ nombre
				+", tlf: "+tlf
				+", Tipo: "+ tipo
				+", Precio: "+ precioEntrada;
		}else{
			texto= "La localidad "+fila+"."+butaca+" está libre";
		}return texto;
	
	}
	
	//HECHO---
	public double calcularRecaudacion() {
		double totaleuros = 0;
		int cont=0;
		for (int f=0; f<this.localidades.length; f++) {
			for (int c=0; c<this.localidades[f].length; c++) {
				//si están las plazas ocupadas
				if (this.localidades[f][c]!=null) {
					
					totaleuros = totaleuros + this.precioFinalEntrada(f, c);
					cont++;
			}
			}
		}
		//System.out.println("Se han vendido "+contador+ " entradas\n");
		return totaleuros;	
	}
	//METODO EXTRA AÑADIDO
	public double precioFinalEntrada(int fila, int butaca) {
		
		double precioEntrada =this.getPrecio();
		String tipoEntrada = this.localidades[fila][butaca].rangoEdad();
		
			//calcular precio a partir de entrada
		    switch(tipoEntrada) {
				case "INFANTIL":
					//descuento 50%
					precioEntrada= precioEntrada*0.5;
					break;
				case "MENOR":
					//descuento 20%
					precioEntrada = precioEntrada*0.8;
					break;
				case "MAYOR":
					//System.out.println("No hay descuento\n");
					break;
				case "JUBILADO":
					//descuento 66%
					precioEntrada = precioEntrada*0.44;
					break;
					}
		    
		return precioEntrada;
		
	}
	
	
	
	//set and get
	public Obra getObra() {
		return obra;
	}

	public void setObra(Obra obra) {
		this.obra = obra;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public Espectador[][] getLocalidades() {
		return localidades;
	}

	public void setLocalidades(Espectador[][] localidades) {
		this.localidades = localidades;
	}
	
	
	
	
	
}
