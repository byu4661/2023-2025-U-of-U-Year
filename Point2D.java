package assign05;

/**
 * @author Brian Yu
 * @version JavaSE-21
 */


public class Point2D {
	private int x;
	private int y;
	
	public Point2D() {
		this.x = 0;
		this.y = 0;
	}
	
	public Point2D(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * x getter
	 * @return int x
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * y getter
	 * @return int y
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * Method resets instance variables to value 0
	 */
	public void clear() {
		this.x = 0;
		this.y = 0;
	}
	
	/**
	 * Changes instance variable values to method call parameters
	 * @param int x
	 * @param int y
	 */
	public void move(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Creates a string representation of the instance variables
	 * @return "(x, y)"
	 */
	public String toString() {
		return "(" + x + ", " + y + ")";
	}
	
	/**
	 * Calculates distance using the Euclidean formula 
	 * @param secondPoint
	 * @return double value
	 */
	public double distance(Point2D secondPoint) {
		return Math.sqrt(Math.pow(secondPoint.x - this.x, 2) + Math.pow(secondPoint.y - this.y, 2));
	}
	
	/**
	 * Uses slope formula to calculate slope between two points
	 * @param secondPoint
	 * @return double value
	 */
	public double slope(Point2D secondPoint) {
		if(secondPoint.x - this.x == 0)
			return 0.0;
		return ((double) (secondPoint.y - this.y) / (secondPoint.x - this.x));
	}
	
	/**
	 * Checks if values inputed are the same as the current instance variable values
	 * @return boolean
	 */
	public boolean equals(Object other) {
		if (!(other instanceof Point2D))
			return false;
		Point2D otherFraction = (Point2D) other;
		return this.x == otherFraction.x && this.y == otherFraction.y;
		}
	
	/**
	 * Creates a new object that copies the inputed object's parameters.
	 * @return Point2D obj
	 */
	public Point2D copyOf() {
		Point2D newObj = new Point2D(this.x, this.y);
		return newObj;
	}
	
	/**
	 * Adds Point2D respective x points, and y points to make a new point. (x + x, y + y)
	 * @param other
	 * @return Point2D obj
	 */
	public Point2D add(Point2D other) {
		Point2D newObj = new Point2D(this.x + other.x, this.y + other.y);
		return newObj;
	}
	
	/**
	 * Calculates an object's midpoint based on the other obj's and this obj's x and y values. 
	 * @param other
	 * @return Point2D midpoint of two object's midpoints
	 */
	public Point2D midpoint(Point2D other) {
		Point2D newObj = new Point2D((other.x + this.x) / 2 , (other.y + this.y) / 2);
		return newObj;
	}
	
	
}
