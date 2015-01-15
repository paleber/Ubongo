package de.htwg.se.ubongo.tui;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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

/** TuiLevelController. */
public final class TuiLevelController extends AbstractTuiController implements
        ILevelControllerSubject {

    private static final Logger LOGGER = LogManager.getLogger();

    /** Constructor.
     * @param observer observer
     * @param console console
     * @param levelData levelData */
    public TuiLevelController(final ILevelController observer,
            final IConsole console, final ILevelData levelData) {

        super(observer, console, "level");
        observer.register(this);

        addTextCmd("menu", new TextCmdShowMenu(observer));
        addTextCmd("numBoards", new TextCmdNumberBoards(levelData));
        addTextCmd("numVariants", new TextCmdNumberVariants(levelData));
        addTextCmd("board", new TextCmdSelectBoard(observer));
        addTextCmd("variant", new TextCmdSelectVariant(observer));
        addTextCmd("game", new TextCmdShowGame(observer));
    }

    @Override
    public void onSelect(final int index, final int variant) {
        LOGGER.info("selected - board: " + index + " - variant: " + variant);
    }

    @Override
    protected void onStart() {}

}
