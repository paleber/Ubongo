package de.htwg.se.ubongo.model.geo;

import de.htwg.se.ubongo.model.geo.imp.Point2D;
import de.htwg.se.ubongo.model.geo.imp.Polygon2D;
import de.htwg.se.ubongo.model.geo.imp.Vector2D;

/** Factory for creating geometric objects. */
public final class GeoFactory {

    private GeoFactory() {}

    /** Create a new Point.
     * @return Point */
    public static IPoint createPoint() {
        return new Point2D();
    }

    /** Create a new Vector.
     * @return Vector */
    public static IVector createVector() {
        return new Vector2D();
    }

    /** Create a new Polygon.
     * @return Polygon */
    public static IPolygon createPolygon() {
        return new Polygon2D();
    }
    
}
