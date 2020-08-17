package Models;

public class Toad extends GameObject {
	protected Ball curBall;
	
	public Toad(int x, int y) {
		super(0, x, y, WayDirection.UP);
	}

	public Ball getCurBall() {
		return curBall;
	}
	
	public void clearBall() {
		curBall.setX(-50);
		curBall.setY(-50);
		curBall.setCurDirection(WayDirection.NOTHING);
	}
}
