import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ClientSignalHandler implements Runnable {
	private DatagramSocket socket;
	private String message;

	private HandlerListener listener;

	public ClientSignalHandler(DatagramSocket socket, HandlerListener listener) {
		this.socket = socket;
		this.listener = listener;
	}

	@Override
	public void run() {
		message = new String();

		byte buf[] = new byte[256];
		DatagramPacket packet = new DatagramPacket(buf, buf.length);

	    try {
			socket.receive(packet);
			message = new String(packet.getData(), 0, packet.getLength());
			switch(message.substring(0, 2)) {
				case "SC":
					listener.setCards(message.substring(2, 4), message.substring(4, 6), message.substring(6, 8), message.substring(8, 10));
					break;
				case "WN":
					listener.pressEnter();
					break;
				default:
					System.out.println("No match!");
			}
	        System.out.println("Received: " + message);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
