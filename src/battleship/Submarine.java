package battleship;

public class Submarine extends Ship {
	public Submarine() {
		this.length = 1;
		this.hit = new boolean[] {false, false, false, false};
	}
	
	@Override
	public int getLength() {
		return this.length;
	}
	
	@Override
	public String getShipType() {
		return "submarine";
	}
}
