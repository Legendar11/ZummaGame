package ViewModels;

import Controllers.ChainController;

import java.util.ArrayList;
import java.util.Iterator;

import Models.Ball;
import Models.Chain;
import Models.WayDirection;
import Settings.BallSettings;
import Settings.ChainSettings;
import Models.FutureWay;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import Main.Main;
import Models.Pair;

public class ViewChain extends Chain implements Runnable  {
	private void createChain() {
		final int ball_width = ViewBall.settings.getWidthTile(), 
				ball_height = ViewBall.settings.getHeightTile();
		
		BallSettings.Color cur_color;
		int coef_x = 0, coef_y = 0, idx = 1;
		
		switch(this.getCurDirection()) {
			case LEFT: 	coef_x = 1; 	coef_y = 0; 	break;
			case UP: 	coef_x = 0; 	coef_y = 1; 	break;
			case RIGHT: coef_x = -1;	coef_y = 0; 	break;
			case DOWN: 	coef_x = 0; 	coef_y = -1; 	break;
			default: break;
		}
		
		balls.add(new ViewBall(id, 
				BallSettings.Color.BLACK, 
				getX(), 
				getY(), 
				getCurDirection()));
		

		cur_color = BallSettings.Color.BLUE;
		for (int i = 0; i < ChainSettings.getCountBalls(cur_color, type); i++, idx++) {
			balls.add(new ViewBall(id, 
					cur_color, 
					getX() + coef_x * (idx * ball_width), 
					getY() + coef_y * (idx * ball_height), 
					getCurDirection()));
		}
		
		cur_color = BallSettings.Color.RED;
		for (int i = 0; i < ChainSettings.getCountBalls(cur_color, type); i++, idx++) {
			balls.add(new ViewBall(id, 
					cur_color, 
					getX() + coef_x * (idx * ball_width), 
					getY() + coef_y * (idx * ball_height), 
					getCurDirection()));
		}
		
		cur_color = BallSettings.Color.GREEN;
		for (int i = 0; i < ChainSettings.getCountBalls(cur_color, type); i++, idx++) {
			balls.add(new ViewBall(id, 
					cur_color, 
					getX() + coef_x * (idx * ball_width), 
					getY() + coef_y * (idx * ball_height), 
					getCurDirection()));
		}
		
		cur_color = BallSettings.Color.YELLOW;
		for (int i = 0; i < ChainSettings.getCountBalls(cur_color, type); i++, idx++) {
			balls.add(new ViewBall(id, 
					cur_color, 
					getX() + coef_x * (idx * ball_width), 
					getY() + coef_y * (idx * ball_height), 
					getCurDirection()));
		}
		
		balls.add(new ViewBall(id, 
				BallSettings.Color.BLACK, 
				getX() + coef_x * (idx * ball_width), 
				getY() + coef_y * (idx * ball_height), 
				getCurDirection()));
    }
    
    public ChainSettings.TYPE_CHAIN type;
    
    public ViewChain(ChainSettings.TYPE_CHAIN t) {
		super(0);
    	type = t;
    	
    	intersected = false;
    	
    	setX(ChainSettings.getStartPosition(type).getLeft());
    	setY(ChainSettings.getStartPosition(type).getRight());
    	setCurDirection(ChainSettings.getStartDirection(type));
    	
    	whole = new ViewWhole(0,
    			ChainSettings.getWholePosition(type).getLeft(),
    			ChainSettings.getWholePosition(type).getRight());
    	
    	getWhole().updateCoords();
    	
    	createChain();
	}
    
    @Override
    public ViewWhole getWhole() {
		return (ViewWhole)whole;
	}
	
	@Override
    public void run() {
		for (Pair<WayDirection, Integer> p : ChainSettings.getPath(type)) {
			move(p.getLeft(), p.getRight());
		}
		
		isEnded = true;
		
		if (balls.size() == 2
				&& balls.get(0).getColor().equals(BallSettings.Color.BLACK)
				&& balls.get(1).getColor().equals(BallSettings.Color.BLACK)) {
			isReachedHole = false;
		} else {
			isReachedHole = true;
		}
		
		
		synchronized (balls) {
			Platform.runLater(new Runnable() {
                @Override
                public void run() {
	                while (balls.size() > 1) {
	                	getBall(1).getViewObj().getPane().getChildren().clear();
	                	balls.remove(1);
                	}
	                getBall(0).getViewObj().getPane().getChildren().clear();
                	balls.remove(0);
                }
            });
		}
    }
	
	
	public void insertBall(final int id, final BallSettings.Color color, final int position) {
		int ball_width = ViewBall.settings.getWidthTile(), 
				ball_height = ViewBall.settings.getHeightTile();
		int newBall_x = getBall(position).getX(), 
				newBall_y = getBall(position).getY();
		
		for (int i = balls.size() - 1; i >= position; i--) {
			switch(balls.get(i).getCurDirection()) {
				case LEFT: 	getBall(i).setX(getBall(i).getX() + ball_width); 	break;
				case UP: 	getBall(i).setY(getBall(i).getY() + ball_height); 	break;
				case RIGHT: getBall(i).setX(getBall(i).getX() - ball_width); 	break;
				case DOWN: 	getBall(i).setY(getBall(i).getY() - ball_height); 	break;
				default: break;
			}
		}
		
		ViewBall newBall = new ViewBall(0, color, newBall_x, newBall_y, balls.get(position).getCurDirection());
		newBall.fway.clear();
		Iterator<FutureWay> it = getBall(position).fway.iterator();
		while(it.hasNext())  {
			newBall.fway.add(it.next());
		}

		balls.add(position, newBall);
		
		synchronized (balls) {
			Platform.runLater(new Runnable() {
                @Override
                public void run() {
                	Main.root.getChildren().add(getBall(position).getViewObj().getPane());
                }
            });
		}
	}
	
