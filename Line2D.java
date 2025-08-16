package assign05;
/**
 * @author Brian Yu
 * @version JavaSE-21
 */

public class Line2D {
	private Point2D endPoint1;
	private Point2D endPoint2;
	public Line2D() {
		this.endPoint1 = new Point2D(0, 0);
		this.endPoint2 = new Point2D(1, 1);
	}
	
	public Line2D(Point2D endPoint1, Point2D endPoint2) {
		this.endPoint1 = endPoint1;
		this.endPoint2 = endPoint2;
	}
	
	/**
	 * Gets both end points of this line2D obj, and returns it as two elements in an array
	 * @return Point2D[] with a length of two
	 */
	public Point2D[] getEndpoints() {
		Point2D[] arr = new Point2D[2];
		Point2D copy1 = new Point2D(endPoint1.getX(), endPoint1.getY());
		Point2D copy2 = new Point2D(endPoint2.getX(), endPoint2.getY());
		arr[0] = copy1;
		arr[1] = copy2;
		return arr;
	}
	
	/**
	 * Method invokes the distance() method from Point2D.java on the line2D object's end points
	 * @return A double value of the distance between the two end points
	 */
	public double distance() {
		return endPoint1.distance(endPoint2);
	}
	
	/**
	 * Method invokes the slope() method from Point2D.java on the line2D object's end points
	 * @return a double value of the slope between the two end points
	 */
	public double slope() {
		return endPoint1.slope(endPoint2);
	}
	
	/**
	 * Method invokes the midpoint() method from Point2D.java on the line2D object's end points
	 * @return new Line2D object of its inputted object's midpoint
	 */
	public Point2D midpoint() {
		return endPoint1.midpoint(endPoint2);
	}
	
	/**
	 * An equals method for this Line2D object's endpoints, invoking the equals() method from Point2D.java class
	 * @return boolean
	 */
	public boolean equals(Object other) {
		Line2D otherPoint = (Line2D) other;
		return this.endPoint1.equals(otherPoint.endPoint1) && this.endPoint2.equals(otherPoint.endPoint2);
	}
	
}

