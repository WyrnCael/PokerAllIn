package Players;

import Cards.Hand;
import Game.BestHand;

public class player implements Comparable<Object> {

	// campos de la clase
	String name;
	BestHand besthand;

	/**
	 * Constructor
	 * 
	 * @param name
	 *            El parametro name es el nombre(numero) del jugador
	 * @param cards
	 *            El parametro cards es las carta que tiene ese jugador
	 */
	public player(String name, Hand cards) {
		this.name = name;
		this.besthand = new BestHand(cards);
	}

	@Override
	public int compareTo(Object arg1) {
		// TODO Auto-generated method stub
		Hand jugada1 = this.besthand.getBestHand();
		Hand jugada2 = ((player) arg1).besthand.getBestHand();
		int cardValue1;
		int cardValue2;
		int pos = 0;
		int k = 1;

		// Si una jugada es mejor se devuelve la mejor
		if (this.besthand.getRank().getValue() > ((player) arg1).besthand.getRank().getValue()) {
			return -1;
		} else if (this.besthand.getRank().getValue() < ((player) arg1).besthand.getRank().getValue()) {
			return 1;
		}
		// Si son iguales se compara por jugada.
		else {
			switch (this.besthand.getRank().getValue()) {
			// Carta alta
			case 1:
				k = 1;
				break;
			// Pareja y trio.
			case 2:
			case 4:
				// Comprobamos la mayor pareja o trio
				cardValue1 = jugada1.getCardsList().get(0).getValue().getValue();
				cardValue2 = jugada2.getCardsList().get(0).getValue().getValue();
				if (cardValue1 > cardValue2) {
					return -1;
				} else if (cardValue1 < cardValue2) {
					return 1;
				}
				pos = 2;
				k = 1;
				break;
			// Doble pareja
			case 3:

				// Miramos la primera pareja
				k = 1;
				break;
			// Escalera, color o escalera de color
			case 5:
			case 6:
			case 9:
				// Comprobamos la escalera o color mas alto
				k = 5;
				// Full house
			case 7:
				// Miramos el trio
				k = 3;

				// Poker
			case 8:
				// Miramos el poker
				k = 4;
			default:
				break;
			}
		}
		for (int i = pos; i < 5; i = i + k) {
			cardValue1 = jugada1.getCardsList().get(i).getValue().getValue();
			cardValue2 = jugada2.getCardsList().get(i).getValue().getValue();
			if (cardValue1 > cardValue2) {
				return -1;
			} else if (cardValue1 < cardValue2) {
				return 1;
			}
		}

		return 0;
	}

	public String toString() {
		return this.name + ": " + this.besthand.getRank().getName() + " (" + this.besthand.getBestHand() + ")";
	}
}
