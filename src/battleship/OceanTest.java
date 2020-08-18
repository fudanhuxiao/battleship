package battleship;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * test the Ocean class.
 * @author Xiao Hu and Chen Lin.
 *
 */
public class OceanTest {
	
	Ocean ocean;

	/**
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		ocean = new Ocean();
	}

	/**
	 * test the constructor by examining whether every element is instance of EmptySea at beginning. 
	 * test all the variable values at the beginning.
	 */
	@Test
	public void testOcean() {
		assertTrue(ocean instanceof Ocean);
		Ship[][] shipArray = ocean.getShipArray();
		for (int i=0; i<10; i++) {
			for (int j=0; j<10; j++) {
				assertTrue(shipArray[i][j] instanceof EmptySea);
			}
		}
		assertEquals(ocean.getShotsFired(),0);
		assertEquals(ocean.getHitCount(),0);
		assertEquals(ocean.getShipsSunk(),0);
	}

	/**
	 * This method generate ships randomly, so I only examined whether the total number of ships is right.
	 */
	@Test
	public void testPlaceAllShipsRandomly() {
		int battleshipNum = 0;
		int cruiserNum = 0;
		int destroyerNum = 0;
		int submarineNum = 0;
		Ship[][] shipArray = ocean.getShipArray();
		for (int i=0; i<10; i++) {
			for (int j=0; j<10; j++) {
				if (shipArray[i][j] instanceof Battleship) {
					battleshipNum += 1;
				} else if (shipArray[i][j] instanceof Cruiser) {
					cruiserNum += 1;
				} else if (shipArray[i][j] instanceof Destroyer) {
					destroyerNum += 1;
				} else if (shipArray[i][j] instanceof Submarine) {
					submarineNum += 1;
				}
			}
		}
		assertEquals(0, battleshipNum);
		assertEquals(0, cruiserNum);
		assertEquals(0, destroyerNum);
		assertEquals(0, submarineNum);
		
		ocean.placeAllShipsRandomly();
		for (int i=0; i<10; i++) {
			for (int j=0; j<10; j++) {
				if (shipArray[i][j] instanceof Battleship) {
					battleshipNum += 1;
				} else if (shipArray[i][j] instanceof Cruiser) {
					cruiserNum += 1;
				} else if (shipArray[i][j] instanceof Destroyer) {
					destroyerNum += 1;
				} else if (shipArray[i][j] instanceof Submarine) {
					submarineNum += 1;
				}
			}
		}
		assertEquals(4, battleshipNum);
		assertEquals(6, cruiserNum);
		assertEquals(6, destroyerNum);
		assertEquals(4, submarineNum);
	}

	/**
	 * test the occupied scenarios before and after a ship is placed. 
	 */
	@Test
	public void testIsOccupied() {
		assertFalse(ocean.isOccupied(1, 1));
		Submarine submarine = new Submarine();
		submarine.placeShipAt(1, 1, true, ocean);
		assertTrue(ocean.isOccupied(1, 1));
		
		assertFalse(ocean.isOccupied(3, 2));
		Destroyer destroyer = new Destroyer();
		destroyer.placeShipAt(3, 2, true, ocean);
		assertTrue(ocean.isOccupied(3, 2));
		assertTrue(ocean.isOccupied(3, 3));
		assertFalse(ocean.isOccupied(3, 4));
		
		assertFalse(ocean.isOccupied(4, 5));
		Battleship battleship = new Battleship();
		battleship.placeShipAt(4, 5, false, ocean);
		assertTrue(ocean.isOccupied(4, 5));
		assertTrue(ocean.isOccupied(5, 5));
		assertTrue(ocean.isOccupied(6, 5));
		assertTrue(ocean.isOccupied(7, 5));
		assertFalse(ocean.isOccupied(8, 5));
		
		assertFalse(ocean.isOccupied(6, 7));
		Cruiser cruiser = new Cruiser();
		cruiser.placeShipAt(6, 7, false, ocean);
		assertTrue(ocean.isOccupied(6, 7));
		assertTrue(ocean.isOccupied(7, 7));
		assertTrue(ocean.isOccupied(8, 7));
		assertFalse(ocean.isOccupied(9, 7));
		
		assertFalse(ocean.isOccupied(9, 8));
		EmptySea emptysea = new EmptySea();
		ocean.getShipArray()[9][8] = emptysea;
		assertFalse(ocean.isOccupied(9, 8));
	}

