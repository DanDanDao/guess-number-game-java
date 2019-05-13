package mpgg;

import java.net.ServerSocket;
import java.util.LinkedList;

public class Lobby implements Runnable {
	private ServerSocket servSocket = null;
	protected LinkedList<Player> queue = new LinkedList<Player>();
	private final int LOBBY_LIMIT = 6;
	
	public Lobby(ServerSocket ss){
		this.servSocket = ss;
	}
	
	public ServerSocket getServSocket() {
		return servSocket;
	}

	protected boolean joinLobby(Player p){
		if(queue.size() == LOBBY_LIMIT)
			return false;
		else{
			queue.addLast(p);
			return true;
		}
	}

	protected void removePlayers(){
		
		
	}
	
	protected void removeAllPlayers(){
		queue.clear();
	}
	
	@Override
	public void run() {
		while(true){
			
		}
		
	}
	
	
}
