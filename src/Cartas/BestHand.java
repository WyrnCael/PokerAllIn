package Cartas;

import java.util.ArrayList;
import java.util.List;

public class BestHand {
	// Campo de la clase
	private List<Card> bestCards;
	private List<Card> parejas;
	private List<Card> trios;
	private List<Card> poker;
	private List<Card> escaleraFinal;
	private List<List<Card>> escaleras; // En el 0 estara la escalera original
										// y luego por colores segun el valor
										// asociado.
	private List<Card> escaleraDeColor;
	private List<Card> color;
	private List<Card> cartaAlta;
	
	private List<Card> escaleraGutshot;
	
	private Hand hand;
	private int gutshot;
	private boolean esEscaleraGutshot;
	private boolean esEscaleraOpenEnded;
	private int bestValue;
	

	/**
	 * Constructor
	 * 
	 * @param datos El parametro datos definen las cartas que tienen en la mano
	 */
	public BestHand() {
		bestCards = new ArrayList<Card>();
		parejas = new ArrayList<Card>();
		trios = new ArrayList<Card>();
		poker = new ArrayList<Card>();
		escaleraFinal = new ArrayList<Card>();
		escaleras = new ArrayList<List<Card>>();
		escaleras.add(new ArrayList<Card>());
		escaleras.add(new ArrayList<Card>());
		escaleras.add(new ArrayList<Card>());
		escaleras.add(new ArrayList<Card>());
		escaleras.add(new ArrayList<Card>());
		escaleraDeColor = new ArrayList<Card>();
		color = new ArrayList<Card>();
		cartaAlta = new ArrayList<Card>();
		hand = new Hand();
		escaleraGutshot = new ArrayList<Card>();
		gutshot = 0;
		esEscaleraGutshot = false;
		esEscaleraOpenEnded = false;
	}
	
	public void setHand(Hand hand){
		this.hand = hand;
	}

	public void setJugada(Ranking jugada, List<Card> cards) {
		switch (jugada) {
		case HIGH_CARD:
			cartaAlta.addAll(cards);
			break;
		case PAIR:
			parejas.addAll(cards);
			break;
		case THREE_OF_A_KIND:
			trios.addAll(cards);
			break;
		case FOUR_OF_A_KIND:
			poker.addAll(cards);
			break;
		case STRAIGHT:
			escaleraFinal.addAll(cards);
			break;
		case FLUSH:
			color.addAll(cards);
			break;
		case STRAIGHT_FLUSH:
			escaleraDeColor.addAll(cards);
			break;
		default:
			break;
		}
	}

	private Ranking getJugada() {
		bestCards = new ArrayList<Card>();
		if (escaleraDeColor.size() >= 5) {
			
			bestCards.addAll(escaleraDeColor);
			return Ranking.STRAIGHT_FLUSH;
			
		} else if (poker.size() >= 4) {
			
			bestCards.addAll(poker);
			return Ranking.FOUR_OF_A_KIND;
			
		} else if (trios.size() >= 3 && (parejas.size() >= 2 || trios.size() >= 6)) {
			
			bestCards.addAll(trios);
			if(parejas.size() >= 2){
				bestCards.addAll(parejas);
			}
			return Ranking.FULL_HOUSE;
			
		} else if (color.size() >= 5) {
			
			bestCards.addAll(color);
			return Ranking.FLUSH;
			
		} else if (escaleraFinal.size() >= 5) {
			
			bestCards.addAll(escaleraFinal);
			return Ranking.STRAIGHT;
			
		} else if (trios.size() >= 3) {
			
			bestCards.addAll(trios);
			return Ranking.THREE_OF_A_KIND;
			
		} else if (parejas.size() >= 4) {
			
			bestCards.addAll(parejas);
			return Ranking.TWO_PAIR;
			
		} else if (parejas.size() >= 2) {
			
			bestCards.addAll(parejas);
			return Ranking.PAIR;
			
		} else {
			bestCards.add(cartaAlta.get(0));
			return Ranking.HIGH_CARD;
		}
	}
	
	public List<String> getDraws() {
		ArrayList<String> draws = new ArrayList<String>();
		if (escaleraDeColor.size() >= 5) {
			return draws;
		}
		
		if(escaleraDeColor.size() == 4){
			draws.add(Ranking.STRAIGHT_FLUSH.getName());
		}
		
		if (color.size() == 4) {
			
			draws.add(Ranking.FLUSH.getName());
		}
		if (this.bestValue < Ranking.STRAIGHT.getValor() && escaleraGutshot.size() == 4 && this.esEscaleraGutshot) {
			
			draws.add(Ranking.STRAIGHT.getName() + " Gutshot");
		}
		if(this.esEscaleraOpenEnded){
			draws.add(Ranking.STRAIGHT.getName() + " OpenEnded");
		}
		
		return draws;
	}

