package assign05;

/**
 * @author Brian Yu
 * @version JavaSE-21
 */

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

class Line2DTest {
	
	@Test 
	public void testGetEndpoints() {
		Point2D endPointOne = new Point2D(0,0);
		Point2D endPointTwo = new Point2D(1, 3);
		Line2D obj1 = new Line2D(endPointOne, endPointTwo);
		Point2D[] arr = new Point2D[2];
		arr[0] = endPointOne;
		arr[1] = endPointTwo;
		assertTrue(Arrays.equals(arr, obj1.getEndpoints()));
	}
	
	@Test 
	public void testDistanceLine2D() {
		Point2D endPointOne = new Point2D(0,0);
		Point2D endPointTwo = new Point2D(1, 3);
		Line2D obj1 = new Line2D(endPointOne, endPointTwo);
		assertEquals(3.162, obj1.distance(), 0.001);
	}
	
	@Test 
	public void testDistanceLine2DNeg() {
		Point2D endPointOne = new Point2D(-1,0);
		Point2D endPointTwo = new Point2D(2, -3);
		Line2D obj1 = new Line2D(endPointOne, endPointTwo);
		assertEquals(4.243, obj1.distance(), 0.001);
	}
	
	@Test 
	public void testDistanceLine2DBig() {
		Point2D endPointOne = new Point2D(0,0);
		Point2D endPointTwo = new Point2D(500000, 300000);
		Line2D obj1 = new Line2D(endPointOne, endPointTwo);
		assertEquals(583095.189, obj1.distance(), 0.001);
	}
	
	@Test
	public void testSlopeLine2D() {
		Point2D endPointOne = new Point2D(0,0);
		Point2D endPointTwo = new Point2D(4, 4);
		Line2D obj1 = new Line2D(endPointOne, endPointTwo);
		assertEquals(1, obj1.slope(), 0.001);
	}
	
	@Test
	public void testSlopeLine2DNeg() {
		Point2D endPointOne = new Point2D(0,0);
		Point2D endPointTwo = new Point2D(-4, 4);
		Line2D obj1 = new Line2D(endPointOne, endPointTwo);
		assertEquals(-1, obj1.slope(), 0.001);
	}
	
	@Test
	public void testSlopeLine2DZero() {
		Point2D endPointOne = new Point2D(0,0);
		Point2D endPointTwo = new Point2D(0, 0);
		Line2D obj1 = new Line2D(endPointOne, endPointTwo);
		assertEquals(0, obj1.slope(), 0.001);
	}

	@Test
	public void testMidpointLine2D() {
		Point2D endPointOne = new Point2D(0,0);
		Point2D endPointTwo = new Point2D(5, 8);
		Line2D obj1 = new Line2D(endPointOne, endPointTwo);
		Point2D midpoint = new Point2D(2, 4);
		assertTrue(obj1.midpoint().equals(midpoint));
	}
	
	@Test
	public void testMidpointLine2DNeg() {
		Point2D endPointOne = new Point2D(0,0);
		Point2D endPointTwo = new Point2D(-4, -8);
		Line2D obj1 = new Line2D(endPointOne, endPointTwo);
		Point2D midpoint = new Point2D(-2, -4);
		assertTrue(obj1.midpoint().equals(midpoint));
	}
	
	@Test
	public void testMidpointLine2DBig() {
		Point2D endPointOne = new Point2D(0,0);
		Point2D endPointTwo = new Point2D(500000, 800000);
		Line2D obj1 = new Line2D(endPointOne, endPointTwo);
		Point2D midpoint = new Point2D(250000, 400000);
		assertTrue(obj1.midpoint().equals(midpoint));
	}
	
	@Test
	public void testEqualsFalseLine2D() {
		Point2D endPointOne = new Point2D(0,0);
		Point2D endPointTwo = new Point2D(500000, 800000);
		Line2D obj1 = new Line2D(endPointOne, endPointTwo);
		Point2D point1 = new Point2D(0,0);
		Point2D point2 = new Point2D(1, 1);
		Line2D obj2 = new Line2D(point1, point2);
		assertFalse(obj1.equals(obj2));
	}
	
	@Test
	public void testEqualsTrueLine2D() {
		Point2D endPointOne = new Point2D(0,0);
		Point2D endPointTwo = new Point2D(4, 1);
		Line2D obj1 = new Line2D(endPointOne, endPointTwo);
		Point2D point1 = new Point2D(0,0);
		Point2D point2 = new Point2D(4, 1);
		Line2D obj2 = new Line2D(point1, point2);
		assertTrue(obj1.equals(obj2));
	}
	
	@Test
	public void testEqualsNegZeroLine2D() {
		Point2D endPointOne = new Point2D(-0,-0);
		Point2D endPointTwo = new Point2D(4, 1);
		Line2D obj1 = new Line2D(endPointOne, endPointTwo);
		Point2D point1 = new Point2D(-0,-0);
		Point2D point2 = new Point2D(4, 1);
		Line2D obj2 = new Line2D(point1, point2);
		assertTrue(obj1.equals(obj2));
	}
	

}
