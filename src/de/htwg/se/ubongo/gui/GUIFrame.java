package de.htwg.se.ubongo.gui;

import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JFrame;

/** Frame with switchable content.
 * @author Patrick Leber
 * @version 19.11.2014 */
public class GUIFrame {

    private JFrame frame = new JFrame();
    
    public GUIFrame() {
        frame.getContentPane().setPreferredSize(new Dimension(640, 480));
        frame.pack();
        frame.setVisible(true);
    }
    
    public void setContent(Container c) {
        frame.setContentPane(c);
    }
    
    public void exit() {
        frame.setVisible(false);
        frame.dispose();
    }
    
}
