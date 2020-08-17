package ViewModels;

import java.util.Random;

import Models.Toad;
import Models.WayDirection;
import Settings.BallSettings;
import Settings.ToadSettings;
import Settings.BallSettings.Color;

public class ViewToad extends Toad {
	public static ToadSettings settings = new ToadSettings();
	
	private ViewBall helpBall;
	private ViewObject viewObj;
    
	public ViewToad(int Width, int Height) {
		super(Width / 2 - settings.getWidthTile() / 2, Height / 2 - settings.getHeightTile() / 2);
		
        viewObj = new ViewObject(settings);
        updateCoords();
	}
	
	public void rotate(final WayDirection direction) {
		setCurDirection(direction);
		
		if (direction == WayDirection.UP) {
			viewObj.getPane().setRotate(180);
		} 
		
		if (direction == WayDirection.RIGHT) {
			viewObj.getPane().setRotate(-90);
		} 
		
		if (direction == WayDirection.DOWN) {
			viewObj.getPane().setRotate(0);
		} 
		
		if (direction == WayDirection.LEFT) {
			viewObj.getPane().setRotate(90);
		} 
	}
	
	public void createHelpBall() {
		Random rand = new Random();
		Color cur_color = 
				Color.values()[rand.nextInt(BallSettings.Color.values().length - 1) + 1];
		
		helpBall = new ViewBall(0, 
				cur_color, 
        		ToadSettings.getHelpBallLocation().getLeft(),
        		ToadSettings.getHelpBallLocation().getRight(),
        		WayDirection.NOTHING);
		
        viewObj.setOffsetY(ToadSettings.getCorrectTile(cur_color));
        rotate(getCurDirection());
	}
	
	public void nextBall() {
		Random rand = new Random();
		Color cur_color = 
				Color.values()[rand.nextInt(BallSettings.Color.values().length - 1) + 1];
		
		createBall();
		
		helpBall.setColor(cur_color);

        viewObj.setOffsetY(ToadSettings.getCorrectTile(cur_color));
	}
	
	private void createBall() {
		int x = 0, y = 0;
		
		final int toad_width = ViewToad.settings.getWidthTile(), 
				toad_height = ViewToad.settings.getHeightTile(); 
		final int ball_width = ViewBall.settings.getWidthTile(), 
				ball_height = ViewBall.settings.getHeightTile(); 
		
		if (getCurDirection() == WayDirection.UP) {
			x = getX() + toad_width / 2 - ball_width / 2;
			y = getY() - ball_height / 2;
		}
		else if (getCurDirection() == WayDirection.RIGHT) {
			x = getX() + toad_width - ball_width / 2;
			y = getY() + ball_height / 2;
		} 
		else if (getCurDirection() == WayDirection.DOWN) {
			x = getX() + toad_width / 2 - ball_width / 2;
			y = getY() + toad_height - ball_height / 2;
		}
		else if (getCurDirection() == WayDirection.LEFT) {
			x = getX() + ball_width / 2;
			y = getY() + ball_height / 2;
		}
		
		if (curBall == null) {
			curBall = new ViewBall(0, helpBall.getColor(), x, y, getCurDirection());
		} else {
			curBall.setX(x);
			curBall.setY(y);
			curBall.setCurDirection(getCurDirection());
			curBall.setColor(helpBall.getColor());
		}
		
		getCurBall().updateCoords();
	}
	
	@Override
	public ViewBall getCurBall() {
		return (ViewBall)curBall;
	}
	
	public ViewBall getHelpBall() {
		return helpBall;
	}
	
	public void updateCoords() {
		viewObj.getPane().setTranslateX(getX());
		viewObj.getPane().setTranslateY(getY());
	}
	
	public ViewObject getViewObj() {
		return viewObj;
	}
}
