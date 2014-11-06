package de.htwg.se.ubongo.loader;

import de.htwg.se.ubongo.gameobject.Block;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;


/**
 * Created by Konstantin on 06.11.2014.
 * laden und Instanz behalten.
 */
public final class BlockLoader {

    public static final int NUM_BLOCKS = 12;
    public static final int NUM_VARIANTS = 6;
    public static final int NUM_BOARDS = 72;

    private static final Block[] blockstore = new Block[NUM_BLOCKS];

    BlockLoader() {
    }

    Block getBlock(int blocknum) {
        if (blockstore[blocknum] == null) {
            blockstore[blocknum] = loadBlock(blocknum);
        }
        return blockstore[blocknum];
    }

    private Block loadBlock(int blocknum) {
        Block block;

        try {
            List<byte[]> l = new LinkedList();
            BufferedReader reader = new BufferedReader(new FileReader("res/blocks/" + String.valueOf(blocknum)));
            String s;

            while ((s = reader.readLine()) != null) {
                byte[] bs = s.getBytes();
                for (int i = 0; i < bs.length; i++) {
                    bs[i] -= '0';
                }
                l.add(bs);
            }

            block = new Block(l.toArray(new byte[0][]));
        } catch (IOException e) {
            return null;
        }

        return block;
    }
}
