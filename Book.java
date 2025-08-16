package assign06;

import java.io.File;  
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * This class represents a book (read from file), specifically all words in a
 * book. The instance methods provide various book stats, such as number of
 * words.
 * 
 * Note that a word is considered to be any sequence of symbols separated by
 * whitespace. For example, a book file containing "Hello, world." has two
 * words: "Hello," and "world."
 *
 * @author Prof. Martin and Brian Yu
 * @version JavaSE-21
 */
public class Book {

	// DO NOT ADD OR REMOVE ANY INSTANCE VARIABLES
	private DynamicArray words;

	/**
	 * Creates a Book object, given a filename for the book file to read. If no file
	 * with the given name exists, creates an empty book.
	 * 
	 * @param filename - the name of the file containing the book
	 * 
	 *                 DO NOT MODIFY THIS METHOD
	 */
	public Book(String filename) {
		words = new DynamicArray();

		// This try-with-resources block automatically closes the Scanner when the try
		// block
		// finishes or if an exception is handled.
		// Notice the omission of an explicit call to close.
		try (Scanner fileInput = new Scanner(new File(filename))) {
			while (fileInput.hasNext())
				words.append(fileInput.next());
		} catch (FileNotFoundException e) {
			// Do nothing -- words dynamic array contains 0 elements.
		}

	}

	/**
	 * Gets and returns the # of words in the dynamic array
	 * @return int size
	 */
	public int getWordCount() {
		return words.size();
		}

	/**
	 * Gets the number of characters in this book.
	 * 
	 * @return the number of characters in this book
	 */
	public int getCharacterCount() {
		int characterCount = 0;
		for(int index = 0; index < words.size(); index++)
			characterCount += words.getElement(index).length();

		return characterCount;
	}

	/**
	 * Iterates through all elements in the dynamic array and returns an average of the total # ofcharacters divided by the number of words.
	 * @return double average
	 */
	public double getAverageWordLength() {
		double count = getCharacterCount();
		return count /= words.size();
		}

	/**
	 * Goes through all elements in the dynamic array and returns the word with the shrotest character count.
	 * If there are no words in the dynamic array, returns null
	 * @return
	 */
	public String getShortestWord() {
		if(words.size() == 0)
			return null;
		String shortest = words.getElement(0);
		for(int index = 1; index < words.size(); index++) {
			String currentWord = words.getElement(index);
			if(currentWord.length() < shortest.length())
				shortest = currentWord;
		}
		return shortest; 
	}

	/**
	 * Goes through all elements in the dynamic array and returns the word with the longest character count.
	 * If there are no words in the dynamic array, returns null
	 * @return String longest
	 */
	public String getLongestWord() {
		if(words.size() == 0) 
			return null;
		String longest = words.getElement(0);
		for(int index = 1; index < words.size(); index++) {
			String currentWord = words.getElement(index);
			if(currentWord.length() > longest.length())
				longest = currentWord;
		}
		return longest; 
	}

	/**
	 * Goes through all the elements in the dynamic array and returns the word count of the target word
	 * @param targetWord
	 * @return int targetCount
	 */
	public int getTargetWordCount(String targetWord) {
		int targetCount = 0;
		for(int index = 0; index < words.size(); index++)
			if(words.getElement(index).equals(targetWord)) {
				targetCount ++;
			}
		return targetCount; 
	}

	/**
	 * Determines the word that would come immediately before the given target word
	 * if all the distinct words in this book were arranged in lexicographic order.
	 * If there is no such word, returns the target word.
	 * 
	 * Note: This method does not actually put the words into lexicographic order.
	 * 
	 * @param targetWord - the given target word
	 * @return the word that comes immediately before the target word,
	 *         lexicographically
	 */
	public String getWordLexicographicallyBefore(String targetWord) {
		DynamicArray beforeWords = new DynamicArray();
		for(int index = 0; index < words.size(); index++) {
			String currentWord = words.getElement(index);
			if(currentWord.compareTo(targetWord) < 0)
				beforeWords.append(currentWord);
		}
		// Step 2: If no strings come before targetWord (lexicographically),
				// return targetWord.

		if(beforeWords.size() == 0)
					return targetWord;

		// Step 3: The (lexicographically) latest string in the wordsThatComeBefore
		// dynamic array is the one that comes immediately before targetWord (lexicographically).
		String wordSoFar = beforeWords.getElement(0);
		for(int index = 0; index < beforeWords.size(); index++) {
			String word = beforeWords.getElement(index);
			if (wordSoFar.compareTo(word) < 0)
				wordSoFar = word;
		}
		return wordSoFar;
	}
		
	/**
	 * Determines the word that would come immediately after the given target word
	 * if all the distinct words in this book were arranged in lexicographic order.
	 * If there is no such word, returns the target word.
	 * 
	 * Note: This method does not actually put the words into lexicographic order.
	 * 
	 * @param targetWord - the given target word
	 * @return the word that comes immediately after the target word,
	 *         lexicographically
	 */
	public String getWordLexicographicallyAfter(String targetWord) {
		String nextWord = null;

	    for (int index = 0; index < words.size(); index++) {
	        String currentWord = words.getElement(index);
	        if (currentWord.compareTo(targetWord) > 0) { 
	            if (nextWord == null || currentWord.compareTo(nextWord) < 0) {
	                nextWord = currentWord; 
	            }
	        }
	    }
	    if (nextWord == null) {
	        return targetWord; 
	    } else {
	        return nextWord;
	    }
	}

	/**
	 * Writes the words of this book to file, in reverse order.
	 * 
	 * @param filename - the name of the file to write
	 */
	public void saveReversedBook(String filename) {
		// This try-with-resources block automatically closes the FileWriter when the
		// try block
		// finishes or if an exception is handled.
		try (FileWriter fileOutput = new FileWriter(filename)) {
			 for(int index = words.size() - 1; index >= 0; index--) {
				 fileOutput.write(words.getElement(index));
				 if(index > 0) {
					 fileOutput.write(" ");
				 }
			 }
		} catch (IOException e) {
		}
	}
}