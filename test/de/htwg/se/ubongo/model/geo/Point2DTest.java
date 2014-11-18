package de.htwg.se.ubongo.model.geo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import de.htwg.se.ubongo.model.geo.Point2D;
import de.htwg.se.ubongo.model.geo.Vector2D;

import org.junit.Test;

/** TODO
 * @author Patrick Leber
 * @version 24.10.2014 */
public class Point2DTest {

	private final Point2D q = new Point2D(2, 3);
	private final double delta = 1e-9;

	@Test
	public void testConstructorDefault() {
		Point2D p = new Point2D();
		assertEquals(0, p.getX(), 0);
		assertEquals(0, p.getY(), 0);
	}

	@Test
	public void testConstructorParameter() {
		Point2D p = new Point2D(2, 3);
		assertEquals(p.getX(), 2, 0);
		assertEquals(p.getY(), 3, 0);
	}
	
	@Test
	public void testConstructorCopy() {
		Point2D p = new Point2D(q);
		assertEquals(p.getX(), 2, 0);
		assertEquals(p.getY(), 3, 0);
	}

	@Test
	public void testSet() {
		Point2D p = new Point2D();
		p.set(4, 5);
		assertEquals(4, p.getX(), 0);
		assertEquals(5, p.getY(), 0);
	} 

	@Test
	public void testGetX() {
		assertEquals(2, q.getX(), 0);
	}

	@Test
	public void testGetY() {
		assertEquals(3, q.getY(), 0);
	}

	@Test
	public void testMove() {
		
		Point2D p = new Point2D(q);
		
		p.move(new Vector2D(4, 5));
		assertTrue(p.distanceSquareTo(new Point2D(6, 8)) < 1E-9);

		p.move(new Vector2D(1, 0));
		assertFalse(p.distanceSquareTo(new Point2D(6, 8)) < 1E-9);

	}

	@Test
	public void testRotateAround() {

		Point2D p = new Point2D();
		
		p.set(0, 3);
		p.rotateAround(90, new Point2D(0, 0));
		assertTrue(p.distanceSquareTo(new Point2D(-3, 0)) < 1E-9);

		p.set(1, 0);
		p.rotateAround(180, new Point2D(3, 1));
		assertTrue(p.distanceSquareTo(new Point2D(5, 2)) < 1E-9);

	}
	
	@Test
	public void testDistanceTo() {
		assertEquals(5, q.distanceTo(new Point2D(6, 6)), delta);
	}
	
	@Test
	public void testDistanceSqaureTo() {
		assertEquals(25, q.distanceSquareTo(new Point2D(6, 6)), delta);
	}
	
	@Test
	public void testToString() {
		assertEquals("(2.000|3.000)", q.toString());
	}

	
	@Test
	public void testMirrorX() {
	    Point2D p = new Point2D(1, 0);
	    p.mirrorX(-1);
	    assertEquals(-3, p.getX(), delta);
	}
	
	@Test
    public void testMirrorY() {
        Point2D p = new Point2D(0, 1);
        p.mirrorY(3);
        assertEquals(5, p.getY(), delta);
    } 
	
	
}