	/**
	 * test the logical return and all change of variables after shoot at, shoot aside or sunk a target.
	 */
	@Test
	public void testShootAt() {
		assertEquals(0,ocean.getShotsFired());
		assertEquals(0,ocean.getHitCount());
		assertEquals(0,ocean.getShipsSunk());
		Battleship battleship = new Battleship();
		battleship.placeShipAt(2, 3, true, ocean);
		assertTrue(ocean.shootAt(2, 3));
		assertEquals(1,ocean.getShotsFired());
		assertEquals(1,ocean.getHitCount());
		assertEquals(0,ocean.getShipsSunk());
		assertTrue(ocean.shootAt(2, 4));
		assertEquals(2,ocean.getShotsFired());
		assertEquals(2,ocean.getHitCount());
		assertEquals(0,ocean.getShipsSunk());
		assertTrue(ocean.shootAt(2, 5));
		assertEquals(3,ocean.getShotsFired());
		assertEquals(3,ocean.getHitCount());
		assertEquals(0,ocean.getShipsSunk());
		assertFalse(ocean.shootAt(2, 2));
		assertEquals(4,ocean.getShotsFired());
		assertEquals(3,ocean.getHitCount());
		assertEquals(0,ocean.getShipsSunk());
		assertFalse(ocean.shootAt(2, 7));
		assertEquals(5,ocean.getShotsFired());
		assertEquals(3,ocean.getHitCount());
		assertEquals(0,ocean.getShipsSunk());
		assertFalse(ocean.shootAt(1, 3));
		assertEquals(6,ocean.getShotsFired());
		assertEquals(3,ocean.getHitCount());
		assertEquals(0,ocean.getShipsSunk());
		assertFalse(ocean.shootAt(3, 4));
		assertEquals(7,ocean.getShotsFired());
		assertEquals(3,ocean.getHitCount());
		assertEquals(0,ocean.getShipsSunk());
		assertTrue(ocean.shootAt(2, 4));
		assertEquals(8,ocean.getShotsFired());
		assertEquals(4,ocean.getHitCount());
		assertEquals(0,ocean.getShipsSunk());
		assertTrue(ocean.shootAt(2, 6));
		assertEquals(9,ocean.getShotsFired());
		assertEquals(5,ocean.getHitCount());
		assertEquals(1,ocean.getShipsSunk());
		assertFalse(ocean.shootAt(2, 3));
		assertEquals(10,ocean.getShotsFired());
		assertEquals(5,ocean.getHitCount());
		assertEquals(1,ocean.getShipsSunk());
		assertFalse(ocean.shootAt(2, 4));
		assertEquals(11,ocean.getShotsFired());
		assertEquals(5,ocean.getHitCount());
		assertEquals(1,ocean.getShipsSunk());
		assertFalse(ocean.shootAt(2, 5));
		assertEquals(12,ocean.getShotsFired());
		assertEquals(5,ocean.getHitCount());
		assertEquals(1,ocean.getShipsSunk());
		
		Cruiser cruiser = new Cruiser();
		cruiser.placeShipAt(6, 5, false, ocean);
		assertTrue(ocean.shootAt(6, 5));
		assertEquals(13,ocean.getShotsFired());
		assertEquals(6,ocean.getHitCount());
		assertEquals(1,ocean.getShipsSunk());
		assertTrue(ocean.shootAt(7, 5));
		assertEquals(14,ocean.getShotsFired());
		assertEquals(7,ocean.getHitCount());
		assertEquals(1,ocean.getShipsSunk());
		assertFalse(ocean.shootAt(9, 5));
		assertEquals(15,ocean.getShotsFired());
		assertEquals(7,ocean.getHitCount());
		assertEquals(1,ocean.getShipsSunk());
		assertFalse(ocean.shootAt(5, 5));
		assertEquals(16,ocean.getShotsFired());
		assertEquals(7,ocean.getHitCount());
		assertEquals(1,ocean.getShipsSunk());
		assertTrue(ocean.shootAt(7, 5));
		assertEquals(17,ocean.getShotsFired());
		assertEquals(8,ocean.getHitCount());
		assertEquals(1,ocean.getShipsSunk());
		assertFalse(ocean.shootAt(6, 4));
		assertEquals(18,ocean.getShotsFired());
		assertEquals(8,ocean.getHitCount());
		assertEquals(1,ocean.getShipsSunk());
		assertFalse(ocean.shootAt(7, 6));
		assertEquals(19,ocean.getShotsFired());
		assertEquals(8,ocean.getHitCount());
		assertEquals(1,ocean.getShipsSunk());
		assertTrue(ocean.shootAt(8, 5));
		assertEquals(20,ocean.getShotsFired());
		assertEquals(9,ocean.getHitCount());
		assertEquals(2,ocean.getShipsSunk());
		assertFalse(ocean.shootAt(6, 5));
		assertEquals(21,ocean.getShotsFired());
		assertEquals(9,ocean.getHitCount());
		assertEquals(2,ocean.getShipsSunk());
		assertFalse(ocean.shootAt(7, 5));
		assertEquals(22,ocean.getShotsFired());
		assertEquals(9,ocean.getHitCount());
		assertEquals(2,ocean.getShipsSunk());
	}

