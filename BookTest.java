package assign06;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This class contains tests for the Book class.
 *
 * @author Prof. Martin and Brian Yu
 * @version JavaSE-21
 */
public class BookTest {

	private static Book smallBook;

	// This code executes before all tests.
	// You can reference smallBook in your tests without having to create it.
	@BeforeAll
	public static void setup() {
		smallBook = new Book("small_book.txt");
	}

	@Test
	public void testGetWordCount() {
		assertEquals(32, smallBook.getWordCount());
	}

	@Test
	public void testGetCharacterCount() {
		assertEquals(137, smallBook.getCharacterCount());
	}

	@Test
	public void testGetAverageWordLength() {
		assertEquals(4.28125, smallBook.getAverageWordLength(), 0.00001);
	}

	@Test
	public void testGetShortestWord() {
		assertEquals("a", smallBook.getShortestWord());
	}

	@Test
	public void testGetLongestWord() {
		assertEquals("checking", smallBook.getLongestWord());
	}

	@Test
	public void testGetTargetWordCount() {
		assertEquals(2, smallBook.getTargetWordCount("do"));
	}
	
	@Test
	public void testGetTargetWordCountZero() {
		assertEquals(0, smallBook.getTargetWordCount("watermelon"));
	}
	
	@Test
	public void testGetTargetWordCountChars() {
		assertEquals(0, smallBook.getTargetWordCount(","));
	}

	@Test
	public void testGetWordLexicographicallyBefore() {
		assertEquals("Use", smallBook.getWordLexicographicallyBefore("a"));
	}
	
	@Test
	public void testGetWordLexicographicallyBeforePeriod() {
		assertEquals("checking", smallBook.getWordLexicographicallyBefore("class."));
	}
	
	@Test
	public void testGetWordLexicographicallyBeforeSameWord() {
		assertEquals("class.", smallBook.getWordLexicographicallyBefore("do"));
	}

	@Test
	public void testGetWordLexicographicallyAfter() {
		assertEquals("the", smallBook.getWordLexicographicallyAfter("testing"));
	}
	
	@Test
	public void testGetWordLexicographicallyAfterPeriod() {
		assertEquals("initial", smallBook.getWordLexicographicallyAfter("files."));
	}

	
	@Test
	public void testGetWordLexicographicallyAfterEndWord() {
		assertEquals("very", smallBook.getWordLexicographicallyAfter("very"));
	}


	@Test
	public void testSaveReversedBook() {
		String expected = "files. book large using testing thorough do also to sure be However, class. Book the of checking initial simple, some do to it Use book. small very a of example an This";
		String filename = "reverse_test.txt";
		smallBook.saveReversedBook(filename);
		try {// update to close w/ resources
			Scanner fileInput = new Scanner(new File(filename));
			String actual = fileInput.nextLine();
			assertEquals(expected, actual);
			fileInput.close();
		} catch (FileNotFoundException e) {
			fail("File not written or written using incorrect path.");
		}
	}

//	@BeforeAll
//	public static void regular_book() {
//		smallBook = new Book("regular_book.txt");
//	}
//	
//	@Test
//	public void testGetWordCountRegularBook() {
//		assertEquals(57, smallBook.getWordCount());
//	}
//
//	@Test
//	public void testGetCharacterCountRegularBook() {
//		assertEquals(261, smallBook.getCharacterCount());
//	}
//	
//	@Test
//	public void testGetTargetWordCountRegularBook() {
//		assertEquals(3, smallBook.getTargetWordCount("dot"));
//	}
//	
//	@Test
//	public void testGetTargetWordCountZeroRegularBook() {
//		assertEquals(0, smallBook.getTargetWordCount("watermelon"));
//	}
//	
//	@Test
//	public void testGetTargetWordCountCharsRegularBook() {
//		assertEquals(0, smallBook.getTargetWordCount(","));
//	}
//
//	@Test
//	public void testGetWordLexicographicallyBeforeRegularBook() {
//		assertEquals("This", smallBook.getWordLexicographicallyBefore("WILL"));
//	}
//	
//	@Test
//	public void testGetWordLexicographicallyBeforePeriodRegularBook() {
//		assertEquals("that", smallBook.getWordLexicographicallyBefore("this."));
//	}
//	
//	@Test
//	public void testGetWordLexicographicallyBeforeFirstWordRegularBook() {
//		assertEquals("/", smallBook.getWordLexicographicallyBefore("/This."));
//	}
//
//	@Test
//	public void testGetWordLexicographicallyAfterRegularBook() {
//		assertEquals("/full", smallBook.getWordLexicographicallyAfter("/for"));
//	}
//	
//	@Test
//	public void testGetWordLexicographicallyAfterPeriodRegularBook() {
//		assertEquals("/truly", smallBook.getWordLexicographicallyAfter("/this."));
//	}
//
//	
//	@Test
//	public void testGetWordLexicographicallyAfterEndWordRegularBook() {
//		assertEquals("you", smallBook.getWordLexicographicallyAfter("you"));
//	}
}
