package Models;

import java.util.ArrayList;
import java.util.List;

public class Chain extends GameObject {
	public List<Ball> balls;

	protected Whole whole;
	
    protected boolean intersected;
    
    protected boolean isReachedHole;
    protected boolean isEnded;
    
	public Chain(int id) {
		super(0, 0, 0, WayDirection.NOTHING);
		balls = new ArrayList<Ball>();
		this.id = id;
		isReachedHole = false;
		isEnded = false;
	}
	
	public Ball getBall(int index) { 
		return balls.get(index);
	}
	
	public List<Ball> getBalls() { 
		return balls;
	}
	
	public Whole getWhole() {
		return whole;
	}
	
	protected void pushToPoint() {
		for (Ball b : balls) {
			b.pushToPoint();
		}
	}

	protected boolean pointNotReached(int coordinate) {
		switch(this.getCurDirection()) {
			case LEFT: 	return getBall(0).getX() > coordinate;
			case UP: 	return getBall(0).getY() > coordinate;
			case RIGHT: return getBall(0).getX() < coordinate;
			case DOWN: 	return getBall(0).getY() < coordinate;
			default: break;
		}
    	return false;
    }
	
	public int isIntersect(Ball ball) {
		int result = -1;
		
		for (int i = 0; i < balls.size(); i++) {
			if (balls.get(i).isIntersect(ball)) {
				result = i;
				break;
			}
		}
		
		return result;
	}
	
	public boolean isIntersect(Chain other) {
		for (Ball b1 : balls) {
			for (Ball b2 : other.getBalls()) {
				if (b1.isIntersect(b2)) {
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean getIsReachedHole() {
		return isReachedHole;
	}
	
	public boolean getEnded() {
		return isEnded;
	}
	
	public void setIntersected() {
		intersected = true;
	}
	public boolean getIntersected() {
		return intersected;
	}
}
