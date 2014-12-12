package de.htwg.se.ubongo.model.geo;

/** Interface for Polygon. */
public interface IPolygon extends Iterable<IPoint> {

    /** Copy the values of other a Polygon.
     * @param other other Polygon */
    void copy(IPolygon other);

    /** Set the points of the Polygon.
     * @param point points as Point-Array */
    void setPoints(IPoint[] point);

    /** Rotate around a Point.
     * @param angleDeg angle in degree.
     * @param pivot pivot-Point */
    void rotateAround(final double angleDeg, final IPoint pivot);

    /** Move along a Vector.
     * @param v movement */
    void move(IVector v);

    /** Get the number of Points.
     * @return number of Points */
    int getNumberPoints();

    /** Get a point by index.
     * @param index index
     * @return point by index */
    IPoint getPoint(int index);

    /** Get the arithmetical mid.
     * @return mid */
    IPoint getMid();

    /** Mirror vertical at y-axis.
     * @param yAxis y-axis */
    void mirrorVertical(double yAxis);

    /** Mirror horizontal at x-axis.
     * @param xAxis x-axis */
    void mirrorHorizontal(double xAxis);

    /** Get the Polygon as String.
     * @return polygon as String */
    String toString();

}
