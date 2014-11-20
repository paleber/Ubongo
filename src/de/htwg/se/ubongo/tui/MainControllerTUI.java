package de.htwg.se.ubongo.tui;

import de.htwg.se.ubongo.main.main.IMainController;
import de.htwg.se.ubongo.main.main.MainController;

/** TUI Implementaion for MainController
 * @author Patrick Leber
 * @version 18.11.2014 */
public class MainControllerTUI implements IMainController {

    private final MainController mc = MainController.getInstance();
  
    public MainControllerTUI() {
        mc.register(this);
    }

    @Override
    public void startMenu() {
        System.out.println("TUI: Start Menu");
    }

    @Override
    public void startGame() {
        System.out.println("TUI: Start Game");   
    }

    @Override
    public void startHelp() {
        System.out.println("TUI: Start Help");
    }

    @Override
    public void exit() {
        System.out.println("TUI: Exit");
    }

    /* @Override
    public void showGame() {
        System.out.println("Not implemented: Show Game");
    } */

    /*@Override
    public void showHelp() {
        System.out.println("Not implemented: Show Help");
    } */

    /*@Override
    public void stop() {
        System.out.println("Beende Programm");
    } */

    /* @Override
    public void start() {
        System.out.println("War hier");
        // Eigener Thread
        try (Scanner in = new Scanner(System.in);) {
            while (in.hasNext()) {
                switch (in.next()) {
                case "game":
                    mc.showGame();
                    break;
                case "help":
                    mc.showHelp();
                    break;
                case "exit":
                    in.close();
                    mc.stop();
                    return;
                }
            }
        }
    } */

}
