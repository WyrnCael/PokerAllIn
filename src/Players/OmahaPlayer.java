package Players;

import java.util.ArrayList;
import java.util.List;

import com.sun.org.apache.bcel.internal.generic.NEWARRAY;

import Cards.Card;
import Cards.Hand;
import Game.BestHand;

public class OmahaPlayer implements Comparable<Object> {

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
	public OmahaPlayer(String name, Hand hand) {
		this.name = name;
		this.hand = hand;
		wonGames = 0;
		
	}
	
	/**
	 * Poner el mejor mano del jugador
	 * @param bestHand El mejor mano
	 */
	public void calculateBestHand(String hnd, String commonCards) {
		Hand hand = new Hand(hnd); 
		Card carta1, carta2, carta3, carta4;
		carta1 = hand.getCard(0);
		carta2 = hand.getCard(1);
		carta3 = hand.getCard(2);
		carta4 = hand.getCard(3);
		
		List<BestHand> mejorMano = new ArrayList<BestHand>();
		
		mejorMano.add(0, new BestHand(new Hand(carta1.toString() + carta2.toString() + commonCards)));
		mejorMano.add(1, new BestHand(new Hand(carta1.toString() + carta3.toString() + commonCards)));
		mejorMano.add(2, new BestHand(new Hand(carta1.toString() + carta4.toString() + commonCards)));
		mejorMano.add(3, new BestHand(new Hand(carta2.toString() + carta3.toString() + commonCards)));
		mejorMano.add(4, new BestHand(new Hand(carta2.toString() + carta4.toString() + commonCards)));
		mejorMano.add(5, new BestHand(new Hand(carta3.toString() + carta4.toString() + commonCards)));
		
		this.bestHand = mejorMano.get(0);
		for(int i = 1; i < 6; i++){
			if(this.bestHand.compareTo(mejorMano.get(i)) > 0){
				this.bestHand = mejorMano.get(i);
			}
		}
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
		OmahaPlayer player2 = ((OmahaPlayer) o);
		return this.name.compareTo(player2.name);
	}

	public String toString() {
		return this.name + ": "+ this.wonGames ;
	}
}
