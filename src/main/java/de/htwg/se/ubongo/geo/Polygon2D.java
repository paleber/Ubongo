package main.java.de.htwg.se.ubongo.geo;

import java.util.ArrayList;
import java.util.List;

/** A Polygon connects several Points.
 * @author Patrick Leber
 * @version 25.10.2014 */
public final class Polygon2D {

    protected final List<Point2D> list = new ArrayList<>();

    public void addPoint(double x, double y) {
        list.add(new Point2D(x, y));
    }

    public void rotateAround(final double angleDeg, final Point2D pivot) {
        for (Point2D p : list) {
            p.rotateAround(angleDeg, pivot);
        }
    }

    public void move(final Vector2D v) {
        for (Point2D p : list) {
            p.move(v);
        }
    }

    public int numbPoint() {
        return list.size();
    }

    public Point2D getPoint(int index) {
        return list.get(index);
    }

    public Point2D getMid() {
        double x = 0;
        double y = 0;
        for (Point2D p : list) {
            x += p.x;
            y += p.y;
        }
        return new Point2D(x / list.size(), y / list.size());
    }

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

    public void mirrorX(int yAxis) {
        for (Point2D p : list) {
            p.mirrorX(yAxis);
        }
    }

    public void mirrorY(int xAxis) {
        for (Point2D p : list) {
            p.mirrorY(xAxis);
        }
    }

}
