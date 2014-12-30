package de.htwg.se.ubongo.gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

import de.htwg.se.ubongo.ctrl.obs.IGameController;
import de.htwg.se.ubongo.ctrl.sub.IGameControllerSubject;
import de.htwg.se.ubongo.model.gameobject.IBlock;
import de.htwg.se.ubongo.util.frame.ISwitchFrame;
import de.htwg.se.ubongo.util.geo.ILine;

/** Subject-GameController of TUI. */
public final class GuiGameController implements IGameControllerSubject {

    private final JPanel content = new Content();
    private final ISwitchFrame frame;

    private IBlock board;
    private IBlock[] blocks;

    private double width;
    private double height;

    private class Content extends JPanel {

        private static final long serialVersionUID = 1L;

        @Override
        public void paint(final Graphics graphics) {
            super.paint(graphics);

            Graphics2D g = (Graphics2D) graphics;

            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                    RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g.setStroke(new BasicStroke(1));

            if (board == null) {
                return;
            }

            g.setColor(Color.CYAN);

            double xScale = content.getWidth() / width;
            double yScale = content.getHeight() / height;
            double scale = Math.min(xScale, yScale);

            double xOffset = (getWidth() - width * scale) / 2;
            double yOffset = (getHeight() - height * scale) / 2;

            g.setStroke(new BasicStroke(2));
            for (ILine edge : board.getEdgesOuter()) {

                int x1 = (int) (edge.getStart().getX() * scale + xOffset) ;
                int y1 = (int) (edge.getStart().getY() * scale + yOffset);
                int x2 = (int) (edge.getEnd().getX() * scale + xOffset) ;
                int y2 = (int) (edge.getEnd().getY() * scale + yOffset) ;

                g.setColor(Color.MAGENTA);
                g.drawLine(x1, y1, x2, y2);

            }
            g.setStroke(new BasicStroke(1));
            for (ILine edge : board.getEdgesInner()) {
                int x1 = (int) (edge.getStart().getX() * scale + xOffset);
                int y1 = (int) (edge.getStart().getY() * scale + yOffset);
                int x2 = (int) (edge.getEnd().getX() * scale + xOffset);
                int y2 = (int) (edge.getEnd().getY() * scale + yOffset);

                g.setColor(Color.MAGENTA);
                g.drawLine(x1, y1, x2, y2);
            }

        }

    }

    /** Default-Constructor.
     * @param observer Observer-GameController
     * @param frame ISwitchFrame */
    public GuiGameController(final IGameController observer,
            final ISwitchFrame frame) {

        observer.register(this);
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
        // TODO Auto-generated method stub
    }

    @Override
    public void onDropBlock() {
        // TODO Auto-generated method stub
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
