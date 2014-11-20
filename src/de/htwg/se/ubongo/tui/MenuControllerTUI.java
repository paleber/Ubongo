package de.htwg.se.ubongo.tui;

import java.util.Scanner;

import de.htwg.se.ubongo.ctrl.MenuController;
import de.htwg.se.ubongo.util.Controller;
import de.htwg.se.ubongo.util.Timer;

/** TODO */
public class MenuControllerTUI implements Controller, MenuController.Subject,
        Timer.Trigger {

    private final MenuController mc = MenuController.getInstance();

    public MenuControllerTUI() {

    }

    
    private final Timer timer = new Timer(this, 10);

    private Scanner s;

    @Override
    public void startController() {
        s = new Scanner(System.in);
        timer.start();
    }

    @Override
    public void stopController() {
        s.close();
        timer.stop();
    }

    @Override
    public void timerTrigger() {
        // TODO Auto-generated method stub
        
    }

    

}
