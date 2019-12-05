import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Collections;
import java.util.Map;
import java.util.Scanner;

public class Server extends UDPComponent implements Runnable, GameConstants{
	private int gameState;
	private int maxPlayers;
	private ArrayList<User> players;
	private Thread thread;
	private HashMap<User, String> hands;
	private HashMap<User, String> log;
	private HashMap<User, String> passedCards;
	private int tracker;
	private int endgame = 0;

	/*
	 * Constructs a server by binding a DatagramSocket to a specified
	 * port. Run the server on a separate thread.
	 */
	public Server(int port, int maxPlayers) throws IOException{
		super(port);
		this.maxPlayers = maxPlayers;
		this.gameState = GAME_START;
		this.players = new ArrayList<User>();
		this.hands = new HashMap<User, String>();
		this.log = new HashMap<User, String>();
		this.passedCards = new HashMap<User, String>();
		new Thread(this).start();
	}

	/*
	 * Broadcasts a given message to all of its connected players.
	 */
	public void broadcast(String message) throws IOException{
		InetAddress receiver;
		int port;

		for(User p : this.players){
			receiver = p.getAddress();
			port = p.getPort();

			this.send(message, receiver, port);
		}
	}

	/*
	 * Provides each player with a shuffled hand of four cards.
	 * Sends these cards over to each player IP Addresses as a signal.
	 */
	public void distributeCards() throws IOException{
		int deckCount = 0;
		ArrayList<String> gameDeck = new ArrayList<String>(), mainSetup;

		while(deckCount < (this.players.size() * 4)){
			gameDeck.add(this.deck[deckCount]);
			deckCount++;
		}

		Collections.shuffle(gameDeck);
		mainSetup = new ArrayList<String>();

		for(int i = 0; i < this.players.size(); i++){
			String firstFour = "";
			for(int cardCount = 0; cardCount < 4; cardCount++) firstFour = firstFour + gameDeck.get(cardCount + (4 * i));
			this.hands.put(this.players.get(i), firstFour);
			this.log.put(this.players.get(i), firstFour);
			firstFour = "SC" + firstFour;
			this.send(firstFour, this.players.get(i).getAddress(), this.players.get(i).getPort());
		}
	}

	public void initPassedCards(){
		for (Map.Entry<User, String> entry : this.hands.entrySet()){
			this.passedCards.put(entry.getKey(), new String());
		}
	}

	/*
	 * Prints information of all the players and their current cards.
	 */
	public void printPlayerStatuses(){
		for(Map.Entry<User, String> p : this.hands.entrySet()){
			System.out.println("Player ID: " + p.getKey().getUserID() + " Hand: " + p.getValue());
		}
	}

	/*
	 * Prints the messages of all the players sent to the server in the
	 * current turn.
	 */
	public void printPlayerLogs(){
		for(Map.Entry<User, String> p : this.log.entrySet()){
			System.out.println("Player ID: " + p.getKey().getUserID() + " Hand: " + p.getValue());
		}
	}

	public void printPlayerPasses(){
		for(Map.Entry<User, String> p : this.passedCards.entrySet()){
			System.out.println("Player ID: " + p.getKey().getUserID() + " Passed: " + p.getValue());
		}
	}

	/*
	 * Sends to the players their respective hands.
	 */
	public void sendCards() throws IOException{
		User player;
		String hand;

		for(Map.Entry<User, String> entry : this.hands.entrySet()){
			player = entry.getKey();
			hand = "SC" + entry.getValue();
			this.send(hand, player.getAddress(), player.getPort());
		}
	}

	/*
	 * Updates the log with new messages from the clients.
	 */
	public synchronized void updateLog(User player, String signal){
		this.log.replace(player, signal);
	}

	public synchronized void updatePassedCards(User player, String card){
		this.passedCards.replace(player, card);
	}

	/*
	 * Checks if the player has four cards with the same numbered card
	 * of all suits.
	 */
	private boolean isFourOfAKind(String hand){
		if((hand.charAt(0) == hand.charAt(2)) && (hand.charAt(0) == hand.charAt(4)) && (hand.charAt(0)) == hand.charAt(6)){
			return true;
		}

		return false;
	}

	/*
	 * Returns a boolean value as a result of isFourOfAKind.
	 */
	public boolean hasWon(User player, String hand){
		return this.isFourOfAKind(hand);
	}

	/*
	 * Returns the user instance of the one who got the four-of-a-kind.
	 */
	public User determineWinner(){
		for(Map.Entry<User, String> entry : this.hands.entrySet()){
			if(this.hasWon(entry.getKey(), entry.getValue())) return entry.getKey();
		}

		return null;
	}

	public boolean foundFourOfAKind(){
		for(Map.Entry<User, String> entry : this.hands.entrySet()){
			if(this.hasWon(entry.getKey(), entry.getValue())) return true;
		}

		return false;
	}

