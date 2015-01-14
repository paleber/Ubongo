package de.htwg.se.ubongo.model.gameobject.imp;

import java.util.Iterator;

import com.google.inject.Injector;

import de.htwg.se.ubongo.UbongoModule;
import de.htwg.se.ubongo.model.gameobject.IGameObject;
import de.htwg.se.ubongo.util.geo.IPoint;
import de.htwg.se.ubongo.util.geo.IPolygon;
import de.htwg.se.ubongo.util.geo.IVector;

/** Superclass for Block and Board. */
abstract class AbstractGameObject implements IGameObject {

    /** Guice-Injector. */
    protected static final Injector INJECTOR = Module.getInjector();

    private static final double FACTOR_HALF = 0.5d;
    private IPolygon[] polys;

    @Override
    public final void setPolygons(final IPolygon[] polys) {
        this.polys = polys.clone();
    }

    @Override
    public final int getNumberPolygons() {
        return polys.length;
    }

    @Override
    public final IPolygon getPolygon(final int index) {
        return polys[index];
    }

    @Override
    public final void setMid(final IPoint p) {
        IVector v = INJECTOR.getInstance(IVector.class);
        v.stretchBetween(calcMid(), p);
        for (IPolygon poly : polys) {
            poly.move(v);
        }
    }

    @Override
    public final IPoint calcMid() {
        Bounds b = calcBounds();
        IPoint mid = INJECTOR.getInstance(IPoint.class);
        double x = (b.xMin + b.xMax) * FACTOR_HALF;
        double y = (b.yMin + b.yMax) * FACTOR_HALF;
        mid.set(x, y);
        return mid;
    }

    @Override
    public final double calcWidth() {
        Bounds b = calcBounds();
        return b.xMax - b.xMin;
    }

    @Override
    public final double calcHeight() {
        Bounds b = calcBounds();
        return b.yMax - b.yMin;
    }

    private static final class Bounds {
        private double xMin = Double.POSITIVE_INFINITY;
        private double xMax = Double.NEGATIVE_INFINITY;
        private double yMin = Double.POSITIVE_INFINITY;
        private double yMax = Double.NEGATIVE_INFINITY;
    }

    private Bounds calcBounds() {
        Bounds b = new Bounds();
        for (IPolygon poly : polys) {
            for (IPoint p : poly) {
                b.xMin = Math.min(b.xMin, p.getX());
                b.xMax = Math.max(b.xMax, p.getX());
                b.yMin = Math.min(b.yMin, p.getY());
                b.yMax = Math.max(b.yMax, p.getY());
            }
        }
        return b;
    }

    @Override
    public final Iterator<IPolygon> iterator() {
        return new Iterator<IPolygon>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < polys.length;
            }

            @Override
            public IPolygon next() {
                return polys[index++];
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    @Override
    public final String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append('{');
        for (IPolygon poly : polys) {
            builder.append(poly.calcMid());
        }
        builder.append('}');
        return builder.toString();
    }

}
