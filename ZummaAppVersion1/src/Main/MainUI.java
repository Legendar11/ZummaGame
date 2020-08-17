package Main;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MainUI {
	private Scene scene;
	private MenuBar menu;
	private MenuItem menuItem1;
	
	private Background background;
	
	private double width, height;
	
	public MenuBar CreateMenu(Stage PrimaryStage) {
		menu = new MenuBar();
		menu.setStyle("-fx-base: white");
		menu.prefWidthProperty().bind(PrimaryStage.widthProperty());
		
        Menu program = new Menu("Program");
        menuItem1 = new MenuItem("Start");
        
        program.getItems().addAll(menuItem1);
        menu.getMenus().addAll(program);
        
        return menu;
	}
	
	public MainUI(double Width, double Height) {
        width = Width;
        height = Height;
	}
	
	public Background CreateBackground() {
		double XMargin = -3.0;
		double YMargin = 22.0;
		 
        background = new Background(width, height, XMargin, YMargin);
        return background;
	}
	
	public Scene CreateScene(Pane root) {
		 double XMargin = 10.0;
		 double YMargin = 0.0;
		 
		scene = new Scene(root, width - XMargin, height - YMargin);
		return scene;
	}
	
	public Scene GetScene() { return scene; }
	
	public MenuBar GetMenu() { return menu; }
	
	public MenuItem GetMenuItem1() { return menuItem1; }
}
