package de.htwg.se.ubongo.ctrl.sub;

import de.htwg.se.ubongo.util.ctrl.IAbstractSubSubject;

/** Subject-Interface for Level-Controller. */
public interface ILevelControllerSubject extends IAbstractSubSubject {

    /** Called on select a board or variant.
     * @param index board-index
     * @param variant board-variant*/
    void onSelect(int index, int variant);

}
