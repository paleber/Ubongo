package de.htwg.se.ubongo.tui;

import de.htwg.se.ubongo.ctrl.obs.ILevelController;
import de.htwg.se.ubongo.ctrl.sub.ILevelControllerSubject;
import de.htwg.se.ubongo.model.data.ILevelData;
import de.htwg.se.ubongo.tui.abs.AbstractTuiController;
import de.htwg.se.ubongo.tui.cmd.level.TextCmdNumberBoards;
import de.htwg.se.ubongo.tui.cmd.level.TextCmdNumberVariants;
import de.htwg.se.ubongo.tui.cmd.level.TextCmdSelectBoard;
import de.htwg.se.ubongo.tui.cmd.level.TextCmdSelectVariant;
import de.htwg.se.ubongo.tui.cmd.level.TextCmdShowGame;
import de.htwg.se.ubongo.tui.cmd.shared.TextCmdShowMenu;
import de.htwg.se.ubongo.util.console.IConsole;

public final class TuiLevelController extends AbstractTuiController implements
        ILevelControllerSubject {

    private final IConsole console;

    public TuiLevelController(ILevelController observer, IConsole console,
            ILevelData levelData) {

        super(observer, console, "level");
        observer.register(this);
        this.console = console;

        addTextCmd("menu", new TextCmdShowMenu(observer));
        addTextCmd("numBoards", new TextCmdNumberBoards(levelData, console));
        addTextCmd("numVariants", new TextCmdNumberVariants(levelData, console));
        addTextCmd("board", new TextCmdSelectBoard(observer, console));
        addTextCmd("variant", new TextCmdSelectVariant(observer, console));
        addTextCmd("game", new TextCmdShowGame(observer));
    }

    @Override
    public void onSelect(int index, int variant) {
        console.writeLine("selected - board: " + index + " - variant: "
                + variant);

    }

    @Override
    protected void onControllerStart() {}

    @Override
    protected void onControllerStop() {}

}
