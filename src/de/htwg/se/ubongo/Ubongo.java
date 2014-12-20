package de.htwg.se.ubongo;

import de.htwg.se.ubongo.ctrl.main.UbongoMainController;
import de.htwg.se.ubongo.tui.MainControllerTUI;

/** Start-Class of the Application. */
public final class Ubongo {

    /** Main.
     * @param args unused */
    public static void main(final String[] args) {
        UbongoMainController main = new UbongoMainController();
        new MainControllerTUI(main);
        main.switchToGame();
    }

    private Ubongo() {}

}