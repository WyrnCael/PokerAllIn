package controlArchivo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

public class Salida {
	
	private String path;
	
	/**
	 * Constructor
	 * 
	 * @param nombreArchivo El parametroArchivo define el nombre del archivo que va a guardar
	 */
	public Salida(String nombreArchivo){
		this.path = "datos/" + nombreArchivo;
	}
	
	/**
	 * Metodo que guarda los datos en el archivo
	 * 
	 * @param datos El parametro datos definen los datos que va a guardar en el archivo
	 */
	public void guardarDatos(Vector<String> datos){
		try {
			FileWriter fw = new FileWriter(new File(this.path));
			BufferedWriter bw = new BufferedWriter(fw);
			for(int i = 0; i< datos.size(); i++){
				bw.write(datos.get(i) + "\n");
			}
			
			bw.close();
			fw.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
