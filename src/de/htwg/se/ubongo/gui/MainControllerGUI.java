package de.htwg.se.ubongo.gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import de.htwg.se.ubongo.ctrl.MainSubject;

/** GUI Implementaion for MainController. */
public class MainControllerGUI implements MainSubject {

    //private final MainController mc = MainController.getInstance();

   // private final GUIFrame frame = new GUIFrame();

    private JPanel content = new JPanel();

    public MainControllerGUI() {
        //mc.register(this);

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
    public void shutdown() {
        // TODO Auto-generated method stub
        
    }

}
