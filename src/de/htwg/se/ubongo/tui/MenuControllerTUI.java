package de.htwg.se.ubongo.tui;

import de.htwg.se.ubongo.util.Timer;
import de.htwg.se.ubongo.util.Trigger;

/** TextBased Implementation of Menu. */
public class MenuControllerTUI implements MenuSubject, Trigger {

    
        
    private final MenuController observer;

    private final MainControllerTUI main;

    private final Timer timer = new Timer(this, 10);

    public MenuControllerTUI(MainControllerTUI main, MenuController iMenuController) {
        this.main = main;
        this.observer = iMenuController;
        iMenuController.register(this);
    }

    @Override
    public void onStartSubController() {
        timer.start();
        main.writeLine("--- Menu opened ---");
        main.writeLine("cmd: game, help, exit");
    }

    @Override
    public void onStopSubController() {
        timer.stop();
        main.writeLine("--- Menu closed ---");
    }

    @Override
    public void onTrigger() {

        String line = main.readLine();

        if (line == null) {
            return;
        }

        switch (line) {
        case "game":
            observer.switchToLevel();
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
