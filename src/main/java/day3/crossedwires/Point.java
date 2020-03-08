package day3.crossedwires;

public class Point implements Cloneable {
	private int xAxis;
	private int yAxis;

	public Point(int xAxis, int yAxis) {
		this.xAxis = xAxis;
		this.yAxis = yAxis;
	}

	public int getxAxis() {
		return xAxis;
	}

	public void setxAxis(int xAxis) {
		this.xAxis = xAxis;
	}

	public int getyAxis() {
		return yAxis;
	}

	public void setyAxis(int yAxis) {
		this.yAxis = yAxis;
	}

	public void moveDown(int distance) {
		this.yAxis = yAxis - distance;
	}

	public void moveUp(int distance) {
		this.yAxis = yAxis + distance;
	}

	public void moveLeft(int distance) {
		this.xAxis = xAxis - distance;
	}

	public void moveRight(int distance) {
		this.xAxis = xAxis + distance;
	}

	public int calculateManhattanDistance(Point p) {
		return Math.abs(p.getxAxis() - this.getxAxis()) + Math.abs(p.getyAxis() - this.getyAxis());
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public String toString() {
		return "Point{" + "xAxis=" + xAxis + ", yAxis=" + yAxis + '}';
	}

	public boolean equals(Object o) {
		if (o == null)
			return false;
		if (o == this)
			return true;
		if (!(o instanceof Point))
			return false;
		Point p = (Point) o;
		return this.getxAxis() == p.getxAxis() && this.getyAxis() == p.getyAxis();
	}

	public Point clone() {
		try {
			return (Point) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
	}
}
