package de.htwg.se.ubongo.ctrl.menu;

import java.util.ArrayList;
import java.util.List;

import de.htwg.se.ubongo.util.Controller;

/** Menu Controller. */
public class MenuController implements Controller {

    /** MenuController Interface. */
    public interface Subject {

        void startGame();

        void startHelp();

        void exit();

    }

    private static MenuController INSTANCE = new MenuController();

    public static MenuController getInstance() {
        return INSTANCE;
    }

    private final List<Subject> subjects = new ArrayList<>();

    public void register(Subject s) {
        subjects.add(s);
    }

    @Override
    public void startController() {
        System.out.println("Start MenuController");
    }

    @Override
    public void stopController() {
        System.out.println("Stop MenuController");
    }

    public void startGame() {
        // TODO Auto-generated method stub

    }

    public void startHelp() {
        // TODO Auto-generated method stub

    }

    public void exit() {
        // TODO Auto-generated method stub

    }

    /*public void showGame() { for (IMainController i : imc) { i.showGame(); }
     * }
     * 
     * public void showHelp() { for (IMainController i : imc) { i.showHelp(); }
     * }
     * 
     * public void stop() { for (IMainController i : imc) { i.stop(); } }
     * 
     * public void start() { for (IMainController i : imc) { i.start(); } } */
}
