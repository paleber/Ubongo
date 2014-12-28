package de.htwg.se.ubongo.util.geo.module;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;

import de.htwg.se.ubongo.util.geo.IPoint;
import de.htwg.se.ubongo.util.geo.IPolygon;
import de.htwg.se.ubongo.util.geo.IVector;
import de.htwg.se.ubongo.util.geo.imp.Point2D;
import de.htwg.se.ubongo.util.geo.imp.Polygon2D;
import de.htwg.se.ubongo.util.geo.imp.Vector2D;

/** Geometric module for dependency-injection with guice. */
public final class GeoModule extends AbstractModule {

    private static final Injector INJECTOR = Guice
            .createInjector(new GeoModule());

    private GeoModule() {}

    @Override
    protected void configure() {
        bind(IPoint.class).to(Point2D.class);
        bind(IVector.class).to(Vector2D.class);
        bind(IPolygon.class).to(Polygon2D.class);
    }

    /** Create a new Point.
     * @return new Point */
    public static IPoint createPoint() {
       return new Point2D();//return INJECTOR.getInstance(IPoint.class);
    }

    /** Create a new Vector.
     * @return new Vector */
    public static IVector createVector() {
        return INJECTOR.getInstance(IVector.class);
    }

    /** Create a new Polygon.
     * @return new Polygon */
    public static IPolygon createPolygon() {
        return INJECTOR.getInstance(IPolygon.class);
    }

}
