package Players;

import Cards.Hand;
import Game.BestHand;

public class Player implements Comparable<Object> {

	// campos de la clase
	String name;
	BestHand besthand;
	Hand hand;

	/**
	 * Constructor
	 * 
	 * @param name
	 *            El parametro name es el nombre(numero) del jugador
	 * @param cards
	 *            El parametro cards es las carta que tiene ese jugador
	 */
	public Player(String name, Hand cards) {
		this.name = name;
		this.besthand = new BestHand(cards);
		this.hand = cards;
	}
	
	public Player(String name, String cards) {
		this.name = name;		
		this.hand = new Hand(cards);
	}
	
	public BestHand getBestHand(){
		return this.besthand;
	}
	
	public Hand getHand(){
		return this.hand;
	}

	/**
	 * Metodo que compare dos jugadadores
	 */
	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		Player player2 = ((Player) o);
		return this.besthand.compareTo(player2.besthand);
	}

	public String toString() {
		return this.name + ": " + this.besthand.getRank().getName() + " (" + this.besthand.getBestHand() + ")";
	}
}
