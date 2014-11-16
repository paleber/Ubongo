package de.htwg.se.ubongo.loader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import de.htwg.se.ubongo.gameobject.Block;

/** Created by Konstantin on 06.11.2014. laden und Instanz behalten. */
public final class BlockLoader {

    public static final int NUM_BLOCKS = 12;
    public static final int NUM_VARIANTS = 6;
    public static final int NUM_BOARDS = 72;

    private BlockLoader() {
    }

    private static final Block[] BLOCKSTORE = new Block[NUM_BLOCKS];

    public static Block getBlock(int blocknum) {
        if (blocknum >= NUM_BLOCKS) {
            throw new IllegalArgumentException("block out of range");
        }

        if (BLOCKSTORE[blocknum] == null) {
            Logger.getLogger(BlockLoader.class.getName()).log(Level.FINE,
                    "loading block");
            BLOCKSTORE[blocknum] = loadBlock(blocknum);
        } else {
            Logger.getLogger(BlockLoader.class.getName()).log(Level.FINER,
                    "block already cached");
        }

        return BLOCKSTORE[blocknum];
    }

    private static Block loadBlock(int blocknum) {
        Block block;

        try {
            BufferedReader reader = new BufferedReader(new FileReader(
                    "res/blocks/" + blocknum));

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
            reader.close();
        } catch (IOException e) {
            Logger.getLogger(BlockLoader.class.getName()).log(Level.SEVERE,
                    e.getMessage());
            return null;
        }

        return block;
    }
}
