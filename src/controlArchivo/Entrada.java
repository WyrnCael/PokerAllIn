package controlArchivo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Entrada {
	
	private String path;
	private FileReader fr;
	private BufferedReader br;
	
	/**
	 * Constructor
	 * 
	 * @param nombreArchivo El parametro Archivo define el nombre del archivo que va a guardar
	 */
	public Entrada(String nombreArchivo){
		this.path = "datos/" + nombreArchivo;
		
		try {
			fr = new FileReader(this.path);
			br = new BufferedReader(fr);			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Archivo no encontrado");
		}
	}
	
	/**
	 * Metodo que lea los datos del fichero
	 * 
	 * @return vector de String con los datos del fichero de entrada
	 */
	public String leerDato(){
		String str = null;
		try {			
			str = br.readLine();
			//System.out.println(str);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error en leer los datos");
		}
		
		return str;
	}
	
	public void close(){
		try {
			br.close();
			fr.close();			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error en leer los datos");
		}
	}
}
