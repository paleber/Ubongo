package de.htwg.se.ubongo.util.geo.imp;

import de.htwg.se.ubongo.util.geo.IPoint;

/** Line. */
public final class Line2D {

    private static final double DELTA = 1e-9;

    private IPoint start;
    private IPoint end;

    private BoundingBox2D bb = new BoundingBox2D();

    /** Get the start-point.
     * @return start point */
    public IPoint getStart() {
        return start;
    }

    /** Get the end-point.
     * @return end point */
    public IPoint getEnd() {
        return end;
    }

    /** Set start and end point.
     * @param start start
     * @param end end */
    public void setStartEnd(final IPoint start, final IPoint end) {
        this.start = start;
        this.end = end;
    }

    /** Set the startpoint, degree and length.
     * @param start start
     * @param degree degree
     * @param length length */
    public void setStartAngleLength(final IPoint start, final double degree,
            final double length) {
        this.start = start;
        Vector2D v = new Vector2D();
        v.setAngleDegree(degree);
        v.setLength(length);
        end = new Point2D();
        end.copy(start);
        end.move(v);
    }

    /** Check if the Line Overlap with a other line.
     * @param other other line
     * @return */
    public boolean overlap(final Line2D other) {
        return calcIntersectionSegment(other) != null;
    }

    /** Calculate the nearest distance to a point.
     * @param point point
     * @return */
    public double distanceTo(final IPoint point) {
        IPoint intersection = calcIntersectionSegment(calcNormal(point));
        if (intersection != null) {
            return point.distanceTo(intersection);
        }
        return Math.min(point.distanceTo(start), point.distanceTo(end));
    }

    private IPoint calcIntersectionStraights(final Line2D other) {
        double a1, b1, c1, a2, b2, c2;

        a1 = end.getY() - start.getY();
        b1 = start.getX() - end.getX();
        c1 = a1 * start.getX() + b1 * start.getY();

        a2 = other.end.getY() - other.start.getY();
        b2 = other.start.getX() - other.end.getX();
        c2 = a2 * other.start.getX() + b2 * other.start.getY();

        double det = a1 * b2 - a2 * b1;
        if (Math.abs(det) < DELTA) {
            return null;
        }

        double x = (b2 * c1 - b1 * c2) / det;
        double y = (a1 * c2 - a2 * c1) / det;

        IPoint intersection = new Point2D();
        intersection.set(x, y);
        return intersection;
    }

    private IPoint calcIntersectionSegment(final Line2D other) {
        IPoint intersection = calcIntersectionStraights(other);
        if (intersection == null) {
            return null;
        }
        if (!checkIntersectionInSegement(intersection)) {
            return null;
        }
        if (!other.checkIntersectionInSegement(intersection)) {
            return null;
        }
        return intersection;
    }

    private boolean checkIntersectionInSegement(final IPoint intersection) {
        double lengthSq = start.distanceSquareTo(end);
        if (lengthSq < intersection.distanceSquareTo(start)) {
            return false;
        }
        if (lengthSq < intersection.distanceSquareTo(end)) {
            return false;
        }
        return true;
    }

    private Line2D calcNormal(final IPoint p) {
        Vector2D v = new Vector2D();
        v.stretchBetween(start, end);
        v.convertToNormal();
        Line2D n = new Line2D();
        n.setStartAngleLength(p, v.getAngleDegree(), 1);
        return n;
    }

    @Override
    public String toString() {
        return "<Line" + start + end + ">";
    }

    /** Update the Bounding-Box. */
    public void updateBoundingBox() {
        bb.update(start, end);
    }

}
