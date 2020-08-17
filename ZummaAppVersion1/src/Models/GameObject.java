package Models;

public abstract class GameObject {
	protected int id, x, y;
	protected WayDirection curDirection;
	
	public GameObject(int id, int x, int y, WayDirection way) {
		this.id = id;
		this.setX(x);
		this.setY(y);
		this.setCurDirection(way);
	}
	
	public int getID() {
		return id;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}
	
    public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	
	public WayDirection getCurDirection() {
		return curDirection;
	}

	public void setCurDirection(WayDirection curDirection) {
		this.curDirection = curDirection;
	}
}
