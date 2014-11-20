package de.htwg.se.ubongo.main.menu;

import de.htwg.se.ubongo.main.main.IController;

/** Menu Controller. */
public class MenuController implements IController {

    @Override
    public void start() {
        System.out.println("Start MenuController");
        
    }

    @Override
    public void stop() {
        System.out.println("Stop MenuController");
        
    }

    /*
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

    */
}
