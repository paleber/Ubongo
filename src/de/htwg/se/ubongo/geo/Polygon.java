package de.htwg.se.ubongo.geo;

/** A Polygon connects several Points.
 * @author Patrick Leber
 * @version 25.10.2014 */
public class Polygon {

	private final Point[] point;

	public Polygon(final int numbPoint) {
		assert numbPoint >= 3;

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
