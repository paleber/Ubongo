package test.java.de.htwg.se.ubongo.loader;


import main.java.de.htwg.se.ubongo.loader.BlockLoader;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class BlockLoaderTest {

    @Test
    public void testGetBlock() throws Exception {
        assertNotNull(BlockLoader.getBlock(10));
        assertNotNull(BlockLoader.getBlock(10));
    }
}