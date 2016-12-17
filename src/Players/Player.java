package Players;

import Cards.Hand;
import Game.BestHand;



public abstract class Player {
	
	protected int wonGames;
	
	public abstract void calculateBestHand(String hnd, String commonCards);
	public abstract void setBestHand(BestHand bestHand);
	public abstract BestHand getBestHand();
	/**
	 * El jugador ha ganado una partida
	 */
	public void win(){
		wonGames++;
	}
	public abstract Hand getHand();
	public abstract String getName();
	/**
	 * Devolver el numero de veces que ha ganado el jugador
	 * @return
	 */
	public int getWonGames() {
		return this.wonGames;
	}
	public abstract String toString();
	public void resetGames(){
		this.wonGames = 0;
	}
}
