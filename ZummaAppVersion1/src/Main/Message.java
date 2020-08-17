package Main;

import Controllers.ChainController;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Message {
	public static void show(final String caption, final String text) {
		Platform.runLater(new Runnable() {
            @Override
            public void run() {
            	final Stage dialogStage = new Stage();
        	    dialogStage.initModality(Modality.WINDOW_MODAL);

        	    VBox vbox = new VBox();
        	    vbox.getChildren().add(new Text(caption));
        	    
        	    Button btn = new Button(text);
        	    btn.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                    	ChainController.chains.remove(0);
                    	ChainController.chains.remove(0);
                    	ChainController.chainsCount = 0;
                    	
                    	Main.applicationWindow = new Main();
                    	Main.applicationWindow.run();
                    	Main.doProgram = true;
	                    ChainController.animationTimer.start();
	                    
                    	dialogStage.close();
                    }
                });
        	    vbox.getChildren().add(btn);
        	    
        	    vbox.setAlignment(Pos.CENTER);
        	    vbox.setPadding(new Insets(15));

        	    dialogStage.setScene(new Scene(vbox));
        	    dialogStage.show();
            }
        });
	}
}
