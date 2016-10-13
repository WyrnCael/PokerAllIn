package Jugada;

import java.util.*;

import Cartas.Carta;
import Cartas.Mano;

public class Jugada {

	private Mano manoActual;
	private Vector<String> draws;
	private Mano mejorJugada;
	
	public void compruebaMano(Mano actual, Mano mejorJugada,Vector<String> draws ){
		
		/* INICIO EJEMPLO PARA HACER COMPARACIONES DEL VALOR (POINT) */
		
		// Cogemos una carta
		Carta carta = actual.getCartas().get(0);
		
		// El valor se mira asi:
		carta.getValor().getValor(); // Quizas habria que cambiar el nombre
									 // a una de las dos funciones para evitar
									 // confusiones.
		
		// Y para hacer comparaciones, pues:
			// Cogemos otra carta
			Carta otraCarta = actual.getCartas().get(1);
			if (carta.getValor().getValor() == otraCarta.getValor().getValor()){}
		
		/* FIN DEL EJEMPLO */
		
		// Guardar actual en una variable auxiliar
		this.manoActual = actual;
		Mano manoAnalizar = actual;
		
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
	
	private void isStraightFlush(Mano mano){

	}
	
	private void isFourOfAKind(Mano mano){
		
	}
	
	private void isFullHouse(Mano mano){
		
	}
	
	private void isFlush(Mano mano){
		
	}
	
	private void isStraight(Mano mano){
		
	}
	
	private void isThreeOfAKind(Mano mano){
		
	}
	
	private void isTwoPair(Mano mano){
		
	}
	
	private void isHighCard(Mano mano){
		
	}
	
	public Mano getMejorJugada(){
		return this.mejorJugada;
	}
	
}
