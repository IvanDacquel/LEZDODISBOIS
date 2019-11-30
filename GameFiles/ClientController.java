import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class ClientController implements ViewListener, HandlerListener {
	private InetAddress host;
	private DatagramSocket socket;
	private String signal;
	
	private GUI mainWindow;
	private Thread clientWindowThread;
	private String playerName;
	private String portNumber;
	private int playerID;
	
	private ArrayList<String> cards;
	
	public ClientController() {
		cards = new ArrayList<String>();
		for(int i = 0; i < 4; i++) {
			cards.add("BJ");	
		}
		
		mainWindow = new GUI(this);
		clientWindowThread = new Thread(mainWindow);
	}
	
	public void setCards(String card1, String card2, String card3, String card4) {
		System.out.println(card1);
		System.out.println(card2);
		System.out.println(card3);
		System.out.println(card4);
		cards.set(0, card1);
		cards.set(1, card2);
		cards.set(2, card3);
		cards.set(3, card4);
		
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
		
		new Thread(new ClientSignalHandler(socket, this)).start();
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
		
		clientWindowThread.start();
	}

	@Override
	public String getPlayerName() {
		return playerName;
	}

	@Override
	public void pickCard(int index) {
		String code = String.format("%02d", playerID) + "PA" + cards.get(index);

		System.out.println(code);
		sendMessage(code);
	}

	@Override
	public void shuffle() {
		Collections.shuffle(cards);
		System.out.println(cards);
		mainWindow.updateCards(cards.get(0), cards.get(1), cards.get(2), cards.get(3));
	}

	@Override
	public void pressEnter() {
		mainWindow.pressEnter();
	}

	@Override
	public void returnToLogIn() {
		sendMessage("EX" + String.format("%02d", playerID));
		setCards("BJ", "BJ", "BJ", "BJ");
		clientWindowThread = new Thread(mainWindow);
		mainWindow.returnToLogin();
	}
}