	/**
	 * test getShotsFired, variable shotsFired should always increases 1 no matter hit or not.
	 */
	@Test
	public void testGetShotsFired() {
		assertEquals(0,ocean.getShotsFired());
		ocean.shootAt(0, 0);
		assertEquals(1,ocean.getShotsFired());
		ocean.shootAt(5, 7);
		assertEquals(2,ocean.getShotsFired());
		ocean.shootAt(9, 8);
		assertEquals(3,ocean.getShotsFired());
		ocean.shootAt(6, 2);
		assertEquals(4,ocean.getShotsFired());
		Submarine submarine = new Submarine();
		submarine.placeShipAt(1, 1, true, ocean);
		ocean.shootAt(1, 1);
		assertEquals(5,ocean.getShotsFired());
	}

	/**
	 * test getHitCount, variable hitCount should increases 1 only after hitting the target and before it sunk.
	 */
	@Test
	public void testGetHitCount() {
		assertEquals(0,ocean.getHitCount());
		Battleship battleship = new Battleship();
		battleship.placeShipAt(2, 3, true, ocean);
		assertTrue(ocean.shootAt(2, 3));
		assertEquals(1,ocean.getHitCount());
		assertTrue(ocean.shootAt(2, 4));
		assertEquals(2,ocean.getHitCount());
		assertTrue(ocean.shootAt(2, 5));
		assertEquals(3,ocean.getHitCount());
		assertFalse(ocean.shootAt(2, 2));
		assertEquals(3,ocean.getHitCount());
		assertFalse(ocean.shootAt(2, 7));
		assertEquals(3,ocean.getHitCount());
		assertFalse(ocean.shootAt(1, 3));
		assertEquals(3,ocean.getHitCount());
		assertFalse(ocean.shootAt(3, 4));
		assertEquals(3,ocean.getHitCount());
		assertTrue(ocean.shootAt(2, 4));
		assertEquals(4,ocean.getHitCount());
		assertTrue(ocean.shootAt(2, 6));
		assertEquals(5,ocean.getHitCount());
		assertFalse(ocean.shootAt(2, 3));
		assertEquals(5,ocean.getHitCount());
		assertFalse(ocean.shootAt(2, 4));
		assertEquals(5,ocean.getHitCount());
		assertFalse(ocean.shootAt(2, 5));
		assertEquals(5,ocean.getHitCount());
		
		Cruiser cruiser = new Cruiser();
		cruiser.placeShipAt(6, 5, false, ocean);
		assertTrue(ocean.shootAt(6, 5));
		assertEquals(13,ocean.getShotsFired());
		assertEquals(6,ocean.getHitCount());
		assertEquals(1,ocean.getShipsSunk());
		assertTrue(ocean.shootAt(7, 5));
		assertEquals(14,ocean.getShotsFired());
		assertEquals(7,ocean.getHitCount());
		assertEquals(1,ocean.getShipsSunk());
		assertFalse(ocean.shootAt(9, 5));
		assertEquals(15,ocean.getShotsFired());
		assertEquals(7,ocean.getHitCount());
		assertEquals(1,ocean.getShipsSunk());
		assertFalse(ocean.shootAt(5, 5));
		assertEquals(16,ocean.getShotsFired());
		assertEquals(7,ocean.getHitCount());
		assertEquals(1,ocean.getShipsSunk());
		assertTrue(ocean.shootAt(7, 5));
		assertEquals(17,ocean.getShotsFired());
		assertEquals(8,ocean.getHitCount());
		assertEquals(1,ocean.getShipsSunk());
		assertFalse(ocean.shootAt(6, 4));
		assertEquals(18,ocean.getShotsFired());
		assertEquals(8,ocean.getHitCount());
		assertEquals(1,ocean.getShipsSunk());
		assertFalse(ocean.shootAt(7, 6));
		assertEquals(19,ocean.getShotsFired());
		assertEquals(8,ocean.getHitCount());
		assertEquals(1,ocean.getShipsSunk());
		assertTrue(ocean.shootAt(8, 5));
		assertEquals(20,ocean.getShotsFired());
		assertEquals(9,ocean.getHitCount());
		assertEquals(2,ocean.getShipsSunk());
		assertFalse(ocean.shootAt(6, 5));
		assertEquals(21,ocean.getShotsFired());
		assertEquals(9,ocean.getHitCount());
		assertEquals(2,ocean.getShipsSunk());
		assertFalse(ocean.shootAt(7, 5));
		assertEquals(22,ocean.getShotsFired());
		assertEquals(9,ocean.getHitCount());
		assertEquals(2,ocean.getShipsSunk());
	}

