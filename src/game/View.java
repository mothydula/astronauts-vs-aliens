package game;

import java.io.File;
import java.util.Observable;
import java.util.Observer;

import javafx.application.Application;
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
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class View extends Application implements Observer{
	
	private final int SCENE_WIDTH = 1500;
	private final int SCENE_HEIGHT = 700;
	private final int ROWS = 6;
	private final int COLS = 10;
	
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
		Label title = new Label("Astronauts vs. Aliens");
		title.setFont(new Font("Courier New", 30));
		
		// Create OK Button
		Button okBtn = new Button("Start");
		okBtn.setOnAction( e -> {
			// Switch scene to Game Scene
			setupGameScene();
			primaryStage.show();
		});
		
		startBorderPane.setTop(title);
		startBorderPane.setCenter(okBtn);
		
		BorderPane.setAlignment(title, Pos.CENTER);
		BorderPane.setAlignment(okBtn, Pos.CENTER);
		
		Scene scene = new Scene(startBorderPane, SCENE_WIDTH, SCENE_HEIGHT);
		primaryStage.setScene(scene);
	}
	
	public void setupGameScene() {
		gameBorderPane = new BorderPane();
		setupGridPane();
		
		ImageView view = new ImageView();
		File file = new File("~/Downloads/astr-assets/homebase.png");
		Image homebase = new Image(file.toURI().toString());
		view.setImage(homebase);
		
		gameBorderPane.setRight(new Button("right"));
		gameBorderPane.setLeft(view);
		gameBorderPane.setCenter(gridPane);
		gameBorderPane.setTop(new Button("Top"));
		
		File file2 = new File("~/Downloads/astr-assets/mars-background.png");
		Image bgImage = new Image(file2.toURI().toString());
		BackgroundSize bSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false);
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
	
	public void setupGridPane() {
		gridPane = new GridPane();
		gridPane.setHgap(1);
		gridPane.setVgap(1);
		gridPane.setGridLinesVisible(true);
		gridPane.setAlignment(Pos.CENTER);
		
		for (int row = 0; row < ROWS; row++) {
			for (int col = 0; col < COLS; col++) {
				Circle circle = new Circle(40);
				circle.setFill(Color.DARKRED);
				gridPane.add(circle, col, row);
			}
		}
	}
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

}

