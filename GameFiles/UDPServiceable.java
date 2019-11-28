import java.net.InetAddress;

public interface UDPServiceable{
  public static final int SUCCESS = 1;
  public static final int FAIL = 0;

  public int send(String message, InetAddress recepient, int port);
  public int receive();
  public int synchronize(InetAddress recepient, int port);
}
