package controlArchivo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

public class Entrada {
	
	private String path;
	
	/**
	 * Constructor
	 * 
	 * @param nombreArchivo El parametro Archivo define el nombre del archivo que va a guardar
	 */
	public Entrada(String nombreArchivo){
		this.path = "datos/" + nombreArchivo;
	}
	
	/**
	 * Metodo que lea los datos del fichero
	 * 
	 * @return vector de String con los datos del fichero de entrada
	 */
	public Vector<String> leerDatos(){
		Vector<String> datos = new Vector<String>();
		
		try {
			FileReader fr = new FileReader(this.path);
			BufferedReader br = new BufferedReader(fr);
			
			String str = "";
			while((str = br.readLine()) != null){
				datos.add(str);
				System.out.println(str);
			}
			
			br.close();
			fr.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Archivo no encontrado");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error en leer los datos");
		}
		
		return datos;
	}
}
