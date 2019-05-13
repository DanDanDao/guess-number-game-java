package mpgg;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;


public class Server {
	private final static int SOCKET_PORT = 61583;
	
	public static void main(String[] args) throws IOException {
		runServer();
	}

	// Method runs the server.
	private static void runServer() throws IOException {
		ServerSocket servSock = null;
		Socket socket = null;
		

		// Attempts to connect and send file to client.
		try {
			servSock = new ServerSocket(SOCKET_PORT);
			while(true){
				System.out.println("Waiting...");
				socket = servSock.accept();
				System.out.println("Accepted connection : " + socket);
				System.out.println("Connection to client established...");
				System.out.println("Game now commencing.");
				runGame(socket);
				
				System.out.print("Game is over. Continue running server? (Y/N): ");
				if(checkResponse())
					break;
			}
		} 
		catch(SocketException e) {
			System.out.println("Error: Port in use, please select another port.");
			e.printStackTrace();
		}
		catch(IOException e){
			System.out.println("Error: Issue reading/sending file.");
			e.printStackTrace();
		}
		
		servSock.close();
		socket.close();
	}
	
	private static void runGame(Socket socket) throws IOException {
		Game game = new Game();
		int guessNum = 0, guess;
		PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
		BufferedReader br = null;
		InputStream in = null;
		String message;
		
		while(!game.getResult() && guessNum!=4){
			++guessNum;
			in = socket.getInputStream();
			br = new BufferedReader(new InputStreamReader(in));
			guess = Integer.parseInt(br.readLine());
			
			message = game.result(guess, guessNum);
			out.println(message);
			System.out.println(message);
		}
		
	}
	
	private static boolean checkResponse() throws IOException{
		//Enter data using BufferReader
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		// Reading data using readLine
		String input = reader.readLine();
		if(input.equals("N"))
			return true;
		else 
			return false;
		
	}

	
}
