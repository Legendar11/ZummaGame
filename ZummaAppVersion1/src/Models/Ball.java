package Models;

import Settings.BallSettings;

public class Ball extends GameObject {
	protected BallSettings.Color color;
	
    public Ball(final int id, final BallSettings.Color color, final int x, final int y, final WayDirection way) {
    	super(id, x, y, way);
        this.color = color;
	}
    
    public void pushToPoint() {
    	if (getCurDirection() == WayDirection.LEFT)		setX(getX() - 1); 
		if (getCurDirection() == WayDirection.UP)		setY(getY() - 1); 
		if (getCurDirection() == WayDirection.RIGHT) 	setX(getX() + 1); 
		if (getCurDirection() == WayDirection.DOWN) 	setY(getY() + 1); 
	}
    
    public boolean isIntersect(final Ball other) {
		return getX() == other.getX() && getX() == other.getX()
				&& getY() == other.getY() && getY() == other.getY();
	}

	public BallSettings.Color getColor() {
		return color;
	}

	public void setColor(final BallSettings.Color color) {
		this.color = color;
	}
}