	public void anadeAEscalera(Card carta) {
		// Comprobamos en la escalera principal que la diferencia es 1 con la
		// ultima insertada.
		// Si no existia
		List<Card> escaleraPrincipal = escaleras.get(0);

		if (escaleraPrincipal.size() == 0) {
			escaleraPrincipal.add(carta);
		}
		if (this.escaleraGutshot.size() == 0) {
			this.escaleraGutshot.add(carta);
		}
		if(escaleraPrincipal.size() == 4){
			this.esEscaleraOpenEnded = true;
		}
		else if (escaleraPrincipal.get(escaleraPrincipal.size() - 1).getValue().getValue()
				- carta.getValue().getValue() == 1) {
			escaleraPrincipal.add(carta);
			this.escaleraGutshot.add(carta);
			if(carta.getValue().getValue() == 2){
				Card as = compruebaSiHayAs();
				escaleraPrincipal.add(as);
			}
		} else if (escaleraPrincipal.get(escaleraPrincipal.size() - 1).getValue().getValue()
				- carta.getValue().getValue() > 1) {
			if(escaleraPrincipal.get(escaleraPrincipal.size() - 1).getValue().getValue()
					- carta.getValue().getValue() > 2) {
				this.escaleraGutshot.clear();
				this.gutshot = 0;
				this.esEscaleraGutshot = false;
			} else{
				if(this.esEscaleraGutshot){
					this.esEscaleraGutshot = false;
					this.escaleraGutshot.remove(this.gutshot);
				} else{
					this.esEscaleraGutshot = true;
					this.gutshot = escaleraPrincipal.size() - 1;
				}
			}
			
			this.escaleraGutshot.add(carta);
			reiniciaEscaleras();
			escaleras.get(0).add(carta);
		}

		// La insertamos en su color si es la siguiente a la ultima, sino
		// empezamos de 0 esa escalera
		int valorAsociado = carta.getSuit().getValorAsociado();
		List<Card> escaleraColorActual = escaleras.get(valorAsociado);
		if (escaleraColorActual.size() == 0) {
			escaleraColorActual.add(carta);
		}
		// Caso especial, que sea 2, por lo que un As del mismo color puede
		// valer:
		else if (escaleraColorActual.get(escaleraColorActual.size() - 1).getValue().getValue()
				- carta.getValue().getValue() == 1) {
			escaleraColorActual.add(carta);
			if(carta.getValue().getValue() == 2){
				Card as = compruebaSiHayAsdeColor(carta.getSuit());
				if (as != null){
					escaleraColorActual.add(as);
				}
			}
		}
		else {
			escaleraColorActual = new ArrayList<Card>();
			escaleraColorActual.add(carta);
		}
		escaleras.set(valorAsociado, escaleraColorActual);
		compruebaEscalera();

	}

	private void compruebaEscalera() {
		if (escaleras.get(0).size() >= 5) {
			// Comprobamos la escalera de color
			for (int i = 1; i <= 4; i++) {
				if (escaleras.get(i).size() >= 5) {
					// Si no habia escalera de color o la actual es superior a
					// la guardada
					if (escaleraDeColor.size() < 5 || escaleraDeColor.get(0).getValue().getValue() < escaleras.get(i)
							.get(0).getValue().getValue()) {
						ArrayList<Card> escaleraColorAux = new ArrayList<Card>();
						for (int j = 0; j < 5; j++) {
							escaleraColorAux.add(escaleras.get(i).get(j));
						}
						escaleraDeColor = escaleraColorAux;
					}
				}
			}
			// Asignamos a la escalera final si es superior a la que habia y es
			// escalera
			if (escaleras.get(0).size() >= 5 && (escaleraFinal.size() < 5
					|| escaleraFinal.get(0).getValue().getValue() < escaleras.get(0).get(0).getValue().getValue())) {
				escaleraFinal = escaleras.get(0);
			}
		}
	}

	private void reiniciaEscaleras() {
		for(int i=0;i<escaleras.size();i++){
			escaleras.get(i).clear();
		}
	}

	private Card compruebaSiHayAs() {
		if(this.hand.getCard(0).getValue() == Value.Ace)
			return this.hand.getCard(0);
		else
			return null;
	}
	
	public List<Card> getCartaAltaList(){
		return this.cartaAlta;
	}

	private Card compruebaSiHayAsdeColor(Suit suit) {
		int i = 0;
		for (i = 0; i < this.hand.getCardsList().size() && i < 4; i++) {
			if (this.hand.getCard(i).getValue() == Value.Ace &&
					suit == this.hand.getCard(i).getSuit())
				return this.hand.getCard(i);
		}
		return null;
	}

	public String toString() {
		Ranking rk = getJugada();
		this.bestValue = rk.getValor();
		String str = rk.getName() + " (";
		for (int i = 0; i < this.bestCards.size() && i < 5; i++) {
			str += this.bestCards.get(i);
		}
		str += ")";
		return str;
	}
}
