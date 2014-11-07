package de.htwg.se.ubongo.gameobject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import de.htwg.se.ubongo.geo.Point2D;

/** Tests for BasicBlock.
 * @author Patrick Leber
 * @version 07.11.2014 */
public class BasicBlockTest {

    private static final double DELTA = 1e-9;

    @Test
    public void testConstructor() {
        BasicBlock block = new Block(new int[] { 0, 1 }, new int[] { 0, 0 });
        assertTrue(block.list.get(1).getMid().distanceTo(new Point2D(1.5, 0.5)) < DELTA);
        assertEquals(2, block.list.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorIllegalArgument() {
        new Block(new int[] {}, new int[] { 0 });
    }

    @Test
    public void testGetMid() {
        BasicBlock block = new Block(new int[] {}, new int[] {});
        assertTrue(block.getMid().distanceSquareTo(new Point2D(0, 0)) < DELTA);
        block = new Block(new int[] { 0, 1, 0 }, new int[] { 0, 0, 1 });
        assertTrue(block.getMid().distanceSquareTo(new Point2D(1, 1)) < DELTA);
    }

    @Test
    public void testToString() {
        BasicBlock b = new Block(new int[] { 0, 1 }, new int[] { 0, 0 });
        System.out.println(b.toString());
        assertEquals("{(0.500|0.500)(1.500|0.500)}", b.toString());
    }

}
