package de.htwg.se.ubongo.gui;

import de.htwg.se.ubongo.ctrl.obs.IMainController;
import de.htwg.se.ubongo.util.ctrl.IMainControllerSubject;
import de.htwg.se.ubongo.util.frame.ISwitchFrame;
import de.htwg.se.ubongo.util.frame.imp.SwitchFrame;

/** MainController-Subject of GUI. */
public final class GuiManager implements IMainControllerSubject {

    private static final int DEF_WIDTH = 800;
    private static final int DEF_HEIGHT = 600;

    private final ISwitchFrame frame;

    /** Default-Constructor.
     * @param observer observer */
    public GuiManager(final IMainController observer) {
        observer.register(this);
        frame = new SwitchFrame(observer);
        frame.setContentSize(DEF_WIDTH, DEF_HEIGHT);
        new GuiGameController(observer.getGameController(), frame);
    }

    @Override
    public void onShutdown() {
        frame.shutdown();
    }

}
