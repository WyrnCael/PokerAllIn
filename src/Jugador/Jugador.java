package Jugador;

import Cartas.BestHand;
import Cartas.Hand;
import Jugada.JugadaMejorCartas;

public class Jugador implements Comparable<Object> {
	String nombre;
	JugadaMejorCartas jugada;
	Hand cartasIniciales;

	public Hand getCartasIniciales() {
		return cartasIniciales;
	}

	public Jugador(String nomb, Hand cartas) {
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

	@Override
	public int compareTo(Object arg1) {
		// TODO Auto-generated method stub
		BestHand jugada1 = this.jugada.getBestHand();
		BestHand jugada2 = ((Jugador) arg1).getJugada().getBestHand();
		Hand mano1 = jugada1.getManoOrdenada();
		Hand mano2 = jugada2.getManoOrdenada();

		// Si una jugada es mejor se devuelve la mejor
		if (jugada1.getJugada().getValor() > jugada2.getJugada().getValor()) {
			return -1;
		} else if (jugada1.getJugada().getValor() < jugada2.getJugada().getValor()) {
			return 1;
		}
		// Si son iguales se compara por jugada.
		else {
			switch (jugada1.getJugada().getValor()) {
			// Carta alta
			case 1:
				if (jugada1.getCartaAltaList().get(0).getValue().getValue() > jugada2.getCartaAltaList().get(0)
						.getValue().getValue()) {
					return -1;
				} else {
					return 1;
				}
				// Pareja y trio.
			case 2: // Esto funciona? Mis pruebas dicen que si. Opinen.
			case 4:
				// Comprobamos la mayor pareja o trio
				if (jugada1.getBestCards().get(0).getValue().getValue() > jugada2.getBestCards().get(0).getValue()
						.getValue()) {
					return -1;
				} else if (jugada1.getBestCards().get(0).getValue().getValue() < jugada2.getBestCards().get(0)
						.getValue().getValue()) {
					return 1;
				}
				// Si son iguales, miramos el resto de cartas
				for (int i = 0; i < 5; i++) {
					if (mano1.getCard(i).getValue().getValue() > mano2.getCard(i).getValue().getValue()) {
						return -1;
					} else if (mano1.getCard(i).getValue().getValue() < mano2.getCard(i).getValue().getValue()) {
						return 1;
					}
				}
				return 0;
			// Doble pareja
			case 3:
				// Miramos la primera pareja
				if (jugada1.getBestCards().get(0).getValue().getValue() > jugada2.getBestCards().get(0).getValue()
						.getValue()) {
					return -1;
				} else if (jugada1.getBestCards().get(0).getValue().getValue() < jugada2.getBestCards().get(0)
						.getValue().getValue()) {
					return 1;
				} else { // Si son iguales miramos la segunda pareja
					if (jugada1.getBestCards().get(2).getValue().getValue() > jugada2.getBestCards().get(2).getValue()
							.getValue()) {
						return -1;
					} else if (jugada1.getBestCards().get(2).getValue().getValue() < jugada2.getBestCards().get(2)
							.getValue().getValue()) {
						return 1;
					}
				}

				// Si son iguales, miramos el resto de cartas
				for (int i = 0; i < 5; i++) {
					if (mano1.getCard(i).getValue().getValue() > mano2.getCard(i).getValue().getValue()) {
						return -1;
					} else if (mano1.getCard(i).getValue().getValue() < mano2.getCard(i).getValue().getValue()) {
						return 1;
					}
				}
				return 0;
			// Escalera, color o escalera de color
			case 5:
			case 6:
			case 9:
				// Comprobamos la escalera o color mas alto
				if (jugada1.getBestCards().get(0).getValue().getValue() > jugada2.getBestCards().get(0).getValue()
						.getValue()) {
					return -1;
				} else if (jugada1.getBestCards().get(0).getValue().getValue() < jugada2.getBestCards().get(0)
						.getValue().getValue()) {
					return 1;
				}
				return 0;
			// Full house
			case 7:
				// Miramos el trio
				if (jugada1.getBestCards().get(0).getValue().getValue() > jugada2.getBestCards().get(0).getValue()
						.getValue()) {
					return -1;
				} else if (jugada1.getBestCards().get(0).getValue().getValue() < jugada2.getBestCards().get(0)
						.getValue().getValue()) {
					return 1;
				} else { // Si son iguales miramos la pareja
					if (jugada1.getBestCards().get(4).getValue().getValue() > jugada2.getBestCards().get(4).getValue()
							.getValue()) {
						return -1;
					} else if (jugada1.getBestCards().get(4).getValue().getValue() < jugada2.getBestCards().get(4)
							.getValue().getValue()) {
						return 1;
					}
				}
				return 0;
			// Poker
			case 8:
				// Miramos el poker
				if (jugada1.getBestCards().get(0).getValue().getValue() > jugada2.getBestCards().get(0).getValue()
						.getValue()) {
					return -1;
				} else if (jugada1.getBestCards().get(0).getValue().getValue() < jugada2.getBestCards().get(0)
						.getValue().getValue()) {
					return 1;
				}

				// Si son iguales, miramos el resto de cartas
				for (int i = 0; i < 5; i++) {
					if (mano1.getCard(i).getValue().getValue() > mano2.getCard(i).getValue().getValue()) {
						return -1;
					} else if (mano1.getCard(i).getValue().getValue() < mano2.getCard(i).getValue().getValue()) {
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
}
