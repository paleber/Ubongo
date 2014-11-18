package de.htwg.se.ubongo;

import de.htwg.se.ubongo.gui.MainControllerGUI;
import de.htwg.se.ubongo.main.MainController;
import de.htwg.se.ubongo.tui.MainControllerTUI;

/** @author Admin */
public final class Ubongo {

    private Ubongo() {
    }

    /** @param args the command line arguments */
    public static void main(String[] args) {
       MainControllerTUI.getInstance();
       MainControllerGUI.getInstance();
       MainController.getInstance().start();
    }

}
