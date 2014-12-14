package de.htwg.se.ubongo.model.geo;

import com.google.inject.Singleton;
import com.google.inject.AbstractModule;

import de.htwg.se.ubongo.model.geo.imp.Point2D;
import de.htwg.se.ubongo.model.geo.imp.Polygon2D;
import de.htwg.se.ubongo.model.geo.imp.Vector2D;

/** Geometric module for dependency-injection with guice. */
public final class GeoModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(IPoint.class).to(Point2D.class).in(Singleton.class);
        bind(IVector.class).to(Vector2D.class);
        bind(IPolygon.class).to(Polygon2D.class);
    }

}
