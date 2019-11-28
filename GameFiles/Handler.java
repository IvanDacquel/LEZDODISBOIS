import java.io.IOException;

public class Handler implements Runnable{
  private Server server;
  private User user;

  public Handler(Server server, User user){
    this.server = server;
    this.user = user;
  }

  @Override
  public void run(){
    Boolean gameStart, gameEnd;

    gameStart = this.server.hasGameStarted();
    gameEnd = this.server.hasGameEnded();

    String signal = "SC2CAH2S5D";
    this.server.send(signal, this.user.getIPAddress(), this.user.getPort());

    while(!signal.equals("LV")){
      this.server.receive();
      signal = this.server.getMessage();
      if(signal.equals("LV")) this.server.send("Permission accepted", this.user.getIPAddress(), this.user.getPort());
      else System.out.println(signal);
    }

    try{
      this.server.close();
    }catch(IOException e){
      e.printStackTrace();
    }
  }
}
