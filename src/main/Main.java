package main;

import java.util.Vector;

import Cartas.Carta;
import Cartas.Mano;
import Cartas.Palo;
import Cartas.Point;
import controlArchivo.Entrada;
import controlArchivo.Salida;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// args[0] es el opcion de la jugada
		switch(args[0]){
		case "1":
			System.out.println("Mejor jugada con 5 cartas");
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
		
		Vector<String> datos = new Vector<String>();
		
		Entrada entrada = new Entrada(args[1]);
		datos = entrada.leerDatos();
		
		/* Prueba: Lectura de la mano del apartado 1 */
		String manoString = datos.get(0);
		Mano mano = new Mano();
		for(int i = 0; i < 10; i=i+2){
			// ¿Como inicializo el enum?
			Carta carta = new Carta(Point.parsea(manoString.substring(i, i+1)), Palo.parsea(manoString.substring(i+1, i+2)));
			mano.addCartas(carta);
		}
		/* FIN de prueba */
		
		Salida salida = new Salida(args[2]);
		salida.guardarDatos(datos);		
	}

}
