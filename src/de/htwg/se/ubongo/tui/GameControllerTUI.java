package de.htwg.se.ubongo.tui;

import de.htwg.se.ubongo.ctrl.*;
import de.htwg.se.ubongo.util.Timer;

/** TODO */
public class GameControllerTUI implements GameSubject, Timer.Trigger {

    private final MainController mainObserver = MainController.getInstance();
    private final MainControllerTUI main;

    private final Timer timer = new Timer(this, 150);

    private int testCounter;

    private static final int MAX_COUNTS = 15;

    /** TODO
     * @param mainControllerTUI */
    public GameControllerTUI(MainControllerTUI main) {
        this.main = main;
        GameController.getInstance().register(this);
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
            mainObserver.switchToMenu();
            return;
        }

        main.writeLine("Play Round ( " + testCounter + "|" + MAX_COUNTS + ")");

    }

}
