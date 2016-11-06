package Range;

import java.util.ArrayList;
import java.util.List;

public class Range {
	private int [][] table;     // 1: SameCards, 2: Suited, 3: offSuited 4: Selected
	
	public Range(){
		this.table = new int[13][13];
		
		for(int i = 0; i < 13; i++){
			for(int j = 0; j < 13; j++){
				if(i == j) table [i][j] = 1;
				else if(i < j) table [i][j] = 2;
				else table [i][j] = 3;
			}
		}
	}
	
	public void parseRange(String in){
		List<Character> aux = new ArrayList<Character>();
		
		for(int i = 0; i <= in.length(); i++){
			if(i < in.length() && in.charAt(i) != ','){ // Leemos la entrada
				aux.add(in.charAt(i));
			}
			else{  
				RangeSet set = new RangeSet(aux);
				
				int highValue = Math.max(set.getPrimerValor(), set.getSegundoValor());
				int lowValue = Math.min(set.getPrimerValor(), set.getSegundoValor());
				int x = 0, y = 0;
				if(set.isSuited()){
					x = lowValue;
					y = highValue;					
				}
				else{
					x = highValue;
					y = lowValue;
				}
				
				table[x][y] = 4;
				
				if(set.isMore()){
					if(x == y){
						for(int h = x ; h >= 0; h--){
							table[h][h] = 4;
						}
					}
					else if (x > y){
						for(int h = x ; h > y; h--){
							table[h][y] = 4;
						}
					}
					else{
						for(int h = y ; h > x; h--){
							table[x][h] = 4;
						}
					}
				}
				else if(set.isLess()){
					int highValue2 = Math.max(set.getTercerValor(), set.getCuartoValor());
					int lowValue2 = Math.min(set.getTercerValor(), set.getCuartoValor());
					int x2 = 0, y2 = 0;
					if(set.isSuitedSecondSet()){
						x2 = lowValue2;
						y2 = highValue2;					
					}
					else{
						x2 = highValue2;
						y2 = lowValue2;
					}
					
					for(int h = Math.max(x, x2) ; h >= Math.min(x, x2); h--){
						for(int g = Math.max(y, y2); g >= Math.min(y, y2); g--){
							table[h][g] = 4;
						}						
					}
				}
				
				aux = new ArrayList<Character>();
			}
		}
	}
	
	public int getValue(int x, int y){
		return table[x][y];
	}
	
	
}
