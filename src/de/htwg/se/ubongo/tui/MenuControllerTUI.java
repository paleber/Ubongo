package de.htwg.se.ubongo.tui;

import de.htwg.se.ubongo.ctrl.MainController;
import de.htwg.se.ubongo.ctrl.MenuController;
import de.htwg.se.ubongo.util.Timer;

/** TextBased Implementation of Menu. */
public class MenuControllerTUI implements MenuController.MenuSubject, Timer.Trigger {

    private final MenuController observer = MenuController.getInstance();
    private final MainController mainObserver = MainController.getInstance();
    private final MainControllerTUI main;

    private final Timer timer = new Timer(this, 10);

    public MenuControllerTUI(MainControllerTUI main) {
        this.main = main;
        observer.register(this);
    }

    @Override
    public void startSubController() {
        timer.start();
        main.writeLine("--- Menu opened ---");
        main.writeLine("cmd: game, help, exit");
    }

    @Override
    public void stopSubController() {
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
            mainObserver.switchToGame();
            break;
        case "help":
            mainObserver.switchToHelp();
            break;
        case "exit":
            mainObserver.shutdown();
            break;
        default:
            main.writeLine("Unknown command");
            break;
        }
    }

}
