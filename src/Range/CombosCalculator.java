package Range;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.JButton;

import Cards.Hand;
import Cards.Ranking;
import GUI.TableGUI;

public class CombosCalculator {

	// Campos de clase
	private TableGUI panelPrincipal;

	private List<JButton> selectedList;
	private List<String> selectedBoard;
	private List<String> todosCombo;

	private Map<String, List<String>> mapa;
	private Map<String, Integer> mapaResultado;
	private Map<String, Integer> mapaDraw;
	private Map<Character, Integer> mapaNCarta;

	private String manoBoard;

	private String[] jugada = { "STRAIGHT_FLUSH", "FOUR_OF_A_KIND", "FULL_HOUSE", "FLUSH", "STRAIGHT",
			"THREE_OF_A_KIND", "TWO_PAIR", "OVER_PAIR", "TOP_PAIR", "PP_BELOW_TP", "MIDDLE_PAIR", "WEAK_PAIR", "PAIR",
			"ace high", "no made hand", "Str.Flush OpenEnded", "Str.Flush Gutshot", "Draw Flush", 
			"Straight OpenEnded", "Straight Gutshot"};

	private int nTotalCombos;

	char doble = 0, doble2, trio, poker, segAlta = 0;

	/**
	 * Constructor
	 * 
	 * @param selectedList
	 *            Lista de rangos seleccionados
	 * @param selectedBoard
	 *            Lista de cartas selecionadas del board
	 * @param panelPrincipal
	 *            El objeto de panel principal
	 */
	public CombosCalculator(List<JButton> selectedList, List<JButton> selectedBoard, TableGUI panelPrincipal) {
		this.panelPrincipal = panelPrincipal;

		this.selectedList = new ArrayList<JButton>();
		this.selectedBoard = new ArrayList<String>();
		this.selectedList.addAll(selectedList);
		this.manoBoard = "";
		for (int i = 0; i < selectedBoard.size(); i++) {
			this.selectedBoard.add(selectedBoard.get(i).getText());
			manoBoard += selectedBoard.get(i).getText();
		}
		todosCombo = new ArrayList<String>();

		this.mapa = new TreeMap<String, List<String>>();
		this.mapaResultado = new TreeMap<String, Integer>();
		this.mapaDraw = new TreeMap<String, Integer>();
		this.mapaNCarta = new TreeMap<Character, Integer>();

		nTotalCombos = 0;

		numCartaMismaBoard();
		construirMapa();
		procesarMapa();
		mostrarSolucion();
	}

	/**
	 * El metodo que recorre toda la lista de carta de board, para saber si
	 * existe parejas, trio o poker, en caso afirmativo, asignar el valor de la
	 * carta al char correspondiente
	 */
	private void numCartaMismaBoard() {
		int cont = 0;
		// guardar la carta con el numero de veces que aprece
		for (int i = 0; i < selectedBoard.size(); i++) {
			char aux = selectedBoard.get(i).charAt(0);
			if (mapaNCarta.containsKey(aux)) {
				mapaNCarta.put(aux, mapaNCarta.get(aux) + 1);
			} else {
				cont++;
				mapaNCarta.put(aux, 1);
				if (cont == 2) {
					segAlta = aux;
				}
			}
		}

		// asignar la carta al char correspondiente
		// la variable doble2 es para guarda la segunda carta en caso de doble
		// pareja en board
		for (Map.Entry<Character, Integer> entry : mapaNCarta.entrySet()) {
			if (entry.getValue() == 2) {
				if (doble == 0) {
					doble = entry.getKey();
				} else {
					doble2 = entry.getKey();
				}
			} else if (entry.getValue() == 3) {
				trio = entry.getKey();
			} else if (entry.getValue() == 4) {
				poker = entry.getKey();
			}
		}

	}

