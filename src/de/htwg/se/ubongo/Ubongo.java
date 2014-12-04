package de.htwg.se.ubongo;

import de.htwg.se.ubongo.ctrl.MainController;
import de.htwg.se.ubongo.tui.MainControllerTUI;

/** Start-Class of the Application. */
public final class Ubongo {

    /** Main.
     * @param args unused */
    public static void main(final String[] args) {
        new Ubongo();
    }

    private Ubongo() {
        MainController main = new MainController();
        new MainControllerTUI(main);
        main.switchToGame();
    }

}