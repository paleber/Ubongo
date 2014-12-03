package de.htwg.se.ubongo.ctrl;

import de.htwg.se.ubongo.util.ctrl.SubController;
import de.htwg.se.ubongo.util.ctrl.SubSubject;

/** TODO
 *  */
public abstract class UbongoSubController<T extends SubSubject> extends SubController<T> {

    private final MainController main;
    
    protected UbongoSubController(MainController main) {
        this.main = main;
    }
    
    public final void switchToMenu() {
        main.switchToMenu();
    }
    
    public final void switchToGame() {
        main.switchToGame();
    }
    
    public final void switchToHelp() {
        main.switchToHelp();
    }
    
    public final void shutdown() {
        main.shutdown();
    }
    
}
