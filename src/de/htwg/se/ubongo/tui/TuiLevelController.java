package de.htwg.se.ubongo.tui;

import de.htwg.se.ubongo.ctrl.obs.ILevelController;
import de.htwg.se.ubongo.ctrl.sub.ILevelControllerSubject;
import de.htwg.se.ubongo.model.data.ILevelData;
import de.htwg.se.ubongo.tui.abs.AbstractTuiController;
import de.htwg.se.ubongo.tui.cmd.level.TextCmdNumberBoards;
import de.htwg.se.ubongo.tui.cmd.level.TextCmdNumberVariants;
import de.htwg.se.ubongo.tui.cmd.level.TextCmdSelectBoard;
import de.htwg.se.ubongo.tui.cmd.level.TextCmdSelectVariant;
import de.htwg.se.ubongo.tui.cmd.shared.TextCmdShowMenu;

public final class TuiLevelController extends AbstractTuiController implements
        ILevelControllerSubject {

    private final TuiManager tuiManager;
    
    public TuiLevelController(ILevelController observer, TuiManager tuiManager, ILevelData levelData) {
        super(observer, tuiManager, "level");
        observer.register(this);
        this.tuiManager = tuiManager;
        
        addTextCmd("menu", new TextCmdShowMenu(observer));
        addTextCmd("numBoards", new TextCmdNumberBoards(levelData));
        addTextCmd("numVariants", new TextCmdNumberVariants(levelData));
        addTextCmd("board", new TextCmdSelectBoard(levelData, tuiManager));
        addTextCmd("variant", new TextCmdSelectVariant(levelData, tuiManager));
    }

    @Override
    public void onBoardSelected(final int index) {
        tuiManager.writeLine("board selected: " + index);
    }

    @Override
    public void onBoardVariantSelected(final int variant) {
        tuiManager.writeLine("variant selected: " + variant);
    }

    @Override
    protected void onControllerStart() {}

    @Override
    protected void onControllerStop() {}

}
