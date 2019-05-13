package mpgg;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.net.SocketException;
import java.util.Scanner;

public class Client {
	
	private static final int PORT_ADDRESS = 61583;
	
	public static void main(String[] args) throws IOException {
		runClient();
	}
	
	private static void runClient() throws IOException{
		Socket socket = null;
		// Attempting to receive server address from command line and then receive file from server.
		try {
			// Gets the server address and sets up the socket connection.
			String serverAddress = getInput("Please input the server address: ");
			socket = new Socket(serverAddress, PORT_ADDRESS);
			System.out.println("Connection to server established...");
			
			System.out.println("Game now commencing.");
			runGame(socket);
		} 
		catch (UnknownHostException e) {
			System.out.println("Invalid host server address.");
			e.printStackTrace();
		}
		catch(SocketException e){
			System.out.println("Could not assign requested address.");
		}
		catch (IOException e){
			System.out.println("Issue with I/O operations encountered.");
			e.printStackTrace();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			socket.close();
		}
			
	}
	
	private static void runGame(Socket socket) throws IOException{
		int guessNum = 0, guess;
		Scanner sc = new Scanner(System.in);
		boolean over = false;
		
		// Continues running while game is still being played
		while(!over){
			guess = getInteger(sc, "Guess a number between 0-9: ");
			// Checks the guess is within the range otherwise loops again
			if(guess>=0 && guess<=9){
				// Checks if the game is over (win or lose) as a result of the recent guess
				if(handleGuess(guess, socket, guessNum))
					over = true;
				else
					guessNum++;
			}
		}
		
		sc.close();
	}
	
	// Method only accepts integers as input
	private static int getInteger(Scanner sc, String prompt){
		System.out.print(prompt);
		
		while(!sc.hasNextInt()){
			System.out.print(prompt);
			sc.next();
		}
		
		return sc.nextInt();
	}
	
	// Method handles client's guess and communicates with the server
	private static boolean handleGuess(int guess, Socket socket, int guessNum) throws IOException{
		String serverResponse = null;
		PrintWriter out = null;
		InputStream in = null;
		BufferedReader bin = null;
	
		
		// Sends guess to the serve
		out = new PrintWriter(socket.getOutputStream(), true);
		out.println(guess);
		
		// Receives response from the server
		in = socket.getInputStream();
		bin = new BufferedReader(new InputStreamReader(in));
		serverResponse = bin.readLine();
		System.out.println(serverResponse);	
		
		// If correctly guessed generated number or the maximum number of guesses has been reached
		if(serverResponse.equals("Congratulations!") || ++guessNum == 4)
			return true;
		else 
			return false;
	}
	
	// Method gets the server address from command line.
	private static String getInput(String prompt) throws IOException{
		System.out.print(prompt);
		//Enter data using BufferReader
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		// Reading data using readLine
		String serverAddress = reader.readLine();
		
		return serverAddress;
	}
}
