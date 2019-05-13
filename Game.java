package mpgg;

import java.util.ArrayList;
import java.util.List;

public class Game {
	private final int number;
	private boolean result;
	protected List<Player> activePlayers = new ArrayList<Player>();
	
	public Game(){
		this.number = getRandom();
		this.result = false;
	}
	
	// Returns a message to the client based on the outcome of the guess
	protected String result(int guess, int guessNum){
		int remainingGuesses = 4-guessNum;
		
		if(guess==number){
			this.result = true;
			return "Congratulations!";
		}		
		else if(remainingGuesses==0 && !getResult())
			return annouceAnswer();
		else if(guess<number)
			return "The generated number is higher than " + guess + "; you have " + remainingGuesses + " remaining guesses.";
		else
			return "The generated number is lower than " + guess + "; you have " + remainingGuesses + " remaining guesses.";
	}
	
	// Announces the answer in the event of the client failing to guess the generated number
	protected String annouceAnswer(){
		return "The client has failed to guess the generated number; the correct answer was " + getNumber() + "."; 
	}
	
	// Generates a random number between 0 and 9
	private static int getRandom(){
		int max = 9;
		return (int)(Math.random()*max);	
	}
	
	protected void addPlayers(){
		
	}

	protected int getNumber(){
		return number;
	}
	
	protected boolean getResult(){
		return result;
	}
}
