package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BoardPanel extends backgroundPanel {

//	private Image backgroundImage;
	private HandPanel handPanel;
	private HandPanel bestHandPanel;
	
	public BoardPanel() {
//		backgroundImage = new ImageIcon(".\\resources\\img\\poker_board.png").getImage();
		super();
		initGUI();
	}

	private void initGUI() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		this.setVisible(true);
	}
	
	public void setHandPanel(HandPanel handPanel){
		this.handPanel = handPanel;
	}
	
	public void setBestHandPanel(HandPanel bestHandPanel){
		this.removeAll();
//		JLabel bestHandLabel = new JLabel("Best hand");
//		this.add(bestHandLabel);
		this.bestHandPanel = bestHandPanel;
		this.bestHandPanel.setPreferredSize(new Dimension(550,150));
		this.add(this.bestHandPanel);
		this.revalidate();
//		this.repaint();
//		this.repaint();
	}
	
}
