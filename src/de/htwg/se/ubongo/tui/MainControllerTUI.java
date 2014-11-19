package de.htwg.se.ubongo.tui;

import java.util.Scanner;

import de.htwg.se.ubongo.main.IMainController;
import de.htwg.se.ubongo.main.MainController;

/** TUI Implementaion for MainController
 * @author Patrick Leber
 * @version 18.11.2014 */
public class MainControllerTUI implements IMainController {

  
    public MainControllerTUI() {
        MainController.register(this);
    }

    @Override
    public void showGame() {
        System.out.println("Not implemented: Show Game");
    }

    @Override
    public void showHelp() {
        System.out.println("Not implemented: Show Help");
    }

    @Override
    public void exit() {
        System.out.println("Beende Programm");
    }

    @Override
    public void start() {
        // Eigener Thread
        try (Scanner in = new Scanner(System.in);) {
            while (in.hasNext()) {
                switch (in.next()) {
                case "game":
                    MainController.showGame();
                    break;
                case "help":
                    MainController.showHelp();
                    break;
                case "exit":
                    in.close();
                    MainController.exit();
                    return;
                }
            }
        }
    }

}
