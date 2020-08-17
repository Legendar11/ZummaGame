package Models;

public class FutureWay {
	public WayDirection futureDirection;
	public int coordX, coordY;
	
	public FutureWay() {
		futureDirection = WayDirection.NOTHING;
		coordX = 0;
		coordY = 0;
	}
	
	public FutureWay(int coordX, int coordY, WayDirection futureDirection) {
		this.futureDirection = futureDirection;
		setXY(coordX, coordY);
	}
	
	public boolean pointIsReached(int coordX, int coordY) {
		return this.coordX == coordX && this.coordY == coordY;
	}
	
	public void setXY(int coordX, int coordY) {
		this.coordX = coordX; this.coordY = coordY;
	}
}
