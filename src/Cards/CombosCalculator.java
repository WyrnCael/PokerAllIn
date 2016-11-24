package Cards;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.JButton;

import Range.BestHand;

public class CombosCalculator {

	private List<JButton> selectedList;
	private List<String> selectedBoard;
	private int nTotalCombos;
	private Map<String, List<String>> mapa;
	private Map<String, Integer> mapaResultado;
	private Map<Character, Integer> mapaNCarta;
	private List<String> todosCombo;
	private String manoBoard;
	private List<String> doblePareja;
	private String[] jugada = { "STRAIGHT_FLUSH", "FOUR_OF_A_KIND", "FULL_HOUSE", "FLUSH", "STRAIGHT", "THREE_OF_A_KIND",
			"TWO_PAIR", "OVERPAIR", "TOP_PAIR", "PP_BELOW_TP", "MIDDLE_PAIR", "WEAK_PAIR", "TWO_PAIR", "PAIR",
			"no made hand" };

	public CombosCalculator(List<JButton> selectedList, List<JButton> selectedBoard) {
		this.selectedList = new ArrayList<JButton>();
		this.selectedBoard = new ArrayList<String>();
		this.selectedList.addAll(selectedList);
		manoBoard = "";
		for (int i = 0; i < selectedBoard.size(); i++) {
			this.selectedBoard.add(selectedBoard.get(i).getText());
			manoBoard += selectedBoard.get(i).getText();
		}
		this.mapa = new TreeMap<String, List<String>>();
		this.mapaResultado = new TreeMap<String, Integer>();
		this.mapaNCarta = new TreeMap<Character, Integer>();
		todosCombo = new ArrayList<String>();
		doblePareja = new ArrayList<String>();
		nTotalCombos = 0;

		// construirMapaResultado();
		construirMapa();
		mostrarSolucion();
		// calcularCombo1();
	}

	private void construirMapa() {
		// TODO Auto-generated method stub
		String str = "";
		char doble = 0, trio = 0, poker = 0;
		String cartaAlta = "";

		for (int i = 0; i < selectedBoard.size(); i++) {
			char aux = selectedBoard.get(i).charAt(0);
			if (mapaNCarta.containsKey(aux)) {
				mapaNCarta.put(aux, mapaNCarta.get(aux) + 1);
			} else {
				mapaNCarta.put(aux, 1);
			}
		}

		for (Map.Entry<Character, Integer> entry : mapaNCarta.entrySet()) {
			if (entry.getValue() == 2) {
				doble = entry.getKey();
			} else if (entry.getValue() == 3) {
				trio = entry.getKey();
			} else if (entry.getValue() == 4) {
				poker = entry.getKey();
			}
		}

		for (int i = 0; i < selectedList.size(); i++) {
			str = selectedList.get(i).getText();
			List<String> listaCombo = combos(str);
			mapa.put(str, listaCombo);
		}
		for (Map.Entry<String, List<String>> entry : mapa.entrySet()) {
			// System.out.println(entry.getKey() + " : " +
			// entry.getValue().size() + " ");
			String manos = "";
			for (int p = 0; p < entry.getValue().size(); p++) {
				manos = entry.getValue().get(p);
				BestHand bestHand = new BestHand(new Hand(this.manoBoard + manos));
				String rank = "";
				if (bestHand.getRank() == Ranking.TWO_PAIR) {
					boolean noForma = false;
					String aux = bestHand.toString();
					for (int k = 0; k < aux.length() && !noForma; k += 2) {
						if (doble == aux.charAt(k)) {
							noForma = true;
						}
					}
					if (noForma) {
						rank = "PAIR";
					} else {
						rank = bestHand.getRank().toString();
					}

				} else if (bestHand.getRank() == Ranking.PAIR) {
					boolean noForma = false;
					String aux = bestHand.toString();
					for (int k = 0; k < aux.length() && !noForma; k += 2) {
						if (doble == aux.charAt(k)) {
							noForma = true;
						}
					}
					if (noForma) {
						if (bestHand.getHighCard().getValue().name().equals("A")) {
							rank = "ace high";
						} else {
							rank = "no made hand";
						}
					} else {
						rank = bestHand.getRank().toString();
					}

				} else {
					rank = bestHand.getRank().toString();
				}

				if (this.mapaResultado.containsKey(rank)) {
					this.mapaResultado.put(rank, this.mapaResultado.get(rank) + 1);
				} else {
					this.mapaResultado.put(rank, 1);
				}
			}
		}
	}
	
	private void mostrarSolucion(){
		for (int i = 0; i < jugada.length; i++) {
			if(mapaResultado.containsKey(jugada[i])){
				System.out.println(jugada[i] + " : " + mapaResultado.get(jugada[i]));
			}
		}
		System.out.println("numero total de combos: " + nTotalCombos);
	}

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

