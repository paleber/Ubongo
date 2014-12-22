package de.htwg.se.ubongo.ctrl.obs;

import de.htwg.se.ubongo.ctrl.obs.abs.ISubController;
import de.htwg.se.ubongo.ctrl.sub.ILevelControllerSubject;

/** Interface for LevelController. */
public interface ILevelController extends
        ISubController<ILevelControllerSubject> {

    /** Select the board.
     * @param index board-index */
    void selectBoard(int index);

    /** Select the variant of the board.
     * @param variant */
    void selectBoardVariant(int variant);

    /** Get the number of boards.
     * @return */
    int getNumberBoards();

    /** Get the number of variants of a board.
     * @param index board-index
     * @return */
    int getNumberVariantsOfBoard(int index);

}
