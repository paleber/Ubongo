package de.htwg.se.ubongo.model.gameobject;

import de.htwg.se.ubongo.model.gameobject.imp.Block;
import de.htwg.se.ubongo.model.gameobject.imp.Board;

/** Factory for creating GameObjects. */
public final class GameObjectFactory {

    private GameObjectFactory() {}

    /** Create a new Block.
     * @return Block */
    public static IBlock createBlock() {
        return new Block();
    }

    /** Create a new Board.
     * @return Board */
    public static IBoard createBoard() {
        return new Board();
    }

}
