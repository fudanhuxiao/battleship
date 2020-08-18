package battleship;

public class EmptySea extends Ship {
	public EmptySea() {
		this.length = 1;
		this.hit = new boolean [] {false, false, false, false};
	}
	
	@Override
	public boolean shootAt(int row, int column) {
		this.hit[0] = true;
		return false;
	}
	
	@Override
	public boolean isSunk() {
		return false;
	}
	
	@Override
	public String toString() {
		if (this.hit[0] == true) {
			return "-";
		} else {
			return ".";
		}
	}
	
	@Override
	public int getLength() {
		return this.length;
	}
	
	@Override
	public String getShipType() {
		return "empty";
	}

}
