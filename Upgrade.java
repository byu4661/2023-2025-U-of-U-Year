package assign08;

/**
 * @author Brian Yu
 * @version JavaSE-21
 */
public class Upgrade extends Item {
	public boolean upgradeUsed;
	
	public Upgrade() {
		this.upgradeUsed = false;
	}
	
	/**
	 * Returns a boolean expression whether upgradeUsed is true or false
	 * @return boolean
	 */
	public boolean isUsed() {
		return this.upgradeUsed;
	}
	
	/**
	 * Compares two upgrade objects, where unused > used, otherwise they are equals
	 * @return int compared upgrade value
	 */
	public int compareTo(Item other) {
		if(other instanceof Upgrade) {
			Upgrade upgrade = (Upgrade) other;
			if(this.upgradeUsed == false && upgrade.upgradeUsed == true) {
				System.out.println("This item's upgrade is unused.");
				return 1;
			} 
			else if(this.upgradeUsed == false && upgrade.upgradeUsed == false)
				return 1;
			else if(this.upgradeUsed == true && upgrade.upgradeUsed == false) {
				return -1;
			}
			else {
				System.out.println("This item's upgrade has already been used.");
				return 0;
			}
		}
		else {
			
			return 0;
		}
	}
	
	/**
	 * Returns a description whether the item can be upgraded or not
	 */
	public String getDescription() {
		if(this.upgradeUsed == false)
			return "Upgrade: ready";
		else {
			return "Upgrade: used";
		}
	}

	/**
	 * Changes instance variable upgradeUsed to true
	 */
	public void useUpgrade() {
		upgradeUsed = true;
	}
	
	

}
