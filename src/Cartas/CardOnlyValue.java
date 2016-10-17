package Cartas;

@SuppressWarnings("rawtypes")
public class CardOnlyValue implements Comparable{

	// Campos de la clase 
	private Value value;
	
	/**
	 * Constructor
	 * @param value El parametro value define el valor de la carta
	 * @param suit El parametro suit define el palo de la carta
	 */
	public CardOnlyValue(Value value){
		this.value = value;
	}
	
	/**
	 * El metodo para obtener el valor de la carta
	 * @return value es el valor de la carta
	 */
	public Value getValue() {
		return this.value;
	}
	
	public String toString(){
		return this.value.getName();
	}
	
	@Override
	public int compareTo(Object o){
		int n1 = value.getValue();
		int n2 = ((CardOnlyValue) o).getValue().getValue();
		if(n1 == n2)
			return 0;
		else if( n1 > n2)
			return -1;
		else
			return 1;
	}
}
