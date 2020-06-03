package com.peliculas.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ResourceBundle;

public class Conexion {
	
	private static Connection con = null;
	
	public static Connection getConnection() {
		try
		{
			if (con == null)
			{
				ResourceBundle rb = ResourceBundle.getBundle("com.peliculas.util.jdbc");
				String driver = rb.getString("driver");
				String url = rb.getString("url");
				String usr = rb.getString("usr");
				String pwd = rb.getString("pwd");
				Class.forName(driver);
				con = DriverManager.getConnection(url,usr,pwd);
			}
			return con;
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			throw new RuntimeException("Error al crear la conexión",ex);
		}
	}
	
	static class MishDwnHook extends Thread{
		//cerrar conexion;
		public void run() {
			try {
				Connection con = Conexion.getConnection();
				con.close();
			} catch (Exception ex) {
				ex.printStackTrace();
				throw new RuntimeException(ex);
			}
		}
	}
}
