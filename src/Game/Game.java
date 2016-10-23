package Game;

public abstract class Game {
	/**
	 * El metodo que parsea los datos de la partida
	 * @param gameInfo El parametro gameInfo define los datos de la partida
	 */
	public abstract void parseGame(String gameInfo);
	
	/**
	 * El metodo que procesa la partida
	 */
	public abstract void processGame();
	
	/**
	 * El metodo que clear la lista de la clase
	 */
	public abstract void clear();
	
	public abstract BestHand getBestHand();
}
