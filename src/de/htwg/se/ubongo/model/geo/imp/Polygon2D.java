package de.htwg.se.ubongo.model.geo.imp;

import de.htwg.se.ubongo.model.geo.GeoFactory;
import de.htwg.se.ubongo.model.geo.IPoint;
import de.htwg.se.ubongo.model.geo.IPolygon;
import de.htwg.se.ubongo.model.geo.IVector;

/** Implementation of IPolygon. */
public final class Polygon2D implements IPolygon {

    private IPoint[] point;

    @Override
    public void setPoints(final IPoint[] point) {
        this.point = point;
    }

    @Override
    public void copy(final IPolygon other) {
        point = new IPoint[other.getNumberPoints()];
        for (int i = 0; i < point.length; i++) {
            point[i] = GeoFactory.createPoint();
            point[i].copy(other.getPoint(i));
        }
    }

    @Override
    public void rotateAround(final double angleDeg, final Point2D pivot) {
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
    public IPoint getMid() {
        double x = 0;
        double y = 0;
        for (IPoint p : point) {
            x += p.getX();
            y += p.getY();
        }
        IPoint mid = GeoFactory.createPoint();
        mid.set(x / point.length, y / point.length);
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
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append('[');
        for (IPoint p : point) {
            builder.append(p);
        }
        builder.append(']');
        builder.append(getMid());
        return builder.toString();
    }

}
