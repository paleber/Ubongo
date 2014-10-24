package de.htwg.se.ubongo;


import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


/** TODO
 * @author Patrick Leber
 * @version 23.10.2014 */
public class CellTest {

	private Cell cell1;
	
	@Before
	public void setUp() {
		cell1 = new Cell(1, 3);
	}
	
	@Test
	public void testGetPosX() {
		cell1.setPos(3, 0);
		assertEquals(cell1.getPosX(), 3);		
	}
	
	@Test
	public void testGetPosY() {
		cell1.setPos(0, -2);
		assertEquals(cell1.getPosY(), -2);		
	}
	
	@Test
	public void testSetPosition() {
		cell1.setPos(2, 5);
		assertEquals(cell1.getPosX(), 2);
		assertEquals(cell1.getPosY(), 5);
	}
	
}
