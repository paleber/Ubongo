package de.htwg.se.ubongo.gui;

import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JFrame;

/** Frame with switchable content. */
public class GUIFrame {

    private static final Dimension DIM_DEFAULT = new Dimension(640, 480);    
    
    private final JFrame frame = new JFrame();

    public GUIFrame() {
        frame.getContentPane().setPreferredSize(DIM_DEFAULT);
        frame.pack();
        frame.setVisible(true);
    }

    public void setContent(Container c) {
        frame.setContentPane(c);
        frame.revalidate();
    }

    public void exit() {
        frame.setVisible(false);
        frame.dispose();
    }

}
