package assign05;
/**
 * @author Brian Yu
 * @version JavaSE-21
 */
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

public class Point2DTest {
	
	@Test
	public void testGetX() {
		Point2D location = new Point2D(-43, 55);
		assertEquals(-43, location.getX());
	}
	
	@Test
	public void testGetY() {
		Point2D location = new Point2D(40, -65);
		assertEquals(-65, location.getY());
	}
	
	@Test
	public void testClear() {
		Point2D num = new Point2D(5, 5);
		assertEquals(5, num.getX());
		assertEquals(5, num.getY());
		num.clear();
		assertEquals(0, num.getX());
		assertEquals(0, num.getY());
	}
	
	@Test
	public void testBigClear() {
		Point2D dum = new Point2D(-50000, 50000);
		assertEquals(-50000, dum.getX());
		assertEquals(50000, dum.getY());
		dum.clear();
		assertEquals(0, dum.getX());
		assertEquals(0, dum.getY());
	}
	
	@Test 
	public void testAlreadyClear() {
		Point2D bum = new Point2D(0, 0);
		assertEquals(0, bum.getY());
		bum.clear();
		assertEquals(0, bum.getX());
		assertEquals(0, bum.getY());
	}
	
	
	@Test
	public void testMove() {
		Point2D zero = new Point2D();
		assertEquals(0, zero.getX());
		assertEquals(0, zero.getY());
		zero.move(100, -2);
		assertEquals(100, zero.getX());
		assertEquals(-2, zero.getY());
	}
	
	@Test
	public void testMoveBig() {
		Point2D zero = new Point2D();
		assertEquals(0, zero.getX());
		assertEquals(0, zero.getY());
		zero.move(1000000, -200000);
		assertEquals(1000000, zero.getX());
		assertEquals(-200000, zero.getY());
	}
	
	@Test
	public void testMoveZeros() {
		Point2D zero = new Point2D();
		assertEquals(0, zero.getX());
		assertEquals(0, zero.getY());
		zero.move(0, 0);
		assertEquals(0, zero.getX());
		assertEquals(0, zero.getY());
	}
	
	
	@Test
	public void testToString() {
		Point2D zero = new Point2D();
		assertEquals("(0, 0)", zero.toString());
	}
	
	@Test
	public void testToStringLarge() {
		Point2D zero = new Point2D(100000, 200000);
		assertEquals("(100000, 200000)", zero.toString());
	}
	
	@Test
	public void testToStringNegative() {
		Point2D zero = new Point2D(-5, -75);
		assertEquals("(-5, -75)", zero.toString());
	}
	
	@Test
	public void testEquals() {
		Point2D center = new Point2D(50, 50);
		Object obj = new Point2D(50, 50);
		assertTrue(center.equals(obj), 
				"The parameter of equals() does not correctly match the requirements.");
	}
	
	@Test
	public void testEqualsZero() {
		Point2D center = new Point2D();
		Object obj = new Point2D();
		assertTrue(center.equals(obj), 
				"The parameter of equals() does not correctly match the requirements.");
	}

	@Test
	public void testEqualsFalse() {
		Point2D center = new Point2D(100, 50);
		Object obj = new Point2D(50, 50);
		assertFalse(center.equals(obj), 
				"The parameter of equals() does not correctly match the requirements.");
	}
	@Test
	public void testDistance() {
		Point2D southWest = new Point2D(3, 1);
		Point2D northEast = new Point2D(10, 12);
		
		/* This version of assertEquals has a third parameter for the error 
		 * tolerance when comparing two doubles, which is necessary since 
		 * many values with decimal points cannot be represented exactly in 
		 * a computer. 
		 */
		assertEquals(13.0384, southWest.distance(northEast), 0.0001);
	}
	
	@Test
	public void testDistanceZero() {
		Point2D southWest = new Point2D(0, 0);
		Point2D northEast = new Point2D(0, 0);
		
		/* This version of assertEquals has a third parameter for the error 
		 * tolerance when comparing two doubles, which is necessary since 
		 * many values with decimal points cannot be represented exactly in 
		 * a computer. 
		 */
		assertEquals(0, southWest.distance(northEast), 0.0001);
	}
	
