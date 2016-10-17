package Cartas;

public enum Suit {
	Hearts("Red", 'h'),
	Diamonds("Red", 'd'),
	Clubs("Black", 'c'),
	Spades("Black", 's');
	
	
	private String color;
	private char letra;
	
	private Suit(String color, char letter){
		this.color = color;
		this.letra = letter;
	}
	
	public String getPalo(){
		return this.color;
	}
	
	public char getChar(){
		return this.letra;
	}
	
	public int getValorAsociado(){
		switch (this.letra){
		case 'h':
			return 1;
		case 'd':
			return 2;
		case 'c':
			return 3;
		case 's':
			return 4;
		default:		
			return -1;
		}
	}
}

