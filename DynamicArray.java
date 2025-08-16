package assign06;

/**
 * This class represents a dynamic array of strings.
 *
 * A dynamic array behaves like a regular array that uses [], except it can grow
 * and shrink, as needed. Like a regular array, it is indexed beginning with 0.
 * When a DynamicArray object is created, it is empty.
 *
 * @author Prof. Martin
 * @version Assignment 6
 *
 *          DO NOT MODIFY THIS FILE
 */
public class DynamicArray {

	private String[] elements; // the backing array

	/**
	 * Creates an empty dynamic array.
	 */
	public DynamicArray() {
		elements = new String[0];
	}

	/**
	 * Appends the given string to end of this dynamic array.
	 *
	 * @param str - the string to append
	 */
	public void append(String str) {
		insert(elements.length, str);
	}

	/**
	 * Inserts a given string into this dynamic array at a given index. Throws an
	 * exception if the given index is out of bounds.
	 *
	 * @param index - the index at which to insert
	 * @param str   - the string to insert
	 */
	public void insert(int index, String str) {
		// Ensure the value of index is valid.
		if (index < 0 || index > elements.length)
			throw new IndexOutOfBoundsException(
					"Index " + index + " is invalid for adding to this array with length " + elements.length);

		// Adding a new element requires more space, so make a new array.
		String[] largerArray = new String[elements.length + 1];

		// Copy elements from the backing array to the new array, up to the given index.
		for (int i = 0; i < index; i++)
			largerArray[i] = elements[i];

		// Insert the new element at index.
		largerArray[index] = str;

		// Copy the remaining elements from the backing array to the new array, but
		// shifted up one array slot.
		for (int i = index; i < elements.length; i++)
			largerArray[i + 1] = elements[i];

		// Set the backing array reference to the new array.
		elements = largerArray;
	}

	/**
	 * Gets the string stored in this dynamic array at the given index. Throws an
	 * exception if the given index is out of bounds.
	 *
	 * @param index - the index of the element to get
	 * @return the element at the given index
	 */
	public String getElement(int index) {
		if (index < 0 || index >= elements.length)
			throw new IndexOutOfBoundsException(
					"Index " + index + " is invalid for this array with length " + elements.length);

		return elements[index];
	}

	/**
	 * Returns the number of elements in this dynamic array.
	 *
	 * @return the number of elements
	 */
	public int size() {
		return elements.length;
	}

	/**
	 * Sets (i.e., changes) the string stored in this dynamic array at the given
	 * index to the given string. Throws an exception if the given index is out of
	 * bounds.
	 *
	 * @param index - the index of the element to set
	 * @param str   - the new string value for setting the element
	 */
	public void setElement(int index, String str) {
		if (index < 0 || index >= elements.length)
			throw new IndexOutOfBoundsException(
					"Index " + index + " is invalid for this array with length " + elements.length);

		elements[index] = str;
	}

	/**
	 * Deletes the string at the given index from this dynamic array. Throws an
	 * exception if the given index is out of bounds.
	 *
	 * @param index - the index of the element to delete
	 */
	public void delete(int index) {
		if (index < 0 || index >= elements.length)
			throw new IndexOutOfBoundsException(
					"Index " + index + " is invalid for this array with length " + elements.length);

		// Deleting an element requires less space, so make a new array.
		String[] smallerArray = new String[elements.length - 1];

		// Copy elements from the backing array to the new array, up to the given index.
		for (int i = 0; i < index; i++)
			smallerArray[i] = elements[i];

		// Copy the elements from the backing array to the new array, but
		// shifted down one array slot. This overwrites the deleted element.
		for (int i = index + 1; i < elements.length; i++)
			smallerArray[i - 1] = elements[i];

		// Set the backing array reference to the new array.
		elements = smallerArray;
	}

	/**
	 * Generates a textual representation of this dynamic array.
	 *
	 * @return the textual representation
	 */
	public String toString() {
		String result = "[";
		if (elements.length > 0)
			result += elements[0];
		for (int i = 1; i < elements.length; i++)
			result += ", " + elements[i];
		return result + "]";
	}
}
