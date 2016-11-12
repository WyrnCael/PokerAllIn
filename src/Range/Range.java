package Range;

import java.io.IOException;
import java.util.Scanner;

import GUI.TableGUI;

public class Range {

	// campos de clase
	private TableGUI table;
	private String in;

	/**
	 * Metodo constructor
	 * 
	 * @param table
	 *            El parametro table es el objeto grafico de la tabla
	 * @param in
	 *            El parametro in es el objeto de lectura de la consola
	 */
	public Range(TableGUI table, String in) {
		this.table = table;
		this.in = in;
		process();
	}

	/**
	 * El metodo process llama la tabla pasando el boton que marca y la
	 * direction.
	 * Si hay una excepcion por introducir mal la mano, la recoge y muestra 
	 * por pantalla
	 */
	private void process() {
		String str = in;
		// limpiar tabla
		table.clearTable();
		try{
			// separar entrada por coma
			String[] ranges = str.split(",");
			
			for (int i = 0; i < ranges.length; i++) {
				switch (ranges[i].length()) {
				case 2:
					// AA, K2, A2 ...
					this.table.updateButton(ranges[i], ranges[i]);
					break;
				case 3:
					if (ranges[i].charAt(2) == '+') {
						// AA+, JJ+, 22+ ...
						this.table.updateButton(ranges[i], "Diagonal", 1);
					} else if (ranges[i].charAt(2) == '-') {
						// AA-, KK-, 33- ...
						this.table.updateButton(ranges[i], "Diagonal", -1);
					} else {
						// T2o, K9s ...
						this.table.updateButton(ranges[i], ranges[i]);
					}
					break;
				case 4:
					if (ranges[i].charAt(3) == '+') {
						// T2s+, KTo+ ...
						this.table.updateButton(ranges[i], "VertHor", 1);
					} else {
						// T2s-, KTo- ...
						this.table.updateButton(ranges[i], "VertHor", -1);
					}
					break;
				case 7:
					// ATs-A8s ...
					this.table.updateButton(ranges[i].substring(0, 3), ranges[i].substring(4, 7));
					break;
				default:
					break;
				}
			}
		}
		catch(IOException e){
			System.out.println("La mano introducida es incorrecta: " + e.getMessage());
		}
	}
}
