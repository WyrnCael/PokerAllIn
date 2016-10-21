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
			String line = entrada.readLine();
			while(line != null){
				Hand hand = new Hand(line);
				JugadaMejorCartas jugada = new JugadaMejorCartas(hand);
				salida.saveResult(line);
				salida.saveResult(jugada.toString());
				
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
				pokerGame game = new pokerGame(line);
				salida.saveResult(game.toString());
				System.out.println(game.toString());
								
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
