package Controllers;

import ViewModels.ViewToad;
import Settings.ChainSettings;

public class ToadController {
	static public ViewToad toad;
	
	public static void createToad(int Width, int Height) {
		toad = new ViewToad(Width, Height);
	}
	
	public static boolean isIntersect() {
		synchronized (ChainController.chains) {
			for (int i = 0; i < ChainController.chains.size(); i++) {
				int result = ChainController.chains.get(i).isIntersect(ToadController.toad.getCurBall());
				
				int count_balls = ChainController.chains.get(i).getBalls().size();
				
				if (result > 0 
						&& result < count_balls
						&& count_balls < ChainSettings.MAX_LENGTH_CHAIN) {
					ToadController.toad.clearBall();
					
					ChainController.chains.get(i).insertBall(0, 
							ToadController.toad.getCurBall().getColor(), result);
					
					ChainController.chains.get(i).ifExplode();
					
					return true;
				}
			}
			return false;
		}
	}
}
