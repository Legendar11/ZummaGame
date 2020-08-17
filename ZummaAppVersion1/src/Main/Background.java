package Main;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Background {
	private Image img;
	private ImageView view;
	
	public Background(final double Width, final double Height, final double XMargin, final double YMargin) {
		img = new Image("File:C:\\TestImages\\background.png");
		view = new ImageView(img);

		view.setFitWidth(Width + 3);
		view.setFitHeight(Height);
		view.setTranslateX(XMargin);
		view.setTranslateY(YMargin);
	}
	
	public ImageView GetView() { 
		return view; 
	}
}
