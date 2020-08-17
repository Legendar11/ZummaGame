package Settings;

import java.util.ArrayList;

import Models.Pair;
import Models.WayDirection;

public class ChainSettings {
    public static enum TYPE_CHAIN {
    	H_1, H_2
    }
    
    public static final int MAX_LENGTH_CHAIN = 25;
    
    public static final int COUNT_FOR_REMOVE = 3;
    
    public static WayDirection getStartDirection(final TYPE_CHAIN type) {
    	switch(type) {
			case H_1: return WayDirection.RIGHT;
			case H_2: return WayDirection.LEFT;
			default: return WayDirection.NOTHING;
		}
    }
    
    public static Pair<Integer, Integer> getStartPosition(final TYPE_CHAIN type) {
    	switch(type) {
			case H_1: return new Pair<Integer, Integer>(10, 50);
			case H_2: return new Pair<Integer, Integer>(870, 450);
			default: return new Pair<Integer, Integer>(0, 0);
		}
    }
    
    public static ArrayList<Pair<WayDirection, Integer>> getPath(final TYPE_CHAIN type) {
    	ArrayList<Pair<WayDirection, Integer>> result = new  ArrayList<Pair<WayDirection, Integer>>();
    	
    	switch(type) {
			case H_1: 
				result.add(new Pair<WayDirection, Integer>(WayDirection.RIGHT, 760));
				result.add(new Pair<WayDirection, Integer>(WayDirection.DOWN, 450));
				result.add(new Pair<WayDirection, Integer>(WayDirection.LEFT, 100));
				result.add(new Pair<WayDirection, Integer>(WayDirection.UP, 80));
				result.add(new Pair<WayDirection, Integer>(WayDirection.RIGHT, 650));
				result.add(new Pair<WayDirection, Integer>(WayDirection.DOWN, 450));
				result.add(new Pair<WayDirection, Integer>(WayDirection.LEFT, 30));
				break;
				
			case H_2:
				result.add(new Pair<WayDirection, Integer>(WayDirection.LEFT, 110));
				result.add(new Pair<WayDirection, Integer>(WayDirection.UP, 80));
				result.add(new Pair<WayDirection, Integer>(WayDirection.RIGHT, 650));
				result.add(new Pair<WayDirection, Integer>(WayDirection.DOWN, 450));
				result.add(new Pair<WayDirection, Integer>(WayDirection.LEFT, 100));
				result.add(new Pair<WayDirection, Integer>(WayDirection.UP, 50));
				result.add(new Pair<WayDirection, Integer>(WayDirection.RIGHT, 830));
				break;
		}
    	
    	return result;
    }
    
    public static long getThreadDelay(final TYPE_CHAIN type) {
    	switch (type) {
    	case H_1: 	return 11;
    	case H_2: 	return 12;
		default: 	return 100;
    	}
    }
    
    public static Pair<Integer, Integer> getWholePosition(final TYPE_CHAIN type) {
    	switch (type) {
    	case H_1: 	return new Pair<Integer, Integer>(30, 450);
    	case H_2: 	return new Pair<Integer, Integer>(830, 50);
		default: 	return new Pair<Integer, Integer>(0, 0);
    	}
    }
    
    public static int getCountBalls(BallSettings.Color color, final TYPE_CHAIN type) {
    	switch (color) {
	    	case BLUE:
	    		switch(type) {
	    			case H_1: return 1; 
	    			case H_2: return 2; 
	    		}
	    		break;
	    		
	    	case RED:
	    		switch(type) {
	    			case H_1: return 2; 
	    			case H_2: return 2; 
	    		}
	    		break;
	    		
	    	case GREEN:
	    		switch(type) {
	    			case H_1: return 1; 
	    			case H_2: return 1; 
	    		}
	    		break;
	    		
	    	case YELLOW:
	    		switch(type) {
	    			case H_1: return 1; 
	    			case H_2: return 2; 
	    		}
	    		break;
	    		
	    	default: break;
    	}
		return 0;
    }
}
