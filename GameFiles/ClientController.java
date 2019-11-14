import java.util.ArrayList;
import java.util.Collections;

public class ClientController implements ViewListener {
	private gui mainWindow;
	private String playerName;
	private String serverAddress;
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

	@Override
	public void startGame(String playerName, String serverAddress, String portNumber) {
		this.playerName = playerName;
		this.serverAddress = serverAddress;
		this.portNumber = portNumber;
		
		mainWindow.setInitialValues(playerName, portNumber);
		
		//TODO Request cards from server here
		
		mainWindow.startGame();
	}

	@Override
	public String getPlayerName() {
		return playerName;
	}

	@Override
	public void sendMessage(int index) {
		String code = String.format("%02d", playerID) + "PA" + cards.get(index);
		
		System.out.println(code);
		//send code to server?
	}

	@Override
	public void shuffle() {
		Collections.shuffle(cards);
		System.out.println(cards);
		mainWindow.updateCards(cards.get(0), cards.get(1), cards.get(2), cards.get(3));
	}
}
