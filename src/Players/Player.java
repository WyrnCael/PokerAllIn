package Players;

import Cards.Hand;
import Game.BestHand;

public class Player implements Comparable<Object> {

	// campos de la clase
	String name;
	Hand hand;
	BestHand bestHand;
	int wonGames;

	/**
	 * Constructor
	 * 
	 * @param name
	 *            El parametro name es el nombre(numero) del jugador
	 * @param holeCard
	 *            El parametro cards es las carta que tiene ese jugador
	 */
	public Player(String name, Hand holeCard) {
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
	 * El jugador ha ganado una partida
	 */
	public void win() {
		wonGames++;
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
	 * Devolver el numero de veces que ha ganado el jugador
	 * @return
	 */
	public int getWonGames() {
		return this.wonGames;
	}

	/**
	 * Metodo que compare dos jugadadores
	 */
	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		Player player2 = ((Player) o);
		return this.name.compareTo(player2.name);
	}

	public String toString() {
		return this.name + ": "+ this.wonGames ;
	}
}
