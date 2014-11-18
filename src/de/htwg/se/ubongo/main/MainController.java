package de.htwg.se.ubongo.main;

import java.util.ArrayList;
import java.util.List;

/** TODO
 * @author Patrick Leber
 * @version 18.11.2014 */
public final class MainController implements IMainController {

    private final List<IMainController> imc = new ArrayList<>();

    private static final MainController INSTANCE = new MainController();

    private MainController() {
    }
    
    public static MainController getInstance() {
        return INSTANCE;
    }

    public static MainController register(IMainController i) {
        INSTANCE.imc.add(i);
        return INSTANCE;
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

    public void exit() {
        for (IMainController i : imc) {
            i.exit();
        }
    }

    @Override
    public void start() {
        for (IMainController i: imc) {
            i.start();
        }
    }

}
