package de.htwg.se.ubongo.gui;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import de.htwg.se.ubongo.main.IMainController;
import de.htwg.se.ubongo.main.MainController;

/** GUI Implementaion for MainController
 * @author Patrick Leber
 * @version 18.11.2014 */
public class MainControllerGUI implements IMainController {

    private final GUIFrame frame = new GUIFrame();

    private JPanel content = new JPanel();

    public MainControllerGUI() {
        MainController.register(this);

        content.setLayout(new GridLayout(3, 1));

        JButton bnGame = new JButton("Neues Spiel");
        //bnGame.addActionListener(ae -> MainController.showGame());
        content.add(bnGame);

        JButton bnHelp = new JButton("Hilfe");
        //bnHelp.addActionListener(ae -> MainController.showHelp());
        content.add(bnHelp);

        JButton bnExit = new JButton("Beenden");
        //bnExit.addActionListener(ae -> MainController.exit());
        content.add(bnExit);

        frame.setContent(content);

    }

    @Override
    public void showGame() {
        System.out.println("GUI: Not implemented: Show Game");
    }

    @Override
    public void showHelp() {
        System.out.println("GUI: Not implemented: Show Help");
    }

    @Override
    public void exit() {
        frame.exit();
    }

    @Override
    public void start() {

    }

}
