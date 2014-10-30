package de.htwg.se.ubongo.geo;

/** A Polygon connects several Points.
 * @author Patrick Leber
 * @version 25.10.2014 */
public final class Polygon {

	protected final Point[] point;

	public Polygon(final int numbPoint) {
		if(numbPoint < 3) {
			throw new IllegalArgumentException("Polygon need at least 3 Points.");
		}

		point = new Point[numbPoint];
		for (int i = 0; i < point.length; i++) {
			point[i] = new Point();
		}
	}

	public void setPoint(final int index, double x, double y) {
		point[index].set(x, y);
	}

	public void rotateAround(final double angleDeg, final Point pivot) {
		for (Point p : point) {
			p.rotateAround(angleDeg, pivot);
		}
	}

	public void move(final Vector v) {
		for (Point p : point) {
			p.move(v);
		}
	}
	
	public int numbPoint() {
		return point.length;
	}
	
	public Point getPoint(int index) throws IndexOutOfBoundsException {
		return point[index];
	}

	/** Calculate the average of all x and y values seperated and
	 * return the averages as Point.
	 * @return mid */
	public Point getMid() {
		double x = 0;
		double y = 0;
		for (Point p : point) {
			x += p.x;
			y += p.y;
		}
		return new Point(x / point.length, y / point.length);
	}

}
