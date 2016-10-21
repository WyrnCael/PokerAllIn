package Jugada;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import Cartas.Hand;
import Jugador.Jugador;

public class ComparadorJugadores {
	
	private List<Jugador> jugadores;
	
	public ComparadorJugadores(){
		this.jugadores = new ArrayList<Jugador>();
	}
	
	public void anadeJugador(Hand hand, String nombre){
		jugadores.add(new Jugador(nombre, hand));
	}
	
	@SuppressWarnings("unchecked")
	public List<Jugador> getJugadoresOrdenadosMejorPeor(){
		// Ordenamos
		Collections.sort(jugadores);
		return jugadores;		
	}
}
