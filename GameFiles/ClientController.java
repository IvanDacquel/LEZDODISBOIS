import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class ClientController implements ViewListener {
	private InetAddress host;
	private DatagramSocket socket;
	private String signal;
	
	private gui mainWindow;
	private String playerName;
	private String portNumber;
	private int playerID;
	
	private ArrayList<String> cards;
	
	public ClientController() {
		cards = new ArrayList<String>();
		
		mainWindow = new gui(this);
		
		//============TEMP CALL, REMOVE LATER. DUMMY VALUES=============
		initCards("2H", "4C", "5H", "KD");
		//===============================================================
	}
	
	//--------TEMP FUNCTION FOR WHEN SERVER HAS FINISHED GIVING CARDS-----------
	public void initCards(String card1, String card2, String card3, String card4) {
		cards.add(card1);
		cards.add(card2);
		cards.add(card3);
		cards.add(card4);
		
		mainWindow.updateCards(cards.get(0), cards.get(1), cards.get(2), cards.get(3));
	}
	
	@SuppressWarnings("resource")
	private void sendMessage(String message) {
	    byte[] buf = new byte[256];
	    
	    signal = new Scanner(message).nextLine();
        buf = signal.getBytes();
	    DatagramPacket packet = new DatagramPacket(buf, buf.length, host, Integer.parseInt(portNumber));
	    
		try {
			socket.send(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void startGame(String playerName, String serverAddress, String portNumber) {
		this.playerName = playerName;
		this.portNumber = portNumber;
		
		mainWindow.setInitialValues(playerName, portNumber);

		try {
			host = InetAddress.getByName(serverAddress);
			socket = new DatagramSocket();
			
			sendMessage(playerName);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (SocketException e) {
			e.printStackTrace();
		}
		
		mainWindow.startGame();
	}

	@Override
	public String getPlayerName() {
		return playerName;
	}

	@Override
	public void pickCard(int index) {
		String code = String.format("%02d", playerID) + "PA" + cards.get(index);

		sendMessage(code);
		System.out.println(code);
	}

	@Override
	public void shuffle() {
		Collections.shuffle(cards);
		System.out.println(cards);
		mainWindow.updateCards(cards.get(0), cards.get(1), cards.get(2), cards.get(3));
	}

	@Override
	public void exit() {
		sendMessage(playerName + " has left the game!");
		System.exit(0);
	}
}
