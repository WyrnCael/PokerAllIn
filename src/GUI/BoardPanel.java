package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Cards.Hand;
import Players.Player;

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
		this.setLayout(new GridBagLayout());	
		
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
		GridBagConstraints c = new GridBagConstraints();
		this.add(this.bestHandPanel, c);		
		this.revalidate();
		this.repaint();
//		this.repaint();
	}
	
	public void setPlayersAndCommunityCards(Hand community, List<Player> players){
		if(players == null){
			setBestHandPanel(new HandPanel(community));
		}
		else{
			this.removeAll();
			this.bestHandPanel = new HandPanel(community);
			this.bestHandPanel.setPreferredSize(new Dimension(550,150));
			GridBagConstraints c = new GridBagConstraints();
			c.gridx = 0; c.gridy = 0; c.gridwidth = players.size();
			this.add(this.bestHandPanel, c);	
			
			c.gridwidth = 1;
			for (int i = 0; i < players.size(); i++){
				c.gridx = i; c.gridy = 1; 
				PlayerPanel player = new PlayerPanel(players.get(i).getHand());
				this.add(player, c);	
			}
			this.revalidate();
			this.repaint();
		}
	}
	
}
