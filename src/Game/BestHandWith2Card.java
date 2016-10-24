package Game;

import java.util.ArrayList;
import java.util.List;

import Cards.Hand;
import Players.Player;

public class BestHandWith2Card extends Game{

	private Hand hand;
	private BestHand bestHand;
	private Hand sharedHand;
	private Player player;
	
	public BestHandWith2Card(){
		
	}
	
	@Override
	public void parseGame(String gameInfo) {
		String HoleCards = gameInfo.substring(0, 4);
		String CommunityCards = gameInfo.substring(7, gameInfo.length());
		this.hand = new Hand(HoleCards);
		this.bestHand = new BestHand(new Hand(HoleCards + CommunityCards));
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

	@Override
	public Hand getSharedHand() {
		// TODO Auto-generated method stub
		return sharedHand;
	}
	
	@Override
	public List<Player> getPlayers() {
		// TODO Auto-generated method stub
		return new ArrayList<Player>(){{ add(player); }};
	}

}
