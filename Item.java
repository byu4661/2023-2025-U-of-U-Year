package assign08;
/**
 * @author Brian Yu
 * @version JavaSE-21
 */

public abstract class Item implements Comparable<Item>  {
	private String name;
	
	public Item(String name) {
		this.name = name;
	}
	
	public Item() {
		this.name = "Unknown";
	}
	
	/**
	 * Method returns a string representation of the current name variable
	 * @return String
	 */
	public String getName() {
		return name;
	}
	
	public abstract String getDescription();
	
	public abstract void useUpgrade();
	
	
		
	
}
