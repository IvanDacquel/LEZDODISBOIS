
public interface ViewListener {

	String getPlayerName();

	void sendMessage(int index);

	void shuffle();

	void startGame(String playerName, String serverAddress, String portNumber);
}
