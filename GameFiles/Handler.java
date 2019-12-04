import java.io.IOException;

public class Handler implements Runnable, GameConstants{
	private Server server;
	private User player;

	public Handler(Server server, User player){
		this.server = server;
		this.player = player;
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
