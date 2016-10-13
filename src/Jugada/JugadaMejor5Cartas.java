package Jugada;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;

import Cartas.Carta;
import Cartas.Palo;
import Cartas.Point;
import Cartas.Ranking;

public class JugadaMejor5Cartas {
	private String cartas;
	private Map<Carta, Integer> map;
	private String best;
	private Vector<String> draws;
	
	public JugadaMejor5Cartas(String datos){
		this.cartas = datos;
		map = new TreeMap<Carta, Integer>();
		draws = new Vector<String>();
		bestHand();
	}
	
	private void bestHand(){
		int red = 0;
		int black = 0;
		
		for(int i = 0; i < this.cartas.length(); i=i+2){
			Point point = Point.parsea(this.cartas.substring(i, i+1));
			Palo palo = Palo.parsea(this.cartas.substring(i+1, i+2));
			if(palo.getPalo().equals("Red")){
				red++;
			} else{
				black++;
			}
			Carta carta = new Carta(point, palo);
			if(map.containsKey(carta)){
				map.put(carta, map.get(carta).intValue()+1);
			} else {
				map.put(carta, 1);
			}
		}
		
		Iterator<Carta> it = map.keySet().iterator();
		int best = 0;
		int count = 1;
		boolean color = true;
		int hueco = 0;
		boolean escalera = true;
		int valor = 0;
		String col = "";
		
		while(it.hasNext()){
			Carta aux = it.next();
			
			switch(map.get(aux)){
			case 1:
				if(best == 0){
					best = Ranking.HIGH_CARD.getValor();
					col = aux.getColor().getPalo();
					valor = aux.getValor().getValor();
					this.best = Ranking.HIGH_CARD.getName() + " " + aux.getValor().getName();
				} else if(escalera || color){
					count++;
					if(valor - aux.getValor().getValor() > 2){
						escalera = false;
					} else if (valor - aux.getValor().getValor() == 2){
						escalera = false;
						hueco++;
					}
					
					if(!col.equals(aux.getColor().getPalo())){
						color = false;
					}
					
					if(count == 5){
						if(color && escalera){
								best = Ranking.STRAIGHT_FLUSH.getValor();
								this.best = Ranking.STRAIGHT_FLUSH.getName();
						}
						else if(color){
							best = Ranking.FLUSH.getValor();
							this.best = Ranking.FLUSH.getName();
						}
						else if (escalera){
							best = Ranking.STRAIGHT.getValor();
							this.best = Ranking.STRAIGHT.getName();
						}
					}
					valor = aux.getValor().getValor();
				}
				
				break;
			case 2:
				// two pair or pair
				if(best == 0){
					col = aux.getColor().getPalo();
					best = Ranking.PAIR.getValor();
					this.best = Ranking.PAIR.getName() + " of " + aux.getValor().getName();
				}
				else{
					if(Ranking.PAIR.getValor() == best){
						// two pair
						best = Ranking.TWO_PAIR.getValor();
						this.best = Ranking.TWO_PAIR.getName() + this.best.substring(4, this.best.length()) + aux.getValor().getName();
					} else if(Ranking.PAIR.getValor() < best){
						// full house
						best = Ranking.FULL_HOUSE.getValor();
						this.best = Ranking.FULL_HOUSE.getName() + this.best.substring(15, this.best.length()) + " " + aux.getValor().getName();
					} else {
						// High card
						best = Ranking.PAIR.getValor();
						this.best = Ranking.PAIR.getName() + aux.getValor().getName();
					}
				}
				break;
			case 3:
				color = false;
				// full house or three of a kind
				if(best == 0){
					best = Ranking.THREE_OF_A_KIND.getValor();
					this.best = Ranking.THREE_OF_A_KIND.getName() + " " + aux.getValor().getName();
				}
				else{
					if(best == Ranking.PAIR.getValor()){
						// full house
						best = Ranking.FULL_HOUSE.getValor();
						this.best = Ranking.FULL_HOUSE.getName() + " " + aux.getValor().getName() + this.best.substring(7, this.best.length());
					} else{
						// Three of a kind
						best = Ranking.THREE_OF_A_KIND.getValor();
						this.best = Ranking.THREE_OF_A_KIND.getName() + " " + aux.getValor().getName();
					}
				}
				
				break;
			case 4:
				color = false;
				best = Ranking.FOUR_OF_A_KIND.getValor();
				this.best = Ranking.FOUR_OF_A_KIND.getName()+ " " + aux.getValor().getName();
				
				break;
			default:
				break;
			}
		}
		
		if(count == 4 && hueco < 2){
			draws.add("Straight Gutshot");
		}
		
		if(red == 4 || black == 4){
			draws.add("Flush");
		}
	}
	
	public String getBestHand(){
		return this.best;
	}
	
	public Vector<String> getDraws(){
		
		return this.draws;
	}
}
