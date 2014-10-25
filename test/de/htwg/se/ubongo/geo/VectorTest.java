package de.htwg.se.ubongo.geo;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/** TODO
 * @author Patrick Leber
 * @version 25.10.2014 */
public class VectorTest {

	private final Vector u = new Vector(3,2);
	
	@Test
	public void testConstructorDefault() {
		Vector v = new Vector();
		assertEquals(0, v.x, 0);
		assertEquals(0, v.y, 0);
	}

	@Test
	public void testConstructorParameter() {
		Vector v = new Vector(4, 5);
		assertEquals(4, v.x, 0);
		assertEquals(5, v.y, 0);
	}
	
	@Test
	public void testConstructorCopy() {
		Vector v = new Vector(u);
		assertEquals(3, v.x, 0);
		assertEquals(2, v.y, 0);
	}
	
	@Test
	public void testSet() {
		Vector v = new Vector();
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
