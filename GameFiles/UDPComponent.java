import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.io.IOException;

public class UDPComponent{
	protected DatagramSocket socket;
	protected InetAddress address;
	protected int port;
	
	public UDPComponent(int port) throws IOException{
		this.socket = new DatagramSocket(port);
	}
	
	public UDPComponent() throws IOException{
		this.socket =  new DatagramSocket();
	}
	
	protected void send(String message, InetAddress address, int port) throws IOException{
		byte buf[] = message.getBytes();
		
		DatagramPacket packet = new DatagramPacket(buf, buf.length, address, port);
		this.socket.send(packet);
	}
	
	protected String receive() throws IOException{
		byte buf[] = new byte[256];
		
		DatagramPacket packet = new DatagramPacket(buf, buf.length);
		this.socket.receive(packet);
	
		this.address = packet.getAddress();
		this.port = packet.getPort();
	
		return new String(packet.getData(), 0, packet.getLength());
	}
	
	protected InetAddress getAddress(){
		return this.address;
	}
	
	protected int getPort(){
		return this.port;
	}
	
	protected void close() throws IOException{
		this.socket.close();
	}
}