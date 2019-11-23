package game;

import java.io.File;
import java.util.Observable;
import java.util.Observer;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class View extends Application implements Observer{
	
	private final int SCENE_WIDTH = 1100;
	private final int SCENE_HEIGHT = 700;
	private final int ROWS = 6;
	private final int COLS = 12;
	private final int NUM_CHARACTERS = 9;
	
	// Class fields
	private Model model;
	private Controller controller;
	
	private Stage primaryStage;
	private GridPane gridPane;
	private BorderPane startBorderPane;
	private BorderPane gameBorderPane;

	public View() {
		// this.model
		// this.controller
		// this.model.addObservers(this);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		
		// Setup Start menu
		setupStartMenu();
		this.primaryStage.show();
	}
	
	public void setupStartMenu() {
		startBorderPane = new BorderPane();
		
		// Create Title
		Text title = new Text("Astronauts vs. Aliens");
		title.setFont(new Font("Courier New", 30));
		title.setFill(Color.RED);
		
		// Create OK Button
		Button okBtn = new Button("Start");
		okBtn.setOnAction( e -> {
			// Switch scene to Game Scene
			setupGameScene();
			primaryStage.show();
		});
		
		Image bgImage = new Image("file:assets/space-background.png");
		BackgroundSize bSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true);
	    Background borderPaneBackground = new Background(new BackgroundImage(bgImage,
	            BackgroundRepeat.NO_REPEAT,
	            BackgroundRepeat.NO_REPEAT,
	            BackgroundPosition.CENTER,
	            bSize));
	    
	    startBorderPane.setBackground(borderPaneBackground);
		
		startBorderPane.setTop(title);
		startBorderPane.setCenter(okBtn);
		
		BorderPane.setAlignment(title, Pos.CENTER);
		BorderPane.setAlignment(okBtn, Pos.CENTER);
		
		Scene scene = new Scene(startBorderPane, SCENE_WIDTH, SCENE_HEIGHT);
		primaryStage.setScene(scene);
	}
	
	/**
	 * Sets up the game scene containing the actual game
	 * assets and functionality
	 */
	public void setupGameScene() {
		gameBorderPane = new BorderPane();
		gameBorderPane.setPadding(new Insets(10,10,10,10));
		
		setupGridPane();
		setupTopMenuBar();
	
		gameBorderPane.setCenter(gridPane);
		
		Image bgImage = new Image("file:assets/space-background.png");
		BackgroundSize bSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true);
	    Background borderPaneBackground = new Background(new BackgroundImage(bgImage,
	            BackgroundRepeat.NO_REPEAT,
	            BackgroundRepeat.NO_REPEAT,
	            BackgroundPosition.CENTER,
	            bSize));
	    
	    gameBorderPane.setBackground(borderPaneBackground);
		
		Scene scene = new Scene(gameBorderPane, SCENE_WIDTH, SCENE_HEIGHT);
		primaryStage.setTitle("Aliens vs. Astronauts");
		primaryStage.setScene(scene);
	}
	
	/**
	 * Sets up the GridPane containing a grid of open Tile objects
	 * as well as the extreme homebase/enemy-entry points
	 */
	public void setupGridPane() {
		gridPane = new GridPane();
		gridPane.setHgap(1);
		gridPane.setVgap(1);
		gridPane.setGridLinesVisible(true);
		gridPane.setAlignment(Pos.CENTER);
		
		for (int row = 0; row < ROWS; row++) {
			for (int col = 0; col < COLS; col++) {
				if (col == 0) {
					Circle circle = new Circle(40);
					circle.setFill(Color.DARKRED);
					gridPane.add(circle, col, row);
				} else if (col == 11){
					Circle circle = new Circle(40);
					circle.setFill(Color.DARKBLUE);
					gridPane.add(circle, col, row);
				} else {
					Circle circle = new Circle(40);
					circle.setFill(Color.DARKGREEN);
					gridPane.add(circle, col, row);
				}
			}
		}
	}
	
	/**
	 * Sets up top navigation bar containing the defenders that
	 * can be placed onto the board.
	 */
	public void setupTopMenuBar() {
		HBox hbox = new HBox(10);
		
		// Add 6 Buttons as placeholder for tower items
		for (int i = 0; i < NUM_CHARACTERS; i++) {
			Button btn = new Button("Temp");
			btn.setMinHeight(50);
			btn.setMinWidth(50);
			hbox.getChildren().add(btn);
		}
		
		// Add button to act as the removal
		Button removalBtn = new Button("Remove");
		hbox.getChildren().add(removalBtn);
		hbox.setAlignment(Pos.CENTER);
		gameBorderPane.setTop(hbox);
		
	}
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

}

