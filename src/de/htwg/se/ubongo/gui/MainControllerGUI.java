package de.htwg.se.ubongo.gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import de.htwg.se.ubongo.ctrl.main.IMainController;
import de.htwg.se.ubongo.ctrl.main.MainController;

/** GUI Implementaion for MainController. */
public class MainControllerGUI implements IMainController {

    private final MainController mc = MainController.getInstance();

   // private final GUIFrame frame = new GUIFrame();

    private JPanel content = new JPanel();

    public MainControllerGUI() {
        mc.register(this);

        content.setLayout(new GridLayout(3, 1));

        JButton bnGame = new JButton("Neues Spiel");
        bnGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
               // mc.showGame();
            }
        });
        content.add(bnGame);

        JButton bnHelp = new JButton("Hilfe");
        bnHelp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
               // mc.showHelp();
            }
        });
        content.add(bnHelp);

        JButton bnExit = new JButton("Beenden");
        bnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                //mc.stop();
            }
        });
        content.add(bnExit);

        //frame.setContent(content);

    }

   /* @Override
    public void showGame() {
        System.out.println("GUI: Not implemented: Show Game");
    } */

    /* @Override
    public void showHelp() {
        System.out.println("GUI: Not implemented: Show Help");
    }

   /* @Override
    public void stop() {
        frame.exit();
    } */

   /* @Override
    public void start() {

    } */

    @Override
    public void startMenu() {
        System.out.println("GUI: Start Menu");
    }

    @Override
    public void startGame() {
        System.out.println("GUI: Start Game");   
    }

    @Override
    public void startHelp() {
        System.out.println("GUI: Start Help");
    }

    @Override
    public void exit() {
        System.out.println("GUI: Exit");
    }

}
