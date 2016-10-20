package main;

import java.util.List;

import Cartas.Hand;
import Jugada.ComparadorJugadores;
import Jugada.ComparadorJugadores.Jugador;
import Jugada.JugadaMejorCartas;
import controlArchivo.Entrada;
import controlArchivo.Salida;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		long start = System.currentTimeMillis();
		
		Entrada entrada = new Entrada(args[1]);
		
		Salida salida = new Salida(args[2]);
		
		long finishReadFile = System.currentTimeMillis();
		System.out.println("Tiempo tardade en leer: " + (finishReadFile-start) + "ms " + (finishReadFile-start) / 1000 + "s");
		// args[0] es el opcion de la jugada
		switch(args[0]){
		case "1":
//			System.out.println("Mejor jugada con 5 cartas");
			String linea = entrada.leerDato();
			while(linea != null){
				Hand hand = new Hand();
				hand.insertaCarta(linea);
				JugadaMejorCartas jugada = new JugadaMejorCartas(hand);
				String str = " - Best hand: " + jugada.getBestHandString();
//				System.out.println(str);
				salida.guardarDato(linea);
				salida.guardarDato(str);
				/*Vector<String> draws = jugada.getDraws();
				str = " - Draw: ";
				for(int j = 0; j < draws.size(); j++){
					datosSalida.add(str + draws.get(j));
				}*/
				salida.guardarDato("");
				
				linea = entrada.leerDato();
			}
			break;
		case "2":
			System.out.println("Mejor jugada con 2 cartas");
			linea = entrada.leerDato();
			while(linea != null){
				String aux = linea.substring(0,4);
				aux += linea.substring(7,linea.length());
				System.out.println(aux);
				Hand hand = new Hand();
				hand.insertaCarta(aux);
				JugadaMejorCartas jugada = new JugadaMejorCartas(hand);
				String str = " - Best hand: " + jugada.getBestHandString();
				System.out.println(str);
				salida.guardarDato(linea);
				salida.guardarDato(str);
				str = " - Draw: ";
				List<String> draws = jugada.getDraws();
				for(int j = 0; j < draws.size(); j++){
					salida.guardarDato(str + draws.get(j));
				}
				salida.guardarDato("");
				
				linea = entrada.leerDato();
			}
			break;
		case "3":
			System.out.println("Ordenar jugadores");
			linea = entrada.leerDato();		
			while(linea != null){
				System.out.println(linea);
				salida.guardarDato(linea);
				ComparadorJugadores comparador = new ComparadorJugadores();
				
				// Leemos el numero de jugadores
				int nJugadores = Integer.valueOf(linea.substring(0,1));
				
				// Leemos las cartas comunes
				String comunes = linea.substring(2+(7*nJugadores),linea.length());
				
				// Leemos los jugadores
				for(int i = 2; i < 2+(7*nJugadores); i=i+7){
					String nomb = linea.substring(i,i+2);
					String jug = linea.substring(i+2,i+6);
					Hand hand = new Hand();
					hand.insertaCarta(jug+comunes);
					comparador.anadeJugador(hand, nomb);
				}
				
				List<Jugador> manosOrdenadas = comparador.getJugadoresOrdenadosMejorPeor();
				for(int j = 0; j < manosOrdenadas.size(); j++){
					Jugador jugadorSalida = manosOrdenadas.get(j);
					String out = jugadorSalida.getNombre() + ": " + jugadorSalida.getCartasIniciales() + " " + jugadorSalida.getJugada().getBestHandString();
					salida.guardarDato(out);
					System.out.println(out);
				}
				salida.guardarDato("");
				System.out.println("");
								
				linea = entrada.leerDato();
			}
			break;
		default:
			System.out.println("Fuera del rango de opcion");
			break;
		}
		
		entrada.close();
		salida.close();
		
		long finishProcess = System.currentTimeMillis();
		System.out.println("Tiempo tardade en procesar: " + (finishProcess-finishReadFile) + "ms " + (finishProcess-finishReadFile) / 1000 + "s");
				
		long end = System.currentTimeMillis();
		long time = end-start;
		System.out.println("Tiempo tardade en escribir: " + (end-finishProcess) + "ms " + (end-finishProcess) / 1000 + "s");
		System.out.println("Tiempo de ejecucion: " + time + "ms " + time / 1000 + "s");
	}

}
