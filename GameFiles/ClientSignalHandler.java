import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ClientSignalHandler implements Runnable {
	private DatagramSocket socket;
	private String message;

	private ClientController listener;

	public ClientSignalHandler(DatagramSocket socket, ClientController listener) {
		this.socket = socket;
		//	listener == ClientController
		this.listener = listener;
	}

	/*
	 * Change this so that the client controller would have only one Thread that reads
	 * server messages continuously.
	 */
	@Override
	public void run() {
		message = new String();

		byte buf[] = new byte[256];
		DatagramPacket packet = new DatagramPacket(buf, buf.length);

	    try {
			while(true){
				//	Waits for the server to send a reply
				socket.receive(packet);
				//	Stores the received message in "message"
				message = new String(packet.getData(), 0, packet.getLength());
				//	Identifies the server's commands from received message's format
				System.out.println(message);
				switch(message.substring(0, 2)) {
					//	UN for giving the User Id
					case "UN":
						//	Tells the controller its player's id
						listener.setUserId(Integer.parseInt(message.substring(2, 4)));
						break;
				//	SC for changing the 4 cards at hand for this player
					case "SC":
						//	Tells the controller to change the cards to these newly received ones
						listener.setCards(message.substring(2, 4), message.substring(4, 6), message.substring(6, 8), message.substring(8, 10));
						break;
				//	WN for signaling that the winning condition has been met
					case "WN":
					//	Tells the controller to activate the ENTER button
						listener.pressEnter();
						break;
					case "RL":
						listener.returnToLogIn();
						break;
					default:
						break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
