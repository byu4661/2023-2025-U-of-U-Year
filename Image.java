package assign11;

import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import java.awt.Component;
import java.awt.event.*;

/**
 * This class represents an image as a two-dimensional array of pixels and provides a number
 * of image filters (via instance methods) for changing the appearance of the image.
 * Application of multiple filters is cumulative; e.g., obj.redBlueSwapFilter() followed by
 * obj.rotateClockwiseFilter() results in an image altered both in color and orientation.
 *
 * Note:
 *   - The pixel in the northwest corner of the image is stored in the first row, first column.
 *   - The pixel in the northeast corner of the image is stored in the first row, last column.
 *   - The pixel in the southeast corner of the image is stored in the last row, last column.
 *   - The pixel in the southwest corner of the image is stored in the last row, first column.
 *
 * @author Prof. Martin and Brian Yu
 * @version JavaSE-21
 */
public class Image {

	private Pixel[][] imageArray;

	/**
	 * Creates a new Image object by reading the image file with the given filename.
	 *
	 * DO NOT MODIFY THIS METHOD
	 *
	 * @param filename - name of the given image file to read
	 * @throws IOException if file does not exist or cannot be read
	 */
	public Image(String filename) {
		BufferedImage imageInput = null;
		try {
			imageInput = ImageIO.read(new File(filename));
		}
		catch(IOException e) {
			System.out.println("Image file " + filename + " does not exist or cannot be read.");
		}

		imageArray = new Pixel[imageInput.getHeight()][imageInput.getWidth()];
		for(int i = 0; i < imageArray.length; i++)
			for(int j = 0; j < imageArray[0].length; j++) {
				int rgb = imageInput.getRGB(j, i);
				imageArray[i][j] = new Pixel((rgb >> 16) & 255, (rgb >> 8) & 255, rgb & 255);
			}
	}

	/**
	 * Create an Image object directly from a pre-made Pixel array.
   * This is primarily to be used in testing.
	 *
	 * DO NOT MODIFY THIS METHOD
	 */
	public Image(Pixel[][] imageArray) {
		this.imageArray = imageArray;
	}

	/**
	 * Create a new "default" Image object, whose purpose is to be used in testing.
	 *
	 * The orientation of this image:
	 * 		cyan 	 red
	 *		green	 magenta
	 *		yellow	 blue
	 *
	 * DO NOT MODIFY THIS METHOD
	 */
	public Image() {
		imageArray = new Pixel[3][2];
		imageArray[0][0] = new Pixel(0, 255, 255);  // cyan
		imageArray[0][1] = new Pixel(255, 0, 0);  // red
		imageArray[1][0] = new Pixel(0, 255, 0);  // green
		imageArray[1][1] = new Pixel(255, 0, 255);  // magenta
		imageArray[2][0] = new Pixel(255, 255, 0);  // yellow
		imageArray[2][1] = new Pixel(0, 0, 255);  // blue
	}

	/**
	 * Gets the pixel at the specified row and column indexes.
	 *
	 * DO NOT MODIFY THIS METHOD
	 *
	 * @param rowIndex - given row index
	 * @param columnIndex - given column index
	 * @return the pixel at the given row index and column index
	 * @throws IndexOutOfBoundsException if row or column index is out of bounds
	 */
	public Pixel getPixel(int rowIndex, int columnIndex) {
		if(rowIndex < 0 || rowIndex >= imageArray.length)
			throw new IndexOutOfBoundsException("rowIndex must be in range 0-" + (imageArray.length - 1));

		if(columnIndex < 0 || columnIndex >= imageArray[0].length)
			throw new IndexOutOfBoundsException("columnIndex must be in range 0-" + (imageArray[0].length - 1));

		return imageArray[rowIndex][columnIndex];
	}

	/**
	 * Writes the image represented by this object to file.
	 * Does nothing if the image length is 0.
	 *
	 * DO NOT MODIFY THIS METHOD
	 *
	 * @param filename - name of image file to write
	 * @throws IOException if file does cannot be written
	 */
	public void writeImage(String filename) {
		if(imageArray.length > 0) {
			BufferedImage imageOutput = new BufferedImage(imageArray[0].length,
					imageArray.length, BufferedImage.TYPE_INT_RGB);

			for(int i = 0; i < imageArray.length; i++)
				for(int j = 0; j < imageArray[0].length; j++)
					imageOutput.setRGB(j, i, imageArray[i][j].getPackedRGB());

			try {
				ImageIO.write(imageOutput, "png", new File(filename));
			}
			catch(IOException e) {
				System.out.println("The image cannot be written to file " + filename);
			}
		}
	}

	/**
	 * Applies a filter to the image represented by this object such that for each
	 * pixel the red amount and blue amount are swapped.
	 *
	 * HINT: Since the Pixel class does not include setter methods for its private
	 *       instance variables, create new Pixel objects with the altered colors.
	 */
	public void redBlueSwapFilter() {
		for(int row = 0; row < imageArray.length; row ++) {
			for(int col = 0; col < imageArray[row].length; col ++) {
				Pixel pixel = imageArray[row][col];
				//System.out.println("Before: r=" + pixel.getRedAmount() + " , B=" + pixel.getBlueAmount());
				imageArray[row][col] = new Pixel(pixel.getBlueAmount(), pixel.getGreenAmount(), pixel.getRedAmount());
				//System.out.println("After: r=" + pixel.getRedAmount() + " , B=" + pixel.getBlueAmount());
			}
		}
	}