	/**
	 * El metodo que recorrer toda la lista del rango, sacar posibles
	 * combinaciones del rango a una lista y lo guarda en una mapa con clave el
	 * nombre del rango
	 */
	private void construirMapa() {
		// TODO Auto-generated method stub
		for (int i = 0; i < selectedList.size(); i++) {
			String str = selectedList.get(i).getText();
			List<String> listaCombo = combos(str);
			mapa.put(str, listaCombo);
		}
	}

	/**
	 * El metodo que procesa la mapa que contruye el metodo contruirMapa(),
	 * calcular los mejores manos que puedan tener con todos los combinaciones,
	 * y los guarda en mapa del resultado
	 */
	private void procesarMapa() {
		for (Map.Entry<String, List<String>> entry : mapa.entrySet()) {

			String manos = "";
			for (int p = 0; p < entry.getValue().size(); p++) {
				manos = entry.getValue().get(p);
				BestHand bestHand = new BestHand(new Hand(this.manoBoard + manos));

				String rank = quitarMejorManoBoard(bestHand);

				if (this.mapaResultado.containsKey(rank)) {
					this.mapaResultado.put(rank, this.mapaResultado.get(rank) + 1);
				} else {
					this.mapaResultado.put(rank, 1);
				}
				
				List<String> listDraws = bestHand.getDraws();
				
				for(int k=0; k < listDraws.size();k++){
					String draw = listDraws.get(k);
					if (this.mapaDraw.containsKey(draw)) {
						this.mapaDraw.put(draw, this.mapaDraw.get(draw) + 1);
					} else {
						this.mapaDraw.put(draw, 1);
					}
				}
			}
		}
	}

	/**
	 * El metodo que analiza el mejor mano, si el mejor mano esta formado por
	 * las cartas del board, segun el caso, cambiaria al otro ranking
	 * 
	 * @param bestHand
	 *            El mejor mano
	 * @return devuelve el nuevo rank del mejor mano
	 */
	private String quitarMejorManoBoard(BestHand bestHand) {
		String rank = bestHand.getRank().toString();
		String aux = bestHand.toString();

		// si el mejor mano es poker, pero esta formado por 4 carta del mismo
		// valor en board
		if (bestHand.getRank() == Ranking.FOUR_OF_A_KIND) {
			if (poker == aux.charAt(0)) {
				// si el mejor carta del mejor mano es un ace y no esta en board
				if (bestHand.getHighCard().getValue().getName().equals("A") && selectedBoard.get(0).charAt(0) != 'A') {
					rank = "ace high";
				} else {
					rank = "no made hand";
				}
			}
		} else if (bestHand.getRank() == Ranking.FULL_HOUSE) {
			// si el mejor mano es fullhouse pero el trio esta formado por 3
			// carta del mismo valor en board
			if (trio == aux.charAt(0)) {
				rank = "PAIR";
			}
		} else if (bestHand.getRank() == Ranking.THREE_OF_A_KIND) {
			// si el mejor mano es trio pero el trio esta formado por 3 carta
			// del mismo valor en board
			if (trio == aux.charAt(0)) {
				if (bestHand.getHighCard().getValue().getName().equals("A") && selectedBoard.get(0).charAt(0) != 'A') {
					rank = "ace high";
				} else {
					rank = "no made hand";
				}
			}
		} else if (bestHand.getRank() == Ranking.TWO_PAIR) {
			// en caso de doble pareja
			if (doble == aux.charAt(0) && doble2 == aux.charAt(4)) {
				// el doble pareja esta formado por 2 pareja del board
				rank = "no made hand";
			} else if (doble == aux.charAt(0) || doble == aux.charAt(4)) {
				// algun pareja ya esta en board, pero con el otro puede tener
				// pareja
				rank = "PAIR";
			}

		} else if (bestHand.getRank() == Ranking.HIGH_CARD) {
			if (bestHand.getHighCard().getValue().getName().equals("A") && selectedBoard.get(0).charAt(0) != 'A') {
				rank = "ace high";
			} else {
				rank = "no made hand";
			}
		}

		// en caso de parejas, hay que especificar de que tipo es
		if (rank.equals("PAIR")) {
			rank = especificarPair(bestHand);
		}

		return rank;
	}

