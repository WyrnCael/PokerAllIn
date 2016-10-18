package controlArchivo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

public class Salida {
	
	private String path;
	private FileWriter fw;
	private BufferedWriter bw;
	
	/**
	 * Constructor
	 * 
	 * @param nombreArchivo El parametro Archivo define el nombre del archivo que va a guardar
	 */
	public Salida(String nombreArchivo){
		this.path = "datos/" + nombreArchivo;
		try {
			this.fw = new FileWriter(new File(this.path));
			this.bw = new BufferedWriter(fw);
						
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Metodo que guarda los datos en el archivo
	 * 
	 * @param datos El parametro datos definen los datos que va a guardar en el archivo
	 */
	public void guardarDato(String linea){
		try {
			this.bw.write(linea + "\n");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void close(){
		try {
			this.bw.close();
			this.fw.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
