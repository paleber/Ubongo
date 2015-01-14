package de.htwg.se.ubongo.model.gameobject.imp;

import com.google.inject.AbstractModule;

import de.htwg.se.ubongo.model.gameobject.IBlock;
import de.htwg.se.ubongo.model.gameobject.IGrid;

/** Guice-Module for GameObjects. */
public final class GameObjectModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(IBlock.class).to(Block.class);
        bind(IGrid.class).to(Grid.class);
    }

}
