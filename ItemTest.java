package assign08;

/**
 * @author Brian Yu
 * @version JavaSE-21
 */

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ItemTest {

	@Test
	void toolConstructor() {
		Tool obj = new Tool("box", 2);
		assertEquals(2, obj.getPower());
		assertEquals("box", obj.getName());
	}
	
	@Test
	void toolCompareToSmaller() {
		Tool obj1 = new Tool("box", 2);
		Tool obj2 = new Tool("box", 3);
		assertEquals(-1, obj1.compareTo(obj2));
	}
	
	@Test
	void toolCompareToLarger() {
		Tool obj1 = new Tool("box", 5);
		Tool obj2 = new Tool("hammer", 2);
		assertEquals(1, obj1.compareTo(obj2));
	}
	
	@Test
	void toolCompareToEquals() {
		Tool obj1 = new Tool("box", 5);
		Tool obj2 = new Tool("pen", 5);
		assertEquals(0, obj1.compareTo(obj2));
	}
	
	@Test
	void toolGetDescrption() {
		Tool obj = new Tool("arrow", 5);
		assertEquals("Tool: " + obj.getName() + " - power = 5", obj.getDescription());
	}
	
	@Test 
	void toolGetDescriptionZero() {
		Tool obj = new Tool("box", 0);
		assertEquals("Tool: " + obj.getName() + " - power = 0", obj.getDescription());
	}
	
	@Test
	void toolUseUpgrade() {
		Tool obj = new Tool("box", 2);
		obj.useUpgrade();
		assertEquals(4, obj.getPower());
	}
	
	@Test
	void toolUseUpgradeLarge() {
		Tool obj = new Tool("box", 999999);
		obj.useUpgrade();
		assertEquals(1000001, obj.getPower());
	}
	
	//Start Armor Subclass Tests ---------------------------------------------
	@Test
	void armorConstructor() {
		Armor obj = new Armor("shield", 2);
		assertEquals(2, obj.getDefense());
		assertEquals("shield", obj.getName());
	}
	
	@Test
	void armorCompareToSmaller() {
		Armor obj1 = new Armor("shield", 2);
		Armor obj2 = new Armor("shield", 3);
		assertEquals(-1, obj1.compareTo(obj2));
	}
	
	@Test
	void armorCompareToLarger() {
		Armor obj1 = new Armor("shield", 5);
		Armor obj2 = new Armor("helmet", 2);
		assertEquals(1, obj1.compareTo(obj2));
	}
	
	@Test
	void armorCompareToEquals() {
		Armor obj1 = new Armor("kevlar", 5);
		Armor obj2 = new Armor("boots", 5);
		assertEquals(0, obj1.compareTo(obj2));
	}
	
	@Test
	void armorGetDescrption() {
		Armor obj = new Armor("plate", 5);
		assertEquals("Armor: " + obj.getName() +  " - defense = 5", obj.getDescription());
	}
	
	@Test 
	void armorGetDescriptionZero() {
		Armor obj = new Armor("box", 0);
		assertEquals("Armor: " + obj.getName() + " - defense = 0", obj.getDescription());
	}
	
	@Test
	void armorUseUpgrade() {
		Armor obj = new Armor("boots", 2);
		obj.useUpgrade();
		assertEquals(7, obj.getDefense());
	}
	
	@Test
	void armorUseUpgradeLarge() {
		Armor obj = new Armor("boots", 999999);
		obj.useUpgrade();
		assertEquals(1000004, obj.getDefense());
	}
	
	//Start Magic Subclass Tests ------------------------------------------
	@Test
	void MagicConstructor() {
		Magic obj = new Magic("ice", 2, 3);
		assertEquals(2, obj.getPower());
		assertEquals(3, obj.getCost());
		assertEquals("ice", obj.getName());
	}
	
	@Test
	void MagicCompareToSmaller() {
		Magic obj1 = new Magic("ice", 2, 3);
		Magic obj2 = new Magic("fire", 5, 5);
		assertEquals(-1, obj1.compareTo(obj2));
	}
	
	@Test
	void MagicCompareToLarger() {
		Magic obj1 = new Magic("voodoo", 10, 5);
		Magic obj2 = new Magic("fire", 5, 5);
		assertEquals(1, obj1.compareTo(obj2));
	}
	
	@Test
	void MagicCompareToEquals() {
		Magic obj1 = new Magic("fire", 5, 5);
		Magic obj2 = new Magic("fire", 5, 5);
		assertEquals(0, obj1.compareTo(obj2));
	}
	
	@Test
	void MagicGetDescrption() {
		Magic obj = new Magic("fire", 5, 5);
		assertEquals("Magic: " + obj.getName() + " - power = 5, cost = 5", obj.getDescription());
	}
	
	@Test 
	void MagicGetDescriptionZero() {
		Magic obj = new Magic("ice", 0, 0);
		assertEquals("Magic: " + obj.getName() + " - power = 0, cost = 0", obj.getDescription());
	}
	
	@Test
	void MagicUseUpgrade() {
		Magic obj = new Magic("water", 2, 3);
		obj.useUpgrade();
		assertEquals(4, obj.getPower());
		assertEquals(0, obj.getCost());
	}
	
	@Test
	void MagicUseUpgradeLarge() {
		Magic obj = new Magic("lava", 999999, 1);
		obj.useUpgrade();
		assertEquals(1000001, obj.getPower());
		assertEquals(-2, obj.getCost());
	}
	
	//Start Upgrade Subclass Tests ------------------------------------------
	@Test
	void upgradeConstructor() {
		Upgrade obj = new Upgrade();
		assertEquals(false, obj.isUsed());
	}
	
	@Test
	void upgradeCompareToSmaller() {
		Upgrade obj1 = new Upgrade();
		Upgrade obj2 = new Upgrade();
		obj2.useUpgrade();
		assertEquals(1, obj1.compareTo(obj2));
	}
	
	@Test
	void upgradeCompareToLarger() {
		Upgrade obj1 = new Upgrade();
		Upgrade obj2 = new Upgrade();
		obj1.useUpgrade();
		assertEquals(-1, obj1.compareTo(obj2));
	}
	
	@Test
	void upgradeGetDescription() {
		Upgrade obj = new Upgrade();
		assertEquals("Upgrade: ready", obj.getDescription());
	}
	
	@Test
	void upgradeGetDescrptionUsed() {
		Upgrade obj = new Upgrade();
		obj.useUpgrade();
		assertEquals("Upgrade: used", obj.getDescription());
	}
}
