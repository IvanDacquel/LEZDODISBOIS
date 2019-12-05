import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

//	Handles all the game logic
//	One ClientController for each player
public class ClientController implements ViewListener, HandlerListener {
	private InetAddress host;
	private DatagramSocket socket;
	private String signal;
	
	private GUI mainWindow;
	private Thread clientWindowThread;
	private String playerName;
	private String portNumber;
	private int playerID;
	
	//	Contains the cards(4) at hand of this player
	private ArrayList<String> cards;
	
	//	Class Init
	public ClientController() {
		//	Initializes the cards at hand to 4 "blanks"/dummy cards
		cards = new ArrayList<String>();
		for(int i = 0; i < 4; i++) {
			cards.add("BJ");	
		}
		
		//	Initializes the user interface window and its thread
		mainWindow = new GUI(this);
		clientWindowThread = new Thread(mainWindow);
	}
	
	//	Sets the current values of the cards at hand
	public void setCards(String card1, String card2, String card3, String card4) {
		System.out.println(card1);
		System.out.println(card2);
		System.out.println(card3);
		System.out.println(card4);
		cards.set(0, card1);
		cards.set(1, card2);
		cards.set(2, card3);
		cards.set(3, card4);
		
		//	Tells the UI to update the cards shown
		mainWindow.updateCards(cards.get(0), cards.get(1), cards.get(2), cards.get(3));
	}
	
	//	Function for communicating with the server
	@SuppressWarnings("resource")
	private void sendMessage(String message) {
	    byte[] buf = new byte[256];
	    
	    //	Creates the packet to be sent
	    signal = new Scanner(message).nextLine();
        buf = signal.getBytes();
	    DatagramPacket packet = new DatagramPacket(buf, buf.length, host, Integer.parseInt(portNumber));
	    
		try {
			//	Sends the packet to the server
			socket.send(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//	Called after pressing the START! button from the login window
	@Override
	public void startGame(String playerName, String serverAddress, String portNumber) {
		this.playerName = playerName;
		this.portNumber = portNumber;
		
		//	Tells the UI to update the displayed name and port numbers
		mainWindow.setInitialValues(playerName, portNumber);

		try {
			host = InetAddress.getByName(serverAddress);
			socket = new DatagramSocket();
			
			new Thread(new ClientSignalHandler(socket, this)).start();
			
			//	Sends the player's name to the server
			sendMessage(playerName);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (SocketException e) {
			e.printStackTrace();
		}
		
		//	Starts the window's thread
		clientWindowThread.start();
	}

	@Override
	public String getPlayerName() {
		return playerName;
	}

	//	Called when a card is clicked
	@Override
	public void pickCard(int index) {
		//	Generates the code for "Card Passing": player id, PA, card (eg. 01PAAH for player 2 passing Ace of Hearts)
		String code =  "PA" + String.format("%02d", playerID) + cards.get(index);

		System.out.println(code);
		//	Sends the code the to the server
		sendMessage(code);
	}

	//	Called when the SHUFFLE button is pressed
	@Override
	public void shuffle() {
		//	Shuffles the contents of the cards ArrayList
		Collections.shuffle(cards);
		System.out.println(cards);
		//	Tells the UI to update the displayed order of cards
		mainWindow.updateCards(cards.get(0), cards.get(1), cards.get(2), cards.get(3));
	}

	//	Called when server has given the signal to activate the ENTER button
	@Override
	public void pressEnter() {
		//	Tells the UI to activate the ENTER button
		mainWindow.pressEnter();
	}

	//	Called when the player has pressed the EXIT button
	@Override
	public void exit(boolean sendToServer) {
		//	Generates the code for "Exit": EX, player id (eg. EX00 for player 1 has left the game)
		if(sendToServer) {
			sendMessage("EX" + String.format("%02d", playerID));
		}
		//	Resets the cards at hand to blanks
		setCards("BJ", "BJ", "BJ", "BJ");
		//	Creates a new thread for the UI
		clientWindowThread = new Thread(mainWindow);
		// Tells the UI to hide the game window and show the log in window
		mainWindow.returnToLogin();
	}

	// Called after receiving the player's assigned ID from the server
	@Override
	public void setUserId(int id) {
		playerID = id;
		mainWindow.updateUserId(String.format("%02d", id));
	}

	// Called when player has pressed ENTER
	@Override
	public void pressedEnter() {
		sendMessage("EN" + String.format("%02d", playerID));
	}

	public void showPopup(String wl) {
		mainWindow.showPopup(wl);
	}
}
