package assign08;

/**
 * @author Brian Yu
 * @version JavaSE-21
 */
public class Tool extends Item {
	public int power;
	
	public Tool(String name, int power) {
		super(name);
		this.power = power;
	}
	
	/**
	 * Instance variable power getter
	 * @return int power
	 */
	public int getPower() {
		return this.power;
	}

	/**
	 * Compares two tool objects, where if the original object's power is larger,
	 * returns 1, if smaller returns -1, if equal returns 0.
	 * @return int compared tool value
	 */
	public int compareTo(Item other) {
		if(other instanceof Tool) {
			Tool tool = (Tool) other;
			if(this.power > tool.power) {
				return 1;
			}
			else if(this.power < tool.power) {
				return -1;
			}
			else {
				return 0;
			}
		}
		else {
			
			return 0;
		}
	}

	/**
	 * Returns a string representation describibng the power of the item
	 * @return String description
	 */
	public String getDescription() {
		return "Tool: " + getName() + " - power = "  + power;
	}

	/**
	 * Increases tool's power by a factor of 2
	 */
	public void useUpgrade() {
		power += 2;
	}

}
