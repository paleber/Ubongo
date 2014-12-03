package de.htwg.se.ubongo.ctrl;

import de.htwg.se.ubongo.model.gameobject.Block;
import de.htwg.se.ubongo.model.gameobject.Board;
import de.htwg.se.ubongo.model.loader.BlockLoader;
import de.htwg.se.ubongo.model.loader.BoardLoader;

/** Game Controller. */
public final class GameController extends UbongoSubController<GameSubject> {

    private static final int BOARD_INDEX = 1;
    private static final int[] BLOCK_INDEX = { 9, 5, 3 };

    private Board board;
    private Block[] block;

    public GameController(MainController main) {
        super(main);
    }

    @Override
    protected void onStart() {
        System.out.println("Aufruf");

        
        board = BoardLoader.getBoard(BOARD_INDEX);
        System.out.println(board);
            
      
        block = new Block[BLOCK_INDEX.length];
        for(int i = 0; i < block.length; i++) {
            block[i] = BlockLoader.getBlock(BLOCK_INDEX[i]);
            System.out.println(block[i]);
        }


        

    }

    @Override
    protected void onStop() {}

}
