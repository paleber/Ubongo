package de.htwg.se.ubongo.geo;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/** TODO
 * @author Patrick Leber
 * @version 24.10.2014 */
public class PointTest {

	private Point p;

	@Before
	public void setUp() throws Exception {
		p = new Point(2, 3);
	}

	@Test
	public void testConstructor() {
		assertEquals(p.x, 2, 0);
		assertEquals(p.y, 3, 0);
	}

	@Test
	public void testSet() {
		p.set(4, 5);
		assertEquals(4, p.x,  0);
		assertEquals(5, p.y,  0);
	}

	@Test
	public void testGetX() {
		assertEquals(2, p.getX(), 0);
	}

	@Test
	public void testGetY() {
		assertEquals(3, p.getY(), 0);
	}

	@Test
	public void testMove() {
		
		p.move(new Vector(4, 5));
		assertTrue(p.equals(new Point(6, 8), 1E-9));
		
		p.move(new Vector(1, 0));
		assertFalse(p.equals(new Point(6, 8), 1E-9));
		
	}

	@Test
	public void testRotateAround() {
		
		p.set(0, 3);
		p.rotateAround(90, new Point(0,0));
		assertTrue(p.equals(new Point(3, 0), 1E-9));
		
		p.set(1, 0);
		p.rotateAround(180, new Point(3,1));
		assertTrue(p.equals(new Point(5, 2), 1E-9));
		
	}
	
	@Test
	public void testEquals() {
		assertTrue(p.equals(new Point(2, 3), 1E-9));
		assertFalse(p.equals(new Point(2, 5), 1E-9));
		assertFalse(p.equals(new Point(5, 3), 1E-9));
	}
	
	@Test
	public void testToString() {
		assertEquals("(2.000000|3.000000)", p.toString());
	}

}
