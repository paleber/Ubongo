package de.htwg.se.ubongo;

import de.htwg.se.ubongo.gui.MainControllerGUI;
import de.htwg.se.ubongo.main.main.MainController;
import de.htwg.se.ubongo.tui.MainControllerTUI;

/** Ubongo. */
public final class Ubongo {

    /* Hidden-Constructor. */
    private Ubongo() {}

    /** Main.
     * @param args not used */
    public static void main(final String[] args) {
        new MainControllerTUI();
        new MainControllerGUI();
        MainController.getInstance().start();
    }

}
