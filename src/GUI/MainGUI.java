package GUI;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class MainGUI extends JFrame{
	
	public MainGUI(){
		super("Poker All In");
		initGUI();
	}
	
	private void initGUI(){
		Container container = this.getContentPane();
		container.setLayout(new BorderLayout());
		
		BoardPanel bPanel = new BoardPanel();
		container.add(bPanel, BorderLayout.CENTER);
		
		OptionPanel oPanel = new OptionPanel(bPanel);
		container.add(oPanel, BorderLayout.EAST);
		
		this.setSize(new Dimension(1500,800));
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
