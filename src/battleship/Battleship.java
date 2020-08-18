package battleship;

public class Battleship extends Ship {
	public Battleship() {
		this.length = 4;
		this.hit = new boolean[] {false, false, false, false};
	}
	
	@Override
	public int getLength() {
		return this.length;
	}
	
	@Override
	public String getShipType() {
		return "battleship";
	}
}