	/**
	 * El metodo para especificar el tipo de parejas
	 * 
	 * @param bestHand
	 *            El mejor mano
	 * @return el pareja especificada
	 */
	private String especificarPair(BestHand bestHand) {
		String rank = "";
		String aux = bestHand.toString();

		// en caso de doble pareja
		if (bestHand.getRank() == Ranking.TWO_PAIR) {
			if (doble == aux.charAt(0)) {
				// si la primera pareja esta en board, cojemos la segunda pareja
				aux = aux.substring(4, aux.length());
			} else if (doble == aux.charAt(4)) {
				// si la segunda pareja esta en board, cojemos la primera
				aux = aux.substring(0, 4);
			}
		} else if (bestHand.getRank() == Ranking.FULL_HOUSE) {
			// si el trio del fullhouse esta en board, cojemos la pareja
			if (trio == aux.charAt(0)) {
				aux = aux.substring(6, aux.length());
			}
		}

		// si la pareja ya esta en board
		if (doble == aux.charAt(0)) {
			if (bestHand.getHighCard().getValue().getName().equals("A") && selectedBoard.get(0).charAt(0) != 'A') {
				rank = "ace high";
			} else {
				rank = "no made hand";
			}
		} else if (parseChar(aux.charAt(0)) > parseChar(selectedBoard.get(0).charAt(0))) {
			// si la valor de pareja es mayor que la carta mas alta del board
			rank = "OVER_PAIR";
		} else if (parseChar(aux.charAt(0)) == parseChar(selectedBoard.get(0).charAt(0))) {
			// si la pareja es igual que la carta mas alta del board
			rank = "TOP_PAIR";
		} else if (parseChar(aux.charAt(0)) < parseChar(selectedBoard.get(0).charAt(0))
				&& parseChar(aux.charAt(0)) > parseChar(segAlta)) {
			// si la pareja es menor que la carta mas alta, pero mayor que la
			// segunda carta mas alta
			rank = "PP_BELOW_TP";
		} else if (aux.charAt(0) == segAlta && aux.charAt(0) != doble) {
			// si la pareja es igual que la segunda carta mas alta del board
			rank = "MIDDLE_PAIR";
		} else {
			// resto de parejas
			rank = "WEAK_PAIR";
		}

		return rank;
	}

	/**
	 * El metodo que muestra la solucion en la consola y graficamente
	 */
	private void mostrarSolucion() {
		List<Integer> listValue = new ArrayList<Integer>();
		double porcentaje = 0.00;
		DecimalFormat df = new DecimalFormat("0.00");
		System.out.printf("%-18s%-7s%-9s", "Jugada", "Combo", "Porcentaje");
		System.out.println("");
		for (int i = 0; i < jugada.length - 5; i++) {
			if (mapaResultado.containsKey(jugada[i])) {
				porcentaje = (double) mapaResultado.get(jugada[i]) / nTotalCombos * 100;
				System.out.printf("%-20s%-7s%-9s", jugada[i] + ":", mapaResultado.get(jugada[i]),
						df.format(porcentaje) + "%");
				System.out.println("");
				listValue.add(mapaResultado.get(jugada[i]));
			} else {
				System.out.printf("%-20s%-7s", jugada[i] + ":", 0);
				System.out.println("");
				listValue.add(0);
			}
		}
		
		System.out.println("numero total de combos: " + nTotalCombos + System.getProperty("line.separator"));
		for (int i = 15; i < jugada.length; i++) {
			if (mapaDraw.containsKey(jugada[i])) {
				porcentaje = (double) mapaDraw.get(jugada[i]) / nTotalCombos * 100;
				System.out.printf("%-20s%-7s%-9s", jugada[i] + ":", mapaDraw.get(jugada[i]),
						df.format(porcentaje) + "%");
				System.out.println("");
				listValue.add(mapaDraw.get(jugada[i]));
			} else {
				System.out.printf("%-20s%-7s", jugada[i] + ":", 0);
				System.out.println("");
				listValue.add(0);
			}
		}
		System.out.println(System.getProperty("line.separator"));
		panelPrincipal.actualizar(listValue, nTotalCombos);
	}

