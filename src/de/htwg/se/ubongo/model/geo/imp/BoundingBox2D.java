package de.htwg.se.ubongo.model.geo.imp;

import de.htwg.se.ubongo.model.geo.IPoint;

public final class BoundingBox2D {

    private double xMin, xMax, yMin, yMax;

    /** Update the bounding box.
     * @param points points */
    public void update(IPoint... points) {
        xMin = points[0].getX();
        xMax = points[0].getX();
        yMin = points[0].getY();
        yMax = points[0].getY();
        for (int i = 1; i < points.length; i++) {
            xMin = Math.min(xMin, points[i].getX());
            xMax = Math.max(xMax, points[i].getX());
            yMin = Math.min(yMin, points[i].getY());
            yMax = Math.max(yMax, points[i].getY());
        }
    }

    /** Check if this BoundingBox overlap with other BoundingBox
     * @param other other BoundingBox
     * @return true when overlap, otherwise false */
    public boolean overlapWith(final BoundingBox2D other) {
        if (xMin > other.xMax || xMax < other.xMin) {
            return false;
        }
        if (yMin > other.yMax || yMax < other.yMin) {
            return false;
        }
        return true;
    }

    /** Check if the BoundingBox contains a Point.
     * @param p Point
     * @return true when contains, otherwise false */
    public boolean contains(final IPoint p) {
        if (xMin > p.getX() || xMax < p.getX()) {
            return false;
        }
        if (yMin > p.getY() || yMax < p.getY()) {
            return false;
        }
        return true;
    }

}
