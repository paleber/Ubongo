package de.htwg.se.ubongo;

import java.awt.Point;
import java.awt.geom.Point2D;

/** Cell with x- and y-Position
 * @author Patrick Leber
 * @version 23.10.2014 */
public class Cell {

	// Position
	private int x, y;

	/** Constructor with Position.
	 * @param x x-Position.
	 * @param y y-Position */
	public Cell(final int x, final int y) {
		setPos(x, y);
	}

	/** Set the Position.
	 * @param x x-Position
	 * @param y y-Position */
	public void setPos(final int x, final int y) {
		this.x = x;
		this.y = y;
	}

	/** Get the x-Position.
	 * @return x-Position*/
	public int getPosX() {
		return x;
	}

	/** Get the y-Position.
	 * @return y-Position*/
	public int getPosY() {
		return y;
	}

	public void mirrorAxisX(final int y) {
		
	}
	
	public void mirrorAxisY(final int x) {
		
	}
	
	public void rotateAround(Point p) {
		
	}
	
}
