package de.htwg.se.ubongo.geo;

/** A Vector2D contains a x- and y-value and is used for moving geometric objects.
 * @author Patrick Leber
 * @version 24.10.2014 */
public final class Vector2D {

	protected double x, y;

	public Vector2D() {
		set(0, 0);
	}

	public Vector2D(final double x, final double y) {
		set(x, y);
	}
	
	public Vector2D(final Vector2D other) {
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
