package ViewModels;

import java.io.File;

import javax.imageio.ImageIO;

import Settings.ISettings;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class ViewObject {
	private Pane pane;
    private Image img;
	private ImageView view;
	private ISettings settings;
	
	public ViewObject(final ISettings settings) {
		this.settings = settings;
    	pane = new Pane();
    	img = new Image(settings.getFileImg());
        view = new ImageView(img);
        
    	view.setViewport(new Rectangle2D(
    			settings.getOffsetXStart(), settings.getOffsetYStart(),
    			settings.getWidthTile(), settings.getHeightTile()));
    	
        pane.getChildren().addAll(view);
	}
	
	public Image getImg() {
		return img;
	}

	public Pane getPane() {
		return pane;
	}
	
	public void setOffsetY(final int offset) {
		view.setViewport(new Rectangle2D(
    			settings.getOffsetXStart(), offset,
    			settings.getWidthTile(), settings.getHeightTile()));
	}
    
}