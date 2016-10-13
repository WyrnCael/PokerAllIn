package Cartas;

public enum Point {
	ACE('A'),
	KING('K'),
	QUEEN('Q'),
	JACK('J'),
	TEN('T'),
	NINE('9'),
	EIGHT('8'),
	SEVEN('7'),
	SIX('6'),
	FIVE('5'),
	FOUR('4'),
	THREE('3'),
	TWO('2');
	
	private char point;
	private String desc;
	
	public String toString(){
		return this.desc;
	}
	
	Point(char point){
		this.point = point;
	}
	
	public int getPoint(){
		return this.point;
	}
	
	public static Point parsea(String string){
		switch(string){
			case "2": 	return Point.TWO;
			case "3": 	return Point.THREE;
			case "4": 	return Point.FOUR;
			case "5": 	return Point.FIVE;
			case "6": 	return Point.SIX;
			case "7": 	return Point.SEVEN;
			case "8": 	return Point.EIGHT;
			case "9": 	return Point.NINE;
			case "T": 	return Point.TEN;
			case "J": 	return Point.JACK;
			case "Q": 	return Point.QUEEN;
			case "K": 	return Point.KING;
			case "A": 	return Point.ACE;
		}
		
		return null;
	}
}
