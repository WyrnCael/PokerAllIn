package main;

import java.util.List;

import Cartas.Hand;
import Jugada.pokerGame;
import Jugada.JugadaMejorCartas;
import Jugador.player;
import controlArchivo.InputFile;
import controlArchivo.OutPutFile;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		long start = System.currentTimeMillis();
		
		InputFile entrada = new InputFile(args[1]);
		
		OutPutFile salida = new OutPutFile(args[2]);
		
		long finishReadFile = System.currentTimeMillis();
		System.out.println("Tiempo tardade en leer: " + (finishReadFile-start) + "ms " + (finishReadFile-start) / 1000 + "s");
		// args[0] es el opcion de la jugada
		switch(args[0]){
		case "1":
//			System.out.println("Mejor jugada con 5 cartas");
			String line = entrada.readLine();
			while(line != null){
				Hand hand = new Hand(line);
				JugadaMejorCartas jugada = new JugadaMejorCartas(hand);
				String str = " - Best hand: " + jugada.getBestHand();
//				System.out.println(str);
				salida.saveResult(line);
				salida.saveResult(str);
				/*Vector<String> draws = jugada.getDraws();
				str = " - Draw: ";
				for(int j = 0; j < draws.size(); j++){
					datosSalida.add(str + draws.get(j));
				}*/
				salida.saveResult("");
				
				line = entrada.readLine();
			}
			break;
		case "2":
			System.out.println("Mejor jugada con 2 cartas");
			line = entrada.readLine();
			while(line != null){
				String aux = line.substring(0,4);
				aux += line.substring(7,line.length());
				System.out.println(aux);
				Hand hand = new Hand(aux);
				JugadaMejorCartas jugada = new JugadaMejorCartas(hand);
				String str = " - Best hand: " + jugada.getBestHand();
				System.out.println(str);
				salida.saveResult(line);
				salida.saveResult(str);
				str = " - Draw: ";
				List<String> draws = jugada.getDraws();
				for(int j = 0; j < draws.size(); j++){
					salida.saveResult(str + draws.get(j));
				}
				salida.saveResult("");
				
				line = entrada.readLine();
			}
			break;
		case "3":
			System.out.println("Ordenar jugadores");
			line = entrada.readLine();		
			while(line != null){
				System.out.println(line);
				salida.saveResult(line);
				pokerGame comparador = new pokerGame();
				
				// Leemos el numero de jugadores
				int nJugadores = Integer.valueOf(line.substring(0,1));
				
				// Leemos las cartas comunes
				String comunes = line.substring(2+(7*nJugadores),line.length());
				
				// Leemos los jugadores
				for(int i = 2; i < 2+(7*nJugadores); i=i+7){
					String name = line.substring(i,i+2);
					String jug = line.substring(i+2,i+6);
					Hand hand = new Hand(jug);
					comparador.addPlayer(name, hand);
				}
				
				List<player> manosOrdenadas = comparador.getGameResult();
				for(int j = 0; j < manosOrdenadas.size(); j++){
					player jugadorSalida = manosOrdenadas.get(j);
					String out = jugadorSalida.toString();
					salida.saveResult(out);
					System.out.println(out);
				}
				salida.saveResult("");
				System.out.println("");
								
				line = entrada.readLine();
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
