package de.htwg.se.ubongo.util.geo;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.google.inject.Injector;

import de.htwg.se.ubongo.cfg.UbongoModule;
import de.htwg.se.ubongo.util.geo.imp.BoundingBox2D;

public final class BoundingBoxTest {

    private static final Injector INJECTOR = UbongoModule.getInjector();
    
    private BoundingBox2D bb = new BoundingBox2D();

    @Test
    public void testOverlapWith() {
        BoundingBox2D cc = new BoundingBox2D();

        IPoint b1 = INJECTOR.getInstance(IPoint.class);
        IPoint b2 = INJECTOR.getInstance(IPoint.class);
        b1.set(0.1, 0.1);
        b2.set(1, 1);
        bb.update(b1, b2);

        IPoint c1 = INJECTOR.getInstance(IPoint.class);
        IPoint c2 = INJECTOR.getInstance(IPoint.class);
        c1.set(0, 0);
        c2.set(1, 1);
        cc.update(c1, c2);
        assertTrue(bb.overlapWith(cc));

        c2.set(1, -1);
        cc.update(c1, c2);
        assertFalse(bb.overlapWith(cc));

        c2.set(-1, 1);
        cc.update(c1, c2);
        assertFalse(bb.overlapWith(cc));

        c1.set(1.1, 1.1);
        c2.set(2, -2);
        cc.update(c1, c2);
        assertFalse(bb.overlapWith(cc));

        c2.set(-2, 2);
        cc.update(c1, c2);
        assertFalse(bb.overlapWith(cc));
    }
    
    @Test
    public void testContains() {
        IPoint b1 = INJECTOR.getInstance(IPoint.class);
        IPoint b2 = INJECTOR.getInstance(IPoint.class);
        b1.set(0, 0);
        b2.set(1, 1);
        bb.update(b1, b2);

        IPoint p = INJECTOR.getInstance(IPoint.class);
        p.set(0.5, 0.5);
        assertTrue(bb.contains(p));
        p.set(0.5, -0.5);
        assertFalse(bb.contains(p));
        p.set(0.5, 1.5);
        assertFalse(bb.contains(p));
        p.set(-0.5, 0.5);
        assertFalse(bb.contains(p));
        p.set(1.5, 0.5);
        assertFalse(bb.contains(p));  
    }

}
