import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.io.IOException;
import java.util.ArrayList;

public class Server implements UDPServiceable{
  private DatagramSocket socket;
  private DatagramPacket packet;
  private int max_players;
  private ArrayList<User> users;
  private Boolean gameStart, gameOver;

  public Server(int port) throws IOException{
    this.socket = new DatagramSocket(port);
    this.max_players = 13;
    this.users = new ArrayList<User>(13);
    this.gameStart = this.gameOver = false;
  }

  public Server(int port, int max_players) throws IOException{
    this.socket = new DatagramSocket(port);
    this.max_players = max_players;
    this.users = new ArrayList<User>(this.max_players);
    this.gameStart = this.gameOver = false;
  }

  @Override
  public int send(String message, InetAddress recepient, int port){
    try{
      byte buf[] = message.getBytes();
      this.packet = new DatagramPacket(buf, buf.length, recepient, port);
      this.socket.send(this.packet);
      return UDPServiceable.SUCCESS;
    }catch(IOException e){
      return UDPServiceable.FAIL;
    }
  }

  @Override
  public int receive(){
    try{
      byte buf[] = new byte[256];
      this.packet = new DatagramPacket(buf, buf.length);
      this.socket.receive(this.packet);
      return UDPServiceable.SUCCESS;
    }catch(IOException e){
      return UDPServiceable.FAIL;
    }
  }

  @Override
  public int synchronize(InetAddress recepient, int port){
    try{
      byte buf[] = new byte[256];
      this.packet = new DatagramPacket(buf, buf.length, recepient, port);
      this.socket.send(this.packet);
      return UDPServiceable.SUCCESS;
    }catch(IOException e){
      return UDPServiceable.FAIL;
    }
  }

  public String getMessage(){
    return new String(this.packet.getData(), 0, this.packet.getLength());
  }

  public Boolean hasGameStarted(){
    return this.gameStart;
  }

  public Boolean hasGameEnded(){
    return this.gameOver;
  }

  public void close() throws IOException{
    this.socket.close();
  }

  public void run() throws IOException{
    String received = null;
    try{
      while(!this.gameOver){
        while(!this.gameStart){
          if(this.receive() == UDPServiceable.SUCCESS){
            System.out.println("Successfully connected to " + this.packet.getAddress());
            received = this.getMessage();
            InetAddress recepient = packet.getAddress();
            int recepient_port = packet.getPort();
            User user = new User(received, recepient, recepient_port);
            this.users.add(user);
            new Thread(new Handler(this, user)).start();
          } else {
            break;
          }
        }
      }
      this.close();
    }catch(IOException e){
      System.out.println("Connection failed!");
    }
  }

  public static void main(String[] args) throws IOException{
    try{
        int maxPlayers = Integer.parseInt(args[1]);
        if((maxPlayers >= 3) && (maxPlayers <= 13)){
          Server server = new Server(Integer.parseInt(args[0]));
          server.run();
        }else System.out.println("Number of players must be from 3 to 13!");
    }catch(Exception e){
      if (e instanceof IOException){
        System.out.println("Server start-up failed");
      }else if(e instanceof ArrayIndexOutOfBoundsException){
        System.out.println("Usage: java Server <port> <max_players>");
      }
    }
  }
}
