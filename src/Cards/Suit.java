package Cards;

public enum Suit {
	Hearts('h', 1),
	Diamonds('d', 2),
	Clubs('c', 3),
	Spades('s', 4);

	// Campos de la clase
	private char letter;
	private int num;

	/**
	 * Constructor
	 * 
	 * @param letter
	 *            El parametro letter define el char del palo
	 * @param num
	 *            El parametro num define el numero del palo
	 */
	private Suit(char letter, int num) {
		this.letter = letter;
		this.num = num;
	}

	/**
	 * Metodo que devuelve el char del palo
	 * 
	 * @return this.letter es el char del palo
	 */
	public char getChar() {
		return this.letter;
	}

	/**
	 * Metodo que devuelve el numero del palo
	 * 
	 * @return this.num es el numero del palo
	 */
	public int getNum() {
		return this.num;
	}
}
