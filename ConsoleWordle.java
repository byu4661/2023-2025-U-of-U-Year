package assign03;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;
/**
 * CS1410-001
 * Assignment 3: Wordle
 * 
 * @author Brian Yu
 * @version JavaSE-21
 */
public class ConsoleWordle {
	/**
	 * This method creates an array based on the length of the first integer token in the file
	 * @param filename
	 * @return String[]
	 */
	public static String[] readFile(String filename) throws FileNotFoundException {
		File myFile = new File(filename);
		Scanner myScanner = new Scanner(myFile);
		int size = myScanner.nextInt();
		String[] words = new String[size];
		int counter = 0;
		while(myScanner.hasNext() && counter < size) {
			if(myScanner.hasNext()) {
				words[counter] = myScanner.next();
				counter++;
			}
		}
		myScanner.close();
		return words;
}
	
	/**
	 * Method generates a random index based on the length of the String[] parameter to be used in readFile
	 * @param a
	 * @return Random String[index]
	 */
	public static String getSecretWord(String[] a) {
		Random num = new Random();
		int value = num.nextInt(a.length);
		System.out.println(a[value]);    
		return a[value];
	}
	
	public static boolean letterContainedInWord(char character, String word) {
		for(int i = 0; i < word.length(); i++) {
			if(word.charAt(i) == character) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Method checks it the user guess is a valid 5 letter string, or keeps asking user for input
	 * @param System.in
	 * @return String user guess
	 */
	public static String getUserGuess(Scanner a) {
		String word = "";
		while(true) {
			System.out.println("What is your 5 letter guess?: ");
			word = a.next();
			if(word.length() == 5) {
				return word;
			}
			else {
				System.out.println("Invalid word, guess again!");
			}
		}
	}
	
	/**
	 * Method displays the result based off the user's guess, in a wordle format
	 * @param guess
	 * @param secretWord
	 */
	public static void displayResultOfRound(String guess, String secretWord) {
		String word = "";
		for(int i = 0; i < guess.length(); i++) {
			if(guess.length() > 5) {
				System.out.println("Invalid word, try again!");
				break;
			}
				if(guess.charAt(i) == secretWord.charAt(i)) {
					char lowerCaseChar = guess.charAt(i);
					char upperCaseChar = Character.toUpperCase(lowerCaseChar);
					word += upperCaseChar;
				}
				else if(letterContainedInWord(guess.charAt(i), secretWord) == true) {
					word += guess.charAt(i);
				}
				else {
					word += "-";
				}
			
		}
		System.out.println(word);
	}
	
	public static void main(String[] args) {
		try {
			boolean correctGuess = false;
			int guessCount = 0;

			String[] array = readFile("/Users/Brian/Downloads/words.txt");
			String secretWord = getSecretWord(array);
			System.out.println("Welcome to Wordle! What is your 5 letter guess?: ");
			Scanner userInput = new Scanner(System.in);
			String userGuess = userInput.next();
			displayResultOfRound(userGuess, secretWord);

			while(correctGuess == false && guessCount < 5) {
				guessCount++;
				userGuess = getUserGuess(userInput);
				if(userGuess.equals(secretWord)) {
					System.out.println("You won congrats!");
					correctGuess = true;
					break;
				}
				else if(guessCount < 5){
					displayResultOfRound(userGuess, secretWord);
				}
				else if(guessCount >= 5)
					System.out.println("You lose haha, the word is " + secretWord);
			}
		} 
		catch (FileNotFoundException e) {
			System.out.println("File not found - ");
			}
		
		
	}
}
