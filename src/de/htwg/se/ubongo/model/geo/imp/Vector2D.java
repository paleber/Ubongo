package de.htwg.se.ubongo.model.geo.imp;

import java.util.Locale;

import de.htwg.se.ubongo.model.geo.IPoint;
import de.htwg.se.ubongo.model.geo.IVector;

/** Implementation of IVector. */
public final class Vector2D implements IVector {

    private static final double FULL_ROTATION = 360;
    private double x = 0;
    private double y = 0;

    @Override
    public void stretchBetween(final IPoint from, final IPoint to) {
        set(to.getX() - from.getX(), to.getY() - from.getY());
    }

    @Override
    public void copy(final IVector other) {
        x = other.getX();
        y = other.getY();
    }

    @Override
    public void set(final double x, final double y) {
        this.x = x;
        this.y = y;
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
    public void swap() {
        x = -x;
        y = -y;
    }

    @Override
    public String toString() {
        return String.format(Locale.ENGLISH, "<%.3f|%.3f>", x, y);
    }

    public void setAngleDegree(final double degree) {
        double radian = Math.toRadians(degree);
        x = Math.cos(radian);
        y = Math.sin(radian);
    }
    
    public double getAngleDegree() {
        double angle = Math.toDegrees(Math.atan2(-y, x));
        if(angle < 0) {
            angle += FULL_ROTATION;
        }
        return angle;
    }

    public void setLength(double length) {
        double cur = Math.sqrt(x * x + y * y);
        x = (x / cur) * length;
        y = (y / cur) * length;
    }
    
    public void convertToNormal() {
        set(-y, x);
    }

}
