package main;

import java.util.Vector;

import Cartas.Mano;
import Jugada.JugadaMejor5Cartas;
import controlArchivo.Entrada;
import controlArchivo.Salida;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Vector<String> datos = new Vector<String>();
		Vector<String> datosSalida = new Vector<String>();
		
		Entrada entrada = new Entrada(args[1]);
		datos = entrada.leerDatos();
		
		// args[0] es el opcion de la jugada
		switch(args[0]){
		case "1":
			System.out.println("Mejor jugada con 5 cartas");
			for(int i = 0; i < datos.size();i++){
				JugadaMejor5Cartas jugada = new JugadaMejor5Cartas(datos.get(i));
				String str = " - Best hand: " + jugada.getBestHand();
//				System.out.println(str);
				datosSalida.add(datos.get(i));
				datosSalida.add(str);
				Vector<String> draws = jugada.getDraws();
				str = " - Draw: ";
				for(int j = 0; j < draws.size(); j++){
					datosSalida.add(str + draws.get(j));
				}
				datosSalida.add("");
			}
			break;
		case "2":
			System.out.println("Mejor jugada con 2 cartas");
			break;
		case "3":
			System.out.println("Ordenar jugadores");
			break;
		default:
			System.out.println("Fuera del rango de opcion");
			break;
		}
		
		Salida salida = new Salida(args[2]);
		salida.guardarDatos(datosSalida);		
	}

}
