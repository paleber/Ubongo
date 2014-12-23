package de.htwg.se.ubongo.tui;

import de.htwg.se.ubongo.ctrl.obs.IMenuController;
import de.htwg.se.ubongo.ctrl.sub.IMenuControllerSubject;
import de.htwg.se.ubongo.model.data.ILevelData;
import de.htwg.se.ubongo.tui.abs.AbstractTuiController;
import de.htwg.se.ubongo.tui.cmd.shared.TextCmdShowGuide;
import de.htwg.se.ubongo.tui.cmd.shared.TextCmdShowLevelSelection;
import de.htwg.se.ubongo.tui.cmd.shared.TextCmdStartRandomGame;

/** TextBased Implementation of Menu. */
public final class TuiMenuController extends AbstractTuiController implements
        IMenuControllerSubject {

    /** Default-Constructor.
     * @param observer Observer-MainController.
     * @param main MainControllerTUI
     * @param levelData LevelData */
    public TuiMenuController(final IMenuController observer,
            final TuiManager main, final ILevelData levelData) {
        super(observer, main, "menu");
        observer.register(this);

        addTextCmd("level", new TextCmdShowLevelSelection(observer));
        addTextCmd("random", new TextCmdStartRandomGame(observer, levelData));
        addTextCmd("guide", new TextCmdShowGuide(observer));
    }

    @Override
    protected void onControllerStart() {}

    @Override
    protected void onControllerStop() {}

}
