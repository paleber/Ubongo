package de.htwg.se.ubongo.tui;

import java.util.LinkedHashMap;

import de.htwg.se.ubongo.ctrl.obs.IGameController;
import de.htwg.se.ubongo.ctrl.sub.IGameControllerSubject;
import de.htwg.se.ubongo.model.gameobject.IBlock;
import de.htwg.se.ubongo.tui.cmd.game.TextCmdDropBlock;
import de.htwg.se.ubongo.tui.cmd.game.TextCmdMirrorBlockHorizontal;
import de.htwg.se.ubongo.tui.cmd.game.TextCmdMirrorBlockVertical;
import de.htwg.se.ubongo.tui.cmd.game.TextCmdMoveBlock;
import de.htwg.se.ubongo.tui.cmd.game.TextCmdPrintGrid;
import de.htwg.se.ubongo.tui.cmd.game.TextCmdRotateBlockLeft;
import de.htwg.se.ubongo.tui.cmd.game.TextCmdRotateBlockRight;
import de.htwg.se.ubongo.tui.cmd.game.TextCmdSelectBlock;
import de.htwg.se.ubongo.tui.cmd.shared.TextCmdPrintHelp;
import de.htwg.se.ubongo.tui.cmd.shared.TextCmdShowMenu;
import de.htwg.se.ubongo.tui.cmd.shared.TextCmdShutdown;
import de.htwg.se.ubongo.util.cmd.TextCommand;
import de.htwg.se.ubongo.util.geo.IPoint;
import de.htwg.se.ubongo.util.geo.IPolygon;
import de.htwg.se.ubongo.util.timer.Timer;
import de.htwg.se.ubongo.util.timer.Trigger;

/** Subject-GameController of TUI. */
public final class TuiGameController implements IGameControllerSubject, Trigger {

    private final double FACTOR_HALF = 0.5;
    
    private final TuiManager tuiManager;

    private final Timer timer = new Timer(this, 150);

    private IBlock board;
    private IBlock[] block;

    private char[][] grid;

    private final LinkedHashMap<String, TextCommand> cmdMap =
            new LinkedHashMap<>();

    private IBlock selectedBlock;

    /** Default-Constructor.
     * @param observer Observer-GameController
     * @param tuiManager TuiManager */
    public TuiGameController(final IGameController observer,
            final TuiManager tuiManager) {
        this.tuiManager = tuiManager;
        observer.register(this);

        cmdMap.put("help", new TextCmdPrintHelp(tuiManager, cmdMap));
        cmdMap.put("menu", new TextCmdShowMenu(observer));
        cmdMap.put("exit", new TextCmdShutdown(observer));
        cmdMap.put("select", new TextCmdSelectBlock(observer, tuiManager));
        cmdMap.put("drop", new TextCmdDropBlock(observer));
        cmdMap.put("move", new TextCmdMoveBlock(observer, tuiManager));
        cmdMap.put("grid", new TextCmdPrintGrid(this));
        cmdMap.put("left", new TextCmdRotateBlockLeft(observer));
        cmdMap.put("right", new TextCmdRotateBlockRight(observer));
        cmdMap.put("horizontal", new TextCmdMirrorBlockHorizontal(observer));
        cmdMap.put("vertical", new TextCmdMirrorBlockVertical(observer));
    }

    @Override
    public void onStartSubController() {
        tuiManager.writeLine("--- game started ---");
        tuiManager.writeLine("\"help\" for list of command");
        timer.start();
    }

    @Override
    public void onStopSubController() {
        timer.stop();
        tuiManager.writeLine("--- game stopped ---");
    }

    @Override
    public void onTrigger() {
        String line = tuiManager.readLine();
        if (line != null) {
            TextCommand cmd = cmdMap.get(line);
            if (cmd != null) {
                cmd.execute(line.split(" "));
            } else {
                tuiManager
                        .writeLine("unknown command, \"help\" to print available"
                                + " commands");
            }
        }
    }

    @Override
    public void onSetGridSize(final double width, final double height) {
        grid = new char[(int) width * 2][(int) height * 2];
    }

    @Override
    public void onSetGameObjects(final IBlock board, final IBlock[] block) {
        this.board = board;
        this.block = block;
    }

    @Override
    public void onStartGame() {
        refreshGrid();
    }

    @Override
    public void onSelectBlock(final IBlock block) {
        selectedBlock = block;
        refreshGrid();
    }

    @Override
    public void onDropBlock() {
        selectedBlock = null;
        refreshGrid();
    }

    @Override
    public void onUpdateGameObjects() {
        refreshGrid();
    }

    private void refreshGrid() {
        clearGrid();
        paintBoard();

        paintBlocks();

        printGrid();
    }

    private void clearGrid() {
        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[0].length; y++) {
                grid[x][y] = ' ';
            }
        }
    }

    private void paintBoard() {
        for (IPolygon poly : board) {

            IPoint p = poly.calcMid();
            grid[(int) (p.getX() * 2)][(int) (p.getY() * 2)] = 'X';
        }
    }

    private void printGrid() {
        tuiManager.writeLine("---------------------------------------------");
        for (int y = 0; y < grid[0].length; y++) {
            StringBuffer b = new StringBuffer();
            for (int x = 0; x < grid.length; x++) {
                b.append(grid[x][y]);
            }
            tuiManager.writeLine(b.toString());
        }
        tuiManager.writeLine("---------------------------------------------");
    }

    private void paintBlocks() {

        int index = 0;
        for (IBlock b : block) {
            for (IPolygon poly : b) {
                IPoint p = poly.calcMid();
                try {
                    grid[(int) (p.getX() * 2 + FACTOR_HALF)][(int) (p.getY() * 2 + FACTOR_HALF)] =
                            Integer.toString(index).charAt(0);
                } catch (ArrayIndexOutOfBoundsException e) {
                    continue;
                }
            }
            index++;
        }

        if (selectedBlock != null) {
            for (IPolygon poly : selectedBlock) {
                IPoint p = poly.calcMid();
                try {
                    grid[(int) (p.getX() * 2 + FACTOR_HALF)][(int) (p.getY() * 2 + FACTOR_HALF)] =
                            'S';
                } catch (ArrayIndexOutOfBoundsException e) {
                    continue;
                }
            }
        }
    }

    @Override
    public void onWin() {
        tuiManager.writeLine("you win!");
    }

}
