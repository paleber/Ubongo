package de.htwg.se.ubongo.ctrl;

import de.htwg.se.ubongo.model.gameobject.Block;
import de.htwg.se.ubongo.model.gameobject.Board;
import de.htwg.se.ubongo.util.ctrl.SubSubject;

/** TODO */
public interface GameSubject extends SubSubject {

    void setGridSize(int width, int height);

    void setBoard(Board board);

    void setBlocks(Block[] block);

    void startGame();

}
