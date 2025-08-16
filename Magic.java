package assign08;

/**
 * @author Brian Yu
 * @version JavaSE-21
 */
public class Magic extends Item{
	public int power;
	public int cost;
	
	public Magic(String name, int power, int cost) {
		super(name);
		this.power = power;
		this.cost = cost;
	}
	
	/**
	 * Instance variable power getter
	 * @return int power
	 */
	public int getPower() {
		return this.power;
	}
	
	/**
	 * Instance variable cost getter
	 * @return int cost
	 */
	public int getCost() {
		return this.cost;
	}

	/**
	 * Compares two Magic objects, where the quotient of power/cost is compared, returning 1
	 * if the original item greater, -1 if lesser, or 0 if the same
	 * @return int compared magic value
	 */
	public int compareTo(Item other) {
		if(other instanceof Magic) {
			Magic magic = (Magic) other;
			int thisQuotient = this.power / magic.cost;
			int otherQuotient = magic.power / this.cost;
			if(thisQuotient > otherQuotient) {
				return  1;
			} else if(thisQuotient < otherQuotient) {
				return -1;
			} else {
				return 0;
			}
		} else {
			
			return 0;
		}
	}

	/**
	 * Returns a string representation of the item's power and cost
	 * @return String descpription
	 */
	public String getDescription() {
		return "Magic: " + getName() + " - power = " + power + ", cost = " + cost;
	}

	/**
	 * Increases magic power by a factor of two and descreases cost by 3
	 */
	public void useUpgrade() {
		power += 2;
		cost -= 3;
	}

}
