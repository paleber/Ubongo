package de.htwg.se.ubongo.model.gameobject.module;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;

import de.htwg.se.ubongo.model.gameobject.IBlock;
import de.htwg.se.ubongo.model.gameobject.IGrid;
import de.htwg.se.ubongo.model.gameobject.imp.Block;
import de.htwg.se.ubongo.model.gameobject.imp.Grid;

/** GameObject module for dependency-injection with guice. */
public final class GameObjectModule extends AbstractModule {

    private static final Injector INJECTOR = Guice
            .createInjector(new GameObjectModule());

    @Override
    protected void configure() {
        bind(IBlock.class).to(Block.class);
        bind(IGrid.class).to(Grid.class);
    }

    /** Create a new Block.
     * @return new Block */
    public static IBlock createBlock() {
        return INJECTOR.getInstance(IBlock.class);
    }

    /** Create a new Grid.
     * @return new Grid */
    public static IGrid createGrid() {
        return INJECTOR.getInstance(IGrid.class);
    }

}
