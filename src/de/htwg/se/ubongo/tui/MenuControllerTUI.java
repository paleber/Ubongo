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

    @Override
    public void startGame() {
        System.out.println("MenuTui: Start Game");
    }

    @Override
    public void startHelp() {
        System.out.println("MenuTui: Start Help");
    }

    @Override
    public void exit() {
        System.out.println("MenuTui: Exit");
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
        
        try {
            
            if (s.hasNext()) {
                switch (s.nextLine()) {
                case "game":
                    mc.startGame();
                    break;
                case "help":
                    mc.startHelp();
                    break;
                case "exit":
                    mc.exit();
                    break;
                }
            }
            
        } catch (IllegalStateException e) {
            return;
        }
        
    }

}
