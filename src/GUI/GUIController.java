package GUI;

import FileIO.InputFile;
import FileIO.OutPutFile;

public class GUIController {
	private InputFile inputFile;
	private OutPutFile outputFile;
	
	public GUIController(String path){
		this.inputFile = new InputFile(path);
		this.outputFile = new OutPutFile("datos/SalidaGUI.txt");
	}
	
	public String readLine(){
		return this.inputFile.readLine();
	}
	
	public void close(){
		this.inputFile.close();
		this.outputFile.close();
	}
}
