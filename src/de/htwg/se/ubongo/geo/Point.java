package de.htwg.se.ubongo.geo;

import java.util.Locale;

/** A Point contains a x- and y-value. It can be moved along a Vector and
 * rotated around a other Point.
 * @author Patrick Leber
 * @version 24.10.2014 */
public final class Point {

	protected double x, y;

	public Point() {
		set(0, 0);
	}

	public Point(final double x, final double y) {
		set(x, y);
	}
	
	public Point(final Point other) {
		x = other.x;
		y = other.y;
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

	public void rotateAround(final double angleDeg, final Point pivot) {

		double angleRad = Math.toRadians(angleDeg);
		double sin = Math.sin(angleRad);
		double cos = Math.cos(angleRad);

		x -= pivot.x;
		y -= pivot.y;

		double xn = x * cos + y * sin;
		double yn = -x * sin + y * cos;

		x = xn + pivot.x;
		y = yn + pivot.y;

	}

	public boolean equals(Point other, double tolerance) {

		if (Math.abs(other.x - x) > tolerance) {
			return false;
		}

		if (Math.abs(other.y - y) > tolerance) {
			return false;
		}

		return true;

	}

	public String toString() {
		return String.format(Locale.ENGLISH, "(%.6f|%.6f)", x, y);
	}
}
