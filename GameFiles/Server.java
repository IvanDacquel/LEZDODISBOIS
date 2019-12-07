/***
  * Server.java
  * Authors: Ivan Amadeus Dacquel, Michael Anthony Dollentes, Francis James Figueroa, Jeran Salcedo, Paul Villaro
  * Description: A Server object which hosts a 1-2-3 PASS! Game Server.
 ***/

import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Collections; //for shuffling contents in a collection like ArrayList;
import java.util.Map;
import java.util.Scanner;

public class Server extends UDPComponent implements Runnable, GameConstants{
	private int gameState; //stores the state of the game
	private int maxPlayers; //maximum number of connected players
	private ArrayList<User> players; //storage of player information
	private HashMap<User, String> hands; //HashMap of cards in players' hands
	private HashMap<User, String> log; //HashMap of received messages from each player for current turn
	private HashMap<User, String> passedCards; //HashMap of passed cards by each player
	private int tracker; //tracks the number of players who sent a message to the server.
	private int endgame = 0; //tracks how many players pressed "Enter"
	private int turn; //tracks the number of turns

	/*
	 * Constructs a server by binding a DatagramSocket to a specified
	 * port. Run the server on a separate thread. Creates new ArrayLists
	 * and HashMaps for the data structures. Initialize number of turns
	 * to 0, as it is in the state of waiting for players.
	 */
	public Server(int port, int maxPlayers) throws IOException{
		super(port);
		this.maxPlayers = maxPlayers;
		this.gameState = GAME_START;
		this.players = new ArrayList<User>();
		this.hands = new HashMap<User, String>();
		this.log = new HashMap<User, String>();
		this.passedCards = new HashMap<User, String>();
		this.turn = 0;
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
			gameDeck.add(this.deck[deckCount]); //add cards to a new ArrayList depending on the number of players
			deckCount++;
		}

		Collections.shuffle(gameDeck); //shuffle the contents of the gameDeck
		mainSetup = new ArrayList<String>();

