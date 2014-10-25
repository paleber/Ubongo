package de.htwg.se.ubongo.geo;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/** TODO
 * @author Patrick Leber
 * @version 24.10.2014 */
public class PointTest {

	private Point p;
	
	/** TODO
	 * @throws java.lang.Exception */
	@Before
	public void setUp() throws Exception {
		p = new Point(1, 2);
	}

	/** Test method for
	 * {@link de.htwg.se.ubongo.geo.Point#Point(double, double)}. */
	@Test
	public void testPoint() {
		p = new Point(4, 5);
		assertEquals(p.x, 4, 0);
		
		fail("Not yet implemented");
	}

	/** Test method for {@link de.htwg.se.ubongo.geo.Point#set(double, double)}. */
	@Test
	public void testSet() {
		fail("Not yet implemented");
	}

	/** Test method for {@link de.htwg.se.ubongo.geo.Point#getX()}. */
	@Test
	public void testGetX() {
		fail("Not yet implemented");
	}

	/** Test method for {@link de.htwg.se.ubongo.geo.Point#getY()}. */
	@Test
	public void testGetY() {
		fail("Not yet implemented");
	}

	/** Test method for
	 * {@link de.htwg.se.ubongo.geo.Point#move(de.htwg.se.ubongo.geo.Vector)}. */
	@Test
	public void testMove() {
		fail("Not yet implemented");
	}

	/** Test method for
	 * {@link de.htwg.se.ubongo.geo.Point#rotateAround(de.htwg.se.ubongo.geo.Point, double)}
	 * . */
	@Test
	public void testRotateAround() {
		fail("Not yet implemented");
	}

}
