package webservices;


import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;


import entidades.Videojuego;
import persistencia.DaoVideojuego;

@Path("videojuegos")
public class WebServiceVideojuegos {
	
	DaoVideojuego daovj = new DaoVideojuego();
	//http://localhost:8082/Procesos_4_REST_videojuego/rest/videojuegos
	
	//Alta de videojuego
	@POST
	@Consumes({"application/json"})
	@Produces({"application/json"})
	//Response del tipo javax.ws.rs.core.Response
	public Response altaVideojuego(Videojuego videoj) {
		System.out.println("altaVideojuego: " + videoj);
		
		return daovj.add(videoj);
		
	}
	
	//Dar de baja videojuego segun ID
	@DELETE
	@Path("/{id}")
	@Produces({"application/json"})
	public Response borrarVideojuego(@PathParam("id") int id) {
		Videojuego vid = null;
		System.out.println("ID del videojuego a borrar " + id);
		
		 daovj.delete(id);
		 //System.out.println("BORRADO -->"+vid.toString());
		 
		 return Response.status(Response.Status.ACCEPTED).entity(vid).build();
		
	}
	
	//Modificar Videojuego segun ID
	@PUT
	@Path("/{id}")
	@Consumes({"application/json"})
	@Produces({"application/json"})
	public Response modificarVideojuego(@PathParam("id") int id, Videojuego videoj) {
		System.out.println("Id del videojuego a modificar "+ id);
		System.out.println("Datos a modificar "+ videoj);
		
		videoj.setId(id);
		
		return daovj.update(videoj);
		
	}
	
	
	//listar videojuegos
	@GET
	@Produces({"application/json"})
	public List<Videojuego> listarVideojuegos() {
		
			return daovj.list();
		
	}
	//obtener videojuego por compania
	//http://localhost:8082/Procesos_4_REST_videojuego/rest/videojuegos/query?compania=EA
	@GET
	@Path("/query")
	@Produces({"application/json"})
	public Response getByCompania(@QueryParam("compania") String compania){
		System.out.println("Buscando videojuego de la compania "+ compania);

		return daovj.getByCompania(compania);
		
		
	}
	
	
	
	//obtener videojuego por id
	@GET
	@Path("/{id}")
	@Produces({"application/json"})
	public Response getVideojuego(@PathParam("id") int id) {
		System.out.println("Buscando videojuego con id: "+ id);
		
		return daovj.get(id);
	
	}
}
