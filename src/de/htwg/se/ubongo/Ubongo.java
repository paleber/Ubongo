package de.htwg.se.ubongo;

import de.htwg.se.ubongo.tui.MainControllerTUI;

/** @author Admin */
public final class Ubongo {

    private Ubongo() {
    }

    /** @param args the command line arguments */
    public static void main(String[] args) {
       new MainControllerTUI();
       // new MainControllerGUI();
    }

}
