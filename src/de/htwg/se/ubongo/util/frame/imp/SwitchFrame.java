package de.htwg.se.ubongo.util.frame.imp;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import de.htwg.se.ubongo.ctrl.obs.IMainController;
import de.htwg.se.ubongo.util.frame.ISwitchFrame;

/** Implementation of ISwitchFrame. */
public final class SwitchFrame implements ISwitchFrame {

    private final JFrame frame = new JFrame();
    private final LoadPanel loadPane = new LoadPanel();
    
    private final IMainController observer;
    
    private static final class LoadPanel extends JPanel {
        
        private static final Color COLOR_BACK = new Color(135, 206, 250);

        public void paint(final Graphics g) {
            g.setColor(COLOR_BACK);
            g.fillRect(0, 0, getWidth(), getHeight());
        }
    }

    
    public SwitchFrame(final IMainController observer) {
        this.observer = observer;
        frame.setLayout(null);
        frame.addWindowListener(new WindowAdapter() {
         
            @Override
            public void windowClosing(final WindowEvent e) {
                observer.shutdown();
                
            }
        }); 
        hideContent();
        
        frame.setVisible(true);
    }

    @Override
    public int getContentWidth() {
        return frame.getContentPane().getWidth();
    }

    @Override
    public int getContentHeight() {
        return frame.getContentPane().getHeight();
    }

    @Override
    public void setContentSize(int width, int height) {
        Container c = frame.getContentPane();
        Dimension dim = c.getSize();
        dim.setSize(width, height);
        c.setPreferredSize(dim);
        frame.pack();
        frame.setLocationRelativeTo(null);
    }

    @Override
    public void showContent(Container content) {
        frame.setContentPane(content);
        content.revalidate();
    }

    @Override
    public void hideContent() {
        frame.setContentPane(loadPane);
        loadPane.revalidate();
    }

    @Override
    public void shutdown() {
        frame.dispose();
    }

}
