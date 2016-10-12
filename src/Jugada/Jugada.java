package pr1;

import java.util.*;

public class Jugada {

	private Mano manoActual;
	private Vector <String> draws;
	private Mano mejorJugada;
	
	public void compruebaMano(Mano actual, Mano mejorJugada,Vector<String> draws ){
		//
		// Guardar actual en una variable auxiliar
		this.manoActual = actual;
		Mano manoAnalizar = actual;
		
		// Ordenar auxiliar por valor
		
		
		// Recorrer auxiliar y comprobar todas las posibles jugadas:
		// 	-Carta Alta
		// 	-Pareja
		// 	-Trio
		// 	-Doble pareja
		// 	-Color
		// 	-Poker
		// 	-Full
		// 	-Escalera ( de color)
		// 	
		// Devolvemos la mejor Jugada y los draws posibles con esa mano
		// 
	}
	
	
}
