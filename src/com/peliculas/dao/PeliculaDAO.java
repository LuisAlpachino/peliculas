package com.peliculas.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collection;

import java.util.Vector;

import com.peliculas.util.Conexion;
import com.peliculas.dto.CompanyDTO;
import com.peliculas.dto.PeliculaDTO;
import com.peliculas.intefaces.IPelicula;

public class PeliculaDAO implements IPelicula {

	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs= null;
	
	@Override
	public boolean save(String titulo, Date lanzamiento, int longitud, String descripcion , CompanyDTO fkcompany) {
		try {
			con = Conexion.getConnection();
			String sql = "INSERT INTO pelicula(titulo, anio_lanzamiento, longitud_minutos, descripcion, fkcompany) VALUES(?,?,?,?,?)";
			ps = con.prepareStatement(sql);
			
			ps.setString(1, titulo);
			ps.setDate(2, lanzamiento);
			ps.setInt(3, longitud);
			ps.setString(4, descripcion);
			ps.setInt(5, fkcompany.getId());
			
			if(ps.executeUpdate() == 1) {
				return true;
			}else {
				return false;
			}
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			try {
				if(ps != null) ps.close();
			} catch (Exception e2) {
				e2.printStackTrace();
				throw new RuntimeException(e2);
			}
		}
		
	}

	@Override
	public Collection<PeliculaDTO> getAll() {
		try {
			con = Conexion.getConnection();
			//Aquí se modifica
			String sql = "SELECT * FROM pelicula";
			ps = con.prepareStatement(sql);
			
			rs = ps.executeQuery();
			//Aquí se modifica
			Vector<PeliculaDTO> vector = new Vector<PeliculaDTO>();
			
			while(rs.next()) {
				//Aqui se modifica
				PeliculaDTO autorFind= new PeliculaDTO();
				
				
				//Aquí se modifica
				autorFind.setId(rs.getInt("id"));
				autorFind.setTitulo(rs.getString("titulo"));
				autorFind.setLanzamiento(rs.getDate("anio_lanzamiento"));
				autorFind.setLongitud(rs.getInt("longitud_minutos"));
				autorFind.setDescripcion(rs.getString("descripcion"));
				
				//aqui se declara la instacia
				autorFind.setFkcompany(rs.getInt("fkcompany"));
				vector.add(autorFind);
			}
			return vector;
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		finally {
			try {
				if(rs != null) rs.close();
				if(ps != null) ps.close();
			} catch (Exception e2) {
				e2.printStackTrace();
				throw new RuntimeException(e2);
			}
		}
	}

	@Override
	public boolean update(String titulo, Date lanzamiento, int longitud, String descripcion , CompanyDTO fkcompany, int id) {
		try {
			con = Conexion.getConnection();
			
			//aqui se modifica
			String sql = "UPDATE pelicula SET titulo=?, anio_lanzamiento=?, longitud_minutos=?, descripcion=?, fkcompany=? WHERE id = ? ";
			
			ps = con.prepareStatement(sql);
			
			ps.setString(1, titulo);
			ps.setDate(2, lanzamiento);
			ps.setInt(3, longitud);
			ps.setString(4, descripcion);
			ps.setInt(5, fkcompany.getId());
			ps.setInt(6, id);
			
			if(ps.executeUpdate() == 1) {
				return true;
			}else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		finally {
			try {
				if(rs != null) rs.close();
				if(ps != null) ps.close();
			} catch (Exception e2) {
				e2.printStackTrace();
				throw new RuntimeException(e2);
			}
		}
	}

	@Override
	public boolean delete(int id) {
		try {
			con = Conexion.getConnection();
			String sql = "DELETE FROM pelicula where id=? ";
			
			ps = con.prepareStatement(sql);
			
			ps.setInt(1,id);
			
			if(ps.executeUpdate() == 1) {
				return true;
			}else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		finally {
			try {
				if(rs != null) rs.close();
				if(ps != null) ps.close();
			} catch (Exception e2) {
				e2.printStackTrace();
				throw new RuntimeException(e2);
			}
		}
		
	}

	@Override
	public PeliculaDTO findById(int id) {
		
		try {
			con = Conexion.getConnection();
			
			//aquí se modifica
			String sql = "SELECT * FROM pelicula WHERE id= ? ";
			ps = con.prepareStatement(sql);
			
			//Aqui se modifica 
			ps.setInt(1, id);
			
			rs = ps.executeQuery();
			
			PeliculaDTO  autorFind = null;
			
			if(rs.next()) {
				autorFind = new PeliculaDTO();
				
				//Aquí se modifica
				autorFind.setId(rs.getInt("id"));
				autorFind.setTitulo(rs.getString("titulo"));
				autorFind.setLanzamiento(rs.getDate("anio_lanzamiento"));
				autorFind.setLongitud(rs.getInt("longitud_minutos"));
				autorFind.setDescripcion(rs.getString("descripcion"));
			}
			return autorFind;
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		finally {
			try {
				if(rs != null) rs.close();
				if(ps != null) ps.close();
			} catch (Exception e2) {
				e2.printStackTrace();
				throw new RuntimeException(e2);
			}
		}
		
	}

}
