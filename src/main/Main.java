package main;

import java.util.Vector;

import Cartas.Mano;
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
		mano.parseaMano(manoString);
		
			// Probamos el orden y la salida por pantalla
			System.out.println("Mano sin ordenar: " + mano.toString());
			mano.ordenaPorValorMayorAMenor();
			System.out.println("Mano ordenada de mayor a menor: " + mano.toString());
			mano.ordenaPorValorMenorAMayor();
			System.out.println("Mano ordenada de menos a mayor: " + mano.toString());
		
		/* FIN de prueba */
		
		Salida salida = new Salida(args[2]);
		salida.guardarDatos(datos);		
	}

}
