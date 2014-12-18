package de.htwg.se.ubongo.model.geo.imp;

import de.htwg.se.ubongo.model.geo.IPoint;

final class Line2D {

    private IPoint start;
    private IPoint end;

    public void setPoints(final IPoint start, final IPoint end) {
        this.start = start;
        this.end = end;
    }

    public void setPointDegree(final IPoint start, double degree, double length) {
        this.start = start;
        Vector2D v = new Vector2D();
        v.setAngle(degree);
        v.setLength(length);
        end = new Point2D();
        end.copy(start);
        end.move(v);
    }

    public boolean intersect(Line2D other) {
        // TODO
        return false;
    }

    public double distanceTo(IPoint p) {

        return 0;
    }

    private IPoint calcIntersectPoint(Line2D other) {
        final double delta = 1e-9;
        double a1, b1, c1, a2, b2, c2;
        a1 = end.getX() - start.getX();
        b1 = start.getY() - end.getY();
        c1 = a1 * start.getX() + b1 * start.getY();

        a2 = other.end.getX() - other.start.getX();
        b2 = other.start.getY() - other.end.getY();
        c2 = a2 * other.start.getX() + b2 * other.start.getY();

        double det = a1 * b2 - a2 * b1;

        if (Math.abs(det) < delta) {
            return null;
        }

        double x = (b2 * c1 - b1 * c2) / det;
        double y = (a1 * c2 - a2 * c1) / det;

        System.out.println(x + " | " + y);

        if (Math.min(start.getX(), end.getX()) > x + delta) {
            System.out.println("no");
            return null;
        }

        if (Math.max(start.getX(), end.getX()) < x - delta) {
            System.out.println("no");
            return null;
        }

        if (Math.min(start.getY(), end.getY()) > y + delta) {
            System.out.println("no");
            return null;
        }

        if (Math.max(start.getY(), end.getY()) < y - delta) {
            System.out.println("no");
            return null;
        }

        if (Math.min(other.start.getX(), other.end.getX()) > x + delta) {
            System.out.println("no");
            return null;
        }

        if (Math.max(other.start.getX(), other.end.getX()) < x - delta) {
            System.out.println("no");
            return null;
        }

        if (Math.min(other.start.getY(), other.end.getY()) > y + delta) {
            System.out.println("no");
            return null;
        }

        if (Math.max(other.start.getY(), other.end.getY()) < y - delta) {
            System.out.println("no");
            return null;
        }

        return null;
    }

    public static void main(String[] args) {
        IPoint p1 = new Point2D();
        IPoint p2 = new Point2D();
        IPoint p3 = new Point2D();
        IPoint p4 = new Point2D();
        p1.set(0, 0);
        p2.set(1, 1);
        p3.set(2.1, 0);
        p4.set(0, 2.1);

        Line2D l1 = new Line2D();
        l1.setPoints(p1, p2);

        Line2D l2 = new Line2D();
        l2.setPoints(p3, p4);

        l1.calcIntersectPoint(l2);
    }

}