	/*
	 * Distributes the respective User ID's of all clients.
	 */
	public void distributeUIDs() throws IOException{
		for(User u : this.players){
			if(u.getUserID() < 10)
				this.send("UN0" + u.getUserID(), u.getAddress(), u.getPort());
			else
				this.send("UN" + u.getUserID(), u.getAddress(), u.getPort());
		}
	}

	private ArrayList<String> tokenize(String hand){
		ArrayList<String> tokens = new ArrayList<String>();

		for(int i = 0; i < hand.length(); i += 2){
			tokens.add(hand.substring(i, i + 2));
		}

		return tokens;
	}

	/*
	 * Exchanges cards in a round robin fashion.
	 * Fetch the cards, then tokenize them into substrings of length 2.
	 * Get the index of the card to be replaced.
	 * Set that element to the card passed by the current player.
	 * Replace the hand of the player of the corresponding hand.
	 */
	public void exchangeCards(){
		int u_id, recepient, index;
		User curr;
		String currHand, newHand, exc;
		ArrayList<String> tokensUser, tokensRec;


		for(Map.Entry<User, String> entry : this.hands.entrySet()){
			newHand = new String();
			u_id = entry.getKey().getUserID();
			curr = entry.getKey();
			exc = this.passedCards.get(curr);

			if(entry.getKey().getUserID() != this.maxPlayers){
				recepient = u_id + 1;
			}else recepient = 1;

			User p = this.findPlayer(recepient);
			tokensRec = this.tokenize(this.hands.get(p));

			index = tokensRec.indexOf(this.passedCards.get(p));
			tokensRec.set(index, exc);

			for(String token : tokensRec){
				newHand = newHand + token;
			}

			this.hands.replace(p, newHand);
		}

		this.printPlayerStatuses();
	}

	private User findPlayer(int userID){
		for(User u : this.players){
			if(u.getUserID() == userID) return u;
		}
		return null;
	}

	public synchronized void handleReceived(String message) throws IOException{
		switch(message.substring(0,2)){
			case "PA":
				int userid = Integer.parseInt(message.substring(2,4));
				String card = message.substring(4,6);
				this.updatePassedCards(this.findPlayer(userid), card);
				break;
			case "EX":
				this.setGameState(ERROR);
				break;
			case "EN":
				this.endgame += 1;
				if(this.endgame < this.maxPlayers){
					this.send("WI", this.address, this.port);
				}
				else{
					this.send("LO", this.address, this.port);
				}
				break;
			default:
				break;
		}
	}

	/*
	 *
	 */
	@Override
	public void run(){
		InetAddress playerAddress;
		int port;
		String nickname, signal;
		User winner;

		try{
			while(this.players.size() < this.maxPlayers){
				nickname = this.receive();
				playerAddress = this.getAddress();
				port = this.getPort();

				User player = new User(nickname, playerAddress, port);
				this.players.add(player);
			}

			for(User u : this.players){
				new Thread(new Handler(this, u)).start();
			}

			this.distributeUIDs();
			this.distributeCards();
			this.initPassedCards();

			boolean foundFOAK = false;

			while(!foundFOAK && this.gameState != ERROR){
				this.tracker = 0;

				while(this.tracker < this.maxPlayers){
					System.out.println(this.tracker);
				}

				if(this.gameState == ERROR) break;

				this.printPlayerPasses();
				this.exchangeCards();
				this.sendCards();
				foundFOAK = this.foundFourOfAKind();
			}

			if(this.gameState == ERROR){
				this.broadcast("RL");
				this.players.clear();
				this.hands.clear();
				this.log.clear();
				this.passedCards.clear();
				this.close();
			}

			this.broadcast("WN");
			//this.tracker = 0;

			ArrayList<User> winners = new ArrayList<User>();
			for(Map.Entry<User, String> entry : this.hands.entrySet()){
				if(this.isFourOfAKind(entry.getValue())){
					winners.add(entry.getKey());
				}
			}

			String winMes = "Winners: Player/s ";
			for(User u : winners){
				winMes = winMes + u.getUserID() + " ";
			}
			winMes = winMes + "!";
			this.broadcast(winMes);
		}catch(IOException e){
			System.out.println("Cannot listen to " + this.socket.getPort());
		}
	}

	public synchronized void updateTracker(){
		this.tracker++;
	}

	/*
	 * Changes the game state depending on the game's current state
	 * and its variables.
	 */
	private void changeGameState(){
		switch(this.gameState){
			case WAITING_FOR_PLAYERS:
				if(this.players.size() == this.maxPlayers){
					this.gameState = GAME_START;
				}
				break;
			case GAME_START:
				this.gameState = GAME_OVER;
				break;
			default: break;
		}
	}

	private synchronized void setGameState(int state){
		this.gameState = state;
	}

	public int getGameState(){
		return this.gameState;
	}

	public static void main(String[] args){
		try{
			Server serv = new Server(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
		}catch(IOException e){
			System.out.println("Error creating server: Cannot bind to port " + args[1]);
		}catch(ArrayIndexOutOfBoundsException e){
			System.out.println("Usage: java Server <port> <max_players>");
		}
	}
}
