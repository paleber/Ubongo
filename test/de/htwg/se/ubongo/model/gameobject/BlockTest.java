package de.htwg.se.ubongo.model.gameobject;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;

import de.htwg.se.ubongo.cfg.UbongoModule;
import de.htwg.se.ubongo.util.geo.IPoint;
import de.htwg.se.ubongo.util.geo.IPolygon;
import de.htwg.se.ubongo.util.geo.IVector;

public final class BlockTest {

    private static final Injector INJECTOR = Guice.createInjector(new UbongoModule());

    private static final double DELTA = 1e-9;
    private final IBlock go = INJECTOR.getInstance(IBlock.class);

    public BlockTest() {
        IPoint[] p = new IPoint[3];
        for (int i = 0; i < p.length; i++) {
            p[i] = INJECTOR.getInstance(IPoint.class);
        }
        IPolygon[] poly = new IPolygon[1];
        poly[0] = INJECTOR.getInstance(IPolygon.class);
        poly[0].setPoints(p);
        go.setPolygons(poly);
    }

    @Before
    public void setUp() {
        go.getPolygon(0).getPoint(0).set(0, 0);
        go.getPolygon(0).getPoint(1).set(1, 1);
        go.getPolygon(0).getPoint(2).set(0, 1);
    }

    @Test
    public void testMirrorVertical() {
        IPoint p = INJECTOR.getInstance(IPoint.class);
        p.set(1, 0);
        go.mirrorVertical();
    }

    @Test
    public void testMirrorHorizontal() {
        IPoint p = INJECTOR.getInstance(IPoint.class);
        p.set(0, 1);
        go.mirrorHorizontal();
    }

    @Test
    public void rotateLeft() {
        IPoint p = INJECTOR.getInstance(IPoint.class);
        p.set(0, 1);
        go.rotateLeft();
    }

    @Test
    public void rotateRight() {
        IPoint p = INJECTOR.getInstance(IPoint.class);
        p.set(1, 0);
        go.rotateRight();
    }

    @Test
    public void testMove() {
        IVector v = INJECTOR.getInstance(IVector.class);
        v.set(3, 4);
        IPoint p = INJECTOR.getInstance(IPoint.class);
        p.set(3, 4);
        go.move(v);
        assertTrue(p.diffsToLessThan(go.getPolygon(0).getPoint(0), DELTA));
    }

    @Test
    public void testAnchoredMids() {
        IVector v = INJECTOR.getInstance(IVector.class);
        v.set(-0.24, -0.24);
        go.move(v);
        IPoint p = INJECTOR.getInstance(IPoint.class);
        p.set(0.5, 0.5);
        assertTrue(p.diffsToLessThan(go.calcAnchoredMids()[0], DELTA));

        v.set(0.48, 0.48);
        go.move(v);

        assertTrue(p.diffsToLessThan(go.calcAnchoredMids()[0], DELTA));

        v.set(0.03, 0.03);
        go.move(v);
        assertFalse(p.diffsToLessThan(go.calcAnchoredMids()[0], DELTA));
    }

}
