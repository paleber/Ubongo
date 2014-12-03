package de.htwg.se.ubongo.ctrl;

import de.htwg.se.ubongo.util.ctrl.SubController;
import de.htwg.se.ubongo.util.ctrl.SubSubject;

/** Super-Class for SubController of Ubongo. Provide methods to switch between
 * the specific SubControllers.
 * @param <T> SubSubject of the SubController */
public abstract class UbongoSubController<T extends SubSubject> extends
        SubController<T> {

    private final MainController main;

    /** Constructor.
     * @param main MainController */
    protected UbongoSubController(final MainController main) {
        this.main = main;
    }

    /** Stop current SubController and start MenuSubController. */
    public final void switchToMenu() {
        main.switchToMenu();
    }

    /** Stop current SubController and start GameSubController. */
    public final void switchToGame() {
        main.switchToGame();
    }

    /** Stop current SubController and start HelpSubcontroller. */
    public final void switchToHelp() {
        main.switchToHelp();
    }

    /** Stop current SubController and shutdown the application. */
    public final void shutdown() {
        main.shutdown();
    }

}