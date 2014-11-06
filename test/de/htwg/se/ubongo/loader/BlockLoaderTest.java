package de.htwg.se.ubongo.loader;

import junit.framework.TestCase;
import org.junit.Test;

public class BlockLoaderTest extends TestCase {

    @Test
    public void testLoadBlock() throws Exception {
        BlockLoader l = new BlockLoader();
        System.out.println(l.getBlock(2).toString());
    }
}