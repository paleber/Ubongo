package de.htwg.se.ubongo.gameobject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import de.htwg.se.ubongo.geo.Point2D;
import de.htwg.se.ubongo.geo.Vector2D;

/**
 * Test for Block.
 * 
 * @author Patrick Leber
 * @version 01.11.2014
 */
public final class BlockTest {

	private static final double delta = 1e-9;

	@Test
	public void testConstructor() {
		Block block = new Block(new int[] { 0, 1 }, new int[] { 0, 0 });
		assertTrue(block.list.get(1).getMid().distanceTo(new Point2D(1.5, 0.5)) < delta);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testConstructorIllegalArgument() {
		new Block(new int[] {}, new int[] { 0 });
	}

	@Test
	public void testConstructorDepri() {
		Block block = new Block(new byte[][] { { 1, 1, 1 }, { 0, 0, 1 } });
		Point2D mid = block.list.get(3).getMid();
		System.out.println("FF:" + mid);
		assertTrue(mid.distanceSquareTo(new Point2D(2.5, 1.5)) < delta);
	}

	@Test
	public void testGetMid() {
		Block block = new Block(new int[] {}, new int[] {});
		assertTrue(block.getMid().distanceSquareTo(new Point2D(0, 0)) < delta);
		block = new Block(new int[] { 0, 1, 0 }, new int[] { 0, 0, 1 });
		assertTrue(block.getMid().distanceSquareTo(new Point2D(1, 1)) < delta);
	}

	@Test
	public void testToString() {
		Block b = new Block(new int[] { 0, 1 }, new int[] { 0, 0 });
		assertEquals("{(0.500|0.500)(1.500|0.500)}", b.toString());
	}

	@Test
	public void testMirrorX() {
		Block b = new Block(new int[] { 0 }, new int[] { 0 });
		b.mirrorX(0);
		assertTrue(b.list.get(0).getMid()
				.distanceSquareTo(new Point2D(-0.5, 0.5)) < delta);
	}

	@Test
	public void testMirrorY() {
		Block b = new Block(new int[] { 0 }, new int[] { 0 });
		b.mirrorY(0);
		assertTrue(b.list.get(0).getMid()
				.distanceSquareTo(new Point2D(0.5, -0.5)) < delta);
	}

	@Test
	public void rotateLeft() {
		Block b = new Block(new int[] { 0, 1 }, new int[] { 0, 0 });
		b.setMid(0, 0);
		b.rotateLeft();
		assertTrue(b.list.get(0).getMid()
				.distanceSquareTo(new Point2D(0, -0.5)) < delta);
	}

	@Test
	public void rotateRight() {
		Block b = new Block(new int[] { 0, 1 }, new int[] { 0, 0 });
		b.setMid(0, 0);
		b.rotateRight();
		assertTrue(b.list.get(0).getMid().distanceSquareTo(new Point2D(0, 0.5)) < delta);
	}

	@Test
	public void testMove() {
		Block b = new Block(new int[] { 0 }, new int[] { 0 });
		b.move(new Vector2D(1, 1));
		assertTrue(b.list.get(0).getMid().distanceSquareTo(new Point2D(1.5, 1.5)) < delta);
	}

}
