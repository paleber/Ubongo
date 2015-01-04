package de.htwg.se.ubongo.util.frame.imp;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

import de.htwg.se.ubongo.ctrl.obs.IMainController;
import de.htwg.se.ubongo.util.frame.ISwitchFrame;
import de.htwg.se.ubongo.util.timer.Timer;
import de.htwg.se.ubongo.util.timer.Trigger;

/** Implementation of ISwitchFrame. */
public final class SwitchFrame implements ISwitchFrame, Trigger {

    private final JFrame frame = new JFrame();
    private final LoadContent loadContent = new LoadContent();

    private final Timer repaintTimer = new Timer(this, 16);
    
    /* ContentPane for LoadScreen. */
    private static final class LoadContent extends JPanel {

        private static final long serialVersionUID = 1L;

        private static final Color COLOR_BACK = new Color(135, 206, 250);

        public void paint(final Graphics g) {
            g.setColor(COLOR_BACK);
            g.fillRect(0, 0, getWidth(), getHeight());
        }

    }

    /** Constructor.
     * @param observer IMainController. */
    public SwitchFrame(final IMainController observer) {
        frame.setLayout(null);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(final WindowEvent e) {
                observer.shutdown();
            }
        });
        hideContent();
        frame.setVisible(true);
        repaintTimer.start();
    }

    @Override
    public void setContentSize(final int width, final int height) {
        Container c = frame.getContentPane();
        Dimension dim = c.getSize();
        dim.setSize(width, height);
        c.setPreferredSize(dim);
        frame.pack();
        frame.setLocationRelativeTo(null);
    }

    @Override
    public void showContent(final Container content) {
        frame.setContentPane(content);
        content.revalidate();
    }

    @Override
    public void hideContent() {
        frame.setContentPane(loadContent);
        loadContent.revalidate();
    }

    @Override
    public void shutdown() {
        repaintTimer.stop();
        frame.dispose();
    }

    @Override
    public void onTrigger() {
       frame.repaint();
        
    }

}
