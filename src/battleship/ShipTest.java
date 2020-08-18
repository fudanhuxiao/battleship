package battleship;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
/**
 * test the Ship class as well as all 5 subclasses.
 * @author Xiao Hu, Chen Lin.
 *
 */
public class ShipTest {
	
	private Ship ship1;
	private Ship ship2;
	private Ship ship3;
	private Ship ship4;
	private Ship ship5;

	/**
	 * set up the names of the 5 subclass.
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		ship1 = new Battleship();
		ship2 = new Cruiser();
		ship3 = new Destroyer();
		ship4 = new Submarine();
		ship5 = new EmptySea();
	}

	/**
	 * this method is different in every subclass, so test them all.
	 */
	@Test
	public void testGetLength() {
		assertEquals(ship1.getLength(),4);
		assertEquals(ship2.getLength(),3);
		assertEquals(ship3.getLength(),2);
		assertEquals(ship4.getLength(),1);
		assertEquals(ship5.getLength(),1);
	}

	/**
	 * test the getBowRow method. Since this method is same in every subclass, I did not test them all.
	 */
	@Test
	public void testGetBowRow() {
		ship1.setBowRow(1);
		assertEquals(ship1.getBowRow(),1);
		ship2.setBowRow(2);
		assertEquals(ship2.getBowRow(),2);
		ship3.setBowRow(3);
		assertEquals(ship3.getBowRow(),3);
		ship4.setBowRow(4);
		assertEquals(ship4.getBowRow(),4);
	}

	/**
	 * test the setBowRow method. Since this method is same in every subclass, I did not test them all.
	 */
	@Test
	public void testSetBowRow() {
		ship1.setBowRow(0);
		assertEquals(ship1.getBowRow(),0);
		ship2.setBowRow(1);
		assertEquals(ship2.getBowRow(),1);
		ship3.setBowRow(2);
		assertEquals(ship3.getBowRow(),2);
		ship5.setBowRow(3);
		assertEquals(ship5.getBowRow(),3);
	}

	/**
	 * test the getBowColumn method. Since this method is same in every subclass, I did not test them all.
	 */
	@Test
	public void testGetBowColumn() {
		ship1.setBowColumn(5);
		assertEquals(ship1.getBowColumn(),5);
		ship2.setBowColumn(6);
		assertEquals(ship2.getBowColumn(),6);
		ship3.setBowColumn(7);
		assertEquals(ship3.getBowColumn(),7);
		ship4.setBowColumn(8);
		assertEquals(ship4.getBowColumn(),8);
	}

	/**
	 * test the setBowColumn method. Since this method is same in every subclass, I did not test them all.
	 */
	@Test
	public void testSetBowColumn() {
		ship1.setBowColumn(6);
		assertEquals(ship1.getBowColumn(),6);
		ship2.setBowColumn(7);
		assertEquals(ship2.getBowColumn(),7);
		ship3.setBowColumn(8);
		assertEquals(ship3.getBowColumn(),8);
		ship5.setBowColumn(9);
		assertEquals(ship5.getBowColumn(),9);
	}

	/**
	 * test the isHorizontal method. Since this method is same in every subclass, I did not test them all.
	 */
	@Test
	public void testIsHorizontal() {
		ship1.setHorizontal(true);
		assertTrue(ship1.isHorizontal());
		ship2.setHorizontal(false);
		assertFalse(ship2.isHorizontal());
		ship3.setHorizontal(true);
		assertTrue(ship3.isHorizontal());
		ship4.setHorizontal(false);
		assertFalse(ship4.isHorizontal());
	}

	/**
	 * test the setHorizontal method. Since this method is same in every subclass, I did not test them all.
	 */
	@Test
	public void testSetHorizontal() {
		ship1.setHorizontal(false);
		assertFalse(ship1.isHorizontal());
		ship2.setHorizontal(true);
		assertTrue(ship2.isHorizontal());
		ship3.setHorizontal(false);
		assertFalse(ship3.isHorizontal());
		ship5.setHorizontal(true);
		assertTrue(ship5.isHorizontal());
	}

	/**
	 * this method is different in every subclass, so test them all.
	 */
	@Test
	public void testGetShipType() {
		assertEquals(ship1.getShipType(),"battleship");
		assertEquals(ship2.getShipType(),"cruiser");
		assertEquals(ship3.getShipType(),"destroyer");
		assertEquals(ship4.getShipType(),"submarine");
		assertEquals(ship5.getShipType(),"empty");
	}