		/*
		  For every player, get the shuffled cards and send them over to the respective
		  players as a signal.
		 */
		for(int i = 0; i < this.players.size(); i++){
			String firstFour = "";
			for(int cardCount = 0; cardCount < 4; cardCount++) firstFour = firstFour + gameDeck.get(cardCount + (4 * i));
			this.hands.put(this.players.get(i), firstFour);
			this.log.put(this.players.get(i), firstFour);
			firstFour = "SC" + firstFour;
			this.send(firstFour, this.players.get(i).getAddress(), this.players.get(i).getPort());
		}
	}

	/*
	 * Initializes the passedCards HashMap.
	 */
	public void initPassedCards(){
		for (Map.Entry<User, String> entry : this.hands.entrySet()){
			this.passedCards.put(entry.getKey(), new String());
		}
	}

	/*
	 * Prints information of all the players and their current cards.
	 */
	public void printPlayerStatuses(){
		if (this.turn > 0) System.out.println("\nPlayer Hands on Turn: " + this.turn);
		else System.out.println("\nPlayer Hands on Game Start: ");
		for(Map.Entry<User, String> p : this.hands.entrySet()){
			System.out.println("\tPlayer ID: " + p.getKey().getUserID() + " Name: " + p.getKey().getUsername() + " Hand: " + p.getValue());
		}
	}

	/*
	 * Prints the messages of all the players sent to the server in the
	 * current turn.
	 */
	public void printPlayerLogs(){
		System.out.println("\nSignals Received on Turn " + this.turn);
		for(Map.Entry<User, String> p : this.log.entrySet()){
			System.out.println("\tPlayer ID: " + p.getKey().getUserID() + " Name: " + p.getKey().getUsername() + " Signal: " + p.getValue());
		}
	}

	/*
	 * Prints the passed cards of all the players sent to the server in the
	 * current turn.
	 */
	public void printPlayerPasses(){
		System.out.println("\nPassed Cards on Turn " + this.turn);
		for(Map.Entry<User, String> p : this.passedCards.entrySet()){
			System.out.println("\tPlayer ID: " + p.getKey().getUserID() + " Name: " + p.getKey().getUsername() + " Passed: " + p.getValue());
		}
		System.out.println();
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

	/*
	 * Updates the passedCards HashMap with the new cards passed
	 * in the signal.
	 */
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
	 * Returns a the result of isFourOfAKind.
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

	/*
	 * Searches through the current hands to find which player got a
	 * four of a kind.
	 */
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

	/*
	 * Splits the string of cards into an ArrayList of lengths 2.
	 */
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
	}

	/*
	 * Finds the player using a user_id.
	 * Returns null if not found.
	 */
	private User findPlayer(int userID){
		for(User u : this.players){
			if(u.getUserID() == userID) return u;
		}
		return null;
	}

	/*
	 * Finds the player using an InetAddress and a port number.
	 * Returns null if not found.
	 */
	private User findPlayerByIP(InetAddress address, int port){
		for(User u : this.players){
			if((u.getAddress().equals(address)) && (u.getPort() == port)){
				return u;
			}
		}
		return null;
	}
	
	/*
	 * Handles received messages from each client.
	 * Uses the first two characters as the string as the basis
	 * of server's decision.
	 */
	public synchronized void handleReceived(String message) throws IOException{
		switch(message.substring(0,2)){
			case "PA": //update entry in passedCards hashMap with the user and its new passed card
				int userid = Integer.parseInt(message.substring(2,4));
				String card = message.substring(4,6);
				this.updatePassedCards(this.findPlayer(userid), card);
				break;
			case "EX": //set game state to error if player disconnects during the game
				this.setGameState(ERROR);
				break;
			case "EN": //four of a kind found and it's the end game phase
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
	 * The server's run method.
	 */
	@Override
	public void run(){
		InetAddress playerAddress;
		int port;
		String nickname, signal;
		User winner;

		try{
			/*
			 * While the maximum number of players haven't connected
			 * yet, accept connections from clients and add them to the
			 * players ArrayList.
			 */
			while(this.players.size() < this.maxPlayers){
				nickname = this.receive();
				playerAddress = this.getAddress();
				port = this.getPort();

				User player = new User(nickname, playerAddress, port);
				this.players.add(player);
			}

			this.distributeUIDs(); //distribute User ID's to respective users
			this.distributeCards(); //distribute cards
			this.initPassedCards(); //initialize the passed Cards hashMap
			this.printPlayerStatuses();

			boolean foundFOAK = false;
			this.turn = 1; //game start! set current turn count to 1.
			
			/*
			 * Receive signals from client while a four of a kind
			 * isn't found yet or someone hasn't disconnected yet.
			 * Print player statuses before exchanging cards.
			 * Increment turn counter and find if a four of a kind
			 * has already been found before beginning the loop again.
			 */
			while(!foundFOAK && this.gameState != ERROR){
				this.tracker = 0;

				while (this.tracker < this.maxPlayers){
					String received = this.receive();
					User u = this.findPlayerByIP(this.getAddress(), this.getPort());
					this.updateLog(u, received);
					this.handleReceived(received);
					this.updateTracker();
				}

				if(this.gameState == ERROR) break;

				this.printPlayerStatuses();
				this.exchangeCards();
				this.sendCards();
				
				foundFOAK = this.foundFourOfAKind();
				this.turn++;
			}

			/*
			 * Forcefully logs every one out if a user disconnected.
			 */
			if(this.gameState == ERROR){
				this.broadcast("RL");
				this.refresh();
			}else{
			//Else, endgame is reached.
				System.out.println("\nTotal Turns: " + (this.turn-1));
				System.out.println("\nPlayer Hands on Winning: ");
				for(Map.Entry<User, String> p : this.hands.entrySet()){
					System.out.println("\tPlayer ID: " + p.getKey().getUserID() + " Name: " + p.getKey().getUsername() + " Hand: " + p.getValue());
				}

				this.broadcast("WN");
				this.tracker = 0;

				while (this.tracker < this.maxPlayers){
					String received = this.receive();
					User u = this.findPlayerByIP(this.getAddress(), this.getPort());
					this.updateLog(u, received);
					this.handleReceived(received);
					this.updateTracker();
				}
			}
			
			this.close();
		}catch(IOException e){
			System.out.println("Cannot listen to " + this.socket.getPort());
		}
	}

	//Updates the tracker.
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

	/*
	 * Sets the current game state.
	 */
	private synchronized void setGameState(int state){
		this.gameState = state;
	}

	/*
	 * Gets the current game state.
	 */
	public int getGameState(){
		return this.gameState;
	}

	/*
	 * Clears data structures.
	 */
	public void refresh(){
		this.players.clear();
		this.hands.clear();
		this.log.clear();
		this.passedCards.clear();		
	}
	
	/*
	 * Clears data structures and closes the server.
	 */
	public void close() throws IOException{
		this.players.clear();
		this.hands.clear();
		this.log.clear();
		this.passedCards.clear();
		super.close();		
	}
	
	/*
	 * Main program.
	 */
	public static void main(String[] args){
		try{
			Server serv = new Server(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
		}catch(IOException e){
			System.out.println("Error creating server: Cannot bind to port " + args[1]);
		}catch(ArrayIndexOutOfBoundsException e){
			System.out.println("Usage: java Server <port> <max_players>");
		}
	}
	
	/*
	 * Have server administrator choose if he wants to reopen the server.
	 */
	public static int reopenServer(){
		System.out.println("Reopen server?");
		System.out.println("[1] Yes\t[2]No");
		System.out.print("Choice: ");
		return new Scanner(System.in).nextInt();
	}
}