	/**
	 * test getShipsSunk, the variable shipsSunk increases 1 only after all part of the ship was hit.
	 */
	@Test
	public void testGetShipsSunk() {
		assertEquals(0,ocean.getShipsSunk());
		Battleship battleship = new Battleship();
		battleship.placeShipAt(2, 3, true, ocean);
		assertTrue(ocean.shootAt(2, 3));
		assertEquals(0,ocean.getShipsSunk());
		assertTrue(ocean.shootAt(2, 4));
		assertEquals(0,ocean.getShipsSunk());
		assertTrue(ocean.shootAt(2, 5));
		assertEquals(0,ocean.getShipsSunk());
		assertFalse(ocean.shootAt(2, 2));
		assertEquals(0,ocean.getShipsSunk());
		assertFalse(ocean.shootAt(2, 7));
		assertEquals(0,ocean.getShipsSunk());
		assertFalse(ocean.shootAt(1, 3));
		assertEquals(0,ocean.getShipsSunk());
		assertFalse(ocean.shootAt(3, 4));
		assertEquals(0,ocean.getShipsSunk());
		assertTrue(ocean.shootAt(2, 4));
		assertEquals(0,ocean.getShipsSunk());
		assertTrue(ocean.shootAt(2, 6));
		assertEquals(1,ocean.getShipsSunk());
		assertFalse(ocean.shootAt(2, 3));
		assertEquals(1,ocean.getShipsSunk());
		assertFalse(ocean.shootAt(2, 4));
		assertEquals(1,ocean.getShipsSunk());
		assertFalse(ocean.shootAt(2, 5));
		assertEquals(1,ocean.getShipsSunk());
		
		Cruiser cruiser = new Cruiser();
		cruiser.placeShipAt(6, 5, false, ocean);
		assertTrue(ocean.shootAt(6, 5));
		assertEquals(1,ocean.getShipsSunk());
		assertTrue(ocean.shootAt(7, 5));
		assertEquals(1,ocean.getShipsSunk());
		assertFalse(ocean.shootAt(9, 5));
		assertEquals(1,ocean.getShipsSunk());
		assertFalse(ocean.shootAt(5, 5));
		assertEquals(1,ocean.getShipsSunk());
		assertTrue(ocean.shootAt(7, 5));
		assertEquals(1,ocean.getShipsSunk());
		assertFalse(ocean.shootAt(6, 4));
		assertEquals(1,ocean.getShipsSunk());
		assertFalse(ocean.shootAt(7, 6));
		assertEquals(1,ocean.getShipsSunk());
		assertTrue(ocean.shootAt(8, 5));
		assertEquals(2,ocean.getShipsSunk());
		assertFalse(ocean.shootAt(6, 5));
		assertEquals(2,ocean.getShipsSunk());
		assertFalse(ocean.shootAt(7, 5));
		assertEquals(2,ocean.getShipsSunk());
	}

	/**
	 * test the game over after shooting at 10*10 places.
	 */
	@Test
	public void testIsGameOver() {
		assertFalse(ocean.isGameOver());
		ocean.placeAllShipsRandomly();
		for (int i=0; i<10; i++) {
			for (int j=0; j<10; j++) {
				ocean.shootAt(i, j);
			}
		}
		assertTrue(ocean.isGameOver());
		assertEquals(10,ocean.getShipsSunk());
	}

	/**
	 * test get ship array by examining the number of ships in the array.
	 */
	@Test
	public void testGetShipArray() {
		Ship[][] shipArray = ocean.getShipArray();
		for (int i=0; i<10; i++) {
			for (int j=0; j<10; j++) {
				assertTrue(shipArray[i][j] instanceof Ship);
			}
		}
		int battleshipNum = 0;
		int cruiserNum = 0;
		int destroyerNum = 0;
		int submarineNum = 0;
		ocean.placeAllShipsRandomly();
		for (int i=0; i<10; i++) {
			for (int j=0; j<10; j++) {
				if (shipArray[i][j] instanceof Battleship) {
					battleshipNum += 1;
				} else if (shipArray[i][j] instanceof Cruiser) {
					cruiserNum += 1;
				} else if (shipArray[i][j] instanceof Destroyer) {
					destroyerNum += 1;
				} else if (shipArray[i][j] instanceof Submarine) {
					submarineNum += 1;
				}
			}
		}
		assertEquals(4, battleshipNum);
		assertEquals(6, cruiserNum);
		assertEquals(6, destroyerNum);
		assertEquals(4, submarineNum);
	}

	/**
	 * I do not test this method since it only does printing.
	 */
	@Test
	public void testPrint() {
	}

}
