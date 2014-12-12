package de.htwg.se.ubongo.model.loader.imp;

import de.htwg.se.ubongo.model.gameobject.IBlock;
import de.htwg.se.ubongo.model.gameobject.IBoard;

/** Interface for ResourceLoader. */
public interface IResourceLoader {

    /** Get the number of Boards.
     * @return number of Boards. */
    int getNumberBoards();

    /** Create a new Board.
     * @param index index of the Board
     * @return new Board */
    IBoard createBoard(int index);

    /** Get the number of variants of a board.
     * @param index index of the Board
     * @return number variants of a board */
    int getNumberVariantsOfBoard(int index);

    /** Create the Blocks of a Board.
     * @param index index of the Board
     * @param variant variant of the Board
     * @return Blocks of a Board */
    IBlock[] createBlocksOfBoard(int index, int variant);

}
