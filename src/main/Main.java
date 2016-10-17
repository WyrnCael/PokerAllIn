package main;

import java.util.Vector;

import Cartas.Hand;
import Jugada.JugadaMejorCartas;
import controlArchivo.Entrada;
import controlArchivo.Salida;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		long start = System.currentTimeMillis();
		
		Vector<String> datos = new Vector<String>();
		Vector<String> datosSalida = new Vector<String>();
		
		Entrada entrada = new Entrada(args[1]);
		datos = entrada.leerDatos();
		
		long finishReadFile = System.currentTimeMillis();
		System.out.println("Tiempo tardade en leer: " + (finishReadFile-start) + "ms " + (finishReadFile-start) / 1000 + "s");
		// args[0] es el opcion de la jugada
		switch(args[0]){
		case "1":
//			System.out.println("Mejor jugada con 5 cartas");
			for(int i = 0; i < datos.size();i++){
				Hand hand = new Hand();
				hand.parseaEInserta(datos.get(i));
				JugadaMejorCartas jugada = new JugadaMejorCartas(hand);
				String str = " - Best hand: " + jugada.getBestHand();
//				System.out.println(str);
				datosSalida.add(datos.get(i));
				datosSalida.add(str);
				/*Vector<String> draws = jugada.getDraws();
				str = " - Draw: ";
				for(int j = 0; j < draws.size(); j++){
					datosSalida.add(str + draws.get(j));
				}*/
				datosSalida.add("");
			}
			break;
		case "2":
			System.out.println("Mejor jugada con 2 cartas");
			for(int i = 0; i < datos.size();i++){
				String aux = datos.get(i).substring(0,4);
				aux += datos.get(i).substring(7,datos.get(i).length());
				System.out.println(aux);
				Hand hand = new Hand();
				hand.parseaEInserta(aux);
				JugadaMejorCartas jugada = new JugadaMejorCartas(hand);
				String str = " - Best hand: " + jugada.getBestHand();
				System.out.println(str);
				datosSalida.add(datos.get(i));
				datosSalida.add(str);
				/*Vector<String> draws = jugada.getDraws();
				str = " - Draw: ";
				for(int j = 0; j < draws.size(); j++){
					datosSalida.add(str + draws.get(j));
				}*/
				datosSalida.add("");
			}
			break;
		case "3":
			System.out.println("Ordenar jugadores");
			break;
		default:
			System.out.println("Fuera del rango de opcion");
			break;
		}
		
		long finishProcess = System.currentTimeMillis();
		System.out.println("Tiempo tardade en procesar: " + (finishProcess-finishReadFile) + "ms " + (finishProcess-finishReadFile) / 1000 + "s");
		
		Salida salida = new Salida(args[2]);
		salida.guardarDatos(datosSalida);
		
		long end = System.currentTimeMillis();
		long time = end-start;
		System.out.println("Tiempo tardade en escribir: " + (end-finishProcess) + "ms " + (end-finishProcess) / 1000 + "s");
		System.out.println("Tiempo de ejecucion: " + time + "ms " + time / 1000 + "s");
	}

}