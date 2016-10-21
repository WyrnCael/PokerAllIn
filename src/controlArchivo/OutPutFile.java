package controlArchivo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class OutPutFile {
	
	// Campos de la clase
	private String path;
	private FileWriter fw;
	private BufferedWriter bw;
	
	/**
	 * Constructor
	 * 
	 * @param fileName El parametro fileName define el nombre del archivo que va a guardar
	 */
	public OutPutFile(String fileName){
		this.path = "datos/" + fileName;
		try {
			this.fw = new FileWriter(new File(this.path));
			this.bw = new BufferedWriter(fw);
						
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error en abrir el archivo de salida");
		}
	}
	
	/**
	 * Metodo que guarda el resultado de un mano
	 * @param result El parametro result es el resultado calculado de un mano
	 */
	public void saveResult(String result){
			try {
				this.bw.write(result + "\n");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("Error en escribir datos");
			}
	}
	
	/**
	 * Metodo que cierra el archivo de salida
	 */
	public void close(){
			try {
				this.bw.close();
				this.fw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("Error en cerrar el archivo");
			}
	}
}
