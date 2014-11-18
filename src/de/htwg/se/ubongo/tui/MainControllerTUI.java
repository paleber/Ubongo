package de.htwg.se.ubongo.tui;

import java.util.Scanner;

import de.htwg.se.ubongo.main.IMainController;
import de.htwg.se.ubongo.main.MainController;

/** TODO
 * @author Patrick Leber
 * @version 18.11.2014 */
public class MainControllerTUI implements IMainController {

    private final MainController mainCtrl;

    private static final MainControllerTUI INSTANCE = new MainControllerTUI();

    public static MainControllerTUI getInstance() {
        return INSTANCE;
    }

    private MainControllerTUI() {
        mainCtrl = MainController.register(this);
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
