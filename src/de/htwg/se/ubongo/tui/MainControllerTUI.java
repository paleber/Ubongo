package de.htwg.se.ubongo.tui;

import java.util.Scanner;

import de.htwg.se.ubongo.main.IMainController;
import de.htwg.se.ubongo.main.MainController;

/** TODO
 * @author Patrick Leber
 * @version 18.11.2014 */
public class MainControllerTUI implements IMainController {

    private final MainController mainCtrl;

    public MainControllerTUI() {
        mainCtrl = MainController.register(this);
        
        Scanner in = new Scanner(System.in);
        
        while(in.hasNext()) {
            switch(in.next()) {
            case "game":
                mainCtrl.showGame();
                break;
            case "help":
                mainCtrl.showHelp();
                break;
            case "exit":
                mainCtrl.exit();
                in.close();
                break;
            }
            
            
            
        }
        
    }
    
    @Override
    public void showGame() {
        System.out.println("Zeige Spiel");
    }

    @Override
    public void showHelp() {
        System.out.println("Zeige Hilfe");
    }

    @Override
    public void exit() {
        System.out.println("Beende Programm");   
    }

}
