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
				System.out.println(this.server.receive());
			}
		}catch(IOException e){}
  }
}
