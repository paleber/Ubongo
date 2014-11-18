package de.htwg.se.ubongo.model.geo;

import java.util.ArrayList;
import java.util.List;

/** A Polygon connects several Points.
 * @author Patrick Leber
 * @version 25.10.2014 */
public final class Polygon2D {

    private final List<Point2D> list = new ArrayList<>();

    /** Add a Point.
     * @param x x-value of the point
     * @param y y-value of the point */
    public void addPoint(double x, double y) {
        list.add(new Point2D(x, y));
    }

    /** Rotate around a Point2D.
     * @param angleDeg angle in degree.
     * @param pivot pivot-point */
    public void rotateAround(final double angleDeg, final Point2D pivot) {
        for (Point2D p : list) {
            p.rotateAround(angleDeg, pivot);
        }
    }

    /** Move along a Vector2D.
     * @param v movement */
    public void move(final Vector2D v) {
        for (Point2D p : list) {
            p.move(v);
        }
    }

    /** Get the number of points.
     * @return number of points */
    public int numbPoint() {
        return list.size();
    }

    /** Get a point by index.
     * @param index
     * @return point by index */
    public Point2D getPoint(int index) {
        return list.get(index);
    }

    /** Get the arithmetical mid.
     * @return mid */
    public Point2D getMid() {
        double x = 0;
        double y = 0;
        for (Point2D p : list) {
            x += p.getX();
            y += p.getY();
        }
        return new Point2D(x / list.size(), y / list.size());
    }

    /** Mirror x-values at y-Axis.
     * @param yAxis y-Axis */
    public void mirrorX(int yAxis) {
        for (Point2D p : list) {
            p.mirrorX(yAxis);
        }
    }

    /** Mirror y-values at x-Axis.
     * @param xAxis x-Axis */
    public void mirrorY(int xAxis) {
        for (Point2D p : list) {
            p.mirrorY(xAxis);
        }
    }

    /** Get the Polygon as String.
     * @return polygon as String */
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append('[');
        for (Point2D p : list) {
            builder.append(p);
        }
        builder.append(']');
        builder.append(getMid());
        return builder.toString();
    }
}