	/**
	 * Since this method is same in every subclass, I did not test them all.
	 * 1. test the functionality of placing ships in and out the array boundary.
	 * 2. place a ship, and then test the overlap and touch (vertically, horizontally and diagonally) scenarios.
	 */
	@Test
	public void testOkToPlaceShipAt() {
		Ocean ocean = new Ocean();
		assertFalse(ship1.okToPlaceShipAt(-1, 0, true, ocean));
		assertFalse(ship1.okToPlaceShipAt(0, -1, true, ocean));
		assertFalse(ship1.okToPlaceShipAt(10, 10, true, ocean));
		assertTrue(ship1.okToPlaceShipAt(0, 0, true, ocean));
		assertTrue(ship1.okToPlaceShipAt(9, 6, true, ocean));
		assertFalse(ship1.okToPlaceShipAt(9, 7, true, ocean));
		
		assertFalse(ship1.okToPlaceShipAt(-1, 0, false, ocean));
		assertFalse(ship1.okToPlaceShipAt(0, -1, false, ocean));
		assertFalse(ship1.okToPlaceShipAt(10, 10, false, ocean));
		assertTrue(ship1.okToPlaceShipAt(0, 0, false, ocean));
		assertTrue(ship1.okToPlaceShipAt(6, 9, false, ocean));
		assertFalse(ship1.okToPlaceShipAt(7, 9, false, ocean));
		
		// I tested the all extreme cases for diagonal and horizontal and vertical adjacent places around this ship.
		ship1.placeShipAt(5, 5, true, ocean);
		assertFalse(ship1.okToPlaceShipAt(2, 4, false, ocean));
		assertFalse(ship1.okToPlaceShipAt(2, 5, false, ocean));
		assertFalse(ship1.okToPlaceShipAt(2, 6, false, ocean));
		assertFalse(ship1.okToPlaceShipAt(2, 7, false, ocean));
		assertFalse(ship1.okToPlaceShipAt(2, 8, false, ocean));
		assertFalse(ship1.okToPlaceShipAt(2, 9, false, ocean));
		assertFalse(ship1.okToPlaceShipAt(3, 4, false, ocean));
		assertFalse(ship1.okToPlaceShipAt(6, 4, false, ocean));
		assertFalse(ship1.okToPlaceShipAt(6, 5, false, ocean));
		assertFalse(ship1.okToPlaceShipAt(6, 6, false, ocean));
		assertFalse(ship1.okToPlaceShipAt(6, 7, false, ocean));
		assertFalse(ship1.okToPlaceShipAt(6, 8, false, ocean));
		assertFalse(ship1.okToPlaceShipAt(6, 9, false, ocean));
		assertFalse(ship1.okToPlaceShipAt(4, 5, true, ocean));
		assertFalse(ship1.okToPlaceShipAt(6, 5, true, ocean));
		assertTrue(ship1.okToPlaceShipAt(3, 5, true, ocean));
		assertTrue(ship1.okToPlaceShipAt(7, 5, true, ocean));
		assertTrue(ship1.okToPlaceShipAt(3, 1, true, ocean));
		assertFalse(ship1.okToPlaceShipAt(4, 1, true, ocean));
		
	}

	/**
	 * I did not test this method since it only generates random numbers
	 */
	@Test
	public void testGenerateValidPlacement() {
	}

	/**
	 * Since this method is same in every subclass, I did not test them all.
	 * test whether ship was placed by calling the isOccupied() around the place.
	 */
	@Test
	public void testPlaceShipAt() {
		Ocean ocean = new Ocean();
		ship1.placeShipAt(0, 0, true, ocean);
		assertTrue(ocean.isOccupied(0, 0));
		assertTrue(ocean.isOccupied(0, 1));
		assertTrue(ocean.isOccupied(0, 2));
		assertTrue(ocean.isOccupied(0, 3));
		assertFalse(ocean.isOccupied(1, 0));
		assertFalse(ocean.isOccupied(0, 4));
		ship1.placeShipAt(3, 3, false, ocean);
		assertTrue(ocean.isOccupied(3, 3));
		assertTrue(ocean.isOccupied(4, 3));
		assertTrue(ocean.isOccupied(5, 3));
		assertTrue(ocean.isOccupied(6, 3));
		assertFalse(ocean.isOccupied(2, 3));
		assertFalse(ocean.isOccupied(7, 3));
		assertFalse(ocean.isOccupied(2, 2));
		assertFalse(ocean.isOccupied(7, 4));
		assertFalse(ocean.isOccupied(3, 2));
		assertFalse(ocean.isOccupied(4, 2));
		assertFalse(ocean.isOccupied(5, 2));
		assertFalse(ocean.isOccupied(6, 2));
		assertFalse(ocean.isOccupied(3, 4));
		assertFalse(ocean.isOccupied(4, 4));
		assertFalse(ocean.isOccupied(5, 4));
		assertFalse(ocean.isOccupied(6, 4));
	}

