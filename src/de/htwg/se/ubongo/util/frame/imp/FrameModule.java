package de.htwg.se.ubongo.util.frame.imp;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;

import de.htwg.se.ubongo.util.frame.ISwitchFrame;
import de.htwg.se.ubongo.util.geo.imp.GeoModule;

/** Guice-Module for Frames. */
public final class FrameModule extends AbstractModule {

    /** Injector with dependant Modules . */
    protected static final Injector INJECTOR = Guice
            .createInjector(new GeoModule());

    @Override
    protected void configure() {
        bind(ISwitchFrame.class).to(SwitchFrame.class);
    }

}
