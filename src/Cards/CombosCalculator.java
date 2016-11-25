package Cards;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.JButton;
import javax.swing.JFrame;

import GUI.PanelEstadistica;
import GUI.TableGUI;
import Range.BestHand;

public class CombosCalculator {

	private TableGUI panelPrincipal;
	
	private List<JButton> selectedList;
	private List<String> selectedBoard;
	private List<String> todosCombo;
	
	private Map<String, List<String>> mapa;
	private Map<String, Integer> mapaResultado;
	private Map<Character, Integer> mapaNCarta;
	
	private String manoBoard;
	
	private String[] jugada = { "STRAIGHT_FLUSH", 
								"FOUR_OF_A_KIND", 
								"FULL_HOUSE", 
								"FLUSH", 
								"STRAIGHT", 
								"THREE_OF_A_KIND",
								"TWO_PAIR", 
								"OVER_PAIR", 
								"TOP_PAIR", 
								"PP_BELOW_TP", 
								"MIDDLE_PAIR", 
								"WEAK_PAIR", 
								"PAIR", 
								"ace high",
								"no made hand" };
	
	private int nTotalCombos;
	char doble = 0, doble2, trio, poker, segAlta = 0;

	public CombosCalculator(List<JButton> selectedList, List<JButton> selectedBoard, TableGUI panelPrincipal) {
		this.panelPrincipal = panelPrincipal;
		
		this.selectedList = new ArrayList<JButton>();
		this.selectedBoard = new ArrayList<String>();
		this.selectedList.addAll(selectedList);
		manoBoard = "";
		for (int i = 0; i < selectedBoard.size(); i++) {
			this.selectedBoard.add(selectedBoard.get(i).getText());
			manoBoard += selectedBoard.get(i).getText();
		}
		todosCombo = new ArrayList<String>();
		
		this.mapa = new TreeMap<String, List<String>>();
		this.mapaResultado = new TreeMap<String, Integer>();
		this.mapaNCarta = new TreeMap<Character, Integer>();
		
		nTotalCombos = 0;

		numCartaMismaBoard();
		construirMapa();
		procesarMapa();
		mostrarSolucion();
	}
	
	private void numCartaMismaBoard(){
		int cont = 0;
		for (int i = 0; i < selectedBoard.size(); i++) {
			char aux = selectedBoard.get(i).charAt(0);
			if (mapaNCarta.containsKey(aux)) {
				mapaNCarta.put(aux, mapaNCarta.get(aux) + 1);
			} else {
				cont++;
				mapaNCarta.put(aux, 1);
				if(cont == 2){
					segAlta = aux;
				}
			}
		}
		
		for (Map.Entry<Character, Integer> entry : mapaNCarta.entrySet()) {
			if (entry.getValue() == 2) {
				if(doble == 0){
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

	private void construirMapa() {
		// TODO Auto-generated method stub
		for (int i = 0; i < selectedList.size(); i++) {
			String str = selectedList.get(i).getText();
			List<String> listaCombo = combos(str);
			mapa.put(str, listaCombo);
		}
		
	}
	
	private void procesarMapa(){
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
			}
		}
	}
	
	private String quitarMejorManoBoard(BestHand bestHand){
		String rank = bestHand.getRank().toString();
		if(bestHand.getRank() == Ranking.FOUR_OF_A_KIND){
			String aux = bestHand.toString();
			if(poker == aux.charAt(0)){
				rank = "no made hand";
			}
		} else if(bestHand.getRank() == Ranking.THREE_OF_A_KIND){
			String aux = bestHand.toString();
			if(trio == aux.charAt(0)){
				rank = "no made hand";
			}
		} else if (bestHand.getRank() == Ranking.TWO_PAIR) {
			String aux = bestHand.toString();
			if(doble == aux.charAt(0) && doble2 == aux.charAt(4)){
				rank = "no made hand";
			} else if (doble == aux.charAt(0) || doble == aux.charAt(4)) {
				rank = "PAIR";
			}
			
		} else if(bestHand.getRank() == Ranking.HIGH_CARD) {
			if (bestHand.getHighCard().getValue().getName().equals("A") && selectedBoard.get(0).charAt(0) != 'A') {
				rank = "ace high";
			} else {
				rank = "no made hand";
			}
		}
		
		if(rank.equals("PAIR")){
			rank = diferenciarPar(bestHand);
		}
		
		return rank;
	}
	
	private String diferenciarPar(BestHand bestHand){
		String rank = "";
		
		if(parseChar(bestHand.toString().charAt(0)) > parseChar(selectedBoard.get(0).charAt(0))){
			rank = "OVER_PAIR";
		} else if(parseChar(bestHand.toString().charAt(0)) == parseChar(selectedBoard.get(0).charAt(0))){
			rank = "TOP_PAIR";
		} else if(parseChar(bestHand.toString().charAt(0)) < parseChar(selectedBoard.get(0).charAt(0)) && parseChar(bestHand.toString().charAt(0)) > parseChar(segAlta)){
			rank = "PP_BELOW_TP";
		} else if(bestHand.toString().charAt(0) == segAlta && bestHand.toString().charAt(0) != doble){
			rank = "MIDDLE_PAIR";
		} else {
			String aux = bestHand.toString();
			if(bestHand.getRank() == Ranking.TWO_PAIR){
				rank = "WEAK_PAIR";
			} else if (doble == aux.charAt(0)) {
				if (bestHand.getHighCard().getValue().getName().equals("A") && selectedBoard.get(0).charAt(0) != 'A') {
					rank = "ace high";
				} else {
					rank = "no made hand";
				}
			}
		}
		
		return rank;
	}
	
	private void mostrarSolucion(){
		List<Integer> listValue = new ArrayList<Integer>();
		double porcentaje = 0.00;
		DecimalFormat df = new DecimalFormat("0.00");
		System.out.printf("%-18s%-7s%-9s", "Jugada", "Combo", "Porcentaje");
		System.out.println("");
		for (int i = 0; i < jugada.length; i++) {
			if(mapaResultado.containsKey(jugada[i])){
				porcentaje = (double)mapaResultado.get(jugada[i]) / nTotalCombos * 100;
				System.out.printf("%-20s%-7s%-9s", jugada[i] + ":", mapaResultado.get(jugada[i]), df.format(porcentaje) + "%");
				System.out.println("");
				listValue.add(mapaResultado.get(jugada[i]));
			} else {
				System.out.printf("%-20s%-7s", jugada[i] + ":", 0);
				System.out.println("");
				listValue.add(0);
			}
		}
		System.out.println("numero total de combos: " + nTotalCombos + System.getProperty("line.separator"));
		panelPrincipal.actualizar(listValue);
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
