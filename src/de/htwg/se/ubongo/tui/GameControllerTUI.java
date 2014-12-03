package de.htwg.se.ubongo.tui;

import de.htwg.se.ubongo.ctrl.*;
import de.htwg.se.ubongo.util.Timer;

/** TODO */
public class GameControllerTUI implements GameSubject, Timer.Trigger {

    private final GameController observer;
    private final MainControllerTUI main;

    private final Timer timer = new Timer(this, 150);

    private int testCounter;

    private static final int MAX_COUNTS = 15;

    /** TODO
     * @param gameController 
     * @param mainControllerTUI */
    public GameControllerTUI(MainControllerTUI main, GameController observer) {
        this.main = main;
        this.observer = observer;
        observer.register(this);
    }

    @Override
    public void startSubController() {
        testCounter = 0;
        timer.start();
    }

    @Override
    public void stopSubController() {
        timer.stop();
    }

    @Override
    public void timerTrigger() {
        testCounter++;

        if (testCounter > MAX_COUNTS) {
            main.writeLine("You won the game!");
            observer.switchToMenu();
            return;
        }

        main.writeLine("Play Round ( " + testCounter + "|" + MAX_COUNTS + ")");

    }

}
