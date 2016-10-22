package Game;

import Cards.Hand;

public class BestHandWith5Card extends Game{
	
	// Campos de la clase
	private Hand hand;
	private BestHand bestHand;
	
	/**
	 * Constructor
	 */
	public BestHandWith5Card() {
		
	}

	
	@Override
	public void parseGame(String gameInfo) {
		this.hand = new Hand(gameInfo);
	}

	@Override
	public void processGame() {
		this.bestHand = new BestHand(this.hand);
	}
	
	public String toString() {
		return this.hand + System.getProperty("line.separator") + this.bestHand.toString();
	}


	@Override
	public void clear() {
		
	}
}
