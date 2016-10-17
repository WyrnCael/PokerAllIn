package Cartas;

@SuppressWarnings("rawtypes")
public class Card implements Comparable{

	// Campos de la clase 
	private Value value;
	private Suit suit;
	
	/**
	 * Constructor
	 * @param value El parametro value define el valor de la carta
	 * @param suit El parametro suit define el palo de la carta
	 */
	public Card(String value, String suit){
		this.value = parseValue(value);
		this.suit = parseSuit(suit);
	}
	
	// Constructor con valor como int y suit directo
	public Card(int value, Suit suit){
		switch(value){
		case 2:
			this.value = Value.Two;
			break;
		case 3:
			this.value = Value.Three;
			break;
		case 4:
			this.value = Value.Four;
			break;
		case 5:
			this.value = Value.Five;
			break;
		case 6:
			this.value = Value.Six;
			break;
		case 7:
			this.value = Value.Seven;
			break;
		case 8:
			this.value = Value.Eight;
			break;
		case 9:
			this.value = Value.Nine;
			break;
		case 10:
			this.value = Value.Ten;
			break;
		case 11:
			this.value = Value.Jack;
			break;
		case 12:
			this.value = Value.Queen;
			break;
		case 13:
			this.value = Value.King;
			break;
		case 14:
			this.value = Value.Ace;
			break;
		}
		this.suit = suit;
	}
	
	/**
	 * El metodo para obtener el valor de la carta
	 * @return value es el valor de la carta
	 */
	public Value getValue() {
		return this.value;
	}

	/**
	 * El metodo para obtener el palo de la carta
	 * @return suit es el palo de la carta
	 */
	public Suit getSuit() {
		return this.suit;
	}
	
	/**
	 * Metodo para parsear valor de la carta
	 * @param name El parametro name define la letra del valor
	 * @return devuelve el objeto del valor
	 */
	private Value parseValue(String name){
		switch(name){
			case "2": 	return  Value.Two;
			case "3": 	return Value.Three;
			case "4": 	return Value.Four;
			case "5": 	return Value.Five;
			case "6": 	return Value.Six;
			case "7": 	return Value.Seven;
			case "8": 	return Value.Eight;
			case "9": 	return Value.Nine;
			case "T": 	return Value.Ten;
			case "J": 	return Value.Jack;
			case "Q": 	return Value.Queen;
			case "K": 	return Value.King;
			case "A": 	return Value.Ace;
		}
		return null;		
	}
	
	/**
	 * Metodo para parsear el palo de la carta
	 * @param suit El parametro suit define la letra del palo
	 * @return devuelve el objeto del palo
	 */
	private Suit parseSuit(String suit){
		switch(suit){
			case "h":	return Suit.Hearts;
			case "d":	return Suit.Diamonds;
			case "c":	return Suit.Clubs;
			case "s":	return Suit.Spades;
		}
		return null;
	}
	
	public String toString(){
		return this.value.getName() + this.suit.getChar();
	}
	
	@Override
	public int compareTo(Object o){
		int n1 = value.getValue();
		int n2 = ((Card) o).getValue().getValue();
		Suit s2 = ((Card) o).getSuit();
		if(n1 == n2 && Character.compare(suit.getChar(),s2.getChar()) == 0)
			return 0;
		else if (n1 == n2 && Character.compare(suit.getChar(),s2.getChar()) < 0)
			return -1;
		else if (n1 == n2 && Character.compare(suit.getChar(),s2.getChar()) > 0)
			return 1;
		else if( n1 > n2)
			return -1;
		else
			return 1;
	}
}
