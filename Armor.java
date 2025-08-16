package assign08;

/**
 * @author Brian Yu
 * @version JavaSE-21
 */
public class Armor extends Item {
	public int defense;
	
	public Armor(String name, int defense) {
		super(name);
		this.defense = defense;
	}
	
	public int getDefense() {
		return this.defense;
	}

	/**
	 * Compares two armor objects, where if the original object's defense is larger, 
	 * returns 1, if smaller returns -1, if the same returns 0.
	 * @return int compared armor value
	 */
	public int compareTo(Item other) {
		if(other instanceof Armor) {
			Armor armor = (Armor) other;
			if(this.defense > armor.defense)
				return 1;
			else if(this.defense < armor.defense)
				return -1;	
			else {
				return 0;
			}
		}
		else {
			
			return 0;
		}
	}

	/**
	 * Returns a string representation of the defense of the item
	 * @return String description
	 */
	public String getDescription() {
		return "Armor: " + getName() + " - defense = " + defense;
	}

	/**
	 * Increases armor's defense by a factor of 5
	 */
	public void useUpgrade() {
		defense += 5;
	}
	
	
	

}
