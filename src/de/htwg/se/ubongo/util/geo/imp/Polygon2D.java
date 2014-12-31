package de.htwg.se.ubongo.util.geo.imp;

import java.awt.Graphics;
import java.util.Iterator;

import de.htwg.se.ubongo.util.geo.ILine;
import de.htwg.se.ubongo.util.geo.IPoint;
import de.htwg.se.ubongo.util.geo.IPolygon;
import de.htwg.se.ubongo.util.geo.IVector;

/** Implementation of IPolygon. */
public final class Polygon2D implements IPolygon {

    private static final double FACTOR_HALF = 0.5;
    private static final double DELTA = 1e-9;
    private static final double TEST_LINE_LENGTH = 1e9;
    private IPoint[] point;
    private Line2D[] edges;

    @Override
    public void setPoints(final IPoint[] point) {
        this.point = point.clone();
        initEdges();
    }

    @Override
    public void copy(final IPolygon other) {
        point = new IPoint[other.getNumberPoints()];
        for (int i = 0; i < point.length; i++) {
            point[i] = new Point2D();
            point[i].copy(other.getPoint(i));
        }
        initEdges();
    }

    private void initEdges() {
        edges = new Line2D[point.length];
        for (int i = 0; i < edges.length; i++) {
            edges[i] = new Line2D();
            edges[i].setPoints(point[i], point[(i + 1) % edges.length]);
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

        IPoint mid = new Point2D();
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

    private static final double[] TEST_ANGLES = { 0, 120, 240 };

    @Override
    public boolean contains(final IPoint p) {
        for (Line2D edge : edges) {
            edge.updateBoundingBox();
            if (edge.distanceTo(p) < DELTA) {
                return true;
            }
        }

        double min = Double.POSITIVE_INFINITY;

        Line2D[] testLines = new Line2D[TEST_ANGLES.length];
        for (int i = 0; i < testLines.length; i++) {
            testLines[i] = new Line2D();
            testLines[i].setStartAngleLength(p, TEST_ANGLES[i],
                    TEST_LINE_LENGTH);
        }

        for (Line2D testLine : testLines) {
            int overlaps = 0;
            for (Line2D egde : edges) {
                if (testLine.overlap(egde)) {
                    overlaps++;
                }
            }
            min = Math.min(min, overlaps);
        }

        return min % 2 == 1;
    }

    @Override
    public ILine[] getEdges() {
        return edges;
    }

    @Override
    public void paint(Graphics g, double scale, double xOffset, double yOffset) {
        int[] x = new int[getNumberPoints()];
        int[] y = new int[getNumberPoints()];
        for (int i = 0; i < getNumberPoints(); i++) {
            x[i] = (int) (getPoint(i).getX() * scale + xOffset + FACTOR_HALF);
            y[i] = (int) (getPoint(i).getY() * scale + yOffset + FACTOR_HALF);
        }
        g.fillPolygon(x, y, getNumberPoints());

    }

}
