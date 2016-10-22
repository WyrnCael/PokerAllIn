package GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;

import GUI.Helpers.Controller;

public class MainWindow extends JFrame {

	private static MainWindow instance = null;
	private Controller controller;
	private JPanel panelActual;
	
	public MainWindow(){
		controller = new Controller();
		create();
	}
	
	public static MainWindow getInstance(){
		if (instance == null) {
			instance = new MainWindow();
		}
		return instance;
	}
	
	private void create(){
		panelActual = new OptionPanel();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().add(panelActual);
		
		this.setSize(1000, 550);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
	}
	
	public void changeMainPanel(JPanel newPanel){
		this.getContentPane().removeAll();
		panelActual = newPanel;
		this.getContentPane().add(panelActual);
		
		this.revalidate();
		this.repaint();
		this.setSize(1000, 550);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
	}
}
