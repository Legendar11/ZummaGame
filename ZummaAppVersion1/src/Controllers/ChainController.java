package Controllers;

import java.util.ArrayList;
import java.util.List;

import ViewModels.ViewChain;
import javafx.animation.AnimationTimer;
import Settings.ChainSettings;
import Main.Main;
import Main.Message;

public class ChainController {
	static public int chainsCount = 0;
	static public List<ViewChain> chains = new ArrayList<ViewChain>();
		
	public static AnimationTimer animationTimer = new AnimationTimer() {
        @Override
        public void handle(long now) {
        	if (Main.doProgram == false) { animationTimer.stop(); return; }
        	
        	synchronized (ChainController.chains) {
        		for (int i = 0; i < ChainController.chainsCount; i++) {
        			ChainController.chains.get(i).updateBalls();
            		
        			if (ChainController.chains.get(i).getIntersected()) {
        				Main.doProgram = false;
        				
        				Message.show("Цепочки пересеклись - Вы проиграли!", "OK");
        				
        				return;
        			}
        			
        			if (ChainController.chains.get(i).getEnded()) {
        				Main.doProgram = false;
        				
        				boolean notWin = ChainController.chains.get(i).getIsReachedHole();
        				
        				if (notWin) Message.show("Вы проиграли!", "OK");
        				else Message.show("Вы победили!", "OK");
            		}
            	}
        		
        	}
        	
        	if (ToadController.toad != null
        			&& ToadController.toad.getCurBall() != null) {
        		ToadController.toad.getCurBall().updateCoords();
            	ToadController.isIntersect();
        	}
        	
        	if (ToadController.toad != null
        			&& ToadController.toad.getHelpBall() != null) {
            	ToadController.toad.getHelpBall().rotate();
        	}
        }
    };
    
    public static void createNewChain(int a) {
    	if (a == 1) chains.add(new ViewChain(ChainSettings.TYPE_CHAIN.H_1)); 
    	else chains.add(new ViewChain(ChainSettings.TYPE_CHAIN.H_2)); 
    	
        chainsCount++;
        
    }
    
	public static ViewChain getLastChain() {
		return ChainController.chains.get(ChainController.chainsCount - 1);
	}
	
	public static void IsIntersect() {
		synchronized (ChainController.chains) {
			if (ChainController.chainsCount > 1) {
				for (int i = 0; i < ChainController.chains.size(); i++) {
					for (int j = 0; j < ChainController.chains.size(); j++) {
						if (i == j) continue;
							
						if (ChainController.chains.get(i).isIntersect(ChainController.chains.get(j))) {
							ChainController.chains.get(i).setIntersected();
							ChainController.chains.get(j).setIntersected();
						}
					}
				}
			}
		}
	}
}
