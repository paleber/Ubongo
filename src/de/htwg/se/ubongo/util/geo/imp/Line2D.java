package de.htwg.se.ubongo.util.geo.imp;

import java.awt.Graphics;

import de.htwg.se.ubongo.util.geo.ILine;
import de.htwg.se.ubongo.util.geo.IPoint;

/** Implementation of ILine. */
public final class Line2D implements ILine {

    private static final double DELTA = 1e-9;

    private static final double FACTOR_HALF = 0.5;

    private IPoint start;
    private IPoint end;

    private BoundingBox2D bb = new BoundingBox2D();

    @Override
    public IPoint getStart() {
        return start;
    }

    @Override
    public IPoint getEnd() {
        return end;
    }

    @Override
    public void setPoints(final IPoint start, final IPoint end) {
        this.start = start;
        this.end = end;
    }

    @Override
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

    @Override
    public double distanceTo(final IPoint point) {
        IPoint intersection = calcIntersectionSegment(calcNormal(point));
        if (intersection != null) {
            return point.distanceTo(intersection);
        }
        return Math.min(point.distanceTo(start), point.distanceTo(end));
    }

    @Override
    public void updateBoundingBox() {
        bb.update(start, end);
    }

    @Override
    public boolean overlap(final ILine other) {
        return calcIntersectionSegment(other) != null;
    }

    private IPoint calcIntersectionStraights(final ILine other) {
        double a1, b1, c1, a2, b2, c2;

        a1 = end.getY() - start.getY();
        b1 = start.getX() - end.getX();
        c1 = a1 * start.getX() + b1 * start.getY();

        a2 = other.getEnd().getY() - other.getStart().getY();
        b2 = other.getStart().getX() - other.getEnd().getX();
        c2 = a2 * other.getStart().getX() + b2 * other.getStart().getY();

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

    private IPoint calcIntersectionSegment(final ILine other) {
        IPoint intersection = calcIntersectionStraights(other);
        if (intersection == null) {
            return null;
        }
        if (!checkIntersectionInSegement(this, intersection)) {
            return null;
        }
        if (!checkIntersectionInSegement(other, intersection)) {
            return null;
        }
        return intersection;
    }

    private static boolean checkIntersectionInSegement(final ILine line,
            final IPoint intersection) {
        double lengthSq = line.getStart().distanceSquareTo(line.getEnd());
        if (lengthSq < intersection.distanceSquareTo(line.getStart())) {
            return false;
        }
        if (lengthSq < intersection.distanceSquareTo(line.getEnd())) {
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

    @Override
    public boolean nearlyEquals(final ILine other, final double tolerance) {
        boolean ss = start.diffsToLessThan(other.getStart(), tolerance);
        boolean ee = end.diffsToLessThan(other.getEnd(), tolerance);
        boolean se = start.diffsToLessThan(other.getEnd(), tolerance);
        boolean es = end.diffsToLessThan(other.getStart(), tolerance);
        return (ss && ee) || (se && es);
    }

    @Override
    public void paint(final Graphics g, final double scale,
            final double xOffset, final double yOffset) {
        int x1 = (int) (start.getX() * scale + xOffset + FACTOR_HALF);
        int y1 = (int) (start.getY() * scale + yOffset + FACTOR_HALF);
        int x2 = (int) (end.getX() * scale + xOffset + FACTOR_HALF);
        int y2 = (int) (end.getY() * scale + yOffset + FACTOR_HALF);
        g.drawLine(x1, y1, x2, y2);
    }

}
