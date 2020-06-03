package com.peliculas.dto;

import java.io.Serializable;
import java.sql.Date;


public class PeliculaDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String titulo;
	private Date lanzamiento;
	private int longitud;
	private String descripcion;
	private int fkcompany;
	
	public PeliculaDTO() {
		
	}
	
public PeliculaDTO(String titulo, Date lanzamiento, int longitud, String descripcion , int fkcompany ) {
		
	    this.titulo= titulo;
		this.lanzamiento= lanzamiento;
		this.longitud = longitud;
		this.descripcion =descripcion;
		this.fkcompany = fkcompany;
	}
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public Date getLanzamiento() {
		return lanzamiento;
	}
	public void setLanzamiento(Date lanzamiento) {
		this.lanzamiento = lanzamiento;
	}
	public int getLongitud() {
		return longitud;
	}
	public void setLongitud(int longitud) {
		this.longitud = longitud;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public int getFkcompany() {
		return fkcompany;
	}
	public void setFkcompany(int fkcompany) {
		this.fkcompany = fkcompany;
	}
	
	

}
