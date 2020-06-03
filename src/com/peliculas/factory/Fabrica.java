/**
 * 
 */
/**
 * 
 */
package com.peliculas.factory;

import java.util.ResourceBundle;

/**
 * @author Luis Miguel Alamilla 
 *
 */
public class Fabrica {
	
	public static Object getInstancia(String objName){
		try{
			
			ResourceBundle rb = ResourceBundle.getBundle("com.peliculas.factory.factory");
			String nombreClase=rb.getString(objName);
			//newInstance() is deprecate
			//Object retorna=Class.forName(nombreClase).newInstance();
			Object retorna = Class.forName(nombreClase).getDeclaredConstructor().newInstance();
			return retorna;
		}catch(Exception ex){
			ex.printStackTrace();
			throw new RuntimeException();	
		}
		
	}
}
