/**
 * 
 */
package com.peliculas.api;

import java.io.IOException;
import java.util.Collection;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;

import org.json.JSONObject;

import com.google.gson.Gson;
import com.peliculas.dto.PeliculaDTO;
import com.peliculas.factory.Fabrica;
import com.peliculas.intefaces.ICompany;
import com.peliculas.intefaces.IPelicula;



/**
 * @author ADMIN
 *
 */
@Path("peliculas")
@Provider
public class PeliculaResource implements ContainerResponseFilter {
	
	@Context
	private UriInfo context;
	
	private static String SUCCESS="success";
	
	public PeliculaResource() {
		
	}
	
	@GET
	@Path("all")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPeliculas() {
		try {
			
			IPelicula  iPelicula = (IPelicula) Fabrica.getInstancia("PELICULA");
			Collection<PeliculaDTO> users = iPelicula.getAll();
			
			String json = new Gson().toJson(users);
			
			return Response.ok(json, MediaType.APPLICATION_JSON).build();
			
		} catch (Exception e) {
			return Response.status(Response.Status.SEE_OTHER).entity("Error: "+ e.toString()).build();
		}
	}
	
	@POST
	@Path("create")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addUser(PeliculaDTO pelicula) {
		try {
			IPelicula iPelicula = (IPelicula) Fabrica.getInstancia("PELICULA");
			ICompany iCompany = (ICompany) Fabrica.getInstancia("COMPANY");
			
			if(iPelicula.save(pelicula.getTitulo(), 
							  pelicula.getLanzamiento(), 
							  pelicula.getLongitud(), 
							  pelicula.getDescripcion(),
							  iCompany.findById(pelicula.getFkcompany()))){
				return Response.ok(json(SUCCESS),MediaType.APPLICATION_JSON).build();
			}else {
				return Response.status(Response.Status.SEE_OTHER).entity("Error: User no create").build();
			}
 			
		} catch (Exception e) {
			return Response.status(Response.Status.SEE_OTHER).entity("Error: "+ e.toString()).build();

		}
	}
	
	
	@PUT
	@Path("update/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response update(PeliculaDTO pelicula, @PathParam("id") int id) {
		
		ICompany iCompany = (ICompany) Fabrica.getInstancia("COMPANY");
		try {
			pelicula.setId(id);
			IPelicula iPelicula = (IPelicula) Fabrica.getInstancia("PELICULA");
			iPelicula.update(
					  pelicula.getTitulo(), 
					  pelicula.getLanzamiento(), 
					  pelicula.getLongitud(), 
					  pelicula.getDescripcion(),
					  iCompany.findById(pelicula.getFkcompany()),
					  pelicula.getId()
					);
			return Response.ok(json(SUCCESS),MediaType.APPLICATION_JSON).build();
			
		} catch (Exception e) {
			return Response.status(Response.Status.SEE_OTHER).entity("Error: "+ e.toString()).build();
		}
	}
	
	
	@DELETE
	@Path("delete/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response delete(@PathParam("id") int id) {
		try {
			IPelicula iPelicula = (IPelicula) Fabrica.getInstancia("PELICULA");
			
			if(iPelicula.delete(id)) {
				return Response.ok(json(SUCCESS),MediaType.APPLICATION_JSON).build();
			}else {
				return Response.status(Response.Status.SEE_OTHER).entity("Error: Usuario no Eliminado").build();

			}
			
		} catch (Exception e) {
			return Response.status(Response.Status.SEE_OTHER).entity("Error: "+ e.toString()).build();
		}
	}
	
	private String json(String status) {
		String json = "";
		JSONObject obj = new JSONObject();
		obj.put("status", status);
		json =""+obj;
		return json;
	}

	@Override
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
		responseContext.getHeaders().add( "Access-Control-Allow-Origin", "*");
		responseContext.getHeaders().add("Access-Control-Allow-Headers", "origin, content-type, accept, authorization");
		responseContext.getHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");	
	}
	
	
}
