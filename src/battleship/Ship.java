package battleship;

import java.util.*;

public abstract class Ship {
	protected int bowRow;
	protected int bowColumn;
	protected int length;
	protected boolean horizontal;
	protected boolean[] hit = new boolean[4];
	Random rnd;

	public int getLength() {
		return this.length;
	}

	public int getBowRow() {
		return this.bowRow;
	}

	public void setBowRow(int row) {
		this.bowRow = row;
	}

	public int getBowColumn() {
		return this.bowColumn;
	}

	public void setBowColumn(int column) {
		this.bowColumn = column;
	}

	public boolean isHorizontal() {
		return this.horizontal;
	}

	public void setHorizontal(boolean horizontal) {
		this.horizontal = horizontal;
	}

	public abstract String getShipType();

	public boolean okToPlaceShipAt(int row, int column, boolean horizontal, Ocean ocean) {
		if ((row>=0) && (column>=0) && (row<=9) && (column<=9)) {
			if (horizontal && (column+this.length-1<=9)) {
				for (int rowIndex=Math.max(row-1,0); rowIndex<=Math.min(row+1, 9); rowIndex++) {
					for (int columnIndex=Math.max(column-1, 0); columnIndex<=Math.min(column+this.length, 9); columnIndex++) {
						if (ocean.isOccupied(rowIndex, columnIndex)) {
							return false;
						}
					}
				}
				return true;
			} else if (!(horizontal) && (row+this.length-1<=9)) {
				for (int rowIndex=Math.max(row-1,0); rowIndex<=Math.min(row+this.length, 9); rowIndex++) {
					for (int columnIndex=Math.max(column-1, 0); columnIndex<=Math.min(column+1, 9); columnIndex++) {
						if (ocean.isOccupied(rowIndex, columnIndex)) {
							return false;
						}
					}
				}
				return true;
			}	
		}
		return false;
	}

	public void generateValidPlacement(Ocean ocean) {
		rnd = new Random();
		do {
			this.bowRow = rnd.nextInt(10);
			this.bowColumn = rnd.nextInt(10);
			this.horizontal = rnd.nextBoolean();
		} while (this.okToPlaceShipAt(this.bowRow, this.bowColumn, this.horizontal, ocean) == false);
	}

	public void placeShipAt(int row, int column, boolean horizontal, Ocean ocean) {
		if (this.okToPlaceShipAt(row, column, horizontal, ocean)) {
			this.bowRow = row;
			this.bowColumn = column;
			this.horizontal = horizontal;
			if (horizontal) {
				for (int index1=column; index1<column+this.length; index1++) {
					ocean.getShipArray()[row][index1] = this;
				}
			} else {
				for (int index2=row; index2<row+this.length; index2++) {
					ocean.getShipArray()[index2][column] = this;
				}
			}
		}

	}

	public boolean shootAt(int row, int column) {
		if (this.horizontal==false) {
			if ((column==this.bowColumn) && (row>=this.bowRow) &&(row<this.bowRow+this.length) && (this.isSunk()==false)) {
				this.hit[row-this.bowRow] = true;
				return true;
			} else {
				return false;
			}
		} else {
			if ((row==this.bowRow) &&(column>=this.bowColumn) && (column<this.bowColumn+this.length) && (this.isSunk()==false)) {
				this.hit[column-this.bowColumn] = true;
				return true;
			} else {
				return false;
			}
		}
	}

	public boolean isSunk() {
		for (int index=0; index<this.length; index++) {
			if (this.hit[index] == false) {
				return false;
			}
		}
		return true;
	}

	@Override
	public String toString() {
		if (this.isSunk()) {
			return "x";
		} else {
			return "S";
		}

	}

}