	/**
	 * this method is different in EmptySea and all other subclasses. I test Battleship as a representation of all 4 ships.
	 * 1. tested the functionality of shoot at the ship before and after it sunk, and the shoot asides.
	 * 2. the EmptySea should always return false.
	 */
	@Test
	public void testShootAt() {
		Ocean ocean = new Ocean();
		ship1.placeShipAt(1, 1, true, ocean);
		assertTrue(ship1.shootAt(1, 1));
		assertTrue(ship1.shootAt(1, 2));

		assertFalse(ship1.shootAt(0, 0));
		assertFalse(ship1.shootAt(0, 1));
		assertFalse(ship1.shootAt(0, 2));
		assertFalse(ship1.shootAt(0, 3));
		assertFalse(ship1.shootAt(0, 4));
		assertFalse(ship1.shootAt(0, 5));
		assertFalse(ship1.shootAt(1, 0));
		assertFalse(ship1.shootAt(1, 5));
		assertFalse(ship1.shootAt(2, 0));
		assertFalse(ship1.shootAt(2, 1));
		assertFalse(ship1.shootAt(2, 2));
		assertFalse(ship1.shootAt(2, 3));
		assertFalse(ship1.shootAt(2, 4));
		assertFalse(ship1.shootAt(2, 5));

		assertTrue(ship1.shootAt(1, 3));
		assertTrue(ship1.shootAt(1, 4));
		
		assertFalse(ship1.shootAt(1, 1));
		assertFalse(ship1.shootAt(1, 2));
		assertFalse(ship1.shootAt(1, 3));
		assertFalse(ship1.shootAt(1, 4));
		
		ship5.placeShipAt(7, 7, true, ocean);
		assertFalse(ship5.shootAt(7, 7));
	}

	/**
	 * this method is different in EmptySea and all other subclasses. I test Battleship and Submarine as a representation of 4 ships.
	 * test the functionality of this method before and after the ship sunk, the EmptySea should always return false.
	 */
	@Test
	public void testIsSunk() {
		Ocean ocean = new Ocean();
		ship1.placeShipAt(0, 0, true, ocean);
		assertFalse(ship1.isSunk());
		ship1.shootAt(0, 0);
		assertFalse(ship1.isSunk());
		ship1.shootAt(0, 1);
		assertFalse(ship1.isSunk());
		ship1.shootAt(0, 2);
		assertFalse(ship1.isSunk());
		ship1.shootAt(0, 1);
		assertFalse(ship1.isSunk());
		ship1.shootAt(1, 1);
		assertFalse(ship1.isSunk());
		ship1.shootAt(0, 3);
		assertTrue(ship1.isSunk());
		
		ship4.placeShipAt(5, 5, true, ocean);
		assertFalse(ship4.isSunk());
		ship4.shootAt(5, 5);
		assertTrue(ship4.isSunk());
		
		ship5.placeShipAt(8, 8, true, ocean);
		ship5.shootAt(8, 8);
		assertFalse(ship5.isSunk());
	}

	/**
	 * this method is different in EmptySea and all other subclasses. I test Battleship as a representation of all 4 ships.
	 * test the toString before before and after a ship sunk, as well as the EmptySea.
	 */
	@Test
	public void testToString() {
		Ocean ocean = new Ocean();
		assertEquals(ship1.toString(),"S");
		ship1.placeShipAt(2, 2, true, ocean);
		assertEquals(ship1.toString(),"S");
		ship1.shootAt(2, 2);
		assertEquals(ship1.toString(),"S");
		ship1.shootAt(2, 3);
		assertEquals(ship1.toString(),"S");
		ship1.shootAt(2, 4);
		assertEquals(ship1.toString(),"S");
		ship1.shootAt(2, 4);
		assertEquals(ship1.toString(),"S");
		ship1.shootAt(1, 4);
		assertEquals(ship1.toString(),"S");
		ship1.shootAt(2, 5);
		assertEquals(ship1.toString(),"x");
		
		ship5.placeShipAt(8, 9, true, ocean);
		assertEquals(ship5.toString(),".");
		ship5.shootAt(8, 9);
		assertEquals(ship5.toString(),"-");
	}

}
