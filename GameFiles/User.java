import java.net.InetAddress;

public class User{
  private int userid;
  private String nickname;
  private InetAddress ipAddress;
  private int port;

  static int users = 1;

  public User(String nickname, InetAddress address, int port){
    this.userid = User.users;
    this.nickname = nickname;
    this.ipAddress = address;
    this.port = port;
    User.users++;
  }

  public User(InetAddress address, int port){
    this.userid = User.users;
    this.ipAddress = address;
    this.port = port;
    User.users++;
  }

  public static void decreaseUserCount(){
    User.users--;
  }

  public InetAddress getIPAddress(){
    return this.ipAddress;
  }

  public int getPort(){
    return this.port;
  }
}
