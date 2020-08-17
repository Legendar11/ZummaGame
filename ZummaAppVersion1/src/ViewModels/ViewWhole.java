package ViewModels;

import Models.Whole;
import Settings.WholeSettings;

public class ViewWhole extends Whole {
	public static WholeSettings settings = new WholeSettings();
	
	private ViewObject viewObj;
	
	public ViewWhole(final int id, final int x, final int y) {
		super(id, x, y);
		viewObj = new ViewObject(settings);
	}
	
	public void updateCoords() {
		viewObj.getPane().setTranslateX(getX());
		viewObj.getPane().setTranslateY(getY());
	}
	
	public ViewObject getViewObj() {
		return viewObj;
	}
}
