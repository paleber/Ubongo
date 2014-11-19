package de.htwg.se.ubongo.main;

import java.util.ArrayList;
import java.util.List;

/** TODO
 * @author Patrick Leber
 * @version 18.11.2014 */
public final class MainController {

    private static final List<IMainController> IMC = new ArrayList<>();

    private MainController() {
    }

    public static void register(IMainController i) {
        IMC.add(i);
    }

    public static void showGame() {
        for (IMainController i : IMC) {
            i.showGame();
        }
    }

    public static void showHelp() {
        for (IMainController i : IMC) {
            i.showHelp();
        }
    }

    public static void exit() {
        for (IMainController i : IMC) {
            i.exit();
        }
    }

    public static void start() {
        for (IMainController i : IMC) {
            i.start();
        }
    }

}
