package Main;

import java.util.ArrayList;
import java.util.List;

import Controllers.ChainController;
import Controllers.ToadController;
import Models.WayDirection;
import ViewModels.ViewBall;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Main extends Application implements Runnable {
	public static Main applicationWindow;
    public Thread mainThread;
	
	public volatile static boolean doProgram = false;
	    
	public static Pane root = new Pane();
	private static Scene scene;
	private MainUI mainUI;
	
	private List<Thread> chainsThreads = new ArrayList<Thread>();
	private Thread ballThread;
    private int chainsCount = 0;

	final static double PREF_WIDTH = 900.0, PREF_HEIGHT = 600.0;
	
	private void preLoadUI(Stage primaryStage) {
		root.setPrefSize(PREF_WIDTH, PREF_HEIGHT);
		 
		mainUI = new MainUI(PREF_WIDTH, PREF_HEIGHT);
		
		root.getChildren().add(mainUI.CreateMenu(primaryStage));
		root.getChildren().add(mainUI.CreateBackground().GetView());
		
		scene = mainUI.CreateScene(root);
		primaryStage.setScene(scene); 
		primaryStage.setResizable(false);
		primaryStage.setTitle("Zumma");
	}
	
	private void bindEvents(Stage primaryStage) {
		  mainUI.GetMenuItem1().setOnAction(new EventHandler<ActionEvent>() {
	            @Override public void handle(ActionEvent actionEvent) {
	            	if (!doProgram) {
	                    applicationWindow.run();
	                    doProgram = true;
	                    ChainController.animationTimer.start();
	                }
	            }
	        });

	        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
	            @Override
	            public void handle(WindowEvent t) {
	                Platform.exit();
	                System.exit(0);
	            }
	        });
	        
	        scene.setOnKeyPressed(new EventHandler<KeyEvent>(){
	            @Override
	            public void handle(KeyEvent event) {
	                KeyCode key = event.getCode();
	                if(key == KeyCode.UP){
	                	ToadController.toad.rotate(WayDirection.UP);
	                } else if(key == KeyCode.DOWN) {
	                    ToadController.toad.rotate(WayDirection.DOWN);
	                } else if(key == KeyCode.LEFT) {
	                	ToadController.toad.rotate(WayDirection.LEFT);
	                } else if(key == KeyCode.RIGHT) {
	                	ToadController.toad.rotate(WayDirection.RIGHT);
	                } else if(key == KeyCode.SPACE) {
	                	if (ToadController.toad.getHelpBall() != null) {
		                	ToadController.toad.nextBall();
	                		ballThread = new Thread(taskAddNewBall);
		                	ballThread.start();
	                	}
	                }
	            }
	        });
	}
	
	public void start(Stage primaryStage) {
		preLoadUI(primaryStage);
		
        primaryStage.show();

        bindEvents(primaryStage);
        
		ToadController.createToad((int)PREF_WIDTH, (int)PREF_HEIGHT);
		root.getChildren().add(ToadController.toad.getViewObj().getPane());
    }
	
	@Override
	public void run() {
        mainThread = new Thread(taskAddNewChain);
        mainThread.start();
    }
	 
	public static void main(String[] args) {
		 applicationWindow = new Main();
	     launch(args);
	}

	private void AddNewChain(int a) throws InterruptedException {
		ChainController.createNewChain(a);
    	
		for (ViewBall vb : ChainController.getLastChain().getVBalls()) {
			root.getChildren().add(vb.getViewObj().getPane());
		}
		
        synchronized (ChainController.chains){
        	chainsThreads.add(new Thread(ChainController.chains.get(chainsCount)));
        }

        root.getChildren().add(ChainController.chains.get(chainsCount).getWhole().getViewObj().getPane());
        		
        chainsThreads.get(chainsCount).start();
        chainsCount++;
	}
	
	private Task taskAddNewBall = new Task() {
		 @Override 
		 protected Void call() {
			 Platform.runLater(new Runnable() {
                 @Override
                 public void run() {
                    root.getChildren().add(ToadController.toad.getCurBall().getViewObj().getPane());
                    Thread doThread = new Thread(ToadController.toad.getCurBall());
                    doThread.start();
                 }
             });
			return null;
		 }
	};
	
	private Task taskAddNewChain = new Task() {
        @Override protected Void call() {
        	ToadController.toad.createHelpBall();
        	
        	Platform.runLater(new Runnable() {
                @Override
                public void run() {
                	try {
        				AddNewChain(1);
        				AddNewChain(0);

                        root.getChildren().add(ToadController.toad.getHelpBall().getViewObj().getPane());
        			} catch (InterruptedException e) {
        				// TODO Auto-generated catch block
        				e.printStackTrace();
        			}
                }
            });
			return null;
        }
    };
}
