package de.htwg.se.ubongo.gui;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import com.sun.org.apache.xerces.internal.impl.RevalidationHandler;

import de.htwg.se.ubongo.ctrl.obs.IGameController;
import de.htwg.se.ubongo.ctrl.sub.IGameControllerSubject;
import de.htwg.se.ubongo.model.gameobject.IBlock;
import de.htwg.se.ubongo.util.frame.ISwitchFrame;
import de.htwg.se.ubongo.util.geo.ILine;
import de.htwg.se.ubongo.util.timer.Timer;
import de.htwg.se.ubongo.util.timer.Trigger;

/** Subject-GameController of TUI. */
public final class GuiGameController implements IGameControllerSubject {

    private final JPanel content = new Content();
    private final ISwitchFrame frame;

    private IBlock board;
    private IBlock[] blocks;

    private double width;
    private double height;

    private class Content extends JPanel  {

        private static final long serialVersionUID = 1L;

        public Content() {
            setLayout(null);
            repaint();
            System.out.println("fds");
            
         
        }

        @Override
        public void paint(final Graphics g) {
            super.paint(g);
            
            System.out.println("###############");
            
            g.setColor(Color.CYAN);
            g.fillRect(0, 0, 200, 300);
            
            /* double sx = content.getWidth() / width;
            double sy = content.getHeight() / height;

            for (ILine edge : board.getEdgesOuter()) {
                int x1 = (int) (edge.getStart().getX() * sx);
                int y1 = (int) (edge.getStart().getY() * sy);
                int x2 = (int) (edge.getEnd().getX() * sx);
                int y2 = (int) (edge.getEnd().getY() * sy);

                g.drawLine(x1, y1, x2, y2);
            } */

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
        System.out.println("war hier");
        content.repaint();
        content.revalidate();
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
        System.out.println("gestartet");
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
