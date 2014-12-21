package de.htwg.se.ubongo.tui;

import java.util.HashMap;
import java.util.Map;

import de.htwg.se.ubongo.ctrl.obs.IGameController;
import de.htwg.se.ubongo.ctrl.obs.IMainController;
import de.htwg.se.ubongo.ctrl.sub.IGameControllerSubject;
import de.htwg.se.ubongo.model.gameobject.IBlock;
import de.htwg.se.ubongo.model.geo.IPoint;
import de.htwg.se.ubongo.model.geo.IPolygon;
import de.htwg.se.ubongo.util.TextCommand;
import de.htwg.se.ubongo.util.Timer;
import de.htwg.se.ubongo.util.Trigger;

/** TODO */
public class GameControllerTUI implements IGameControllerSubject, Trigger {

    private final IGameController observer;
    private final MainControllerTUI tui;

    private final Timer timer = new Timer(this, 150);

    private IBlock board;
    private IBlock[] block;

    private char[][] grid;

    private final Map<String, TextCommand> cmdMap = new HashMap<>();

    private IBlock selectedBlock;

    public GameControllerTUI(MainControllerTUI tui, IGameController observer) {
        this.tui = tui;
        this.observer = observer;
        observer.register(this);

        cmdMap.put("help", new CmdHelp());
        cmdMap.put("menu", new CmdMenu());
        cmdMap.put("exit", new CmdExit());
        cmdMap.put("select", new CmdSelect());
        cmdMap.put("drop", new CmdDrop());
        cmdMap.put("move", new CmdMove());
        cmdMap.put("grid", new CmdGrid());
        cmdMap.put("left", new CmdLeft());
        cmdMap.put("right", new CmdRight());
        cmdMap.put("horizontal", new CmdHorizontal());
        cmdMap.put("vertical", new CmdVertical());
    }

    @Override
    public void onStartSubController() {
        tui.writeLine("--- game started ---");
        tui.writeLine("\"help\" for list of command");
        timer.start();
    }

    @Override
    public void onStopSubController() {
        board = null;
        block = null;
        timer.stop();
        tui.writeLine("--- game stopped ---");
    }

    private final class CmdHelp extends TextCommand {

        private CmdHelp() {
            super(0);
        }

        private final String[] text = { "help: print help",
                "menu: back to menu", "exit: shutdown the application",
                "select INDEX: select block at index",
                "drop: drop the selected block",
                "move X Y: move the selected block", "left: rotate left",
                "right: rotate right", "horizontal: mirror horizontal",
                "vertical: mirror vertical", "grid: print the grid" };

        @Override
        protected boolean onExecute(String[] args) {
            for (String line : text) {
                tui.writeLine(line);
            }
            return true;
        }

    }

    private final class CmdMenu extends TextCommand {

        private CmdMenu() {
            super(0);
        }

        @Override
        protected boolean onExecute(String[] args) {
            observer.switchToMenu();
            return true;
        }

    }

    private final class CmdExit extends TextCommand {

        private CmdExit() {
            super(0);
        }

        @Override
        protected boolean onExecute(String[] args) {
            observer.shutdown();
            return true;
        }

    }

    private final class CmdSelect extends TextCommand {

        private CmdSelect() {
            super(1);
        }

        @Override
        protected boolean onExecute(String[] args) {
            try {
                observer.select(Integer.parseInt(args[1]));
            } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
                return false;
            }
            return true;
        }

    }

    private final class CmdDrop extends TextCommand {

        private CmdDrop() {
            super(0);
        }

        @Override
        protected boolean onExecute(String[] args) {
            observer.drop();
            return true;
        }

    }

    private final class CmdMove extends TextCommand {

        private CmdMove() {
            super(2);
        }

        @Override
        protected boolean onExecute(String[] args) {
            try {
                observer.move(Double.parseDouble(args[1]),
                        Double.parseDouble(args[2]));
            } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
                return false;
            }
            return true;
        }

    }

    private final class CmdGrid extends TextCommand {

        private CmdGrid() {
            super(0);
        }

        @Override
        protected boolean onExecute(String[] args) {
            printGrid();
            return true;
        }

    }

    private final class CmdLeft extends TextCommand {

        private CmdLeft() {
            super(0);
        }

        @Override
        protected boolean onExecute(String[] args) {
            observer.rotateLeft();
            return true;
        }

    }

    private final class CmdRight extends TextCommand {

        private CmdRight() {
            super(0);
        }

        @Override
        protected boolean onExecute(String[] args) {
            observer.rotateRight();
            return true;
        }

    }

    private final class CmdHorizontal extends TextCommand {

        private CmdHorizontal() {
            super(0);
        }

        @Override
        protected boolean onExecute(String[] args) {
            observer.mirrorHorizontal();
            return true;
        }

    }

    private final class CmdVertical extends TextCommand {

        private CmdVertical() {
            super(0);
        }

        @Override
        protected boolean onExecute(String[] args) {
            observer.mirrorVertical();
            return true;
        }

    }

    @Override
    public void onTrigger() {
        String line = tui.readLine();
        if (line == null) {
            return;
        }

        String[] args = line.split(" ");

        TextCommand cmd = cmdMap.get(args[0]);
        if (cmd == null) {
            tui.writeLine("unknown command");
            return;
        }

        if (!cmdMap.get(args[0]).execute(args)) {
            tui.writeLine("invalid parameter");
        }
    }

    @Override
    public void onSetGridSize(int width, int height) {
        grid = new char[width * 2][height * 2];
    }

    @Override
    public void onSetGameObjects(IBlock board, IBlock[] block) {
        this.board = board;
        this.block = block;
    }

    @Override
    public void onStartGame() {
        refreshGrid();
    }

    @Override
    public void onSelectBlock(int index) {
        selectedBlock = block[index];
        refreshGrid();
    }

    @Override
    public void onDropBlock() {
        selectedBlock = null;
        refreshGrid();
    }

    @Override
    public void onUpdate() {
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
        tui.writeLine("---------------------------------------------");
        for (int y = 0; y < grid[0].length; y++) {
            StringBuffer b = new StringBuffer();
            for (int x = 0; x < grid.length; x++) {
                b.append(grid[x][y]);
            }
            tui.writeLine(b.toString());
        }
        tui.writeLine("---------------------------------------------");
    }

    private void paintBlocks() {

        int index = 0;
        for (IBlock b : block) {
            for (IPolygon poly : b) {
                IPoint p = poly.calcMid();
                try {
                    grid[(int) (p.getX() * 2 + 0.01)][(int) (p.getY() * 2 + 0.01)] = Integer
                            .toString(index).charAt(0);
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
                    grid[(int) (p.getX() * 2 + 0.01)][(int) (p.getY() * 2 + 0.01)] = 'S';
                } catch (ArrayIndexOutOfBoundsException e) {
                    continue;
                }
            }

        }
    }

    @Override
    public void onWin() {
        tui.writeLine("you win!");
    }

}
