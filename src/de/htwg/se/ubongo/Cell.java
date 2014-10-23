package de.htwg.se.ubongo;

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
	public int getXPos() {
		return x;
	}

	/** Get the y-Position.
	 * @return y-Position*/
	public int getYPos() {
		return y;
	}

}
