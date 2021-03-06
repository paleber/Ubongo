package de.htwg.se.ubongo.util.geo;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;

import de.htwg.se.ubongo.util.geo.imp.GeoModule;

/** Tests for IVector with Implementations. */
public class VectorTest {

    private static final Injector INJECTOR = Guice
            .createInjector(new GeoModule());

    private static final double DELTA = 1e-9;

    private IVector v = INJECTOR.getInstance(IVector.class);
    private IVector u = INJECTOR.getInstance(IVector.class);

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
        IPoint p = INJECTOR.getInstance(IPoint.class);
        IPoint q = INJECTOR.getInstance(IPoint.class);
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

    @Test
    public void testSetAngle() {
        IVector v = INJECTOR.getInstance(IVector.class);
        v.setAngleDegree(0);
        u.set(1, 0);
        testNearlyEquals(u, v);
        v.setAngleDegree(90);
        u.set(0, 1);
        testNearlyEquals(u, v);
    }

    @Test
    public void testGetAngle() {
        IVector v = INJECTOR.getInstance(IVector.class);
        v.set(1, 0);
        assertEquals(0, v.getAngleDegree(), DELTA);
        v.set(1, -1);
        assertEquals(45, v.getAngleDegree(), DELTA);
        v.set(0, 1);
        assertEquals(270, v.getAngleDegree(), DELTA);
    }

    @Test
    public void testConvertToNormal() {
        IVector v = INJECTOR.getInstance(IVector.class);
        v.set(1, 1);
        v.convertToNormal();
        u.set(-1, 1);
        testNearlyEquals(u, v);
        for (int i = 0; i < 4; i++) {
            v.convertToNormal();
        }
        testNearlyEquals(u, v);
    }

    @Test
    public void testSetLength() {
        IVector v = INJECTOR.getInstance(IVector.class);
        v.setAngleDegree(0);
        v.setLength(2.5);
        u.set(2.5, 0);
        testNearlyEquals(u, v);
    }
}
