package de.htwg.se.ubongo.ctrl.sub;

import de.htwg.se.ubongo.model.gameobject.IBlock;
import de.htwg.se.ubongo.util.ctrl.IAbstractSubSubject;

/** Subject-Interface for GameController. */
public interface IGameControllerSubject extends IAbstractSubSubject {

    /** Called when the size of the grid is set.
     * @param width witdh
     * @param height height */
    void onSetGridSize(double width, double height);

    /** Called when the size of the grid is set.
     * @param board Board
     * @param block Block-Array */
    void onSetGameObjects(IBlock board, IBlock[] block);

    /** Called when the game starts. */
    void onStartGame();

    /** Called when a block is selected.
     * @param block selected block */
    void onSelectBlock(IBlock block);

    /** Called when the selected block is dropped. */
    void onDropBlock();

    /** Called when the GameObjects are updated. */
    void onUpdateGameObjects();

    /** Called when the game is won. */
    void onWin();

}
