package de.htwg.se.ubongo.model.geo.imp;

import java.util.Locale;

import de.htwg.se.ubongo.model.geo.IPoint;
import de.htwg.se.ubongo.model.geo.IVector;

/** Implementation of IPoint. */
public final class Point2D implements IPoint {

    private static final double FACTOR_DOUBLE = 2;

    private double x = 0;
    private double y = 0;

    @Override
    public void set(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void copy(final IPoint other) {
        this.x = other.getX();
        this.y = other.getY();
    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }

    @Override
    public void move(final IVector v) {
        x += v.getX();
        y += v.getY();
    }

    @Override
    public void rotateAround(final double angleDeg, final IPoint pivot) {
        double angleRad = Math.toRadians(angleDeg);
        double sin = Math.sin(angleRad);
        double cos = Math.cos(angleRad);

        x -= pivot.getX();
        y -= pivot.getY();

        double xn = x * cos - y * sin;
        double yn = x * sin + y * cos;

        x = xn + pivot.getX();
        y = yn + pivot.getY();
    }

    @Override
    public void mirrorVertical(final double yAxis) {
        x += (yAxis - x) * FACTOR_DOUBLE;
    }

    @Override
    public void mirrorHorizontal(final double xAxis) {
        y += (xAxis - y) * FACTOR_DOUBLE;
    }

    @Override
    public double distanceTo(final IPoint other) {
        return Math.sqrt(distanceSquareTo(other));
    }

    @Override
    public double distanceSquareTo(final IPoint other) {
        return (x - other.getX()) * (x - other.getX()) + (y - other.getY())
                * (y - other.getY());
    }

    @Override
    public String toString() {
        return String.format(Locale.ENGLISH, "(%.3f|%.3f)", x, y);
    }

    @Override
    public boolean diffsToLessThan(IPoint other, double tolerance) {
        if (Math.abs(x - other.getX()) >= tolerance) {
            return false;
        }

        if (Math.abs(y - other.getY()) >= tolerance) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        final int shift = 32;
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(x);
        result = prime * result + (int) (temp ^ (temp >>> shift));
        temp = Double.doubleToLongBits(y);
        result = prime * result + (int) (temp ^ (temp >>> shift));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Point2D other = (Point2D) obj;
        if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x)) {
            return false;
        }
        if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y)) {
            return false;
        }
        return true;
    }

}
