/**
 * 
 */
package com.peliculas.api;

import java.util.Set;

import javax.ws.rs.core.Application;

/**
 * @author ADMIN
 *
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application{
	

	@Override
	public Set<Class<?>> getClasses(){
		
		Set<Class<?>> resources = new java.util.HashSet<>();
		addRestResourceClasses(resources);
		return resources;
	}

	private void addRestResourceClasses(Set<Class<?>> resources) {
		resources.add(com.peliculas.api.PeliculaResource.class);
	}
	
}
