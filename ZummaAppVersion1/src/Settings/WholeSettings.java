package Settings;

public class WholeSettings implements ISettings {
	private final static String FILE_IMG 		= "File:C:\\TestImages\\whole.png";
	private final static int THREAD_DELAY_TIME 	= 7;
	
	private final static int COUNT_TILES 		= 1;
	private final static int COLUMNS_TILES 		= 1;
	private final static int OFFSET_X_START 	= 0;
	private final static int OFFSET_Y_START 	= 0;
	private final static int WIDTH_TILE 		= 50;
	private final static int HEIGHT_TILE 		= 50;
	
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
}
