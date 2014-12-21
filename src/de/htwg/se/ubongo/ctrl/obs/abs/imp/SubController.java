package de.htwg.se.ubongo.ctrl.obs.abs.imp;

import de.htwg.se.ubongo.ctrl.obs.IMainController;
import de.htwg.se.ubongo.ctrl.obs.abs.ISubController;
import de.htwg.se.ubongo.util.ctrl.IAbstractSubSubject;
import de.htwg.se.ubongo.util.ctrl.imp.AbstractSubController;

/** Super-Class for SubController of Ubongo. Provide methods to switch between
 * the specific SubControllers.
 * @param <S> SubSubject of the SubController */
public abstract class SubController<S extends IAbstractSubSubject> extends
        AbstractSubController<S> implements ISubController<S> {

    private final IMainController main;

    /** Constructor.
     * @param main MainController */
    protected SubController(final IMainController main) {
        this.main = main;
    }

    @Override
    public final void switchToMenu() {
        main.switchToMenu();
    }

    @Override
    public final void switchToLevel() {
        main.switchToLevel();
    }

    @Override
    public final void switchToGame() {
        main.switchToGame();
    }

    @Override
    public final void switchToHelp() {
        main.switchToHelp();
    }

    @Override
    public final void shutdown() {
        main.shutdown();
    }

}