package de.htwg.se.ubongo.cfg;

import com.google.inject.AbstractModule;

import de.htwg.se.ubongo.model.gameobject.imp.GameObjectModule;
import de.htwg.se.ubongo.util.geo.imp.GeoModule;

/** Module Configuration for Ubongo. */
public final class UbongoModule extends AbstractModule {

    @Override
    protected void configure() {
        install(new GeoModule());
        install(new GameObjectModule());
    }

}