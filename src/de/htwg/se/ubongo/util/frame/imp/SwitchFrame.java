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
import de.htwg.se.ubongo.util.geo.IPoint;
import de.htwg.se.ubongo.util.geo.IVector;
import de.htwg.se.ubongo.util.geo.imp.Point2D;
import de.htwg.se.ubongo.util.geo.imp.Vector2D;
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
        private static final int NUM_CIRCLES = 12;
        private static final int MAX_COLOR_VALUE = 255;
        private static final Color COLOR_BACK = new Color(135, 206, 250);
        private static final int ALPHA_REDUCE = 30;
        private static final int FULL_ANGLE = 360;
        private static final int DISTANCE_TO_MID = 70;
        private static final int FRAMES_PRO_COLOR = 7;
        private static final int CIRCLE_DIAMETER = 12;

        private static final Color[] COLOR_CIRCLE = new Color[NUM_CIRCLES];
        static {
            for (int i = 0; i < COLOR_CIRCLE.length; i++) {
                int alpha = MAX_COLOR_VALUE - i * ALPHA_REDUCE;
                if (alpha < 0) {
                    alpha = 0;
                }
                COLOR_CIRCLE[i] =
                        new Color(MAX_COLOR_VALUE, MAX_COLOR_VALUE,
                                MAX_COLOR_VALUE, alpha);
            }
        }

        private int offset = 0;
        private int frameCounter = 0;
        private final IPoint[] mids = new IPoint[NUM_CIRCLES];

        private LoadContent() {
            IVector v = new Vector2D();
            for (int i = 0; i < mids.length; i++) {
                v.setAngleDegree((FULL_ANGLE / mids.length) * i);
                v.setLength(DISTANCE_TO_MID);
                mids[i] = new Point2D();
                mids[i].move(v);
            }

        }

        public void paint(final Graphics g) {

            frameCounter++;
            if (frameCounter == FRAMES_PRO_COLOR) {
                frameCounter = 0;
                offset = (offset + 1) % NUM_CIRCLES;
            }

            g.setColor(COLOR_BACK);
            g.fillRect(0, 0, getWidth(), getHeight());

            g.setColor(Color.BLUE);
            int x = getWidth() / 2;
            int y = getHeight() / 2;

            for (int i = 0; i < mids.length; i++) {
                g.setColor(COLOR_CIRCLE[(offset + i) % NUM_CIRCLES]);
                g.fillOval((int) mids[i].getX() + x, (int) mids[i].getY() + y,
                        CIRCLE_DIAMETER, CIRCLE_DIAMETER);
            }

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
