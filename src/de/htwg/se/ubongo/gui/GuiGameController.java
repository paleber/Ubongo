package de.htwg.se.ubongo.gui;

import javax.swing.JPanel;

import de.htwg.se.ubongo.ctrl.obs.IGameController;
import de.htwg.se.ubongo.ctrl.sub.IGameControllerSubject;
import de.htwg.se.ubongo.model.gameobject.IBlock;
import de.htwg.se.ubongo.util.frame.ISwitchFrame;

/** Subject-GameController of TUI. */
public final class GuiGameController  implements
        IGameControllerSubject {

    private final JPanel content = new JPanel();
    private final ISwitchFrame frame;

    private IBlock board;
    private IBlock[] blocks;
    
    private double width;
    private double height;
    
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
        // TODO Auto-generated method stub
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
        // TODO Auto-generated method stub
    }

    @Override
    public void onWin() {
        // TODO Auto-generated method stub
    }

   

}
