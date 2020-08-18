package battleship;

public class Destroyer extends Ship {
	public Destroyer() {
		this.length = 2;
		this.hit = new boolean[] {false, false, false, false};
	}
	
	@Override
	public int getLength() {
		return this.length;
	}
	
	@Override
	public String getShipType() {
		return "destroyer";
	}
}
