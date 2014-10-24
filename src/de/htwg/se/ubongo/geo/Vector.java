package de.htwg.se.ubongo.geo;

/** TODO
 * @author Patrick Leber
 * @version 24.10.2014 */
public final class Vector {

	protected double x, y;
	
	public Vector(final int x, final int y) {
		set(x, y);
	}
	
	public void set(final double x,final double y) {
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
