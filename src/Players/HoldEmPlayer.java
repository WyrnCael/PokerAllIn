package Players;

import Cards.Hand;
import Game.BestHand;

public class HoldEmPlayer extends Player implements Comparable<Object> {

	// campos de la clase
	String name;
	Hand hand;
	BestHand bestHand;


	/**
	 * Constructor
	 * 
	 * @param name
	 *            El parametro name es el nombre(numero) del jugador
	 * @param holeCard
	 *            El parametro cards es las carta que tiene ese jugador
	 */
	public HoldEmPlayer(String name, Hand holeCard) {
		this.name = name;
		this.hand = holeCard;
		wonGames = 0;
	}
	
	/**
	 * Poner el mejor mano del jugador
	 * @param bestHand El mejor mano
	 */
	public void setBestHand(BestHand bestHand) {
		this.bestHand = bestHand;
	}
	
	/**
	 * Coger el mejor mano del jugador
	 * @return El mejor mano
	 */
	public BestHand getBestHand() {
		return bestHand;
	}

	/**
	 * El metodo que devuelve la mano del jugador
	 * 
	 * @return this.hand es el objeto de la mano
	 */
	public Hand getHand() {
		return this.hand;
	}


	/**
	 * El metodo que devuelve el nombre del jugador
	 * 
	 * @return this.name es el nombre del jugador
	 */
	public String getName() {
		return this.name;
	}
	
	

	/**
	 * Metodo que compare dos jugadadores
	 */
	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		HoldEmPlayer player2 = ((HoldEmPlayer) o);
		return this.name.compareTo(player2.name);
	}

	public String toString() {
		return this.name + ": "+ this.wonGames ;
	}

	@Override
	public void calculateBestHand(String hnd, String commonCards) {
		//NO hace nada aqui
		
	}
}
