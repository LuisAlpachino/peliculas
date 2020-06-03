package com.peliculas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collection;
import java.util.Vector;

import com.peliculas.util.Conexion;
import com.peliculas.dto.CompanyDTO;
import com.peliculas.intefaces.ICompany;

public class CompanyDAO implements ICompany{

	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs= null;
	
	@Override
	public boolean save(String nombre) {
		try {
			con = Conexion.getConnection();
			String sql = "INSERT INTO company(nombre) VALUES(?)";
			ps = con.prepareStatement(sql);
			
			ps.setString(1, nombre);
			
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
	public Collection<CompanyDTO> getAll() {
		try {
			con = Conexion.getConnection();
			//Aquí se modifica
			String sql = "SELECT * FROM company";
			ps = con.prepareStatement(sql);
			
			rs = ps.executeQuery();
			//Aquí se modifica
			Vector<CompanyDTO> vector = new Vector<CompanyDTO>();
			
			while(rs.next()) {
				//Aqui se modifica
				CompanyDTO autorFind= new CompanyDTO();
				
				//Aquí se modifica
				autorFind.setId(rs.getInt("id"));
				autorFind.setNombre(rs.getString("nombre"));
				
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
	public boolean update(String nombre, int id) {
		try {
			con = Conexion.getConnection();
			
			//aqui se modifica
			String sql = "UPDATE company SET nombre = ? WHERE id = ? ";
			
			ps = con.prepareStatement(sql);
			
			//aqui se modifica
			ps.setString(1, nombre);
			ps.setInt(2, id);
			
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
			String sql = "DELETE FROM company where id=? ";
			
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
	public CompanyDTO findById(int id) {
		
		try {
			con = Conexion.getConnection();
			
			//aquí se modifica
			String sql = "SELECT * FROM company WHERE id= ? ";
			ps = con.prepareStatement(sql);
			
			//Aqui se modifica 
			ps.setInt(1, id);
			
			rs = ps.executeQuery();
			
			CompanyDTO  autorFind = null;
			
			if(rs.next()) {
				autorFind = new CompanyDTO();
				
				//aqui se cambia referente a la BD
				autorFind.setId(rs.getInt("id"));
				autorFind.setNombre(rs.getString("nombre"));	
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