	@Test
	public void testDistanceLarge() {
		Point2D southWest = new Point2D(0, 0);
		Point2D northEast = new Point2D(100000, 200000);
		
		/* This version of assertEquals has a third parameter for the error 
		 * tolerance when comparing two doubles, which is necessary since 
		 * many values with decimal points cannot be represented exactly in 
		 * a computer. 
		 */
		assertEquals(223606.7977, southWest.distance(northEast), 0.0001);
	}
	
	@Test 
	public void testSlope() {
		Point2D slope1 = new Point2D(3, 1);
		Point2D slope2 = new Point2D(10, 12);
		assertEquals(1.5714, slope1.slope(slope2), 0.0001);
	}
	
	@Test 
	public void testSlopeNegative() {
		Point2D slope1 = new Point2D(-50, 2);
		Point2D slope2 = new Point2D(-40, -100);
		assertEquals(-10.2, slope1.slope(slope2), 0.0001);
	}
	
	@Test 
	public void testSlopeZero() {
		Point2D slope1 = new Point2D(0, 0);
		Point2D slope2 = new Point2D(0, 0);
		assertEquals(0, slope1.slope(slope2), 0.0001);
	}
	
	//Assignment 5 tests onward within Point2D class. ------------------------------------------
	
	@Test
	public void testCopyOf() {
		Point2D slope1 = new Point2D(1, 3);
		slope1.copyOf();
		assertTrue(slope1.equals(slope1.copyOf()));
	}
	
	@Test
	public void testCopyOfZero() {
		Point2D slope1 = new Point2D(0, 0);
		slope1.copyOf();
		assertTrue(slope1.equals(slope1.copyOf()));
	}
	
	@Test
	public void testCopyOfLarge() {
		Point2D slope1 = new Point2D(999999, 9959);
		slope1.copyOf();
		assertTrue(slope1.equals(slope1.copyOf()));
	}
	
	@Test 
	public void testAdd() {
		Point2D obj1 = new Point2D(4, 4);
		Point2D obj2 = new Point2D(1, 1);
		Point2D realSum = obj1.add(obj2);
		Point2D sum = new Point2D(5, 5);
		assertTrue(realSum.equals(sum));	
	}
	
	@Test 
	public void testAddNeg() {
		Point2D obj1 = new Point2D(-4, -4);
		Point2D obj2 = new Point2D(1, 1);
		Point2D realSum = obj1.add(obj2);
		Point2D sum = new Point2D(-3, -3);
		assertTrue(realSum.equals(sum));
	}
	
	@Test 
	public void testAddZero() {
		Point2D obj1 = new Point2D(0, 0);
		Point2D obj2 = new Point2D(-4, -4);
		Point2D realSum = obj1.add(obj2);
		Point2D sum = new Point2D(-4, -4);
		assertTrue(realSum.equals(sum));
	}
	
	@Test 
	public void testMidpoint() {
		Point2D obj1 = new Point2D(3, 3);
		Point2D obj2 = new Point2D(1, -1);
		Point2D midPoint = new Point2D(2, 1);
		assertTrue(midPoint.equals(obj1.midpoint(obj2)));
	}
	
	@Test 
	public void testMidpointRoundDown() {
		Point2D obj1 = new Point2D(0, 3);
		Point2D obj2 = new Point2D(0, 0);
		Point2D midPoint = new Point2D(0, 1);
		assertTrue(midPoint.equals(obj1.midpoint(obj2)));
	}
	
	@Test 
	public void testMidpointBig() {
		Point2D obj1 = new Point2D(500000, -600000);
		Point2D obj2 = new Point2D(0, 0);
		Point2D midPoint = new Point2D(250000, -300000);
		assertTrue(midPoint.equals(obj1.midpoint(obj2)));
	}
	
	//End of Point2D class tests. --------------------------------------------------------
	
	
}