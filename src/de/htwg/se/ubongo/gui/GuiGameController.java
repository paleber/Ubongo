package de.htwg.se.ubongo.gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import com.google.inject.Injector;

import de.htwg.se.ubongo.cfg.UbongoModule;
import de.htwg.se.ubongo.ctrl.obs.IGameController;
import de.htwg.se.ubongo.ctrl.sub.IGameControllerSubject;
import de.htwg.se.ubongo.model.data.ILevelData;
import de.htwg.se.ubongo.model.gameobject.IBlock;
import de.htwg.se.ubongo.util.frame.ISwitchFrame;
import de.htwg.se.ubongo.util.geo.ILine;
import de.htwg.se.ubongo.util.geo.IPoint;
import de.htwg.se.ubongo.util.geo.IVector;
import de.htwg.se.ubongo.util.geo.imp.Vector2D;

/** Subject-GameController of TUI. */
public final class GuiGameController implements IGameControllerSubject {

    private static final Injector INJECTOR = UbongoModule.getInjector();
    
    private final Content content = new Content();
    private final ISwitchFrame frame;

    private IBlock board;
    private IBlock[] blocks;

    private IBlock selected;

    private double width;
    private double height;
    private IGameController observer;
    private ILevelData levelData;

    private static final int LINE_FACTOR_1 = 10;
    private static final int LINE_FACTOR_2 = 15;

    private final class Content extends JPanel {

        private static final long serialVersionUID = 1L;

        private double xOffset;
        private double yOffset;
        private double scale;

        private IPoint last = INJECTOR.getInstance(IPoint.class);
        private IPoint cur = INJECTOR.getInstance(IPoint.class);
        private IVector v = new Vector2D();
        private boolean pressed = false;

        private final MouseListener mouseListener = new MouseAdapter() {

            @Override
            public void mousePressed(final MouseEvent e) {
                pressed = true;
                double x = (e.getX() - xOffset) / scale;
                double y = (e.getY() - yOffset) / scale;
                cur.set(x, y);
                observer.selectBlock(cur);
            }

            @Override
            public void mouseReleased(final MouseEvent e) {
                pressed = false;
                observer.dropBlock();
            }
        };

        private final MouseMotionListener mouseMotionListener =
                new MouseAdapter() {

                    @Override
                    public void mouseDragged(final MouseEvent e) {
                        if (pressed) {
                            last.copy(cur);
                            double x = (e.getX() - xOffset) / scale;
                            double y = (e.getY() - yOffset) / scale;
                            cur.set(x, y);
                            v.stretchBetween(last, cur);
                            observer.moveBlock(v);
                        }
                    }
                };

        private final JButton bnRandom = new JButton("Zufälliges Level");
        private final JButton bnLevel = new JButton("Levelauswahl");
        private final JButton bnMenu = new JButton("Menü");
        private final JLabel lbWin = new JLabel("Level abgeschlossen");
                
        private Content() {
            ActionMap actionMap = getActionMap();
            actionMap.put("left", new AbstractAction() {
                private static final long serialVersionUID = 1L;

                @Override
                public void actionPerformed(final ActionEvent e) {
                    observer.rotateBlockLeft();
                }
            });

            actionMap.put("right", new AbstractAction() {
                private static final long serialVersionUID = 1L;

                @Override
                public void actionPerformed(final ActionEvent e) {
                    observer.rotateBlockRight();
                }
            });

            actionMap.put("horizontal", new AbstractAction() {
                private static final long serialVersionUID = 1L;

                @Override
                public void actionPerformed(final ActionEvent e) {
                    observer.mirrorBlockHorizontal();
                }
            });

            actionMap.put("vertical", new AbstractAction() {
                private static final long serialVersionUID = 1L;

                @Override
                public void actionPerformed(final ActionEvent e) {
                    observer.mirrorBlockVertical();
                }
            });
            
            bnRandom.addActionListener(new ActionListener() {
                
                @Override
                public void actionPerformed(final ActionEvent e) {
                    levelData.random();
                    observer.switchToGame();
                    
                }
            });
            
            bnMenu.addActionListener(new ActionListener() {
                
                @Override
                public void actionPerformed(final ActionEvent e) {
                    observer.switchToMenu();
                }
            });
            add(bnMenu);
            
            bnLevel.addActionListener(new ActionListener() {
                
                @Override
                public void actionPerformed(final ActionEvent e) {
                    observer.switchToLevel();
                }
            });
            
            
        }

        private void setGameRunning() {
            remove(bnLevel);
            remove(bnRandom);
            remove(lbWin);
            
            addMouseListener(mouseListener);
            addMouseMotionListener(mouseMotionListener);

            InputMap inputMap = getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
            inputMap.put(KeyStroke.getKeyStroke("A"), "left");
            inputMap.put(KeyStroke.getKeyStroke("D"), "right");
            inputMap.put(KeyStroke.getKeyStroke("S"), "horizontal");
            inputMap.put(KeyStroke.getKeyStroke("W"), "vertical");

        }

        public void setGameFinished() {

            getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).clear();
  
            removeMouseListener(mouseListener);
            removeMouseMotionListener(mouseMotionListener);
            
            
            add(bnLevel);
            add(bnRandom);
            add(lbWin);


            revalidate();

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

            g.setStroke(new BasicStroke((int) (scale / LINE_FACTOR_1)));
            g.setColor(Color.DARK_GRAY);
            for (ILine edge : board.getEdgesOuter()) {
                edge.paint(g, scale, xOffset, yOffset);
            }

            g.setStroke(new BasicStroke((int) (scale / LINE_FACTOR_2)));
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
     * @param frame ISwitchFrame
     * @param iLevelData */
    public GuiGameController(final IGameController observer,
            final ISwitchFrame frame, ILevelData levelData) {

        observer.register(this);
        this.observer = observer;
        this.frame = frame;
        this.levelData = levelData;
    }

    @Override
    public void onStartSubController() {
        content.setGameRunning();
        frame.showContent(content, "board " + levelData.getBoardIndex()
                + " - variant " + levelData.getVariant());
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
        content.setGameFinished();

    }

}
