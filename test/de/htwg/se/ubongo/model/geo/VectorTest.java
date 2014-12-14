package de.htwg.se.ubongo.model.geo;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/** Tests for IVector with Implementations. */
public class VectorTest {

    private static final double DELTA = 1e-9;

    private IVector v = GeoModule.createVector();
    private IVector u = GeoModule.createVector();

    @Before
    public void setup() {
        v.set(3, 2);
        u.set(0, 0);
    }

    @Test
    public void testGetterSetter() {
        v.set(4, 5);
        assertEquals(4, v.getX(), DELTA);
        assertEquals(5, v.getY(), DELTA);
    }

    @Test
    public void testCopy() {
        u.set(7, 8);
        v.copy(u);
        testNearlyEquals(u, v);
    }

    @Test
    public void testStretchBetween() {
        IPoint p = GeoModule.createPoint();
        IPoint q = GeoModule.createPoint();
        p.set(1, 1);
        q.set(2, 3);
        v.stretchBetween(p, q);
        u.set(1, 2);
        testNearlyEquals(u, v);
    }

    @Test
    public void testSwap() {
        v.swap();
        u.set(-3, -2);
        testNearlyEquals(u, v);
    }

    @Test
    public void testToString() {
        assertEquals("<3.000|2.000>", v.toString());
    }

    public static void testNearlyEquals(IVector v, IVector u) {
        assertEquals(v.getX(), u.getX(), DELTA);
        assertEquals(v.getY(), u.getY(), DELTA);
    }
}
