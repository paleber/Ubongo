package test.java.de.htwg.se.ubongo.geo;

import static org.junit.Assert.assertEquals;
import main.java.de.htwg.se.ubongo.geo.Point2D;
import main.java.de.htwg.se.ubongo.geo.Vector2D;

import org.junit.Test;

/** Vector Tests.
 * @author Patrick Leber
 * @version 25.10.2014 */
public class Vector2DTest {

	private final Vector2D u = new Vector2D(3,2);
	
	@Test
	public void testConstructorDefault() {
		Vector2D v = new Vector2D();
		assertEquals(0, v.getX(), 0);
		assertEquals(0, v.getY(), 0);
	}

	@Test
	public void testConstructorParameter() {
		Vector2D v = new Vector2D(4, 5);
		assertEquals(4, v.getX(), 0);
		assertEquals(5, v.getY(), 0);
	}
	
	@Test
	public void testConstructor2Point() {
	    Vector2D v = new Vector2D(new Point2D(- 1, 1), new Point2D(1, 2));
	    assertEquals(2, v.getX(), 0);
        assertEquals(1, v.getY(), 0);
	}
	
	@Test
	public void testConstructorCopy() {
		Vector2D v = new Vector2D(u);
		assertEquals(3, v.getX(), 0);
		assertEquals(2, v.getY(), 0);
	}
	
	@Test
	public void testSet() {
		Vector2D v = new Vector2D();
		v.set(4, 5);
		assertEquals(4, v.getX(), 0);
		assertEquals(5, v.getY(), 0);
	}

	@Test
	public void testGetX() {
		assertEquals(3, u.getX(), 0);
	}

	@Test
	public void testGetY() {
		assertEquals(2, u.getY(), 0);
	}
	
	@Test
    public void testToString() {
        assertEquals("<3.000|2.000>", u.toString());
    }

}