	/**
	 * Applies a filter to the image represented by this object such that the color
	 * of each pixel is converted to its corresponding grayscale shade, producing the
	 * effect of a black and white photo. The filter sets the amount of red, green,
	 * and blue all to the value of this average:
	 *           (originalRed + originalGreen + originalBlue) / 3
	 *
	 * HINT: Since the Pixel class does not include setter methods for its private
	 *       instance variables, create new Pixel objects with the altered colors.
	 */
	public void blackAndWhiteFilter() {
		for(int row = 0; row < imageArray.length; row ++) {
			for(int col = 0; col < imageArray[row].length; col ++) {
				Pixel pixel = imageArray[row][col];
				int greyScale = (pixel.getRedAmount() + pixel.getGreenAmount() + pixel.getBlueAmount()) / 3;
				imageArray[row][col] = new Pixel(greyScale, greyScale, greyScale);
			}
		}
	}

	/**
	 * Applies a filter to the image represented by this object such that it is rotated
	 * clockwise (by 90 degrees). This filter rotates directly clockwise, it should
   * not do this by rotating counterclockwise 3 times.
	 *
	 * HINT: If the image is not square, this filter requires creating a new array with
	 *       different lengths. Use the technique of creating and reassigning a new backing array
	 *       from BetterDynamicArray (assign06) as a guide for how to make a second array and
	 *       eventually reset the imageArray reference to this new array.
	 *       Note that we learned how to rotate a square 2D array *left* in Class Meeting 11.
	 */
	public void rotateClockwiseFilter() {
	    int rows = imageArray.length;
	    int cols = imageArray[0].length;
	    Pixel[][] newImageArray = new Pixel[cols][rows];
	    for (int i = 0; i < rows; i++) {
	        for (int j = 0; j < cols; j++) {
	            newImageArray[j][rows - 1 - i] = imageArray[i][j];
	        }
	    }
	    imageArray = newImageArray;
	}

	/**
	 * Applies a filter to the image represented by this object such that the right side of the object
	 * mirrors the left side of the object, creating a mirror effect.
	 * 
	 */
	public void customFilter() {
		int rows = imageArray.length;
		int cols = imageArray[0].length;
		int mid = cols / 2; 
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < mid; col++) {
				imageArray[row][cols - 1 - col] = imageArray[row][col];
			}
		}
	}

	/**
	 * 
	 * Applies a filter to the image represented by this object such that makes the image more cool (blue).
	 */
	public void coolBlueFilter() {
		int numRow = imageArray.length;
		int numCol = imageArray[0].length;
		for(int i = 0; i < numRow; i++) {
			for(int j = 0; j < numCol; j++) {
				int red = imageArray[i][j].getRedAmount() - 10;
				if (red < 0)
					red = 0;
				int green = imageArray[i][j].getGreenAmount() - 10;
				if (green < 0)
					green = 0;
				int blue = imageArray[i][j].getBlueAmount() + 50;
				if (blue > 255)
					blue = 255;
				imageArray[i][j] = new Pixel(red, green, blue);
			}
		}
	}
	
	/**
    
	Applies a filter to the image represented by this object such that the brightness is 
	adjusted by the brightener value.
	@param brightener - the amount the brightness is adjusted.*/
	public void brightnessFilter(int brightener) {
	    int numRow = imageArray.length;
	    int numCol = imageArray[0].length;
	    for(int i = 0; i < numRow; i++) {
	        for(int j = 0; j < numCol; j++) {
	            int red = imageArray[i][j].getRedAmount() + brightener;
	            if (red < 0)
	                red = 0;
	            else if (red > 255)
	                red = 255;
	            int green = imageArray[i][j].getGreenAmount() + brightener;;
	            if (green < 0)
	                green = 0;
	            else if (green > 255)
	                green = 255;
	            int blue = imageArray[i][j].getBlueAmount() + brightener;
	            if (blue < 0)
	                blue = 0;
	            else if (blue > 255)
	                blue = 255;
	            imageArray[i][j] = new Pixel(red, green, blue);
	            }
	        }
	    }
	
	/**
    
	Crops an image from the first coordinate (x1, y1) to the second coordinate (x2, y2)
	@param x1 - the x-axis location of the first coordinate
	@param y1 - the y-axis location of the first coordinate
	@param x2 - the x-axis location of the second coordinate
	@param y2 - the y-axis location of the second coordinate*/
	public void cropImageFilter(int x1, int y1, int x2, int y2) {
	    int numRow = y2 - y1; // x2 > x1
	    int numCol = x2 - x1; // y2 < y1
	    Pixel[][] cropArray = new Pixel[numRow][numCol]; // Creates a new array with cropped dimensions
	    for(int row = 0; row < numRow; row++) {
	        for(int col = 0; col < numCol; col++) {
	           cropArray[row][col] = imageArray[row + x1][col + y1];
	           }
	      }
	    imageArray = cropArray;
	}

	
	/**
	 * Returns number of rows in an imageArray
	 * @return int # of rows
	 */
	public int getNumberOfRows() {
		   return this.imageArray.length;
	}

	/**
	 * Returns number of columns in an imageArray
	 * @return int # of columns
	 */
	public int getNumberOfColumns() {
		  if(this.imageArray.length == 0)
		     return 0;
		   return this.imageArray[0].length;
	}
	/**
	 * Returns a copied
	@return
	*/
	public Image copyImage() {
	    int numRow = imageArray.length;
	    int numCol = imageArray[0].length;
	    Pixel[][] copyArray = new Pixel[numRow][numCol]; // Creates a new array with rotated dimensions
	    for(int i = 0; i < numRow; i++) {
	        for(int j = 0; j < numCol; j++) {
	        copyArray[i][j] = imageArray[i][j];}}
	    Image copyImage = new Image(copyArray);
	    return copyImage;
	    }

}
