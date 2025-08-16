package assign09;
/**
 * @author Brian Yu
 * @version JavaSE-21
 */

import java.awt.Color;

import javax.swing.JButton;

public class LightsOutButton extends JButton {
	private int rowNum;
	private int colNum;
	private boolean lightOnOff;
	
	/**
	 * Constructor for LightsOutButton, and inherits the JButton constructor
	 * @param rowNum
	 * @param colNum
	 */
	public LightsOutButton(int rowNum, int colNum) {
		super();
		this.rowNum = rowNum;	
		this.colNum = colNum;
		lightOnOff = false;
		}
	
	/**
	 * Switches the light on/off boolean to the opposite of what it was
	 * and changes the button's look and feel accordingly
	 */
	void toggle() {
		lightOnOff = !lightOnOff;
		if(lightOnOff == true) {
			this.setText("1");
			this.setBackground(Color.YELLOW);
		}
		else if(lightOnOff == false){
			this.setText("0");
			this.setBackground(Color.BLACK);
		}
	}
	
	/**
	 * rowNum variable getter
	 */
	int getRow() {
		return rowNum;
	}
	
	/**
	 * colNum variable getter
	 */
	int getColumn() {
		return colNum;
	}

	/**
	 * lightOnOff variable getter
	 */
	boolean isOn() {
		return lightOnOff;
	}
	
	
}
