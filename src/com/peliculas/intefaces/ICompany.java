package com.peliculas.intefaces;

import java.util.Collection;

import com.peliculas.dto.CompanyDTO;

public interface ICompany {
	
	//CREATE
		public boolean save(String nombre);
		//READ
		public Collection<CompanyDTO> getAll();
		//UPDATE
		public  boolean update(String nombre, int id);
		//DELETE
		public boolean delete(int id);
		//findById
		public CompanyDTO findById(int id);

}
