package GUI;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import Game.Game;

@SuppressWarnings("serial")
public class BoardPanel extends backgroundPanel {

	public BoardPanel() {
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
