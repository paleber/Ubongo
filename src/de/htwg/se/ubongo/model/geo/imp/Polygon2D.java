package de.htwg.se.ubongo.model.geo.imp;

import java.util.Iterator;

import com.google.inject.Guice;
import com.google.inject.Injector;

import de.htwg.se.ubongo.model.geo.GeoModule;
import de.htwg.se.ubongo.model.geo.IPoint;
import de.htwg.se.ubongo.model.geo.IPolygon;
import de.htwg.se.ubongo.model.geo.IVector;

/** Implementation of IPolygon. */
public final class Polygon2D implements IPolygon {

    private static final double FACTOR_HALF = 0.5;
    private IPoint[] point;

    @Override
    public void setPoints(final IPoint[] point) {
        this.point = point;
    }

    @Override
    public void copy(final IPolygon other) {
        point = new IPoint[other.getNumberPoints()];
        for (int i = 0; i < point.length; i++) {
            point[i] = GeoModule.createPoint();
            point[i].copy(other.getPoint(i));
        }
    }

    @Override
    public void rotateAround(final double angleDeg, final IPoint pivot) {
        for (IPoint p : point) {
            p.rotateAround(angleDeg, pivot);
        }
    }

    @Override
    public void move(final IVector v) {
        for (IPoint p : point) {
            p.move(v);
        }
    }

    @Override
    public int getNumberPoints() {
        return point.length;
    }

    @Override
    public IPoint getPoint(final int index) {
        return point[index];
    }

    @Override
    public IPoint calcMid() {
        double xMin = Double.POSITIVE_INFINITY;
        double xMax = Double.NEGATIVE_INFINITY;
        double yMin = Double.POSITIVE_INFINITY;
        double yMax = Double.NEGATIVE_INFINITY;

        for (IPoint p : point) {
            xMin = Math.min(xMin, p.getX());
            xMax = Math.max(xMax, p.getX());
            yMin = Math.min(yMin, p.getY());
            yMax = Math.max(yMax, p.getY());
        }

        double x = (xMin + xMax) * FACTOR_HALF;
        double y = (yMin + yMax) * FACTOR_HALF;

        IPoint mid = GeoModule.createPoint();
        mid.set(x, y);
        return mid;
    }

    @Override
    public void mirrorVertical(final double yAxis) {
        for (IPoint p : point) {
            p.mirrorVertical(yAxis);
        }
    }

    @Override
    public void mirrorHorizontal(final double xAxis) {
        for (IPoint p : point) {
            p.mirrorHorizontal(xAxis);
        }
    }

    @Override
    public Iterator<IPoint> iterator() {
        return new Iterator<IPoint>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < point.length;
            }

            @Override
            public IPoint next() {
                return point[index++];
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append('[');
        for (IPoint p : point) {
            builder.append(p);
        }
        builder.append(']');
        builder.append(calcMid());
        return builder.toString();
    }

}
