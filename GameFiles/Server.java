import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.io.IOException;
import java.util.ArrayList;

public class Server{
  private DatagramSocket socket;
  private ArrayList<User> users;

  public Server(int port) throws IOException{
    this.socket = new DatagramSocket(port);
    this.users = new ArrayList<User>(13);
  }

  public void run() throws IOException{
    String received = null;
    try{
      while(true){
        byte buf[] = new byte[256];

        DatagramPacket packet = new DatagramPacket(buf, buf.length);
        this.socket.receive(packet);
        received = new String(packet.getData(), 0, packet.getLength());
        InetAddress recepient = packet.getAddress();
        int recepient_port = packet.getPort();
        this.users.add(new User(received, recepient, recepient_port));

        received = "UC3H";
        buf = received.getBytes();
        packet = new DatagramPacket(buf, buf.length, recepient, recepient_port);
        this.socket.send(packet);
      }
    }catch(IOException e){
      System.out.println("Connection failed!");
    }
  }

  public static void main(String[] args) throws IOException{
    try{
      Server server = new Server(Integer.parseInt(args[0]));
      server.run();
    }catch(Exception e){
      if (e instanceof IOException){
        System.out.println("Server start-up failed");
      }else if(e instanceof ArrayIndexOutOfBoundsException){
        System.out.println("Usage: java Server <port>");
      }
    }
  }
}
