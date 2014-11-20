package de.htwg.se.ubongo.main.main;

import java.util.ArrayList;
import java.util.List;

/** Main Controller. */
public final class MainController implements IMainController {

    private final List<IMainController> imc = new ArrayList<>();

    private static final MainController INSTANCE = new MainController();

    private MainController() {}

    public static MainController getInstance() {
        return INSTANCE;
    }

    public void register(IMainController i) {
        imc.add(i);
    }

    public void showGame() {
        for (IMainController i : imc) {
            i.showGame();
        }
    }

    public void showHelp() {
        for (IMainController i : imc) {
            i.showHelp();
        }
    }

    public void stop() {
        for (IMainController i : imc) {
            i.stop();
        }
    }

    public void start() {
        for (IMainController i : imc) {
            i.start();
        }
    }

}
