package de.htwg.se.ubongo.geo;

/** A Point contains a x- and y-value. It can be moved along a Vector and
 * rotated around a other Point.
 * @author Patrick Leber
 * @version 24.10.2014 */
public final class Point {

	protected double x, y;

	public Point(final double x, final double y) {
		set(x, y);
	}

	public void set(final double x, final double y) {
		this.x = x;
		this.y = y;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public void move(Vector v) {
		x += v.x;
		y += v.y;
	}

	public void rotateAround(final Point other, final double angleDeg) {
		// TODO
	}

}