	/**
	 * El metodo que calcula posibles combinaciones segun el rango
	 * 
	 * @param str
	 *            El rango
	 * @return Un objeto lista de todos los combinaciones
	 */
	private List<String> combos(String str) {
		// TODO Auto-generated method stub
		List<String> lista = new ArrayList<String>();
		String[] palo = { "h", "c", "d", "s" };
		if (str.length() == 2) {
			// par
			for (int i = 0; i < palo.length; i++) {
				for (int j = i + 1; j < palo.length; j++) {
					if (!selectedBoard.contains(str.charAt(0) + palo[i])
							&& !selectedBoard.contains(str.charAt(1) + palo[j])) {
						lista.add(str.charAt(0) + palo[i] + str.charAt(1) + palo[j]);
						nTotalCombos++;
						todosCombo.add(str.charAt(0) + palo[i] + str.charAt(1) + palo[j]);
					}
				}
			}
		} else {
			if (str.charAt(2) == 's') {
				// suitted
				if (!selectedBoard.contains(str.charAt(0) + "h") && !selectedBoard.contains(str.charAt(1) + "h")) {
					lista.add(str.charAt(0) + "h" + str.charAt(1) + "h");
					nTotalCombos++;
					todosCombo.add(str.charAt(0) + "h" + str.charAt(1) + "h");
				}
				if (!selectedBoard.contains(str.charAt(0) + "c") && !selectedBoard.contains(str.charAt(1) + "c")) {
					lista.add(str.charAt(0) + "c" + str.charAt(1) + "c");
					nTotalCombos++;
					todosCombo.add(str.charAt(0) + "c" + str.charAt(1) + "c");
				}
				if (!selectedBoard.contains(str.charAt(0) + "d") && !selectedBoard.contains(str.charAt(1) + "d")) {
					lista.add(str.charAt(0) + "d" + str.charAt(1) + "d");
					nTotalCombos++;
					todosCombo.add(str.charAt(0) + "d" + str.charAt(1) + "d");
				}
				if (!selectedBoard.contains(str.charAt(0) + "s") && !selectedBoard.contains(str.charAt(1) + "s")) {
					lista.add(str.charAt(0) + "s" + str.charAt(1) + "s");
					nTotalCombos++;
					todosCombo.add(str.charAt(0) + "s" + str.charAt(1) + "s");
				}

			} else {
				// offsuitted
				for (int i = 0; i < palo.length; i++) {
					for (int j = 0; j < palo.length; j++) {
						if (i != j && !selectedBoard.contains(str.charAt(0) + palo[i])
								&& !selectedBoard.contains(str.charAt(1) + palo[j])) {
							lista.add(str.charAt(0) + palo[i] + str.charAt(1) + palo[j]);
							nTotalCombos++;
							todosCombo.add(str.charAt(0) + palo[i] + str.charAt(1) + palo[j]);
						}
					}
				}
			}
		}
		return lista;
	}

	/**
	 * El metodo que parsea la carta
	 * 
	 * @param c
	 *            la valor de la carta
	 * @return el peso de la carta
	 */
	private int parseChar(char c) {
		int num = -1;
		switch (c) {
		case 'A':
			num = 12;
			break;
		case 'K':
			num = 11;
			break;
		case 'Q':
			num = 10;
			break;
		case 'J':
			num = 9;
			break;
		case 'T':
			num = 8;
			break;
		case '9':
			num = 7;
			break;
		case '8':
			num = 6;
			break;
		case '7':
			num = 5;
			break;
		case '6':
			num = 4;
			break;
		case '5':
			num = 3;
			break;
		case '4':
			num = 2;
			break;
		case '3':
			num = 1;
			break;
		case '2':
			num = 0;
			break;
		default:
			break;
		}
		return num;
	}
}
