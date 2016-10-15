package Cartas;

public class Card implements Comparable{

	private Value value;
	private Suit suit;
	
	
	public Card(Value v, Suit c){
		this.value=v;
		this.suit=c;
	}
	
	public Value getValue() {
		return this.value;
	}

	public Suit getSuit() {
		return suit;
	}
	
	public String toString(){
		return this.value.getName() + this.suit.getChar();
	}
	
	@Override
	public int compareTo(Object o){
		int n1 = value.getValor();
		int n2 = ((Card) o).getValue().getValor();
		if(n1 == n2)
			return 0;
		else if( n1 > n2)
			return -1;
		else
			return 1;
	}

}
