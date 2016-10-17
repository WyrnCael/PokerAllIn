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
		private List<List<Card>> escaleras;  // En el 0 estara la escalera original
												  // y luego por colores segun el valor asociado.
		private List<Card> escaleraDeColor;
		private List<Card> color;
		private Card cartaAlta;
		
		/**
		 * Constructor
		 * @param datos El parametro datos definen las cartas que tienen en la mano
		 */
		public BestHand(){
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
		}
		
		public void setJugada(Ranking jugada, List<Card> cards){
			if(jugada == Ranking.HIGH_CARD){
				cartaAlta = cards.get(0);
			}
			else if(jugada == Ranking.PAIR){
				for(int i = 0 ; i < 2; i++){
					parejas.add(cards.get(i));
				}				
			}
			else if(jugada == Ranking.THREE_OF_A_KIND){
				for(int i = 0 ; i < 3; i++){
					trios.add(cards.get(i));
				}				
			}
			else if(jugada == Ranking.FOUR_OF_A_KIND){
				for(int i = 0 ; i < 4; i++){
					poker.add(cards.get(i));
				}				
			}
			else if(jugada == Ranking.STRAIGHT){
				for(int i = 0 ; i < 5; i++){
					escaleraFinal.add(cards.get(i));
				}
			}
			else if(jugada == Ranking.STRAIGHT_FLUSH){
				for(int i = 0 ; i < 5; i++){
					escaleraDeColor.add(cards.get(i));
				}
			}
			else if(jugada == Ranking.FLUSH){
				for(int i = 0 ; i < 5; i++){
					color.add(cards.get(i));
				}
			}
		}
		
		public Ranking getJugada(){
			bestCards = new ArrayList<Card>();
			if(escaleraDeColor.size() >= 5){
				for(int i = 0 ; i < 5; i++){
					bestCards.add(escaleraDeColor.get(i));
				}
				return Ranking.STRAIGHT_FLUSH;
			}
			else if(poker.size() >= 4){
				for(int i = 0 ; i < 4; i++){
					bestCards.add(poker.get(i));
				}				
				return Ranking.FOUR_OF_A_KIND;
			}
			else if(trios.size() >= 3 && (parejas.size() >= 2 || trios.size() >= 6)){
				// Caso doble trio mas pareja, con pareja mayor que segundo trio:
				if(parejas.size() >= 2 && trios.size() >= 6 && parejas.get(0).getValue().getValue() >= trios.get(4).getValue().getValue()){
					for(int i = 0 ; i < 3; i++){
						bestCards.add(trios.get(i));
					}
					
					for(int i = 0 ; i < 2; i++){
						bestCards.add(parejas.get(i));
					}				
				}
				// Caso doble trio mas pareja, con segundo trio mayor pareja:
				else if(parejas.size() >= 2 && trios.size() >= 6 && parejas.get(0).getValue().getValue() < trios.get(4).getValue().getValue()){
					for(int i = 0 ; i < 3; i++){
						bestCards.add(trios.get(i));
					}
					// Cogemos dos de ese trio
					for(int i = 4 ; i < 6; i++){
						bestCards.add(trios.get(i));
					}						
				}
				// Caso doble trio:
				if(trios.size() >= 6){
					for(int i = 0 ; i < 5; i++){
						bestCards.add(trios.get(i));
					}		
				}
				// Caso trio mas pareja:
				else if (parejas.size() >= 2){
					for(int i = 0 ; i < 3; i++){
						bestCards.add(trios.get(i));
					}
					
					for(int i = 0 ; i < 2; i++){
						bestCards.add(parejas.get(i));
					}						
				}
				return Ranking.FULL_HOUSE;
			}
			else if(color.size() >= 5){
				for(int i = 0 ; i < 5; i++){
					bestCards.add(color.get(i));
				}				
				return Ranking.FLUSH;
			}
			else if(escaleraFinal.size() >= 5){
				for(int i = 0 ; i < 5; i++){
					bestCards.add(escaleraFinal.get(i));
				}				
				return Ranking.STRAIGHT;
			}
			else if(trios.size() >= 3){
				for(int i = 0 ; i < 3; i++){
					bestCards.add(trios.get(i));
				}				
				return Ranking.THREE_OF_A_KIND;
			}
			else if(parejas.size() >= 4){
				for(int i = 0 ; i < 4; i++){
					bestCards.add(parejas.get(i));
				}				
				return Ranking.TWO_PAIR;
			}
			else if(parejas.size() >= 2){
				for(int i = 0 ; i < 2; i++){
					bestCards.add(parejas.get(i));
				}				
				return Ranking.PAIR;
			}
			else{
				bestCards.add(cartaAlta);
				return Ranking.HIGH_CARD;
			}
		}
		
		public List<Card> getBestCards(){
			getJugada(); // Para calcular las cartas
			return bestCards;
		}
		
		public void anadeAEscalera(Card carta){
			// Comprobamos en la escalera principal que la diferencia es 1 con la ultima insertada.
			//Si no existia			
			List<Card> escaleraPrincipal = escaleras.get(0);
			
			if(escaleraPrincipal.size() == 0){
				escaleraPrincipal.add(carta);
			}
			// Caso especial, que sea 2, por lo que un As puede valer:
			else if(carta.getValue().getValue() == 2){
				Card as = compruebaSiHayAs();
				if(as != null && escaleraPrincipal.get(escaleraPrincipal.size()-1).getValue().getValue() - carta.getValue().getValue() == 1){ // Añadimos ambas
					escaleraPrincipal.add(carta);
					escaleraPrincipal.add(as);
				}
			}
			else if(escaleraPrincipal.get(escaleraPrincipal.size()-1).getValue().getValue() - carta.getValue().getValue() == 1){
				escaleraPrincipal.add(carta);
			}			
			else if(escaleraPrincipal.get(escaleraPrincipal.size()-1).getValue().getValue() - carta.getValue().getValue() > 1){
				reiniciaEscaleras();
				escaleras.get(0).add(carta);
			}
			
			// La insertamos en su color si es la siguiente a la ultima, sino empezamos de 0 esa escalera
			int valorAsociado = carta.getSuit().getValorAsociado();
			List<Card> escaleraColorActual = escaleras.get(valorAsociado);
			if(escaleraColorActual.size() == 0){
				escaleraColorActual.add(carta);
			}
			// Caso especial, que sea 2, por lo que un As del mismo color puede valer:
			else if(carta.getValue().getValue() == 2){
				Card as = compruebaSiHayAsdeColor(carta.getSuit());
				if(as != null && escaleraColorActual.get(escaleraColorActual.size() - 1).getValue().getValue() - carta.getValue().getValue() == 1){// Añadimos ambas
					escaleraColorActual.add(carta);
					escaleraColorActual.add(as);
				}
			}
			else if(escaleraColorActual.get(escaleraColorActual.size() - 1).getValue().getValue() - carta.getValue().getValue() == 1){
				escaleraColorActual.add(carta);
			}			
			else{
				escaleraColorActual = new ArrayList<Card>();
				escaleraColorActual.add(carta);
			}
			escaleras.set(valorAsociado, escaleraColorActual);
			compruebaEscalera();
			
		}		

		private void compruebaEscalera(){
			if(escaleras.get(0).size() >= 5){
				// Comprobamos la escalera de color
				for(int i = 1 ; i <= 4; i++){					
					if(escaleras.get(i).size() >= 5){
						// Si no habia escalera de color o la actual es superior a la guardada
						if(escaleraDeColor.size() < 5 || escaleraDeColor.get(0).getValue().getValue() < escaleras.get(i).get(0).getValue().getValue()){
							ArrayList<Card> escaleraColorAux = new ArrayList<Card>();
							for(int j = 0; j < 5; j++){
								escaleraColorAux.add(escaleras.get(i).get(j));
							}
							escaleraDeColor = escaleraColorAux;
						}						
					}
				}
				// Asignamos a la escalera final si es superior a la que habia y es escalera
				if(escaleras.get(0).size() >= 5 && ( escaleraFinal.size() < 5 || escaleraFinal.get(0).getValue().getValue() < escaleras.get(0).get(0).getValue().getValue())){
					escaleraFinal = escaleras.get(0);
				}				
			}
		}
		
		private void reiniciaEscaleras(){
			escaleras = new ArrayList<List<Card>>();
			escaleras.add(new ArrayList<Card>()); 
			escaleras.add(new ArrayList<Card>()); 
			escaleras.add(new ArrayList<Card>()); 
			escaleras.add(new ArrayList<Card>()); 
			escaleras.add(new ArrayList<Card>());
		}
		
		private Card compruebaSiHayAs(){
			if(cartaAlta.getValue() == Value.Ace){
				return cartaAlta;
			}
			else if(parejas.size() >= 2){
				if(parejas.get(0).getValue() == Value.Ace){
					return parejas.get(0);
				}				
			}
			else if(trios.size() >= 3){
				if(trios.get(0).getValue() == Value.Ace){
					return trios.get(0);
				}				
			}
			else if(poker.size() >= 4){
				if(poker.get(0).getValue() == Value.Ace){
					return poker.get(0);
				}
			}
			return null;
		}
		
		private Card compruebaSiHayAsdeColor(Suit suit){
			if(cartaAlta.getValue() == Value.Ace && suit == cartaAlta.getSuit()){
				return cartaAlta;
			}
			else if(parejas.size() >= 2){
				if(parejas.get(0).getValue() == Value.Ace){
					// Hay pareja de ases, miramos ambas.
					for(int i = 0; i < 2; i++){
						if(parejas.get(i).getSuit() == suit){
							return parejas.get(i);
						}
					}
				}
			}
			else if(trios.size() >= 3){
				if(trios.get(0).getValue() == Value.Ace){
					// Hay trio de ases, miramos las tres.
					for(int i = 0; i < 3; i++){
						if(trios.get(i).getSuit() == suit){
							return trios.get(i);
						}
					}
				}
			}
			else if(poker.size() >= 4){
				if(poker.get(0).getValue() == Value.Ace){
					// Hay poker de ases, miramos las cuatro.
					for(int i = 0; i < 4; i++){
						if(poker.get(i).getSuit() == suit){
							return poker.get(i);
						}
					}
				}
			}
			return null;
		}
}
