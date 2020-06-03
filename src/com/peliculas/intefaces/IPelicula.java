package com.peliculas.intefaces;

import java.sql.Date;
import java.util.Collection;


import com.peliculas.dto.CompanyDTO;
import com.peliculas.dto.PeliculaDTO;

public interface IPelicula {
	
	//CREATE
	public boolean save(String titulo, Date lanzamiento, int longitud, String descripcion , CompanyDTO fkcompany );
	//READ
	public Collection<PeliculaDTO> getAll();
	//UPDATE
	public  boolean update(String titulo, Date lanzamiento, int longitud, String descripcion , CompanyDTO fkcompany, int id);
	//DELETE
	public boolean delete(int id);
	//findById
	public PeliculaDTO findById(int id);
}
