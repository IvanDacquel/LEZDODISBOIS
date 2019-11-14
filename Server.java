//import java.net.Socket;
import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.io.*;

public class Server{
  private DatagramSocket socket;
  private String received;

  public Server() throws IOException{
    this.socket = new DatagramSocket(3000);
    this.received = null;
  }

  /*
  public Server() throws IOException{
    this.socket = new DatagramSocket();
    this.received = null;
  }
  */

  public void run() throws IOException{
    try{
      byte buf[] = new byte[256];

      //receive request
      DatagramPacket packet = new DatagramPacket(buf, buf.length);
      this.socket.receive(packet);

      packet = new DatagramPacket(buf, buf.length);
      this.socket.receive(packet);

      this.received = new String(packet.getData(), 0, packet.getLength());
      System.out.println("Received: " + this.received);

      this.socket.close();
    }catch(IOException e){
      System.out.println("Connection failed.");
    }
  }

  public static void main(String[] args){
    try{
      Server serv = new Server();
      serv.run();
    }catch(IOException e){
      System.out.println("Failed to start server.");
    }
  }
}
