package Cartas;

public enum Palo {
	Hearts("Red"),
	Diamonds("Red"),
	Clubs("Black"),
	Spades("Black");
	
	
	private String color;
	
	Palo(String color){
		this.color = color;
	}
	
	public String getPalo(){
		return this.color;
	}
}
