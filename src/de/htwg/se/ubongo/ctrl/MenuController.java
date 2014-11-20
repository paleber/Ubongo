package de.htwg.se.ubongo.ctrl;

import java.util.ArrayList;
import java.util.List;

import de.htwg.se.ubongo.util.Controller;

/** Menu Controller. */
public final class MenuController implements Controller {

    /** MenuController Interface. */
    public interface Subject extends Controller {}

    private static MenuController INSTANCE = new MenuController();

    public static MenuController getInstance() {
        return INSTANCE;
    }

    private MainController main = MainController.getInstance();

    private final List<Subject> subjects = new ArrayList<>();

    public void register(Subject s) {
        subjects.add(s);
    }

    @Override
    public void startController() {
        System.out.println("MenuCtrl: Starte");
        for (Subject s : subjects) {
            s.startController();
        }
    }

    @Override
    public void stopController() {
        System.out.println("MenuCtrl: Stop");
        for (Subject s : subjects) {
            s.stopController();
        }
    }

    public void startGame() {
        System.out.println("StartMenu");
        main.switchToGame();
    }

    public void startHelp() {
        System.out.println("StartHelp");
        main.switchToHelp();
    }

    public void shutdown() {
        System.out.println("StartHelp");
        main.shutdown();
    }

}
