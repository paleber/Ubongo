package de.htwg.se.ubongo.tui;

import de.htwg.se.ubongo.ctrl.MainController;

/** TUI Implementaion for MainController
 * @author Patrick Leber
 * @version 18.11.2014 */
public class MainControllerTUI implements  MainController.Subject {

    private final MainController mc = MainController.getInstance();
 
    public MainControllerTUI() {
        mc.register(this);
    }

    @Override
    public void startController() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void stopController() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void switchToMenu() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void switchToGame() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void switchToHelp() {
        // TODO Auto-generated method stub
        
    }

 


}
