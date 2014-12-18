package de.htwg.se.ubongo.model.geo.imp;

import de.htwg.se.ubongo.model.geo.IPoint;

public final class Line2D {

    private static final double DELTA = 1e-9;

    private IPoint start;
    private IPoint end;

    private BoundingBox2D bb = new BoundingBox2D();

    public IPoint getStart() {
        return start;
    }

    public IPoint getEnd() {
        return end;
    }

    public void setStartEnd(final IPoint start, final IPoint end) {
        this.start = start;
        this.end = end;
    }

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

    public boolean overlap(Line2D other) {
        return calcIntersectionSegment(other) != null;
    }

    public double distanceTo(IPoint p) {
        IPoint intersection = calcIntersectionSegment(calcNormal(p));
        if (intersection != null) {
            return p.distanceTo(intersection);
        }
        return Math.min(p.distanceTo(start), p.distanceTo(end));
    }

    private IPoint calcIntersectionStraights(Line2D other) {
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

    private IPoint calcIntersectionSegment(Line2D other) {
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

    private Line2D calcNormal(IPoint p) {
        Vector2D v = new Vector2D();
        v.stretchBetween(start, end);
        v.convertToNormal();
        Line2D n = new Line2D();
        n.setStartAngleLength(p, v.getAngleDegree(), 1);
        return n;
    }

    public String toString() {
        return "<Line" + start + end + ">";
    }

    public void updateBoundingBox() {
        bb.update(start, end);
    }

}
