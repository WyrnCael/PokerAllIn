package Game;

import java.util.List;

import Cards.Hand;
import Players.Player;

public class BestHandWith5Card extends Game{
	
	// Campos de la clase
	private String gameInfo;
	private Hand hand;
	private BestHand bestHand;
	
	/**
	 * Constructor
	 */
	public BestHandWith5Card() {
		
	}

	
	@Override
	public void parseGame(String gameInfo) {
		this.gameInfo = gameInfo;
		this.hand = new Hand(gameInfo);
	}

	@Override
	public void processGame() {
		this.bestHand = new BestHand(this.hand);
	}
	
	public String toString() {
		return this.gameInfo + System.getProperty("line.separator") + this.bestHand.toString();
	}


	@Override
	public void clear() {
		
	}

	@Override
	public Hand getSharedHand() {
		return null;
	}
	
	@Override
	public Hand getHand() {
		// TODO Auto-generated method stub
		return this.hand;
	}

	@Override
	public List<Player> getPlayers() {
		// TODO Auto-generated method stub
		return null;
	}
}
