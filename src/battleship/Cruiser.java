package battleship;

public class Cruiser extends Ship {
	public Cruiser() {
		this.length = 3;
		this.hit = new boolean[] {false, false, false, false};
	}
	
	@Override
	public int getLength() {
		return this.length;
	}
	
	@Override
	public String getShipType() {
		return "cruiser";
	}
}
