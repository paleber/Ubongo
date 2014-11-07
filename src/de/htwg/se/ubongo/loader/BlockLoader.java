package de.htwg.se.ubongo.loader;

import de.htwg.se.ubongo.gameobject.Block;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Created by Konstantin on 06.11.2014.
 * laden und Instanz behalten.
 */
public final class BlockLoader {

    public static final int NUM_BLOCKS = 12;
    public static final int NUM_VARIANTS = 6;
    public static final int NUM_BOARDS = 72;

    private static final Block[] blockstore = new Block[NUM_BLOCKS];


    public static final Block getBlock(int blocknum) {
        if (blocknum >= NUM_BLOCKS) {
            throw new IllegalArgumentException("block out of range");
        }

        if (blockstore[blocknum] == null) {
            Logger.getLogger(BlockLoader.class.getName()).log(Level.FINE, "loading block");
            blockstore[blocknum] = loadBlock(blocknum);
        } else {
            Logger.getLogger(BlockLoader.class.getName()).log(Level.FINER, "block already cached");
        }

        return blockstore[blocknum];
    }

    private static final Block loadBlock(int blocknum) {
        Block block;

        try (BufferedReader reader = new BufferedReader(new FileReader("res/blocks/" + String.valueOf(blocknum)))) {
            List<Integer> data = new LinkedList<>();
            int x = 0;
            int y = 0;
            String s;

            while ((s = reader.readLine()) != null) {
                for (byte b : s.getBytes()) {
                    if (b != '0') {
                        data.add(x);
                        data.add(y);
                    }
                    x++;
                }
                y++;
            }

            block = new Block(data);
        } catch (IOException e) {
            Logger.getLogger(BlockLoader.class.getName()).log(Level.SEVERE, e.getMessage());
            return null;
        }

        return block;
    }
}
