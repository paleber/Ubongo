package de.htwg.se.ubongo.gui;

import java.util.Scanner;

import de.htwg.se.ubongo.main.IMainController;
import de.htwg.se.ubongo.main.MainController;

/** MainControllerGUI.
 * @author Patrick Leber
 * @version 18.11.2014 */
public class MainControllerGUI implements IMainController {

    private final MainController mainCtrl;

    private static final MainControllerGUI INSTANCE = new MainControllerGUI();

    public static MainControllerGUI getInstance() {
        return INSTANCE;
    }

    private MainControllerGUI() {
        mainCtrl = MainController.register(this);
    }

    @Override
    public void showGame() {
        System.out.println("GUI: Not implemented: Show Game");
    }

    @Override
    public void showHelp() {
        System.out.println("GUI: Not implemented: Show Help");
    }

    @Override
    public void exit() {
        System.out.println("GUI: Beende Programm");
    }

    @Override
    public void start() {
        try (Scanner in = new Scanner(System.in);) {
            while (in.hasNext()) {
                switch (in.next()) {
                case "game":
                    mainCtrl.showGame();
                    break;
                case "help":
                    mainCtrl.showHelp();
                    break;
                case "exit":
                    in.close();
                    mainCtrl.exit();
                    return;
                }
            }
        }
    }

}
