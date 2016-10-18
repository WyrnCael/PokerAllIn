package main;

import java.util.List;
import Cartas.Hand;
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
				hand.parseaEInserta(linea);
				JugadaMejorCartas jugada = new JugadaMejorCartas(hand);
				String str = " - Best hand: " + jugada.getBestHand();
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
				hand.parseaEInserta(aux);
				JugadaMejorCartas jugada = new JugadaMejorCartas(hand);
				String str = " - Best hand: " + jugada.getBestHand();
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
