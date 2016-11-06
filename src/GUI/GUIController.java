package GUI;

import java.io.File;

import FileIO.InputFile;
import FileIO.OutPutFile;

public class GUIController {
	
	// Campos de la clase
	private InputFile inputFile;
	private OutPutFile outputFile;
	
	/**
	 * Constructor
	 * @param path El parametro path es el directorio del fichero
	 */
	public GUIController(String path){
		this.inputFile = new InputFile(path);
		this.outputFile = new OutPutFile("datos" + File.separator + "SalidaGUI.txt");
	}
	
	/**
	 * El metodo que lea una linea desde fichero de la entrada
	 * @return una linea de datos
	 */
	public String readLine(){
		return this.inputFile.readLine();
	}
	
	/**
	 * Metodo que guarda el resultado de un mano
	 * 
	 * @param result
	 *            El parametro result es el resultado calculado de un mano
	 */
	public void saveResult(String result) {
		this.outputFile.saveResult(result);
	}
	
	/**
	 * El metodo que cierra los ficheros
	 */
	public void close(){
		this.inputFile.close();
		this.outputFile.close();
	}
}
