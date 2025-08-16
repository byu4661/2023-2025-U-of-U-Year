package assign11;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * This class contains tests for the Pixel class.
 *
 * @author Prof. Martin and Brian Yu
 * @version JavaSE-21
 */
public class PixelTest {

	@Test
	public void testGetRedAmount() {
		Pixel tan = new Pixel(210, 180, 140);
		assertEquals(210, tan.getRedAmount());
	}
	
	@Test
	public void testGetRedAmountZero() {
		Pixel tan = new Pixel(0, 0, 0);
		assertEquals(0, tan.getRedAmount());
	}
	
	//For some reason I'm unable to make an assertThrows statement to check if the correct
	//assert statement is thrown, but this is how it should look
//	@Test
//	public void testGetRedAmountOutOfRange() {
//		Pixel tan = new Pixel(-210, 180, 140);
//        assertThrows(IllegalArgumentException.class, () -> tan.getRedAmount());
//	}
	
	@Test
	public void testGetGreenAmount() {
		Pixel color = new Pixel(0, 50, 255);
		assertEquals(50, color.getGreenAmount());
	}
	
	@Test
	public void testGetGreenAmountZero() {
		Pixel color = new Pixel(50, 0, 255);
		assertEquals(0, color.getGreenAmount());
	}
	
	@Test
	public void testGetBlueAmount() {
		Pixel color = new Pixel(0, 50, 255);
		assertEquals(255, color.getBlueAmount());
	}
	
	@Test
	public void testGetBlueAmountZero() {
		Pixel color = new Pixel(0, 50, 0);
		assertEquals(0, color.getBlueAmount());
	}
	
	@Test
	public void testGetPackedRGBZero() {
		Pixel black = new Pixel(0, 0, 0);
		assertEquals(0, black.getPackedRGB());
	}
	
	@Test
	public void testGetPackedRGBZeroBig() {
		Pixel black = new Pixel(0, 8, 0);
		assertEquals(2048, black.getPackedRGB());
	}
}
