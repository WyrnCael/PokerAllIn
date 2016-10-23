package Game;

import Cards.Hand;

public class BestHandWith2Card extends Game{

	private Hand hand;
	private BestHand bestHand;
	
	public BestHandWith2Card(){
		
	}
	
	@Override
	public void parseGame(String gameInfo) {
		String str = gameInfo.substring(0, 4);
		str += gameInfo.substring(7, gameInfo.length());
		this.hand = new Hand(str);
	}

	@Override
	public void processGame() {
		this.bestHand = new BestHand(this.hand);
	}
	
	public String toString(){
		return this.hand + System.getProperty("line.separator") + this.bestHand.toString();
	}

	@Override
	public void clear() {
		
	}

	@Override
	public BestHand getBestHand() {
		return this.bestHand;
	}

}
