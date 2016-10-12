package main;

import java.util.Vector;

import controlArchivo.Entrada;
import controlArchivo.Salida;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// args[0] es el opcion de la jugada
		switch(args[0]){
		case "1":
			System.out.println("Mejor mano con 5 cartas");
			break;
		case "2":
			System.out.println("Mejor mano con 2 cartas");
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
		
		Salida salida = new Salida(args[2]);
		salida.guardarDatos(datos);
		
		
	}

}
