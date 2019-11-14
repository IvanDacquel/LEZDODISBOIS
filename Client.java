import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.io.*;

public class Client{
  private DatagramSocket socket;
  private InetAddress host;
  private String signal;

  public Client(String host) throws IOException{
    this.host = InetAddress.getByName(host);
    this.socket = new DatagramSocket();
    this.signal = null;
  }

  public void run() throws IOException{
    try{
      byte[] buf = new byte[256];
      DatagramPacket packet = new DatagramPacket(buf, buf.length, this.host, 3000);
      this.socket.send(packet);

      this.signal = new String("01012C");
      buf = this.signal.getBytes();
      packet = new DatagramPacket(buf, buf.length, this.host, 3000);
      this.socket.send(packet);

      this.socket.close();
    }catch(IOException e){
      System.out.println("Exception occured on run()");
    }
  }

  public static void main(String args[]) throws IOException{
    try{
      String host = args[0];
      Client client = new Client(host);
      client.run();
    }catch(ArrayIndexOutOfBoundsException e){
      System.out.println("Usage: java Client <server-ip>");
    }catch(IOException e){
      System.out.println("Connection to server failed!");
    }
  }
}
