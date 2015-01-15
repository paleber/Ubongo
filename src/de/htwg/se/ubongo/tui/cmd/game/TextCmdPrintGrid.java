package de.htwg.se.ubongo.tui.cmd.game;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.htwg.se.ubongo.model.gameobject.IBlock;
import de.htwg.se.ubongo.util.cmd.TextCommand;
import de.htwg.se.ubongo.util.geo.IPoint;
import de.htwg.se.ubongo.util.geo.IPolygon;

/** Game-TextCommand to mirror a block vertical. */
public final class TextCmdPrintGrid implements TextCommand {

    private static final Logger LOGGER = LogManager.getLogger();
    private char[][] grid;
    private static final double FACTOR_HALF = 0.5;

    private IBlock board;
    private IBlock[] blocks;
    private IBlock selectedBlock;

    @Override
    public void execute(final String... args) {
        clearGrid();
        paintBoard();
        paintBlocks();
        printGrid();
    }

    private void clearGrid() {
        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[0].length; y++) {
                grid[x][y] = '.';
            }
        }
    }

    private void paintBoard() {
        for (IPolygon poly : board) {
            IPoint p = poly.calcMid();
            int x = (int) (p.getX() * 2);
            int y = (int) (p.getY() * 2);
            grid[x][y] = 'X';
        }
    }

    private void printGrid() {
        LOGGER.info("---------------------------------------------");
        for (int y = 0; y < grid[0].length; y++) {
            StringBuffer b = new StringBuffer();
            for (int x = 0; x < grid.length; x++) {
                b.append(grid[x][y]);
            }
            LOGGER.info(b.toString());
        }
        LOGGER.info("---------------------------------------------");
    }

    private void paintBlocks() {
        int index = 0;
        for (IBlock b : blocks) {
            for (IPolygon poly : b) {
                IPoint p = poly.calcMid();
                try {
                    int x = (int) (p.getX() * 2 + FACTOR_HALF);
                    int y = (int) (p.getY() * 2 + FACTOR_HALF);
                    grid[x][y] = Integer.toString(index).charAt(0);
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
                    int x = (int) (p.getX() * 2 + FACTOR_HALF);
                    int y = (int) (p.getY() * 2 + FACTOR_HALF);
                    grid[x][y] = 'S';
                } catch (ArrayIndexOutOfBoundsException e) {
                    continue;
                }
            }
        }
    }

    @Override
    public String getDescription() {
        return "print the grid";
    }

    /** Set the grid size.
     * @param width width
     * @param height height */
    public void setGridSize(final double width, final double height) {
        grid = new char[(int) width * 2][(int) height * 2];
    }

    /** Set the GameObjects.
     * @param board board
     * @param blocks blocks */
    public void setGameObjects(final IBlock board, final IBlock[] blocks) {
        this.board = board;
        this.blocks = blocks.clone();
    }

    /** Select a block.
     * @param block block */
    public void selectBlock(final IBlock block) {
        selectedBlock = block;
        execute();
    }

    /** Drop the selected block. */
    public void dropBlock() {
        selectedBlock = null;
    }

}