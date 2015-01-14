package de.htwg.se.ubongo.cfg.geo;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;

import de.htwg.se.ubongo.util.geo.ILine;
import de.htwg.se.ubongo.util.geo.IPoint;
import de.htwg.se.ubongo.util.geo.IPolygon;
import de.htwg.se.ubongo.util.geo.IVector;
import de.htwg.se.ubongo.util.geo.imp.Line2D;
import de.htwg.se.ubongo.util.geo.imp.Point2D;
import de.htwg.se.ubongo.util.geo.imp.Polygon2D;
import de.htwg.se.ubongo.util.geo.imp.Vector2D;

public final class GeoModule extends AbstractModule {

    private static final Injector INJECTOR = Guice.createInjector(new GeoModule());

    public GeoModule() {}
    
    @Override
    protected void configure() {
        bind(IPoint.class).to(Point2D.class);
        bind(IVector.class).to(Vector2D.class);
        bind(IPolygon.class).to(Polygon2D.class);
        bind(ILine.class).to(Line2D.class);
    }

    public static Injector getInjector() {
        return INJECTOR;
    }

}
