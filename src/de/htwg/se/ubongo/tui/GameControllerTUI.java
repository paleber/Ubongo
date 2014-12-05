package de.htwg.se.ubongo.tui;

import java.util.HashMap;
import java.util.Map;

import de.htwg.se.ubongo.ctrl.GameController;
import de.htwg.se.ubongo.ctrl.GameSubject;
import de.htwg.se.ubongo.model.gameobject.Block;
import de.htwg.se.ubongo.model.gameobject.Board;
import de.htwg.se.ubongo.model.geo.Point2D;
import de.htwg.se.ubongo.model.geo.Polygon2D;
import de.htwg.se.ubongo.util.*;

/** TODO */
public class GameControllerTUI implements GameSubject, Trigger {

    private final GameController observer;
    private final MainControllerTUI tui;

    private final Timer timer = new Timer(this, 150);

    private Board board;
    private Block[] block;

    private char[][] grid;

    private final Map<String, TextCommand> cmdMap = new HashMap<>();

    private Block selectedBlock;

    public GameControllerTUI(MainControllerTUI tui, GameController observer) {
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
    public void startSubController() {
        tui.writeLine("--- game started ---");
        tui.writeLine("\"help\" for list of command");
        timer.start();
    }

    @Override
    public void stopSubController() {
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
    public void onSetGameObjects(Board board, Block[] block) {
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
        for (int i = 0; i < board.numPolys(); i++) {
            Polygon2D poly = board.getPoly(i);
            Point2D p = poly.getMid();
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
        for (int i = 0; i < block.length; i++) {
            for (int j = 0; j < block[i].numPolys(); j++) {
                Point2D p = block[i].getPoly(j).getMid();
                try {
                    grid[(int) (p.getX() * 2 + 0.01)][(int) (p.getY() * 2 + 0.01)] = Integer
                            .toString(i).charAt(0);
                } catch (ArrayIndexOutOfBoundsException e) {
                    continue;
                }
            }
        }

        if (selectedBlock != null) {
            for (int j = 0; j < selectedBlock.numPolys(); j++) {
                Point2D p = selectedBlock.getPoly(j).getMid();
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
