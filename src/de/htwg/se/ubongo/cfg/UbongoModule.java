package de.htwg.se.ubongo.cfg;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;

import de.htwg.se.ubongo.model.gameobject.imp.GameObjectModule;
import de.htwg.se.ubongo.util.geo.imp.GeoModule;

/** Module Configuration for Ubongo. */
public final class UbongoModule extends AbstractModule {

    private static final Injector INJECTOR = Guice
            .createInjector(new UbongoModule());

    private UbongoModule() {}

    @Override
    protected void configure() {
        install(new GeoModule());
        install(new GameObjectModule());
    }

    /** Get the Injector.
     * @return Injector */
    public static Injector getInjector() {
        return INJECTOR;
    }

}