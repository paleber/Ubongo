package de.htwg.se.ubongo;

import de.htwg.se.ubongo.ctrl.obs.imp.MainController;
import de.htwg.se.ubongo.tui.TuiManager;

/** Start-Class of the Application. */
public final class Ubongo {

    /** Main.
     * @param args unused */
    public static void main(final String[] args) {
        MainController main = new MainController();
        new TuiManager(main);
        main.switchToMenu();
    }

    private Ubongo() {}

}