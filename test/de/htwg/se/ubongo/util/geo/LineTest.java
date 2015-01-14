package de.htwg.se.ubongo.util.geo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.google.inject.Injector;

import de.htwg.se.ubongo.cfg.UbongoModule;
import de.htwg.se.ubongo.util.geo.imp.Line2D;

public class LineTest {

    private static final Injector INJECTOR = UbongoModule.getInjector();
    
    private static final double DELTA = 1e-9;

    Line2D l = new Line2D();
    Line2D k = new Line2D();

    public LineTest() {
        l.setPoints(INJECTOR.getInstance(IPoint.class), INJECTOR.getInstance(IPoint.class));
        k.setPoints(INJECTOR.getInstance(IPoint.class), INJECTOR.getInstance(IPoint.class));
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
        IPoint start = INJECTOR.getInstance(IPoint.class);
        start.set(2, 3);
        IPoint end = INJECTOR.getInstance(IPoint.class);
        end.set(4, 5);
        l.setPoints(start, end);
        assertTrue(l.getStart().diffsToLessThan(start, DELTA));
        assertTrue(l.getEnd().diffsToLessThan(end, DELTA));
    }

    @Test
    public void testSetPointAngleLength() {
        IPoint start = INJECTOR.getInstance(IPoint.class);
        start.set(0, 0);
        IPoint end = INJECTOR.getInstance(IPoint.class);
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
        IPoint p = INJECTOR.getInstance(IPoint.class);
        p.set(1, 1);
        assertEquals(1, l.distanceTo(p), DELTA);
        p.set(-3, 0);
        assertEquals(3, l.distanceTo(p), DELTA);
        p.set(4, 0);
        assertEquals(2, l.distanceTo(p), DELTA);
    }
    
    @Test
    public void testToString() {
      assertEquals("<Line(0.000|0.000)(2.000|0.000)>", l.toString());
    }

}
