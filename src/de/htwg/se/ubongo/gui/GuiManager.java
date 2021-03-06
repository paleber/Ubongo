package de.htwg.se.ubongo.gui;

import de.htwg.se.ubongo.ctrl.obs.IMainController;
import de.htwg.se.ubongo.model.data.ILevelData;
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
        final ILevelData level = observer.getLevelData();
        new GuiMenuController(observer.getMenuController(), frame, level);
        new GuiLevelController(observer.getLevelController(), frame, level);
        new GuiGameController(observer.getGameController(), frame, level);
        new GuiHelpController(observer.getHelpController(), frame);
    }

    @Override
    public void onShutdown() {
        frame.shutdown();
    }

}
