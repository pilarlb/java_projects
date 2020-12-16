package persistencia;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;

import entidades.Videojuego;

//
public class DaoVideojuego {
	
	public static List<Videojuego> listaVideojuegos;
	public static int contadorId;
	
	//se ejecuta una única vez al comienzo de la app
	static { 
		
		listaVideojuegos = new ArrayList<Videojuego>();
		
		
		Videojuego v1 = new Videojuego(++contadorId, "Hades", "Supergiant Games", new int [] {10});
		Videojuego v2 = new Videojuego(++contadorId,"Life is Strange", "Square Enix",new int [] {10,9,8});
		Videojuego v3 = new Videojuego(++contadorId, "Outer Wilds", "EA",new int [] {7,8,7} );
		Videojuego v4 = new Videojuego(++contadorId,"The Sims","EA", new int [] {7,7});
		Videojuego v5 = new Videojuego(++contadorId,"Age of Empires","Ensemble Studios",new int [] {9,7});
		
		listaVideojuegos.add(v1);
		listaVideojuegos.add(v2);
		listaVideojuegos.add(v3);
		listaVideojuegos.add(v4);
		listaVideojuegos.add(v5);
	}
	
	//Añadir Videojuego a la lista, dar de alta
	public Response add(Videojuego v1) {
		
		boolean validacion = true;
		for(Videojuego v: listaVideojuegos) {
			if (v.getId() == v1.getId()) {
				//no se puede añadir si hay id repetido
				validacion = false;
				
			}else if (v.getNombre().equalsIgnoreCase(v1.getNombre())) {
				//no se puede añadir si hay nombre repetido
				validacion = false;
			}
			
		}
		
		if (validacion) {
			listaVideojuegos.add(v1);
			
			return Response.status(Response.Status.CREATED).entity(v1).build();
		}else {
			//si hay id o nombre repetido 
			return Response.status(Response.Status.BAD_REQUEST).entity(v1).build();
		}
	
		
	}
	
	//Dar de baja Videojuego en la lista según su ID
	public Response delete(int id) {
		Videojuego vid = null;
		for(Videojuego v: listaVideojuegos) {
			if (v.getId() == id) {
				//Igualamos la referencia al videojuego con esa id
				vid = v;
				
			}		
		}
		
		if (vid == null) {
			//el id no ha sido encontrado y por lo tanto no hay videojuego con ese id
			return Response.status(Response.Status.NOT_FOUND).entity(vid).build();
		}else {
			listaVideojuegos.remove(vid);
			return Response.status(Response.Status.ACCEPTED).entity(vid).build();
		}
		
		//return vid;
		
			
	}
	
	//Modificar Videojuego segun ID
	public Response update(Videojuego videoj) {
		Videojuego v0 = null;
		int index = - 1;
		boolean validacion = true;
		
		for(Videojuego v: listaVideojuegos) {
			//Si coincide el nombre no se valida
			if(v.getNombre().equalsIgnoreCase(videoj.getNombre())){
				validacion = false;
				
			//si coincide el id, se modifica el objeto con el mismo id	
			}else if (v.getId() == videoj.getId()) {
				
				index = listaVideojuegos.indexOf(v);
			}		
		}
		
		if (validacion) {
			//SI HAY ID QUE COINCIDA EN LA BBDD
			if (index != -1) {
				v0 = listaVideojuegos.get(index);
				//modificamos los atributos de v 
				//con los atributos del objeto que metemos al metodo
				v0.setNombre(videoj.getNombre());
				v0.setCompania(videoj.getCompania());
				v0.setListanotas(videoj.getListanotas());
				
				return Response.status(Response.Status.ACCEPTED).entity(videoj).build();
			}else {
				//no hay Id que coincida en la bbbdd
				return Response.status(Response.Status.NOT_FOUND).entity(videoj).build();
			}
		//Si coincide el nombre en la bbdd, no se valida
		}else {
			
			return Response.status(Response.Status.BAD_REQUEST).entity(videoj).build();
		}

		
	}
	
	//Obtener Videojuego por ID
	public Response get(int id) {
		Videojuego v0 = null;
		
		for(Videojuego v: listaVideojuegos) {
			if (v.getId() == id) {				
				//Referencio al objeto con v0
				v0 = v;
			}		
		}
		//si es null, es que no está esa id
		if(v0 == null) {
			return Response.status(Response.Status.NOT_FOUND).entity(v0).build();
		}else {
			
			return Response.status(Response.Status.ACCEPTED).entity(v0).build();
		}
			
	}
	
	//Obtener Videojuego por Compañia 
	//porque la compañia se puede repetir
	public Response getByCompania(String compania){
			
		ArrayList<Videojuego> listVideoAux = new ArrayList<Videojuego>();
			//for para recorrerse el array y buscar en cada atributo de cada objeto
			for(Videojuego v: listaVideojuegos) {
				
				if(v.getCompania().equalsIgnoreCase(compania)) {
				
					listVideoAux.add(v);
					
				}
			}
			//si no se añade ningun Videojuego al arraylist
			if(listVideoAux.size()== 0) {
				return Response.status(Response.Status.NOT_FOUND).entity(listVideoAux).build();
				
			}else {
				
				return Response.status(Response.Status.ACCEPTED).entity(listVideoAux).build();
			}
		}
	
	//Listar todos los Videojuegos
	public List<Videojuego> list(){
		return listaVideojuegos;
	}
	
	
	
	
	

}
