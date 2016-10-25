package Game;

import java.util.ArrayList;
import java.util.List;

import Cards.Hand;
import Players.Player;

public class BestHandWith2Card extends Game{

	// campos de la clase
	private String gameInfo;
	private String playerCards;
	private String CommonCards;
	private Hand hand;
	private Hand sharedHand;
	private Player player;
	
	/**
	 * Constructor
	 */
	public BestHandWith2Card(){
		
	}
	
	@Override
	public void parseGame(String gameInfo) {
		this.gameInfo = gameInfo;
		playerCards = gameInfo.substring(0, 4);
		CommonCards = gameInfo.substring(7, gameInfo.length());
		this.hand = new Hand(playerCards);
		this.sharedHand = new Hand(CommonCards);
	}

	@Override
	public void processGame() {
		this.player = new Player("", this.hand, new Hand(playerCards + CommonCards));
	}
	
	public String toString(){
		return this.gameInfo + System.getProperty("line.separator") + this.player.getBestHand().toString();
	}

	@Override
	public void clear() {
		
	}

	@Override
	public Hand getSharedHand() {
		// TODO Auto-generated method stub
		return sharedHand;
	}
	
	@Override
	public Hand getHand() {
		// TODO Auto-generated method stub
		return hand;
	}
	
	@SuppressWarnings("serial")
	@Override
	public List<Player> getPlayers() {
		// TODO Auto-generated method stub
		return new ArrayList<Player>(){{ add(player); }};
	}
}
