
public interface HandlerListener {

	void setCards(String card1, String card2, String card3, String card4);

	void pressEnter();

	void setUserId(int id);

	void showPopup(String substring);

	void exit(boolean fromServer);

}
