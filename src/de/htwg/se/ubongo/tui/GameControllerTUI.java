package de.htwg.se.ubongo.tui;

import de.htwg.se.ubongo.ctrl.*;
import de.htwg.se.ubongo.model.gameobject.Block;
import de.htwg.se.ubongo.model.gameobject.Board;
import de.htwg.se.ubongo.util.Timer;
import de.htwg.se.ubongo.util.Trigger;

/** TODO */
public class GameControllerTUI implements GameSubject, Trigger {

    private final GameController observer;
    private final MainControllerTUI tui;

    private final Timer timer = new Timer(this, 150);

    private int testCounter;
    private Board board;
    private Block[] block;
    private int height;
    private int width;

    private static final int MAX_COUNTS = 15;

    /** TODO
     * @param gameController 
     * @param mainControllerTUI */
    public GameControllerTUI(MainControllerTUI tui, GameController observer) {
        this.tui = tui;
        this.observer = observer;
        observer.register(this);
    }

    @Override
    public void startSubController() {
        tui.writeLine("--- game started ---");
        testCounter = 0;
        timer.start();
    }

    @Override
    public void stopSubController() {
        timer.stop();
        tui.writeLine("--- game stopped ---");
    }

    @Override
    public void onTrigger() {
        testCounter++;

        if (testCounter > MAX_COUNTS) {
            tui.writeLine("You won the game!");
            observer.switchToMenu();
            return;
        }

        tui.writeLine("Play Round ( " + testCounter + "|" + MAX_COUNTS + ")");

    }

    @Override
    public void setGridSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public void setBoard(Board board) {
        this.board = board;
    }
    
    @Override
    public void setBlocks(Block[] block) {
        this.block = block;
    }

    @Override
    public void startGame() {
        printGrid();
    }

    private void printGrid() {
        char[][] a = new char[width][height];
        for(int x = 0; x < width; x++) {
            for(int y = 0; y < height; y++) {
                a[x][y] = '.';
            }
        }
        
        for(int y = 0; y < height; y++) {
            StringBuffer b = new StringBuffer();
            for(int x = 0;x < width; x++) {
                b.append(a[x][y]);
            }
            tui.writeLine(b.toString());
        }
        
        
        
    }
    
}
