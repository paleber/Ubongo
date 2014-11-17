package de.htwg.se.ubongo.gameobject;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import de.htwg.se.ubongo.geo.Point2D;

/** Tests for Board.
 * @author Patrick Leber
 * @version 17.11.2014 */
public final class BoardTest {

    private static final List<Integer> LIST_BLOCK = new ArrayList<>();
    static {
        int[] coords = { 0, 0, 1, 0 };
        for (Integer i : coords) {
            LIST_BLOCK.add(i);
        }
    }

    private static final List<Integer> LIST_FIELD = new ArrayList<>();
    static {
        int[] coords = { 0, 0, 1, 0, 1, 0, 1, 1 };
        for (Integer i : coords) {
            LIST_FIELD.add(i);
        }
    }

    @Test
    public void testConstructor() {
        Board b = new Board(LIST_BLOCK);
        assertTrue(b.getPoly(1).getMid()
                .distanceSquareTo(new Point2D(1.5, 0.5)) < 1e-9);
    }

    @Test
    public void testAddBlock() {

    }

    @Test
    public void testRemoveBlock() {

    }
}
