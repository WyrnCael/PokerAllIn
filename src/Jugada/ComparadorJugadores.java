package Jugada;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import Cartas.BestHand;
import Cartas.Hand;

public class ComparadorJugadores {
	
	public class Jugador{
		String nombre;
		JugadaMejorCartas jugada;
		Hand cartasIniciales;
		
		public Hand getCartasIniciales() {
			return cartasIniciales;
		}

		public Jugador(String nomb, Hand cartas){
			nombre = nomb;
			this.cartasIniciales = cartas;
			this.jugada = new JugadaMejorCartas(cartas);
			
		}
		
		public String getNombre() {
			return nombre;
		}

		public JugadaMejorCartas getJugada() {
			return jugada;
		}
	}
	
	private List<Jugador> jugadores;
	
	public ComparadorJugadores(){
		this.jugadores = new ArrayList<Jugador>();
	}
	
	public void anadeJugador(Hand hand, String nombre){
		jugadores.add(new Jugador(nombre, hand));
	}
	
	public List<Jugador> getJugadoresOrdenadosMejorPeor(){
		// Ordenamos
		Collections.sort(jugadores, new Comparator<Jugador>() {
		   @Override
			public int compare(Jugador arg0, Jugador arg1) {
			   BestHand jugada1 = arg0.getJugada().getBestHand();
			   BestHand jugada2 = arg1.getJugada().getBestHand();
			   Hand mano1 = jugada1.getManoOrdenada();
			   Hand mano2 = jugada2.getManoOrdenada();
			   
			   // Si una jugada es mejor se devuelve la mejor
				if(jugada1.getJugada().getValor() > jugada2.getJugada().getValor()){
					return -1;
				}
				else if(jugada1.getJugada().getValor() < jugada2.getJugada().getValor()){
					return 1;
				}
				// Si son iguales se compara por jugada.
				else{
					switch(jugada1.getJugada().getValor()){
					// Carta alta
					case 1:
						if(jugada1.getCartaAltaList().get(0).getValue().getValue() > jugada2.getCartaAltaList().get(0).getValue().getValue()){
							return -1;
						}
						else{
							return 1;
						}
				    // Pareja y trio.
					case 2: // Esto funciona? Mis pruebas dicen que si. Opinen.
					case 4: 
						// Comprobamos la mayor pareja o trio
						if(jugada1.getBestCards().get(0).getValue().getValue() > jugada2.getBestCards().get(0).getValue().getValue()){
							return -1;
						}
						else if(jugada1.getBestCards().get(0).getValue().getValue() < jugada2.getBestCards().get(0).getValue().getValue()){
							return 1;
						}
						// Si son iguales, miramos el resto de cartas
						for(int i = 0; i < 5; i++){
							if(mano1.getCard(i).getValue().getValue() > mano2.getCard(i).getValue().getValue()){
								return -1;
							}
							else if (mano1.getCard(i).getValue().getValue() < mano2.getCard(i).getValue().getValue()){
								return 1;
							}
						}
						return 0;	
					// Doble pareja
					case 3:
						// Miramos la primera pareja
						if(jugada1.getBestCards().get(0).getValue().getValue() > jugada2.getBestCards().get(0).getValue().getValue()){
							return -1;
						}
						else if(jugada1.getBestCards().get(0).getValue().getValue() < jugada2.getBestCards().get(0).getValue().getValue()){
							return 1;
						}
						else { // Si son iguales miramos la segunda pareja
							if(jugada1.getBestCards().get(2).getValue().getValue() > jugada2.getBestCards().get(2).getValue().getValue()){
								return -1;
							}
							else if(jugada1.getBestCards().get(2).getValue().getValue() < jugada2.getBestCards().get(2).getValue().getValue()){
								return 1;
							}
						}
						
						// Si son iguales, miramos el resto de cartas
						for(int i = 0; i < 5; i++){
							if(mano1.getCard(i).getValue().getValue() > mano2.getCard(i).getValue().getValue()){
								return -1;
							}
							else if (mano1.getCard(i).getValue().getValue() < mano2.getCard(i).getValue().getValue()){
								return 1;
							}
						}
						return 0;	
					default:
						break;
					}
				}
				return 1;
			}
		});		
		
		return jugadores;		
	}
}
