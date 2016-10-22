package Poker_All_In;

import FileIO.InputFile;
import FileIO.OutPutFile;
import Game.BestHandWith2Card;
import Game.BestHandWith5Card;
import Game.Game;
import Game.OmahaGame;
import Game.PokerGame;

public class Poker_All_In {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		long start = System.currentTimeMillis();

		InputFile entrada = new InputFile(args[1]);
		OutPutFile salida = new OutPutFile(args[2]);

		String line;
		Game game = null;
		
		// args[0] es el opcion de la jugada
		switch (args[0]) {
		case "1":
			game = new BestHandWith5Card();
			break;
		case "2":
			game = new BestHandWith2Card();
			break;
		case "3":
			game = new PokerGame();
			break;
		case "4":
			game = new OmahaGame();
			break;
		default:
			System.out.println("Fuera del rango de opcion");
			break;
		}

		line = entrada.readLine();
		while (line != null) {
			
			game.parseGame(line);
			game.processGame();
			salida.saveResult(game.toString());
			
			game.clear();
			line = entrada.readLine();
		}
		
		entrada.close();
		salida.close();


		long end = System.currentTimeMillis();
		long time = end - start;
		System.out.println("Tiempo de ejecucion: " + time + "ms " + time / 1000 + "s");
	}

}
