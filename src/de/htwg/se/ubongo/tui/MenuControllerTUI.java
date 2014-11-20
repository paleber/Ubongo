package de.htwg.se.ubongo.tui;

import de.htwg.se.ubongo.ctrl.MenuController;
import de.htwg.se.ubongo.util.Timer;

/** TextBased Implementation of Menu. */
public class MenuControllerTUI implements MenuController.Subject, Timer.Trigger {

    private final MenuController observer = MenuController.getInstance();
    private final MainControllerTUI main;

    private final Timer timer = new Timer(this, 10);

    public MenuControllerTUI(MainControllerTUI main) {
        this.main = main;
        observer.register(this);
    }

    @Override
    public void startController() {
        timer.start();
        main.writeLine("--- Menu opened ---");
        main.writeLine("cmd: game, help, exit");
    }

    @Override
    public void stopController() {
        timer.stop();
        main.writeLine("--- Menu closed ---");
    }

    @Override
    public void timerTrigger() {

        String line = main.readLine();

        if (line == null) {
            return;
        }

        switch (line) {
        case "game":
            observer.switchToGame();
            break;
        case "help":
            observer.switchToHelp();
            break;
        case "exit":
            observer.shutdown();
            break;
        default:
            main.writeLine("Unknown command");
            break;
        }
    }

}
