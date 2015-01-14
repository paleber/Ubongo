package de.htwg.se.ubongo.util.geo.imp;

import com.google.inject.AbstractModule;

import de.htwg.se.ubongo.util.geo.ILine;
import de.htwg.se.ubongo.util.geo.IPoint;
import de.htwg.se.ubongo.util.geo.IPolygon;
import de.htwg.se.ubongo.util.geo.IVector;

public final class GeoModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(IPoint.class).to(Point2D.class);
        bind(IVector.class).to(Vector2D.class);
        bind(IPolygon.class).to(Polygon2D.class);
        bind(ILine.class).to(Line2D.class);
    }

}
