package test.java.de.htwg.se.ubongo.gameobject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import main.java.de.htwg.se.ubongo.gameobject.BasicBlock;
import main.java.de.htwg.se.ubongo.gameobject.Block;
import main.java.de.htwg.se.ubongo.geo.Point2D;

import org.junit.Test;

/** Tests for BasicBlock.
 * @author Patrick Leber
 * @version 07.11.2014 */
public class BasicBlockTest {

    private static final double DELTA = 1e-9;

    private static final List<Integer> LIST_T_BLOCK = new ArrayList<>();
    static {
        int[] coords = { 0, 0, 1, 0, 2, 0, 1, 1 };
        for (Integer i : coords) {
            LIST_T_BLOCK.add(i);
        }
    }
    private static final BasicBlock T_BLOCK = new Block(LIST_T_BLOCK);

    @Test
    public void testConstructor() {
        BasicBlock block = new Block(LIST_T_BLOCK);
       // assertTrue(block.list.get(1).getMid().distanceTo(new Point2D(1.5, 0.5)) < DELTA);
       // assertEquals(4, block.list.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorIllegalArgument() {
        List<Integer> list = new LinkedList<>();
        list.add(0);
        new Block(list);
    }

    @Test
    public void testGetMid() {
        BasicBlock block = new Block(new LinkedList<Integer>());
        assertTrue(block.getMid().distanceSquareTo(new Point2D(0, 0)) < DELTA);
        assertTrue(T_BLOCK.getMid().distanceSquareTo(new Point2D(1.5, 1)) < DELTA);
    }

    @Test
    public void testToString() {
        assertEquals("{(0.500|0.500)(1.500|0.500)(2.500|0.500)(1.500|1.500)}",
                T_BLOCK.toString());
    }

}
