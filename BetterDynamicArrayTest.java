package assign06;

import static org.junit.jupiter.api.Assertions.*; 

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * This class contains tests for the BetterDynamicArray class.
 *
 * @author Prof. Martin and Brian Yu
 * @version JavaSE-21
 */
public class BetterDynamicArrayTest {

	private static BetterDynamicArray threeStrings;

	// This code executes before each test.
	// You can reference threeStrings in your tests without having to create it.
	@BeforeEach
	public void setup() {
		threeStrings = new BetterDynamicArray();
		threeStrings.append("9");
		threeStrings.append("0");
		threeStrings.append("cats");
	}

	@Test
	public void testConstructor() {
		BetterDynamicArray array = new BetterDynamicArray();
		assertEquals(0, array.size(), "Constructor did not yield a 0-sized dynamic array");
		assertEquals("[] backing array length: 10", array.toString(),
				"Constructor did not yield the correct dynamic array (via toString)");
	}

	@Test
	public void testConstructorCreatesDistinctArrays() {
		BetterDynamicArray array = new BetterDynamicArray();
		BetterDynamicArray otherArray = new BetterDynamicArray();
		otherArray.append("14");
		assertEquals(0, array.size(),
				"Appending an element to one DynamicArray object changed the size of a different DynamicArray object");
	}

	@Test
	public void testAppendSimple() {
		String expected = "[9, 0, cats] backing array length: 10";
		assertEquals(expected, threeStrings.toString(), "Failed appending 3 elements to empty dynamic array");
		assertEquals(3, threeStrings.size(), "Incorrect size after appending 3 elements to dynamic array");
	}

	@Test
	public void testAppendLarger() {
		String[] largeArray = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" };
		BetterDynamicArray array = new BetterDynamicArray();
		for(String elem : largeArray)
			array.append(elem);
		String expected = "[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10] backing array length: 20";
		assertEquals(expected, array.toString(), "Failed appending 11 elements to dynamic array");
		assertEquals(11, array.size(), "Incorrect size after appending 11 elements to dynamic array");
	}

	@Test
	public void testInsertFront() {
		threeStrings.insert(0, "5");
		String expected = "[5, 9, 0, cats] backing array length: 10";
		assertEquals(expected, threeStrings.toString(), "Failed inserting an element to the front");
		assertEquals(4, threeStrings.size(), "Incorrect size after inserting element to the front");
	}

	@Test
	public void testInsertMiddle() {
		threeStrings.insert(1, "7");
		String expected = "[9, 7, 0, cats] backing array length: 10";
		assertEquals(expected, threeStrings.toString(), "Failed inserting an element to the middle");
		assertEquals(4, threeStrings.size(), "Incorrect size after inserting element to the middle");
	}

	@Test
	public void testInsertEnd() {
		threeStrings.insert(3, "30");
		assertEquals(4, threeStrings.size(), "Incorrect size after inserting element to the end");
	}

	@Test
	public void testInsertInvalidLowIndex() {
		// This assertion checks that calling insert with an index that is too low
		// throws the IndexIndexOutOfBoundsException.
		assertThrows(IndexOutOfBoundsException.class, () -> { threeStrings.insert(-1, "12"); }, 
				"Failed to throw exception when inserting with too-low index");

		// This ensures that the exception thrown is not due to an ArrayIndexOutOfBoundsException, 
		// which is closely related to but different than IndexOutOfBoundsException.
		try {
			threeStrings.insert(-1, "12");
		}
		catch(ArrayIndexOutOfBoundsException e) {
			// incorrect exception -- fail test
			fail("Code encounters ArrayIndexOutOfBoundsException because IndexOutOfBoundsException not properly thrown.");
		}
		catch(IndexOutOfBoundsException e) {
			// correct exception -- do nothing
		}
	}

	@Test
	public void testInsertInvalidHighIndex() {
		assertThrows(IndexOutOfBoundsException.class, () -> { threeStrings.insert(15, "12"); }, 
				"Failed to throw exception when inserting with too-high index");
		try {
			threeStrings.insert(15, "12");
		}
		catch(ArrayIndexOutOfBoundsException e) {
			// incorrect exception -- fail test
			fail("Code encounters ArrayIndexOutOfBoundsException because IndexOutOfBoundsException not properly thrown.");
		}
		catch(IndexOutOfBoundsException e) {
			// correct exception -- do nothing
		}
	}

	@Test
	public void testGetElement() {
		assertEquals("9", threeStrings.getElement(0), "Failed getting element from front");
		assertEquals("0", threeStrings.getElement(1), "Failed getting element from middle");
		assertEquals("cats", threeStrings.getElement(2), "Failed getting element from end");
		assertEquals(3, threeStrings.size(), "Calling getElement changed the size of dynamic array");
	}

	@Test
	public void testGetElementInvalidLow() {
		assertThrows(IndexOutOfBoundsException.class, () -> { threeStrings.getElement(-1); }, 
				"Failed to throw exception when getting element with too-low index");
		try {
			threeStrings.getElement(-1);
		}
		catch(ArrayIndexOutOfBoundsException e) {
			// incorrect exception -- fail test
			fail("Code encounters ArrayIndexOutOfBoundsException because IndexOutOfBoundsException not properly thrown.");
		}
		catch(IndexOutOfBoundsException e) {
			// correct exception -- do nothing
		}
	}

	@Test
	public void testGetElementInvalidHigh() {
		assertThrows(IndexOutOfBoundsException.class, () -> { threeStrings.getElement(3); }, 
				"Failed to throw exception when getting element with too-high index");
		try {
			threeStrings.getElement(3);
		}
		catch(ArrayIndexOutOfBoundsException e) {
			// incorrect exception -- fail test
			fail("Code encounters ArrayIndexOutOfBoundsException because IndexOutOfBoundsException not properly thrown.");
		}
		catch(IndexOutOfBoundsException e) {
			// correct exception -- do nothing
		}
	}


}
