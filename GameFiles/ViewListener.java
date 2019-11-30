
public interface ViewListener {

	String getPlayerName();

	void pickCard(int index);

	void shuffle();

	void startGame(String playerName, String serverAddress, String portNumber);

	void returnToLogIn();
}
