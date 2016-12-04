package Players;

import Cards.Hand;
import Game.BestHand;

// Lamar a setHand y setBoard para que funcione, en ese orden.
public class Player implements Comparable<Object> {

	// campos de la clase
	String name;
	Hand hand;
	BestHand besthand;
	int wonGames;
	Double equity;

	/**
	 * Constructor
	 * 
	 * @param name
	 *            El parametro name es el nombre(numero) del jugador
	 * @param holeCard
	 *            El parametro cards es las carta que tiene ese jugador
	 */
	public Player(String name) {
		this.name = name;
		wonGames = 0;
	}
	
	public void setHand(Hand hand){
		this.hand = hand;
	}
	
	public void setBoard(Hand board){
		Hand aux = new Hand(board.toString());
		aux.addAll(this.hand.getCardsList());
		this.besthand = new BestHand(aux);
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
	 * El metodo que devuelve el mejor mano del jugador
	 * 
	 * @return this.besthand es el objeto de mejor mano
	 */
	public BestHand getBestHand() {
		return this.besthand;
	}

	/**
	 * El metodo que devuelve el nombre del jugador
	 * 
	 * @return this.name es el nombre del jugador
	 */
	public String getName() {
		return this.name;
	}
	
	public void wonGame(){
		wonGames++;
	}

	public int getWonGames(){
		return wonGames;
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
		return this.name + ": " + this.besthand.getRank().getName() + " ("
				+ this.besthand.getBestHand() + ")";
	}
}
