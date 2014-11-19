package de.htwg.se.ubongo;

import de.htwg.se.ubongo.gui.MainControllerGUI;
import de.htwg.se.ubongo.main.MainController;
import de.htwg.se.ubongo.tui.MainControllerTUI;

/** Ubongo.
 * @author Patrick Leber
 * @version 19.11.2014 */
public final class Ubongo {

    private Ubongo() {
    }

    /** Main.
     * @param args not used */
    public static void main(final String[] args) {
        new MainControllerTUI();
        new MainControllerGUI();
        MainController.start();
    }

}
