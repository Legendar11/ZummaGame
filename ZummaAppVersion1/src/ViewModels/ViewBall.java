package ViewModels;

import java.util.LinkedList;
import java.util.Queue;

import Models.Ball;
import Models.WayDirection;
import Settings.BallSettings;
import Models.FutureWay;

public class ViewBall extends Ball implements Runnable { 
	public static BallSettings settings = new BallSettings();
	
    private int r_angle = 0;
    
	private ViewObject viewObj;
	public Queue<FutureWay> fway;
    
    public ViewBall(final int id, final BallSettings.Color color, final int x, final int y, final WayDirection way) {
    	super(id, color, x, y, way);
    	fway = new LinkedList<FutureWay>();
        viewObj = new ViewObject(settings);
        updateCoords();
        viewObj.setOffsetY(BallSettings.getCorrectTile(color));
        r_angle = 0;
 	}
    
    @Override
    public void setColor(final BallSettings.Color color) {
		this.color = color;
		viewObj.setOffsetY(BallSettings.getCorrectTile(color));
	}
    
    @Override
    public void run() {
 		move();
    }

	public void move() {
		while (true) {
 			pushToPoint();
 			updateCoords();
 			rotate();
 			
 			try {
                Thread.sleep(settings.getThreadTime());
            } catch (InterruptedException e) {
                System.out.println("Error occur in Chain thread. " + e.getMessage());
            }
 		}
	}

	public void rotate() {
		r_angle += BallSettings.getRotateSpeed();
		viewObj.getPane().setRotate(r_angle);
	}
	
	@Override
	public boolean isIntersect(final Ball other) {
		return getX() >= (other.getX() - settings.getWidthTile()) 
				&& getX() <= (other.getX() + settings.getWidthTile())
				&& getY() >= (other.getY() - settings.getHeightTile()) 
				&& getY() <= (other.getY() + settings.getHeightTile());
	}
	
   
    
	public void updateCoords() {
		viewObj.getPane().setTranslateX(getX());
		viewObj.getPane().setTranslateY(getY());
	}
	
	public ViewObject getViewObj() {
		return viewObj;
	}

}
