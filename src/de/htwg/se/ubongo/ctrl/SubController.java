package de.htwg.se.ubongo.ctrl;

import de.htwg.se.ubongo.ctrl.main.MainController;
import de.htwg.se.ubongo.util.ctrl.AbstractSubController;
import de.htwg.se.ubongo.util.ctrl.AbstractSubSubject;

/** Super-Class for SubController of Ubongo. Provide methods to switch between
 * the specific SubControllers.
 * @param <T> SubSubject of the SubController */
public abstract class SubController<T extends AbstractSubSubject> extends
        AbstractSubController<T> {

    private final MainController main;

    /** Constructor.
     * @param main MainController */
    protected SubController(final MainController main) {
        this.main = main;
    }

    /** Stop current SubController and start MenuSubController. */
    public final void switchToMenu() {
        main.switchToMenu();
    }
    
    /** Stop current SubController and start LevelSubController. */
    public void switchToLevel() {
        main.switchToLevel();
    }

    /** Stop current SubController and start GameSubController. */
    public final void switchToGame(final int index, final int variant) {
        main.switchToGame(index, variant);
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