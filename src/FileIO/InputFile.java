package FileIO;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class InputFile {

	// campos de la clase
	private String path;
	private FileReader fr;
	private BufferedReader br;

	/**
	 * Constructor
	 * 
	 * @param fileName
	 *            El parametro fileName define el nombre del archivo que va a
	 *            guardar
	 */
	public InputFile(String fileName) {
		this.path = "datos/" + fileName;

		try {
			fr = new FileReader(this.path);
			br = new BufferedReader(fr);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Archivo no encontrado");
		}
	}

	/**
	 * Metodo que lea una linea del datos de entrada desde archivo
	 * 
	 * @return str es la linea leida desde la entrada
	 */
	public String readLine() {
		String str = null;
		try {
			str = br.readLine();
			// System.out.println(str);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error en leer los datos");
		}

		return str;
	}

	/**
	 * Metodo que cierra el archivo de entrada
	 */
	public void close() {
		try {
			br.close();
			fr.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error en leer los datos");
		}
	}
}
