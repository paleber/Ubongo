package de.htwg.se.ubongo.util.console;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;

import de.htwg.se.ubongo.util.console.imp.SimpleConsole;

public class SimpleConsoleTest {

    private IConsole console;
    
    @Before
    public void setUp() {
        console = new SimpleConsole();
    }
    
    @Test(expected = IllegalStateException.class)
    public void testOpenIllegalState() {
        console.open();
        console.open();
    }
    
    @Test(expected = IllegalStateException.class)
    public void testCloseIllegalState() {   
        console.close();
    }
    
    
    @Test
    public void writeLine() {
        System.setOut(new PrintStream(new OutputStream() {
            @Override
            public void write(int b) throws IOException {}
        }));
        console.open();
        console.writeLine("");
        console.close();
        System.setOut(System.out);
    }
    
    @Test
    public void testRead() {
        System.setIn(new ByteArrayInputStream("Test123".getBytes()));
        console.open();
        assertEquals("Test123", console.readLine());
        console.close();
        System.setIn(System.in);
    }
    
    @Test
    public void testReadNull() {
        console.open();
        assertNull(console.readLine());
        console.close();
    }
    
}
