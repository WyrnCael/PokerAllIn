package GUI.Helpers;

import Cards.Card;

public final class CardImageName {
	
	public static String getImageName(Card card){
		String name = ".\\resources\\img\\PNGCards\\";
		
		int value = card.getValue().getValue();
		switch(value){
			case 2: 
			case 3: 
			case 4: 
			case 5: 
			case 6: 
			case 7: 
			case 8: 
			case 9: 
			case 10:
				name += String.valueOf(value);
				break;
			case 11:
				name += "jack";
				break;
			case 12:
				name += "queen";
				break;
			case 13:
				name += "king";
				break;
			case 14:
				name += "ace";
				break;
		}
		
		name += "_of_";
		
		switch(card.getSuit().getNum()){
			case 1:
				name += "hearts";
				break;
			case 2:
				name += "diamonds";
				break;
			case 3:
				name += "clubs";
				break;
			case 4:
				name += "spades";
				break;
		}
		
		name += ".png";
		
		return name;
	}
}
