import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Collections;

public class Server extends UDPComponent implements GameConstants{
	private int gameState;
	private int maxPlayers;
	private ArrayList<User> players;
	private Thread thread;
  private HashMap<User, String> log;

	/*
	 * Constructs a server by binding a DatagramSocket to a specified
	 * port. Run the server on a separate thread.
	 */
	public Server(int port, int maxPlayers) throws IOException{
		super(port);
		this.maxPlayers = maxPlayers;
		this.players = new ArrayList<User>();

    this.run();
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
	 * Game state -> Waiting for players:
	 *     Actively listens to client connections and stores their infor-
	 *     mation on an ArrayList.
	 *
	 * Game state -> Game start:
	 *
	 * Game state -> Game over:
	 *     The thread ends when the game is over. The winner will be broadcast-
	 *     ed, and the server's socket will be closed.
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
			firstFour = "SC" + firstFour;
			System.out.println(firstFour);
			this.send(firstFour, this.players.get(i).getAddress(), this.players.get(i).getPort());
		}
	}

	public void run(){
		InetAddress playerAddress;
		int port;
		String nickname, signal;

		try{
			while(this.players.size() != this.maxPlayers){
        nickname = this.receive();
        playerAddress = this.getAddress();
        port = this.getPort();

        User player = new User(nickname, playerAddress, port);
        this.players.add(player);
        new Thread(new Handler(this, player)).start();
      }

      this.distributeCards();

      while(true){

      }
//			this.close();
		}catch(IOException e){
			System.out.println("Cannot listen to " + this.socket.getPort());
		}
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
