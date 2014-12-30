package de.htwg.se.ubongo.gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import de.htwg.se.ubongo.ctrl.obs.IGameController;
import de.htwg.se.ubongo.ctrl.sub.IGameControllerSubject;
import de.htwg.se.ubongo.model.gameobject.IBlock;
import de.htwg.se.ubongo.util.frame.ISwitchFrame;
import de.htwg.se.ubongo.util.geo.ILine;
import de.htwg.se.ubongo.util.geo.IPoint;
import de.htwg.se.ubongo.util.geo.IVector;
import de.htwg.se.ubongo.util.geo.imp.Point2D;
import de.htwg.se.ubongo.util.geo.imp.Vector2D;

/** Subject-GameController of TUI. */
public final class GuiGameController implements IGameControllerSubject {

    private final JPanel content = new Content();
    private final ISwitchFrame frame;

    private IBlock board;
    private IBlock[] blocks;

    private IBlock selected;

    private double width;
    private double height;
    private IGameController observer;

    private class Content extends JPanel {

        private static final long serialVersionUID = 1L;

        private double xOffset;
        private double yOffset;
        private double scale;

        private IPoint last = new Point2D();
        private IPoint cur = new Point2D();
        private IVector v = new Vector2D();
        private boolean pressed = false;

        public Content() {
            addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    pressed = true;
                    double x = (e.getX() - xOffset) / scale;
                    double y = (e.getY() - yOffset) / scale;
                    cur.set(x, y);
                    observer.selectBlock(cur);
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    pressed = false;
                    observer.dropBlock();
                }
            });

            addMouseMotionListener(new MouseAdapter() {
                @Override
                public void mouseDragged(MouseEvent e) {
                    if (pressed) {
                        last.copy(cur);
                        double x = (e.getX() - xOffset) / scale;
                        double y = (e.getY() - yOffset) / scale;
                        cur.set(x, y);
                        v.stretchBetween(last, cur);
                        observer.moveBlock(v);
                    }
                }
            });

            InputMap inputMap = getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
            ActionMap actionMap = getActionMap();

            inputMap.put(KeyStroke.getKeyStroke("released A"), "left");
            actionMap.put("left", new AbstractAction() {
                private static final long serialVersionUID = 1L;

                @Override
                public void actionPerformed(ActionEvent e) {
                    observer.rotateBlockLeft();
                }
            });

            inputMap.put(KeyStroke.getKeyStroke("D"), "right");
            actionMap.put("right", new AbstractAction() {
                private static final long serialVersionUID = 1L;

                @Override
                public void actionPerformed(ActionEvent e) {
                    observer.rotateBlockRight();
                }
            });

            inputMap.put(KeyStroke.getKeyStroke("S"), "horizontal");
            actionMap.put("horizontal", new AbstractAction() {
                private static final long serialVersionUID = 1L;

                @Override
                public void actionPerformed(ActionEvent e) {
                    observer.mirrorBlockHorizontal();
                }
            });

            inputMap.put(KeyStroke.getKeyStroke("W"), "vertical");
            actionMap.put("vertical", new AbstractAction() {
                private static final long serialVersionUID = 1L;

                @Override
                public void actionPerformed(ActionEvent e) {
                    observer.mirrorBlockVertical();
                }
            });

        }

        @Override
        public void paint(final Graphics graphics) {
            super.paint(graphics);

            if (board == null) {
                return;
            }

            Graphics2D g = (Graphics2D) graphics;

            double xScale = content.getWidth() / width;
            double yScale = content.getHeight() / height;
            scale = Math.min(xScale, yScale);

            xOffset = (getWidth() - width * scale) / 2;
            yOffset = (getHeight() - height * scale) / 2;

            // Board
            g.setColor(Color.GRAY);
            board.paint(g, scale, xOffset, yOffset);

            g.setStroke(new BasicStroke((int) (scale / 10)));
            g.setColor(Color.DARK_GRAY);
            for (ILine edge : board.getEdgesOuter()) {
                edge.paint(g, scale, xOffset, yOffset);
            }

            g.setStroke(new BasicStroke((int) (scale / 15)));
            for (ILine edge : board.getEdgesInner()) {
                edge.paint(g, scale, xOffset, yOffset);
            }

            // Blocks
            for (IBlock b : blocks) {
                if (b == selected) {
                    continue;
                }
                
                g.setColor(Color.CYAN);
                b.paint(g, scale, xOffset, yOffset);
                g.setColor(Color.CYAN.darker().darker());
                for (ILine edge : b.getEdgesOuter()) {
                    edge.paint(g, scale, xOffset, yOffset);
                }

            }

            if (selected != null) {
                g.setColor(Color.GREEN.brighter().brighter());
                selected.paint(g, scale, xOffset, yOffset);
                g.setColor(Color.GREEN.darker().darker());
                for (ILine edge : selected.getEdgesOuter()) {
                    edge.paint(g, scale, xOffset, yOffset);
                }
            }

        }

    }

    /** Default-Constructor.
     * @param observer Observer-GameController
     * @param frame ISwitchFrame */
    public GuiGameController(final IGameController observer,
            final ISwitchFrame frame) {

        observer.register(this);
        this.observer = observer;
        this.frame = frame;
    }

    @Override
    public void onStartSubController() {
        frame.showContent(content);
        // content.repaint();
    }

    @Override
    public void onStopSubController() {
        frame.hideContent();
    }

    @Override
    public void onSetGridSize(final double width, final double height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public void onSetGameObjects(final IBlock board, final IBlock[] blocks) {
        this.board = board;
        this.blocks = blocks.clone();

    }

    @Override
    public void onStartGame() {
        content.repaint();
    }

    @Override
    public void onSelectBlock(final IBlock block) {
        selected = block;
        content.repaint();
    }

    @Override
    public void onDropBlock() {
        selected = null;
        content.repaint();
    }

    @Override
    public void onUpdateGameObjects() {
        content.repaint();
    }

    @Override
    public void onWin() {
        // TODO Auto-generated method stub
    }

}
