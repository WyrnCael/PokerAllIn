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
import Game.Game;
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
	
	public void updateBoardPanel(Game game) {
		GridBagConstraints c = new GridBagConstraints();
		this.removeAll();
		if (game.getPlayers() == null) {
			this.add(new HandPanel(game.getHand()), c);
		} else {
			c.gridx = 0;
			c.gridy = 0;
			c.gridwidth = game.getPlayers().size();
			this.add(new HandPanel(game.getSharedHand()), c);

			c.gridwidth = 1;
			for (int i = 0; i < game.getPlayers().size(); i++) {
				c.gridx = i;
				c.gridy = 1;
				PlayerPanel player = new PlayerPanel(game.getPlayers().get(i).getHand());
				this.add(player, c);
			}
		}
		this.revalidate();
		this.repaint();
	}
}
