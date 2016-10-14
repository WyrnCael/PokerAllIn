package Jugada;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;

import Cartas.Carta;
import Cartas.Palo;
import Cartas.Point;
import Cartas.Ranking;

public class JugadaMejor5Cartas {
	
	// Campos de clase
	private String card;
	private Map<Carta, Integer> map;
	private List<Carta> listCard;
	private String bestHand;
	private Vector<String> draws;
	private Vector<Integer> subRange;
	
	/**
	 * Constructor
	 * 
	 * @param datos El parametro datos definen el String de cartas
	 */
	public JugadaMejor5Cartas(String datos){
		this.card = datos;
		map = new TreeMap<Carta, Integer>();
		listCard = new ArrayList<Carta>();
		draws = new Vector<String>();
		subRange = new Vector<Integer>();
		bestHand();
	}
	
	/**
	 * Metodo para calcular el mejor mano a partir del String de cartas
	 * Utiliza la estructura de datos Treemap para facilitar el calculo
	 */
	private void bestHand(){
		
		int red = 0;
		int black = 0;
		for(int i = 0; i < this.card.length(); i=i+2){
			Point point = Point.parsea(this.card.substring(i, i+1));
			Palo palo = Palo.parsea(this.card.substring(i+1, i+2));
			if(palo.getPalo().equals("Red")){
				red++;
			} else{
				black++;
			}
			Carta carta = new Carta(point, palo);
			this.listCard.add(carta);
			if(this.map.containsKey(carta)){
				this.map.put(carta, map.get(carta).intValue()+1);
			} else {
				this.map.put(carta, 1);
			}
		}
		Collections.sort(this.listCard);
		
		int bestValue = 0;
		int count = 1;
		boolean color = true;
		int gutshot = 0;
		boolean straight = true;
		int value = 0;
//		String col = "";
		
		// Si numero rojo o negro es distinto de 5 ya no hay color
		if(red != 5 && black != 5)
			color = false;
		
		Iterator<Carta> it = map.keySet().iterator();
		while(it.hasNext()){
			Carta aux = it.next();
			
			switch(map.get(aux)){
			case 1:
				this.subRange.add(1);
				if(bestValue == 0){
					bestValue = Ranking.HIGH_CARD.getValor();
//					col = aux.getColor().getPalo();
					value = aux.getValor().getValor();
					this.bestHand = Ranking.HIGH_CARD.getName() + " " + aux.getValor().getName();
				} else if(straight || color){
					count++;
					if(value - aux.getValor().getValor() > 2){
						straight = false;
					} else if (value - aux.getValor().getValor() == 2){
						straight = false;
						gutshot++;
					}
					
//					if(!col.equals(aux.getColor().getPalo())){
//						color = false;
//					}
					
					if(count == 5){
						if(color && straight){
								bestValue = Ranking.STRAIGHT_FLUSH.getValor();
								this.bestHand = Ranking.STRAIGHT_FLUSH.getName();
						}
						else if(color){
							bestValue = Ranking.FLUSH.getValor();
							this.bestHand = Ranking.FLUSH.getName();
						}
						else if (straight){
							bestValue = Ranking.STRAIGHT.getValor();
							this.bestHand = Ranking.STRAIGHT.getName();
						}
					}
					value = aux.getValor().getValor();
				}
				
				break;
			case 2:
				// two pair or pair
				this.subRange.add(2);
				if(bestValue == 0){
//					col = aux.getColor().getPalo();
					bestValue = Ranking.PAIR.getValor();
					this.bestHand = Ranking.PAIR.getName() + " of " + aux.getValor().getName();
				}
				else{
					if(Ranking.PAIR.getValor() == bestValue){
						// two pair
						bestValue = Ranking.TWO_PAIR.getValor();
						this.bestHand = Ranking.TWO_PAIR.getName() + this.bestHand.substring(4, this.bestHand.length()) + aux.getValor().getName();
					} else if(Ranking.PAIR.getValor() < bestValue){
						// full house
						bestValue = Ranking.FULL_HOUSE.getValor();
						this.bestHand = Ranking.FULL_HOUSE.getName() + this.bestHand.substring(15, this.bestHand.length()) + " " + aux.getValor().getName();
					} else {
						// High card
						bestValue = Ranking.PAIR.getValor();
						this.bestHand = Ranking.PAIR.getName() + aux.getValor().getName();
					}
				}
				break;
			case 3:
				this.subRange.add(3);
				color = false;
				// full house or three of a kind
				if(bestValue == 0){
					bestValue = Ranking.THREE_OF_A_KIND.getValor();
					this.bestHand = Ranking.THREE_OF_A_KIND.getName() + " " + aux.getValor().getName();
				}
				else{
					if(bestValue == Ranking.PAIR.getValor()){
						// full house
						bestValue = Ranking.FULL_HOUSE.getValor();
						this.bestHand = Ranking.FULL_HOUSE.getName() + " " + aux.getValor().getName() + this.bestHand.substring(7, this.bestHand.length());
					} else{
						// Three of a kind
						bestValue = Ranking.THREE_OF_A_KIND.getValor();
						this.bestHand = Ranking.THREE_OF_A_KIND.getName() + " " + aux.getValor().getName();
					}
				}
				
				break;
			case 4:
				this.subRange.add(4);
				color = false;
				bestValue = Ranking.FOUR_OF_A_KIND.getValor();
				this.bestHand = Ranking.FOUR_OF_A_KIND.getName()+ " " + aux.getValor().getName();
				
				break;
			default:
				break;
			}
		}
		
		if(count == 4 && gutshot < 2){
			draws.add("Straight Gutshot");
		}
		
		if(red == 4 || black == 4){
			draws.add("Flush");
		}
		
		switch(this.subRange.size()){
		case 2: // 2+3 full house
			this.bestHand += " (";
			for(int i = 0; i < this.listCard.size(); i++){
				this.bestHand += this.listCard.get(i).getValor().getName() + this.listCard.get(i).getColor().getLetra();
			}
			this.bestHand += ")";
			break;
		case 3: // 1+2+2 two pair
			this.bestHand += " (";
			for(int i = 0; i < this.subRange.size(); i++){
				if(this.subRange.get(i) >= 2)
					this.bestHand += this.listCard.get(i).getValor().getName() + this.listCard.get(i).getColor().getLetra();
			}
			this.bestHand += ")";
			break;
		case 4: // 1+1+1+2 pair
			this.bestHand += " (";
			for(int i = 0; i < this.subRange.size(); i++){
				if(this.subRange.get(i) == 2){
					this.bestHand += this.listCard.get(i).getValor().getName() + this.listCard.get(i).getColor().getLetra();
					this.bestHand += this.listCard.get(i+1).getValor().getName() + this.listCard.get(i+1).getColor().getLetra();
				}
			}
			this.bestHand += ")";
			break;
		case 5: // 1+1+1+1+1 straight(flush) or flush or high card
			this.bestHand += " (";
			if(!color && !straight){
				this.bestHand += this.listCard.get(0).getValor().getName() + this.listCard.get(0).getColor().getLetra();
			} else {
				for(int i = 0; i < this.listCard.size(); i++){
					this.bestHand += this.listCard.get(i).getValor().getName() + this.listCard.get(i).getColor().getLetra();
				}
			}
			
			this.bestHand += ")";
			break;
		default:
			break;
		}
	}
	
