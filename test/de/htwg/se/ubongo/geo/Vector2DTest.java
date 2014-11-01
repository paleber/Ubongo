package de.htwg.se.ubongo.geo;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/** TODO
 * @author Patrick Leber
 * @version 25.10.2014 */
public class Vector2DTest {

	private final Vector2D u = new Vector2D(3,2);
	
	@Test
	public void testConstructorDefault() {
		Vector2D v = new Vector2D();
		assertEquals(0, v.x, 0);
		assertEquals(0, v.y, 0);
	}

	@Test
	public void testConstructorParameter() {
		Vector2D v = new Vector2D(4, 5);
		assertEquals(4, v.x, 0);
		assertEquals(5, v.y, 0);
	}
	
	@Test
	public void testConstructorCopy() {
		Vector2D v = new Vector2D(u);
		assertEquals(3, v.x, 0);
		assertEquals(2, v.y, 0);
	}
	
	@Test
	public void testSet() {
		Vector2D v = new Vector2D();
		v.set(4, 5);
		assertEquals(4, v.x, 0);
		assertEquals(5, v.y, 0);
	}

	@Test
	public void testGetX() {
		assertEquals(3, u.getX(), 0);
	}

	@Test
	public void testGetY() {
		assertEquals(2, u.getY(), 0);
	}

}
