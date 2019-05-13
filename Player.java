package mpgg;

import java.net.Socket;

// Class that stores relevant player data
public class Player {
	private final Socket pSocket;;
	private final String pName;
	
	public Player(Socket ps, String pn){
		this.pSocket = ps;
		this.pName = pn;
	}
	
	public Socket getSocket() {
		return pSocket;
	}

	public String getName() {
		return pName;
	}
}
