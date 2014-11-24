/**
 * 
 */

/**
 * @author Derick Owens
 *
 */
public class ObjectVector {
	private double changeX;
	private double changeY;
	
	/**
	 * @param changeX the x value of the vector
	 * @param changeY the y value of the vector
	 */
	public ObjectVector(double changeX, double changeY){
		super();
		this.changeX = changeX;
		this.changeY = changeY;
	}

	/**
	 * @return the changeX
	 */
	public double getChangeX() {
		return changeX;
	}

	/**
	 * @param changeX the changeX to set
	 */
	public void setChangeX(double changeX) {
		this.changeX = changeX;
	}

	/**
	 * @return the changeY
	 */
	public double getChangeY() {
		return changeY;
	}

	/**
	 * @param changeY the changeY to set
	 */
	public void setChangeY(double changeY) {
		this.changeY = changeY;
	}
}
