package GUI.Helpers;

import javax.swing.JPanel;

import GUI.MainWindow;

public class Controller {

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
}
