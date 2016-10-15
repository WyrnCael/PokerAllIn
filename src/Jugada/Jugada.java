package Jugada;

import java.util.*;

import Cartas.Card;
import Cartas.Hand;

public class Jugada {

	private Hand manoActual;
	private Vector<String> draws;
	private Hand mejorJugada;
	
	public void compruebaMano(Hand actual, Hand mejorJugada,Vector<String> draws ){
		
		/* INICIO EJEMPLO PARA HACER COMPARACIONES DEL VALOR (POINT) */
		
		// Cogemos una carta
		Card carta = actual.getCartas().get(0);
		
		// El valor se mira asi:
		carta.getValue().getValor(); // Quizas habria que cambiar el nombre
									 // a una de las dos funciones para evitar
									 // confusiones.
		
		// Y para hacer comparaciones, pues:
			// Cogemos otra carta
			Card otraCarta = actual.getCartas().get(1);
			if (carta.getValue().getValor() == otraCarta.getValue().getValor()){}
		
		/* FIN DEL EJEMPLO */
		
		// Guardar actual en una variable auxiliar
		this.manoActual = actual;
		Hand manoAnalizar = actual;
		
		// Ordenar auxiliar por valor
		this.manoActual.ordenaPorValorMayorAMenor();
		
		isStraightFlush(manoAnalizar);
		
		isFourOfAKind(manoAnalizar);
		
		isFullHouse(manoAnalizar);
		
		isFlush(manoAnalizar);
		
		isStraight(manoAnalizar);
		
		isThreeOfAKind(manoAnalizar);
		
		isTwoPair(manoAnalizar);
		
		isHighCard(manoAnalizar);
		
	}
	
	private void isStraightFlush(Hand mano){

	}
	
	private void isFourOfAKind(Hand mano){
		
	}
	
	private void isFullHouse(Hand mano){
		
	}
	
	private void isFlush(Hand mano){
		
	}
	
	private void isStraight(Hand mano){
		
	}
	
	private void isThreeOfAKind(Hand mano){
		
	}
	
	private void isTwoPair(Hand mano){
		
	}
	
	private void isHighCard(Hand mano){
		
	}
	
	public Hand getMejorJugada(){
		return this.mejorJugada;
	}
	
}
