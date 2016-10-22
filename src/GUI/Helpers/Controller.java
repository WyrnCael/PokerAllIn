package GUI.Helpers;

import javax.swing.JPanel;

import FileIO.InputFile;
import FileIO.OutPutFile;
import GUI.MainWindow;

public class Controller {

	private InputFile entrada;
	private OutPutFile salida;
	
	private static Controller instance = null;
	
	public Controller(){
	}
	
	public static Controller getInstance(){
		if (instance == null) {
			instance = new Controller();
		}
		return instance;
	}
	
	public void setMainPanel(JPanel panel){
		MainWindow.getInstance().changeMainPanel(panel);
	}
	
	public void setEntrada(String entrada){
		this.entrada = new InputFile(entrada);
	}
	
	public void setSalida(String salida){
		this.salida = new OutPutFile(salida);
	}
	
	public void closeDatos(){
		entrada.close();
		salida.close();
	}
	
	public String getNextLine(){
		return entrada.readLine();
	}
	
	public void close(){
		closeDatos();
	}
}
