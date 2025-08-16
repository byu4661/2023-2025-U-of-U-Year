package assign11;

/**
 * @author Brian Yu
 * @version JavaSE-21
 */

public class Pixel {
	private int redAmount;   //Range is 0-255 for red, green, and blue
	private int greenAmount;
	private int blueAmount;
	
	public Pixel(int redAmount, int greenAmount, int blueAmount) {
		if(redAmount < 0 || redAmount > 255 || greenAmount < 0 || greenAmount > 255 || blueAmount < 0 || blueAmount > 255)
			throw new IllegalArgumentException("Pixel value must be between 0 & 255.");
		this.redAmount = redAmount;
		this.greenAmount = greenAmount;
		this.blueAmount = blueAmount;
	}
	
	/**
	 * Returns the current red amount
	 * @return int amount
	 */
	public int getRedAmount() {
		return redAmount;
	}
	
	/**
	 * Returns the current green amount
	 * @return int amount
	 */
	public int getGreenAmount() {
		return greenAmount;
	}
	
	/**
	 * Returns the current blue amount
	 * @return int amount
	 */
	public int getBlueAmount() {
		return blueAmount;
	}
	
	/**
	 * Packs the red, green, and blue amounts into a 4 byte value
	 * @return 4 byte value
	 */
	public int getPackedRGB() {
		return redAmount << 16 | greenAmount << 8 | blueAmount;
	}
	

}
