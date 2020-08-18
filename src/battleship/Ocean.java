package battleship;

public class Ocean {
	private Ship[][] ships = new Ship[10][10];
	private int shotsFired;
	private int hitCount;
	private int shipsSunk;
	private EmptySea emptyShip;

	public Ocean() {
		for (int index1=0; index1<10; index1++) {
			for (int index2=0; index2<10; index2++) {
				emptyShip = new EmptySea();
				ships[index1][index2] = emptyShip;
			}
		}
		shotsFired = 0;
		hitCount = 0;
		shipsSunk = 0;
	}
	
	public void placeAllShipsRandomly() {
		Battleship battleship = new Battleship();
		battleship.generateValidPlacement(this);
		battleship.placeShipAt(battleship.bowRow, battleship.bowColumn, battleship.horizontal, this);
		
		for(int index1=1; index1<=2; index1++) {
			Cruiser cruiser = new Cruiser();
			cruiser.generateValidPlacement(this);
			cruiser.placeShipAt(cruiser.bowRow, cruiser.bowColumn, cruiser.horizontal, this);
		}
		
		for(int index2=1; index2<=3; index2++) {
			Destroyer destroyer = new Destroyer();
			destroyer.generateValidPlacement(this);
			destroyer.placeShipAt(destroyer.bowRow, destroyer.bowColumn, destroyer.horizontal, this);
		}
		
		for(int index3=1; index3<=4; index3++) {
			Submarine submarine = new Submarine();
			submarine.generateValidPlacement(this);
			submarine.placeShipAt(submarine.bowRow, submarine.bowColumn, submarine.horizontal, this);
		}
	}

	public boolean isOccupied(int row, int column) {
		if (ships[row][column].getShipType().equals("empty")) {
			return false;
		} else {
			return true;
		}
	}

	public boolean shootAt(int row, int column) {
		this.shotsFired += 1;
		if (this.ships[row][column].shootAt(row, column)) {
			this.hitCount += 1;
			if (this.ships[row][column].isSunk()) {
				this.shipsSunk +=1;
			}
			return true;
		} else {
			return false;
		}
	}

	public int getShotsFired() {
		return this.shotsFired;
	}

	public int getHitCount() {
		return this.hitCount;
	}

	public int getShipsSunk() {
		return this.shipsSunk;
	}

	public boolean isGameOver() {
		if (this.shipsSunk == 10) {
			return true;
		} else {
			return false;
		}
	}

	public Ship[][] getShipArray() {
		return ships;
	}

	public void print() {
		for (int index0=0; index0<10; index0++) {
			System.out.print("\t");
			System.out.print(index0);
		}
		System.out.print("\n");
		for (int index1=0; index1<10; index1++) {
			System.out.print(index1);
			System.out.print("\t");
			for (int index2=0; index2<10; index2++) {
				if ((this.isOccupied(index1, index2)==false)) {
					System.out.print(this.ships[index1][index2]);
				} else if ((this.ships[index1][index2].isHorizontal()) && this.ships[index1][index2].hit[index2-this.ships[index1][index2].bowColumn]) {
					System.out.print(this.ships[index1][index2]);
				} else if ((this.ships[index1][index2].isHorizontal()==false) && this.ships[index1][index2].hit[index1-this.ships[index1][index2].bowRow]) {
					System.out.print(this.ships[index1][index2]);
				} else {
					System.out.print("A");
				}
				System.out.print("\t");
			}
			System.out.print("\n");
		}
	}
}
