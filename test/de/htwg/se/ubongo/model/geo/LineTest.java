package de.htwg.se.ubongo.model.geo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import de.htwg.se.ubongo.model.geo.imp.Line2D;
import de.htwg.se.ubongo.model.geo.imp.Point2D;

public class LineTest {

    private static final double DELTA = 1e-9;

    Line2D l = new Line2D();
    Line2D k = new Line2D();

    public LineTest() {
        l.setStartEnd(new Point2D(), new Point2D());
        k.setStartEnd(new Point2D(), new Point2D());
    }

    @Before
    public void setUp() {
        l.getStart().set(0, 0);
        l.getEnd().set(2, 0);
        k.getStart().set(1, 1);
        k.getEnd().set(1, -1);
    }

    @Test
    public void testGetterSetter() {
        Point2D start = new Point2D();
        start.set(2, 3);
        Point2D end = new Point2D();
        end.set(4, 5);
        l.setStartEnd(start, end);
        assertTrue(l.getStart().diffsToLessThan(start, DELTA));
        assertTrue(l.getEnd().diffsToLessThan(end, DELTA));
    }

    @Test
    public void testSetPointAngleLength() {
        IPoint start = new Point2D();
        start.set(0, 0);
        IPoint end = new Point2D();
        end.set(Math.sin(Math.toRadians(45)) * 3,
                Math.cos(Math.toRadians(45)) * 3);
        l.setStartAngleLength(start, 45, 3);
        assertTrue(l.getEnd().diffsToLessThan(end, DELTA));
    }

    @Test
    public void testOverlap() {
        assertTrue(l.overlap(k));
        l.getEnd().set(-1, 0);
        assertFalse(l.overlap(k));
        l.getEnd().set(2, 0);
        k.getEnd().copy(k.getStart());
        k.getStart().set(1, 2);
        assertFalse(l.overlap(k));
    }

    @Test
    public void testOverlapParallel() {
        l.getStart().set(0, 0);
        l.getEnd().set(1, 0);
        k.getStart().set(0, 1);
        k.getEnd().set(1, 1);
        assertFalse(l.overlap(k));
    }

    @Test
    public void testGetDistance() {
        IPoint p = new Point2D();
        p.set(1, 1);
        assertEquals(1, l.distanceTo(p), DELTA);
        p.set(-3, 0);
        assertEquals(3, l.distanceTo(p), DELTA);
        p.set(4, 0);
        assertEquals(2, l.distanceTo(p), DELTA);

    }

}
