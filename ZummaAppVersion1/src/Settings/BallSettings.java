package Settings;

public class BallSettings implements ISettings {
	public static enum Color {
		BLACK, BLUE, RED, PURPLE, GREEN, YELLOW
	}
	
	private final static String FILE_IMG 		= "File:C:\\TestImages\\ball.png";
	private final static int THREAD_DELAY_TIME 	= 2;
	
	private final static int COUNT_TILES 		= 5;
	private final static int COLUMNS_TILES 		= 1;
	private final static int OFFSET_X_START 	= 0;
	private final static int OFFSET_Y_START 	= 0;
	private final static int WIDTH_TILE 		= 50;
	private final static int HEIGHT_TILE 		= 50;
	
	private final static int DURATION_TIME 		= 300;
	private final static int ROTATE_SPEED		= 4;
    
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
		return WIDTH_TILE;
	}
	@Override
	public int getHeightTile() {
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
	
	public static int getRotateSpeed() {
		return ROTATE_SPEED;
	}
	
	public static int getCorrectTile(final BallSettings.Color color) {
    	switch(color) {
	    	case YELLOW: 	return HEIGHT_TILE * 0;
	    	case BLUE:		return HEIGHT_TILE * 1;
	    	case GREEN:		return HEIGHT_TILE * 2;
	    	case PURPLE: 	return HEIGHT_TILE * 3;
	    	case RED: 		return HEIGHT_TILE * 4;
	    	case BLACK: 	return HEIGHT_TILE * 5;
			default: 	return -1;
    	}
    }
}
