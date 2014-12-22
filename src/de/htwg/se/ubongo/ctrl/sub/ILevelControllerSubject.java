package de.htwg.se.ubongo.ctrl.sub;

import de.htwg.se.ubongo.util.ctrl.IAbstractSubSubject;

/** Subject-Interface for Level-Controller. */
public interface ILevelControllerSubject extends IAbstractSubSubject {

    /** Called when a board is selected.
     * @param index board-index */
    void onBoardSelected(int index);

    /** Called when a varaint of board is selected.
     * @param variant board-variant */
    void onBoardVariantSelected(int variant);

}