	@Override
	public ViewBall getBall(final int index) { 
		return (ViewBall)balls.get(index);
	}
	
	public void removeBall(final int index) {
    	for (int i = balls.size() - 2; i >= index; i--) {
	    	getBall(i + 1).setX(getBall(i).getX());
			getBall(i + 1).setY(getBall(i).getY());
			getBall(i + 1).setCurDirection(getBall(i).getCurDirection());
			
			getBall(i + 1).fway.clear();
			Iterator<FutureWay> it = getBall(i).fway.iterator();
			while(it.hasNext())  {
				getBall(i + 1).fway.add(it.next());
			}
    	}
    	
		synchronized (getBall(index)) {
			Platform.runLater(new Runnable() {
                @Override
                public void run() {
                	getBall(index).getViewObj().getPane().getChildren().clear();
                	balls.remove(index);
                	
                }
            });
		}
	}
	
	public ArrayList<ViewBall> getVBalls() { 
		ArrayList<ViewBall> result = new ArrayList<ViewBall>();
		for (Ball b : balls) result.add((ViewBall)b);
		return result;
	}
	
	private void move(final WayDirection direction, final int coordinate) {
		if (balls.size() == 0) return;
		
		setCurDirection(direction);

		Ball first = getBall(0); 
		for (ViewBall ball : getVBalls()) {
			ball.fway.add(new FutureWay(first.getX(), first.getY(), direction));
		}
		first.setCurDirection(direction);
		
    	while (pointNotReached(coordinate)) {
    		pushToPoint();
    		updateBallsDirections();
    		
    		ChainController.IsIntersect();
    		
    		try {
                Thread.sleep(ChainSettings.getThreadDelay(type));
            } catch (InterruptedException e) {
                System.out.println("Error occur in Chain thread. " + e.getMessage());
            }
    	}
    }
	
	public void updateBalls() {
		for (ViewBall ball : getVBalls()) {
			ball.updateCoords();
			ball.rotate();
		}
	}


	public void ifExplode() {
		for (int i = 1; i < balls.size() - 1; i++) {
			boolean ok = true;
			int count_explode = 1;
			
			for (int j = i + 1; j < i + ChainSettings.COUNT_FOR_REMOVE; j++) {
				if (!balls.get(i).getColor().equals(balls.get(j).getColor())) {
					ok = false; 
					break;
				}

				count_explode++;
			}
			
			if (ok) {
				for (int j = 0; j < count_explode; j++) {
					removeBall(i);
				}
				break;
			}
		}
	}
	
	private void updateBallsDirections() {
		ArrayList<ViewBall> vb = getVBalls();
		
		for (int i = 1; i < vb.size(); i++) {
			if (vb.get(i).fway.isEmpty() == false) {
				FutureWay fw = vb.get(i).fway.peek();
				//if (i == 3) System.out.println(vb.get(i).getX() + " " + vb.get(i).getY() + "   " + vb.get(i).fway.coordX + " " +  vb.get(i).fway.coordY);
				if (fw.pointIsReached(vb.get(i).getX(), vb.get(i).getY())) {
					vb.get(i).setCurDirection(fw.futureDirection);
					vb.get(i).fway.remove();
				}
			}
		}
	}
	
	public int isIntersect(final ViewBall ball) {
		int result = -1;
		
		for (int i = 0; i < getVBalls().size(); i++) {
			if (getBall(i).isIntersect(ball)) {
				result = i;
				break;
			}
		}
		
		return result;
	}
	
	public boolean isIntersect(final ViewChain other) {
		for (ViewBall b1 : getVBalls()) {
			for (ViewBall b2 : other.getVBalls()) {
				if (b1.isIntersect(b2)) {
					return true;
				}
			}
		}
		return false;
	}
	
}
