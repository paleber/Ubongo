package de.htwg.se.ubongo.model.gameobject.imp;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;

import de.htwg.se.ubongo.model.gameobject.IBlock;
import de.htwg.se.ubongo.model.gameobject.IGrid;
import de.htwg.se.ubongo.util.geo.imp.GeoModule;

/** Guice-Module for GameObjects. */
public final class GameObjectModule extends AbstractModule {

    /** Injector with dependant Modules. */
    protected static final Injector INJECTOR = Guice.createInjector(new GeoModule());
    
    @Override
    protected void configure() {
        bind(IBlock.class).to(Block.class);
        bind(IGrid.class).to(Grid.class);
    }
    
    

}
