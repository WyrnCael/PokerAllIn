package GUI;

import java.awt.Dimension;

import Game.Game;

@SuppressWarnings("serial")
public class BoardPanel extends BackgroundPanel {

	/**
	 * Constructor
	 */
	public BoardPanel() {
		super();
		initGUI();
	}

	/**
	 * El metodo que pinta el panel
	 */
	private void initGUI() {
		this.setLayout(null);

		this.setVisible(true);
	}

	/**
	 * El metodo que actualiza el panel
	 * 
	 * @param game
	 *            El parametro game define las informaciones de la partida
	 */
	public void updateBoardPanel(Game game) {
		this.removeAll();
		
		CommonCardsPanel commonPanel = new CommonCardsPanel(game.getSharedHand());
		this.add(commonPanel);
		commonPanel.setSize(new Dimension(550,150));
		int anchura = super.getWidth();
		int altura = super.getHeight();
		int mitadAnchura =  (int) (anchura / 2);
		int mitadAltura =  (int) (altura / 2);
		commonPanel.setLocation(mitadAnchura - 275, mitadAltura - 75);
		
		int[] xPos = new int[]{(anchura)/2 - 60, (anchura-300)/4 + 50, 50, (anchura-500)/4 + 100, ((anchura-500)/4)*2 + 100, ((anchura-500)/4)*3 + 100, ((anchura-500)/4)*4 + 100, (anchura-200), ((anchura-300)/4)*3 + 50 };
		int[] yPos = new int[]{altura-175, altura-175, (altura/2) - 75, 25, 25, 25, 25, (altura/2) - 75, altura-175};
		
		
		if (game.getPlayers() != null) {
			for (int i = 0; i < game.getPlayers().size(); i++) {
				PlayerPanel player = new PlayerPanel(game.getPlayers().get(i));
				this.add(player);
				player.setSize(new Dimension(120,120));
				player.setLocation(xPos[i],yPos[i]);
			}
		}
		this.revalidate();
		this.repaint();
	}
}
