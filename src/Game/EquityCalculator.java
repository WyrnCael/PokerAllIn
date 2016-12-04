package Game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.sun.xml.internal.bind.v2.util.QNameMap;

import Cards.Card;
import Cards.DeckCards;
import Cards.Hand;
import Players.Player;

//Primero asignar el Board, luego los jugadores.
public class EquityCalculator {
	private DeckCards deck;
	private PokerGame pokerGame;
	private int numberOfPlayers;
	private int numberOfBoardCards;
	private Hand board;
	private HashMap<String, Player> players;
	
	public EquityCalculator(){
		pokerGame = new PokerGame();
		deck = new DeckCards();
		players = new HashMap<String, Player>();
		board = new Hand();
		numberOfPlayers = 0;
		numberOfBoardCards = 0;
	}
	
	public void addRandomPlayers(int numberOfPlayers){
		int index = numberOfPlayers;
		for(int i = 1; i <= numberOfPlayers; i++){
			Hand hand = new Hand();
			for(int j = 0; j < 2; j++){
				hand.add(deck.getRandomCard());
			}
			Player player = new Player("R" + i + index);
			player.setHand(hand);
			players.put(player.getName(), player);
			this.numberOfPlayers++;
		}		
	}
	
	public void addRandomBoard(int numberOfCards){
		board = new Hand();
		for(int i = 0; i < numberOfCards; i++){
			board.add(deck.getRandomCard());
		}
		numberOfBoardCards = numberOfCards;
	}
	
	public void addPlayer(String name, Hand hand){
		// Eliminamos las cartas de la baraja
		List<Card> cardsHand = hand.getCardsList();
		for(int i = 0; i < cardsHand.size(); i++){
			deck.removeCard(cardsHand.get(i));
		}
		
		Player player = new Player(name);
		player.setHand(hand);
		players.put(player.getName(), player);
		numberOfPlayers++;
	}
	
	public void addBoard(Hand hand){	
		numberOfBoardCards = 0;
		
		// Eliminamos las cartas de la baraja
		List<Card> cardsHand = hand.getCardsList();
		for(int i = 0; i < cardsHand.size(); i++){
			deck.removeCard(cardsHand.get(i));
			numberOfBoardCards++;
		}
		
		board = hand;
	}
	
	public List<Player> caclulateEquity(){				
		for(int v = 0; v < 2000000; v++){
			pokerGame = new PokerGame();
			ArrayList<Card> añadidas = new ArrayList<Card>();
			Hand boardRandom = new Hand();
			boardRandom.addAll(board.getCardsList());
			
			for(int i = numberOfBoardCards; i < 5; i++){
				Card aux = deck.getRandomCard();
				boardRandom.add(aux);
				añadidas.add(aux);
			}
			pokerGame.addBoard(boardRandom);
			
			Iterator it = players.entrySet().iterator();
			while(it.hasNext()){
				Map.Entry pair = (Map.Entry)it.next();
				Player player = (Player) pair.getValue();
				player.setBoard(boardRandom);
				pokerGame.addPlayer(player);
			}
			
			// Volvemos a añadir las cartas a la baraja
			for(int i = 0; i < 5 - numberOfBoardCards; i++){
				deck.insertCard(añadidas.get(i));
			}
			
			pokerGame.processGame();
			
			
			// Le marcamos como ganador
			players.get(pokerGame.winner().getName()).wonGame();			
		
		}
		
		Iterator it = players.entrySet().iterator();
		while(it.hasNext()){
			Map.Entry pair = (Map.Entry)it.next();
			Player player = (Player) pair.getValue();
			System.out.println("Player " + player.getName() + ": " +  Double.valueOf((Double.valueOf(player.getWonGames()) * 100 ) / 2000000));
		}
		
		
		return null;
	}
}
