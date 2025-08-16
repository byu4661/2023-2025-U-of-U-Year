package assign11;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * This class contains tests for the Image class.
 *
 * @author Prof. Martin and Brian Yu
 * @version JavaSE-21
 */
public class ImageTest {

	@Test
	public void testRedBlueSwapFilter() {
		Image defaultImage = new Image();
		defaultImage.redBlueSwapFilter();

		int[][] expected = { { 255, 255, 0 }, { 0, 0, 255 },
				             { 0, 255, 0 }, { 255, 0, 255 },
				             { 0, 255, 255 }, { 255, 0, 0 } };
		int pixelCounter = 0;
		for(int i = 0; i < 3; i++)
			for(int j = 0; j < 2; j++, pixelCounter++) {
				Pixel p = defaultImage.getPixel(i, j);
				assertEquals(expected[pixelCounter][0], p.getRedAmount(),
						"redAmount for pixel at " + i + ", " + j + " incorrect");
				assertEquals(expected[pixelCounter][1], p.getGreenAmount(),
						"greenAmount for pixel at " + i + ", " + j + " incorrect");
				assertEquals(expected[pixelCounter][2], p.getBlueAmount(),
						"blueAmount for pixel at " + i + ", " + j + " incorrect");
			}

		try {
			defaultImage.getPixel(3, 0);
			fail("getPixel for out-of-range rowIndex should throw exception");
		}
		catch(IndexOutOfBoundsException e) {
			// do nothing -- exception expected
		}

		try {
			defaultImage.getPixel(0, 2);
			fail("getPixel for out-of-range columnIndex should throw exception");
		}
		catch(IndexOutOfBoundsException e) {
			// do nothing -- exception expected
		}
	}
	
	@Test
	public void testGetPixel() {
		Image defaultImage = new Image();
		int expectedRed = 255;
	    int expectedGreen = 0;
	    int expectedBlue = 255;

	    Pixel p = defaultImage.getPixel(1, 1);

	    assertEquals(expectedRed, p.getRedAmount());
	    assertEquals(expectedGreen, p.getGreenAmount());
	    assertEquals(expectedBlue, p.getBlueAmount());
	}
	
	@Test
	public void testGetPixel2() {
		Image defaultImage = new Image();
		int expectedRed = 255;
	    int expectedGreen = 0 ;
	    int expectedBlue = 0;

	    Pixel p = defaultImage.getPixel(0, 1);

	    assertEquals(expectedRed, p.getRedAmount());
	    assertEquals(expectedGreen, p.getGreenAmount());
	    assertEquals(expectedBlue, p.getBlueAmount());
	}
	
	@Test
	public void testRotateClockwise() {
	    Pixel[][] actualPixels = {
	        { new Pixel(0, 255, 255), new Pixel(255, 0, 0) },
	        { new Pixel(0, 255, 0), new Pixel(255, 0, 255) },
	        { new Pixel(255, 255, 0), new Pixel(0, 0, 255) }};
	    Image defaultImage = new Image(actualPixels);
	    defaultImage.rotateClockwiseFilter();
	    Pixel[][] expectedPixels = {
	        { new Pixel(255, 255, 0), new Pixel(0, 255, 0), new Pixel(0, 255, 255) },
	        { new Pixel(0, 0, 255), new Pixel(255, 0, 255), new Pixel(255, 0, 0) }};
	    for (int i = 0; i < expectedPixels.length; i++) {
	        for (int j = 0; j < expectedPixels[i].length; j++) {
	            Pixel actual = defaultImage.getPixel(i, j);
	            Pixel expected = expectedPixels[i][j];
	            assertEquals(expected.getRedAmount(), actual.getRedAmount());
	            assertEquals(expected.getGreenAmount(), actual.getGreenAmount());
	            assertEquals(expected.getBlueAmount(), actual.getBlueAmount());
	        }
	    }
	}
	
	@Test
	public void testRotateClockwiseEmpty() {
	    Pixel[][] actualPixels = {
	        {  },
	        {  },
	        {  }};
	    Image defaultImage = new Image(actualPixels);
	    defaultImage.rotateClockwiseFilter();
	    Pixel[][] expectedPixels = {
		        {  },
		        {  },
		        {  }};
	    for (int i = 0; i < expectedPixels.length; i++) {
	        for (int j = 0; j < expectedPixels[i].length; j++) {
	            Pixel actual = defaultImage.getPixel(i, j);
	            Pixel expected = expectedPixels[i][j];
	            assertEquals(expected.getRedAmount(), actual.getRedAmount());
	            assertEquals(expected.getGreenAmount(), actual.getGreenAmount());
	            assertEquals(expected.getBlueAmount(), actual.getBlueAmount());
	        }
	    }
	}
	
	@Test
	public void testCustomFilter() {
		Pixel[][] actualPixels = {
		        { new Pixel(0, 255, 255), new Pixel(255, 0, 0) },
		        { new Pixel(0, 255, 0), new Pixel(255, 0, 255) },
		        { new Pixel(255, 255, 0), new Pixel(0, 0, 255) }};
		Image defaultImage = new Image(actualPixels);
		defaultImage.customFilter();
		Pixel[][] expectedPixels = {
			    { new Pixel(0, 255, 255), new Pixel(0, 255, 255) },
			    { new Pixel(0, 255, 0), new Pixel(0, 255, 0) },
			    { new Pixel(255, 255, 0), new Pixel(255, 255, 0) }};
		for (int i = 0; i < expectedPixels.length; i++) {
		    for (int j = 0; j < expectedPixels[i].length; j++) {
		        Pixel actual = defaultImage.getPixel(i, j);
		        Pixel expected = expectedPixels[i][j];
		        assertEquals(expected.getRedAmount(), actual.getRedAmount());
		        assertEquals(expected.getGreenAmount(), actual.getGreenAmount());
		        assertEquals(expected.getBlueAmount(), actual.getBlueAmount());
		    }
		}
	}
	
	@Test
	public void testCustomFilterEmpty() {
		Pixel[][] actualPixels = {
		        {  },
		        {  },
		        {  }};
		Image defaultImage = new Image(actualPixels);
		defaultImage.customFilter();
		Pixel[][] expectedPixels = {
		   		{  },
		        {  },
		        {  }};
		for (int i = 0; i < expectedPixels.length; i++) {
			for (int j = 0; j < expectedPixels[i].length; j++) {
				Pixel actual = defaultImage.getPixel(i, j);
		        Pixel expected = expectedPixels[i][j];
		        assertEquals(expected.getRedAmount(), actual.getRedAmount());
		        assertEquals(expected.getGreenAmount(), actual.getGreenAmount());
		        assertEquals(expected.getBlueAmount(), actual.getBlueAmount());
		    }
		}
	}
	
}
