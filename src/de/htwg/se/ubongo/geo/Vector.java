package de.htwg.se.ubongo.geo;


/** A Vector contains a x- and y-value and is used for moving geometric objects.
 * @author Patrick Leber
 * @version 24.10.2014 */
public final class Vector {

	protected double x, y;

	public Vector() {
		set(0, 0);
	}

	public Vector(final double x, final double y) {
		set(x, y);
	}
	
	public Vector(final Vector other) {
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

}