	void bestHand1(){
		int bestValue = 0;
		int cardValue = 0;
		
		for(int i = 0; i < this.card.length(); i=i+2){
			Point point = Point.parsea(this.card.substring(i, i+1));
			Palo palo = Palo.parsea(this.card.substring(i+1, i+2));
			Carta carta = new Carta(point, palo);
			this.listCard.add(carta);
		}
		Collections.sort(this.listCard);
		
		for(int i = 0; i < this.listCard.size(); i++){
			Carta aux = this.listCard.get(i);
			if(bestValue == 0){
				bestValue = Ranking.HIGH_CARD.getValor();
				cardValue = aux.getValor().getValor();
				this.bestHand = Ranking.HIGH_CARD.getName() + Point.parsea(aux.getValor().getName()) + aux.getValor().getName() + aux.getColor().getPalo();
			} else{
				if(cardValue == aux.getValor().getValor()){ // mismo carta
					
				}
			}
			
		}
	}
	
	/**
	 * Metodo que devuelve el mejor mano
	 * @return El String del mejor mano
	 */
	public String getBestHand(){
		return this.bestHand;
	}
	
	/**
	 * Metodo que devuelve los draws
	 * @return El vector de String de los draws
	 */
	public Vector<String> getDraws(){
		
		return this.draws;
	}
}
