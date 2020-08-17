package Settings;

import Models.Pair;

public class ToadSettings implements ISettings {
    private final static String FILE_IMG 		= "File:C:\\TestImages\\toad.png";
	private final static int THREAD_DELAY_TIME 	= 7;
	
	private final static int COUNT_TILES 		= 6;
	private final static int COLUMNS_TILES 		= 1;
	private final static int OFFSET_X_START 	= 0;
	private final static int OFFSET_Y_START 	= 0;
	private final static int WIDTH_TILE 		= 120;
	private final static int HEIGHT_TILE 		= 120;
    
	private final static int DURATION_TIME 		= 300;
    
	@Override
	public int getCountTiles() {
		return COUNT_TILES;
	}
	@Override
	public int getColumnTiles() {
		return COLUMNS_TILES;
	}
	@Override
	public int getOffsetXStart() {
		return OFFSET_X_START;
	}
	@Override
	public int getOffsetYStart() {
		return OFFSET_Y_START;
	}
	@Override
	public int getWidthTile() {
		// TODO Auto-generated method stub
		return WIDTH_TILE;
	}
	@Override
	public int getHeightTile() {
		// TODO Auto-generated method stub
		return HEIGHT_TILE;
	}
	@Override
	public int getDuration() {
		return DURATION_TIME;
	}
	@Override
	public String getFileImg() {
		return FILE_IMG;
	}
	@Override
	public int getThreadTime() {
		return THREAD_DELAY_TIME;
	}
    
    public static Pair<Integer, Integer> getHelpBallLocation() {
    	return new Pair<Integer, Integer>(770, 520);
    }
    
    public static int getCorrectTile(final BallSettings.Color color) {
    	switch(color) {
	    	case YELLOW: 	return HEIGHT_TILE * 3;
	    	case BLUE:		return HEIGHT_TILE * 0;
	    	case GREEN:		return HEIGHT_TILE * 1;
	    	case PURPLE: 	return HEIGHT_TILE * 5;
	    	case RED: 		return HEIGHT_TILE * 4;
			default: 	return -1;
    	}
    }
}
