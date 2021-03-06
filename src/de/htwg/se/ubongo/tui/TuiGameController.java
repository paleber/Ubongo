package de.htwg.se.ubongo.tui;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.htwg.se.ubongo.ctrl.obs.IGameController;
import de.htwg.se.ubongo.ctrl.sub.IGameControllerSubject;
import de.htwg.se.ubongo.model.data.ILevelData;
import de.htwg.se.ubongo.model.gameobject.IBlock;
import de.htwg.se.ubongo.tui.abs.AbstractTuiController;
import de.htwg.se.ubongo.tui.cmd.game.TextCmdDropBlock;
import de.htwg.se.ubongo.tui.cmd.game.TextCmdMirrorBlockHorizontal;
import de.htwg.se.ubongo.tui.cmd.game.TextCmdMirrorBlockVertical;
import de.htwg.se.ubongo.tui.cmd.game.TextCmdMoveBlock;
import de.htwg.se.ubongo.tui.cmd.game.TextCmdPrintGrid;
import de.htwg.se.ubongo.tui.cmd.game.TextCmdRotateBlockLeft;
import de.htwg.se.ubongo.tui.cmd.game.TextCmdRotateBlockRight;
import de.htwg.se.ubongo.tui.cmd.game.TextCmdSelectBlock;
import de.htwg.se.ubongo.tui.cmd.shared.TextCmdShowMenu;
import de.htwg.se.ubongo.tui.cmd.shared.TextCmdStartRandomGame;
import de.htwg.se.ubongo.util.console.IConsole;

/** Subject-GameController of TUI. */
public final class TuiGameController extends AbstractTuiController implements
        IGameControllerSubject {

    private static final Logger LOGGER = LogManager.getLogger();
    private final TextCmdPrintGrid cmdPrintGrid;

    /** Default-Constructor.
     * @param observer Observer-GameController
     * @param console TuiManager
     * @param level LevelData */
    public TuiGameController(final IGameController observer,
            final IConsole console, final ILevelData level) {
        super(observer, console, "game");
        observer.register(this);

        cmdPrintGrid = new TextCmdPrintGrid();

        addTextCmd("menu", new TextCmdShowMenu(observer));
        addTextCmd("random", new TextCmdStartRandomGame(observer, level));
        addTextCmd("grid", cmdPrintGrid);
        addTextCmd("select", new TextCmdSelectBlock(observer));
        addTextCmd("drop", new TextCmdDropBlock(observer));
        addTextCmd("move", new TextCmdMoveBlock(observer));
        addTextCmd("left", new TextCmdRotateBlockLeft(observer));
        addTextCmd("right", new TextCmdRotateBlockRight(observer));
        addTextCmd("horizontal", new TextCmdMirrorBlockHorizontal(observer));
        addTextCmd("vertical", new TextCmdMirrorBlockVertical(observer));
    }

    @Override
    public void onSetGridSize(final double width, final double height) {
        cmdPrintGrid.setGridSize(width, height);
    }

    @Override
    public void onSetGameObjects(final IBlock board, final IBlock[] block) {
        cmdPrintGrid.setGameObjects(board, block);
    }

    @Override
    public void onStartGame() {
        cmdPrintGrid.execute();
    }

    @Override
    public void onSelectBlock(final IBlock block) {
        cmdPrintGrid.selectBlock(block);
        cmdPrintGrid.execute();
    }

    @Override
    public void onDropBlock() {
        cmdPrintGrid.dropBlock();
        cmdPrintGrid.execute();
    }

    @Override
    public void onUpdateGameObjects() {
        cmdPrintGrid.execute();
    }

    @Override
    public void onWin() {
        LOGGER.info("you win!");
    }

    @Override
    protected void onStart() {}

}
