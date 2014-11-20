package de.htwg.se.ubongo;

import de.htwg.se.ubongo.ctrl.main.MainController;
import de.htwg.se.ubongo.tui.MainControllerTUI;

/** Ubongo. */
public final class Ubongo {

    /* Hidden-Constructor. */
    private Ubongo() {}

    /** Main.
     * @param args unused */
    public static void main(final String[] args) {
        new MainControllerTUI();
        //new MainControllerGUI();
        MainController.getInstance().startMenu();
    }

}
