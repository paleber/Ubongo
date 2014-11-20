package de.htwg.se.ubongo.tui;
import de.htwg.se.ubongo.ctrl.GameController;
import de.htwg.se.ubongo.util.Timer;

/** TODO
 *  */
public class GameControllerTUI implements GameController.Subject, Timer.Trigger {

    
    private final GameController observer = GameController.getInstance();
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
    public void startController() {
        testCounter = 0;
        timer.start();
    }

    @Override
    public void stopController() {
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

        main.writeLine("Play Round ( " + testCounter + "|"
                + MAX_COUNTS + ")");

    }
    
}