	// private void construirMapaResultado() {
	// // TODO Auto-generated method stub
	// List<Integer> lista;
	// lista= new ArrayList<Integer>();
	// this.mapaResultado.put("Quads", lista);
	// lista= new ArrayList<Integer>();
	// this.mapaResultado.put("full house", lista);
	// lista= new ArrayList<Integer>();
	// this.mapaResultado.put("flush", lista);
	// lista= new ArrayList<Integer>();
	// this.mapaResultado.put("straight", lista);
	// lista= new ArrayList<Integer>();
	// this.mapaResultado.put("set", lista);
	// lista= new ArrayList<Integer>();
	// this.mapaResultado.put("two pair", lista);
	// lista= new ArrayList<Integer>();
	// this.mapaResultado.put("over pair", lista);
	// lista= new ArrayList<Integer>();
	// this.mapaResultado.put("top pair", lista);
	// lista= new ArrayList<Integer>();
	// this.mapaResultado.put("pocket pair below top pair", lista);
	// lista= new ArrayList<Integer>();
	// this.mapaResultado.put("middle pair", lista);
	// lista= new ArrayList<Integer>();
	// this.mapaResultado.put("weak pair", lista);
	// lista= new ArrayList<Integer>();
	// this.mapaResultado.put("ace high", lista);
	// lista= new ArrayList<Integer>();
	// this.mapaResultado.put("no made hand", lista);
	// }
	/*
	 * private void calcularCombo1(){ // TODO Auto-generated method stub String
	 * str = ""; int nCarta; for(int i = 0; i < selectedList.size(); i++){ str =
	 * selectedList.get(i).getText(); System.out.print(str + " "); char letra =
	 * str.charAt(0);
	 * 
	 * if(str.length() == 2){ // par nCarta = numCarta(str.charAt(0));
	 * switch(nCarta){ case 0: if(parseChar(str.charAt(0)) >
	 * parseChar(this.selectedBoard.get(0).charAt(0))){
	 * this.mapaResultado.get("over pair").add(4); nTotalCombos += 4; } break;
	 * case 1: nTotalCombos += 3; this.mapaResultado.get("set").add(3); break;
	 * case 2: nTotalCombos += 2; this.mapaResultado.get("Quads").add(1); break;
	 * default: break; } } else { if(str.charAt(2) == 's'){ ComboSuited(str); }
	 * else { //offsuited
	 * 
	 * }
	 * 
	 * }
	 * 
	 * }
	 * 
	 * }
	 * 
	 * private int ComboSuited(String str) { // TODO Auto-generated method stub
	 * int numCombo = 4; int n = numCarta(str.charAt(0)); switch(n){ case 1:
	 * numCombo -= 1; break; case 2: numCombo -= 2; break; case 3: numCombo -=
	 * 3; break; case 4: numCombo = 0; break; default: break; }
	 * 
	 * return numCombo; }
	 * 
	 * private int numCarta(char charAt) { // TODO Auto-generated method stub
	 * int n = 0; if(this.selectedBoard.contains(charAt + 'h')){ n++; } else
	 * if(this.selectedBoard.contains(charAt + 'c')){ n++; } else
	 * if(this.selectedBoard.contains(charAt + 'd')){ n++; } else
	 * if(this.selectedBoard.contains(charAt + 's')){ n++; } return n; }
	 */
	private void calcularCombo() {
		// TODO Auto-generated method stub
		String str = "";
		for (int i = 0; i < selectedList.size(); i++) {
			str = selectedList.get(i).getText();
			System.out.print(str + " ");
			char letra = str.charAt(0);

			if (!mapa.containsKey(letra)) {
				List<String> lista = new ArrayList<String>();
				lista.add(str);
				// mapa.put(letra, lista);
			} else {
				mapa.get(letra).add(str);
			}

			if (letra == str.charAt(1)) {
				// par
				nTotalCombos += 6;
			} else if (str.charAt(2) == 's') {
				// suited
				nTotalCombos += 4;
			} else {
				// offsuited
				nTotalCombos += 12;
			}
		}
		System.out.println("numero total de combos antes de poner board: " + nTotalCombos);

		int mayorCard = 0;
		if (!selectedBoard.isEmpty()) {
			mayorCard = parseChar(selectedBoard.get(0).charAt(0));
		}

		// for(int j = 0; j < selectedBoard.size(); j++){
		// str = selectedBoard.get(j).getText();
		// System.out.print(str + " ");
		// char letra = str.charAt(0);
		// char color = ' ';
		// if(str.length() == 3){
		// color = str.charAt(2);
		// }
		// if(mapa.containsKey(letra)){
		// List<String> lista = mapa.get(letra);
		// for(int p=0; p < lista.size(); p++){
		// String tmp = lista.get(p);
		// if(tmp.charAt(0) == tmp.charAt(1)){
		// // par
		// nTotalCombos -= 3;
		// this.mapaResultado.get("set").add(3);
		// } else {
		//
		// if(letra == selectedBoard.get(0).getText().charAt(0) ||
		// selectedBoard.get(0).getText().charAt(1) == str.charAt(1)){
		// if(str.charAt(2) == 's'){
		// this.mapaResultado.get("top pair").add(3);
		// } else {
		// this.mapaResultado.get("top pair").add(9);
		// }
		// }
		//
		// if(tmp.charAt(2) == 's'){
		// nTotalCombos -= 1;
		// } else {
		// nTotalCombos -= 3;
		// }
		// }
		// }
		// }
		// }
		// System.out.println("numero total de combos despues de poner board: "
		// + nTotalCombos);
	}

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
