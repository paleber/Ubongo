package de.htwg.se.ubongo.ctrl.menu;

import de.htwg.se.ubongo.ctrl.main.IController;

/** Menu Controller. */
public class MenuController implements IController {
    
    private static MenuController INSTANCE = new MenuController();

    public static MenuController getInstance() { 
        return INSTANCE;
    }
    
    
    
    
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
