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
	
	public static Palo parsea(String c){
		switch(c){
			case "h":	return Palo.Hearts;
			case "d":	return Palo.Diamonds;
			case "c":	return Palo.Clubs;
			case "s":	return Palo.Spades;
		}
		
		return null;
	}
}
