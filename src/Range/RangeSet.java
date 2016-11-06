package Range;

import java.util.ArrayList;
import java.util.List;

import Cards.Value;

public class RangeSet {
	private Value primerValor;
	private Value segundoValor;
	private boolean suitedB;
	private boolean moreLessB;
	private char suited;
	private char moreLess;
	private RangeSet secondSet;
	
	public RangeSet(List<Character> in){
		this.primerValor = parseValue(in.get(0));
		this.segundoValor = parseValue(in.get(1));
		
		this.suitedB = false;
		this.moreLessB = false;
		
		if(in.size() == 3){
			parseSuitMoreLess(in.get(2));
		}
		else if(in.size() == 4){
			parseSuitMoreLess(in.get(2));
			parseSuitMoreLess(in.get(3));
		}
		else if (in.size() > 4){
			for(int i = 2; i < in.indexOf('-'); i++){
				parseSuitMoreLess(in.get(i));
			}
			
			List<Character> aux = new ArrayList<>();
			for(int i = in.indexOf('-') + 1; i < in.size(); i++){
				aux.add(in.get(i));
			}
			this.moreLessB = true;
			this.moreLess = '-';
			secondSet = new RangeSet(aux);
		}
	}
	
	private void parseSuitMoreLess(char in){
		if(in == 's'){
			this.suitedB = true;
			this.suited = 's';
		}
		else if(in == 'o'){
			this.suitedB = true;
			this.suited = 'o';
		}
		else if(in == '+'){
			this.moreLessB = true;
			this.moreLess = '+';
		}
		else if(in == '-'){
			this.moreLessB = true;
			this.moreLess = '-';
		}
	}
	
	public boolean isSuited(){
		if(this.suitedB && suited == 's') return true;
		else return false;  // Throw exception
	}
	
	public boolean isMore(){
		if(this.moreLessB && this.moreLess == '+') return true;
		else return false;  // Throw exception
	}
	
	public boolean isLess(){
		if(this.moreLessB && this.moreLess == '-') return true;
		else return false;  // Throw exception
	}
	
	public boolean isSuitedSecondSet(){
		return this.secondSet.isSuited();
	}
	
	public boolean isMoreSecondSet(){
		return this.secondSet.isMore();
	}
	
	public boolean isLessSecondSet(){
		return this.secondSet.isLess();
	}
	
	
	public int getPrimerValor() {
		return primerValor.getValue();
	}
	
	public int getSegundoValor() {
		return segundoValor.getValue();
	}
	
	public int getTercerValor() {
		return this.secondSet.getPrimerValor();
	}
	
	public int getCuartoValor() {
		return this.secondSet.getSegundoValor();
	}

	/**
	 * Metodo para parsear valor de la carta
	 * 
	 * @param name
	 *            El parametro name define la letra del valor
	 * @return devuelve el objeto del valor
	 */
	private Value parseValue(char name) {
		switch (name) {
		case '2':
			return Value.Two;
		case '3':
			return Value.Three;
		case '4':
			return Value.Four;
		case '5':
			return Value.Five;
		case '6':
			return Value.Six;
		case '7':
			return Value.Seven;
		case '8':
			return Value.Eight;
		case '9':
			return Value.Nine;
		case 'T':
			return Value.Ten;
		case 'J':
			return Value.Jack;
		case 'Q':
			return Value.Queen;
		case 'K':
			return Value.King;
		case 'A':
			return Value.Ace;
		default:
			System.out.println("Error en parsear el valor");
			break;
		}
		return null;
	}
}
