package de.htwg.se.ubongo.tui;

import de.htwg.se.ubongo.ctrl.*;
import de.htwg.se.ubongo.model.gameobject.Block;
import de.htwg.se.ubongo.model.gameobject.Board;
import de.htwg.se.ubongo.model.geo.Point2D;
import de.htwg.se.ubongo.model.geo.Polygon2D;
import de.htwg.se.ubongo.util.Timer;
import de.htwg.se.ubongo.util.Trigger;

/** TODO */
public class GameControllerTUI implements GameSubject, Trigger {

    private final GameController observer;
    private final MainControllerTUI tui;

    private final Timer timer = new Timer(this, 150);

    private Board board;
    private Block[] block;

    private char[][] grid;


    public GameControllerTUI(MainControllerTUI tui, GameController observer) {
        this.tui = tui;
        this.observer = observer;
        observer.register(this);
    }

    @Override
    public void startSubController() {
        tui.writeLine("--- game started ---");
        timer.start();
    }

    @Override
    public void stopSubController() {
        timer.stop();
        tui.writeLine("--- game stopped ---");
    }

    @Override
    public void onTrigger() {

        String line = tui.readLine();
        if (line == null) {
            return;
        }

        String[] args = line.split(" ");

        switch (args[0]) {
        case "help":
            tui.writeLine("help: print help");
            tui.writeLine("menu: back to menu");
            tui.writeLine("exit: shutdown the application");
            tui.writeLine("select INDEX: select block at index");
            tui.writeLine("drop: drop the selected block");
            tui.writeLine("move X Y: move the selected block");
            tui.writeLine("grid: print the grid");
            break;
        case "menu":
            observer.switchToMenu();
            break;
        case "exit":
            observer.shutdown();
            break;
        case "select":
            try {
                observer.selectBlock(Integer.parseInt(args[1]));
            } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
                tui.writeLine("select need integer argument");
            }
            break;
        case "drop":
            observer.dropBlock();
            break;
        case "move":
            try {
                observer.moveSelectedBlock(Double.parseDouble(args[1]),
                        Double.parseDouble(args[2]));
            } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
                tui.writeLine("select need integer argument");
            }
            break;
        case "grid":
            printGrid();
            break;
        default:
            tui.writeLine("unknown command, \"help\" for list of commands");
            break;
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
                grid[(int) (p.getX() * 2)][(int) (p.getY() * 2)] = Integer
                        .toString(i).charAt(0);
            }
        }

        if (selectedBlock != null) {
            for (int j = 0; j < selectedBlock.numPolys(); j++) {
                Point2D p = selectedBlock.getPoly(j).getMid();
                grid[(int) (p.getX() * 2)][(int) (p.getY() * 2)] = 'S';
            }
        }
    }

    private Block selectedBlock;

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

}
