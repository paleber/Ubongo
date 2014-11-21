package de.htwg.se.ubongo.ctrl;

import de.htwg.se.ubongo.util.ctrl.SubController;

/** Game Controller. */
public final class GameController extends SubController<GameSubject> {

    private static final GameController INSTANCE = new GameController();

    private GameController() {}

    public static GameController getInstance() {
        return INSTANCE;
    }

}
