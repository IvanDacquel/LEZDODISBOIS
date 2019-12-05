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
    try{
		while(true){
			String received = this.server.receive();
			System.out.println(received);
			this.server.updateLog(this.user, received);
			this.server.handleReceived(received);
			if (received.substring(0,2).equals("EX")) this.server.send("RL", user.getAddress(), user.getPort());
			this.server.updateTracker();
		}
	}catch(IOException e){}
  }
}
