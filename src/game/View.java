package game;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import characters.Astronauts.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import map.Tile;

public class View extends Application implements Observer{
	
	// General constants
	private final int NUM_TOWERS = 9;
	private final int CURRENCY_TIMELINE = 5; // seconds
	private final int CURRENCY_DEPOSIT = 50;
	
	// View-specific constants
	private final int SCENE_WIDTH = 1300;
	private final int SCENE_HEIGHT = 800;
	private final int GP_CELL_SIZE = 80;
	private final int CELL_GAP = 5;
	private final int TITLE_TOP_MARGIN = 75;
	private final int PROGRESSBAR_TOP_MARGIN = 50;
	private final int PROGRESSBAR_WIDTH = 500;
	private final int GRIDPANE_TOP_MARGIN = 25;
	private final int DEFENDERS_TOP_PADDING = 10;
	private final int DEFENDERS_BOTTOM_PADDING = 10;
	private final int MENUBAR_LEFT_PADDING = 20;
	
	private final int ASTRO_WIDTH = 300; // Start menu sprite
	private final int ASTRO_HEIGHT = 300; // Start menu sprite
	private final int ASTRO_LEFT_MARGIN = 100;
	private final int ALIEN_WIDTH = 350; // Start menu sprite
	private final int ALIEN_HEIGHT = 300; // Start menu sprite
	private final int ALIEN_RIGHT_MARGIN = 100;
	
	private final String GAME_BACKGROUND_IMAGE 	= "file:assets/stage-one-background.png";
	private final String TITLE_GRAPHIC 			= "file:assets/game-title.png";
	private final String SPACEBUCKS_IMAGE	 	= "file:assets/spacebucks-image.png";
	private final String RAIL_GUN_IMAGE 		= "file:assets/rail-gun.png";
	private final String RAIL_GUN_GIF 			= "file:assets/rail-gun.gif";
	
	// Class fields
	private Model model;
	private Controller controller;
	private DefenderTower[] defenderTowers;
	private DefenderTower selectedTower;
	
	private Stage primaryStage;
	private GridPane gridPane;
	private BorderPane startBorderPane;
	private BorderPane gameBorderPane;
	
	// Automatic currency generator
	private Timeline timeline;
	
	// Attributes so that we can update as the game progresses
	private HBox progressHBox;
	private ProgressBar progressBar;
	private Label progressLabel;
	private Label bankAmount;
	
	// notifies update that defender is being removed, not placed.
	private boolean removeToggled;
	private int[] indexToRemove;

	public View() {
		model = new Model();
		controller = new Controller(model);
		model.addObserver(this);
		defenderTowers = new DefenderTower[]{
				new AstroJoe(),
				new Asteroid(),
				new MoneyBush(),
				new ExplosiveAstroJoe(),
				new StartrellCluggins(),
				new Tars(),
				new MoonZeus(),
				new MoneyTree(),
				new MillenniumFalcon()
		};
		
		removeToggled = false;
		indexToRemove = new int[]{0, 0};
		
		// Currency generator - deposit 50 space bucks every 5 seconds
		timeline = new Timeline(new KeyFrame(Duration.seconds(CURRENCY_TIMELINE), e -> {
			controller.depositSpacebucks(CURRENCY_DEPOSIT);
		}));
		timeline.setCycleCount(Timeline.INDEFINITE);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		
		// Setup Start menu & show to begin the game
		setupStartMenu();
		this.primaryStage.show();
	}
	
	@Override
	public void update(Observable o, Object arg) {
		
//		for (int row = 0; row < Controller.ROWS; row++) {
//			for (int col = 0; col < Controller.COLS; col++) {
//				if (model.getBoard()[row][col].isEmpty()) {
//					System.out.print(" 0 ");
//				} else {
//					System.out.print(" 1 ");
//				}
//			}
//			System.out.println();
//		}
//		System.out.println();
		
		if (arg instanceof DefenderTower) {
			DefenderTower defender = (DefenderTower)arg;
			ObservableList<Node> children = gridPane.getChildren();
			int row = defender.getRow();
			int col = defender.getCol();
			
			if (!removeToggled) { // Place defender tower
				for (Node node: children) {
					if (GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == col) {
						// Place
						ImageView view = (ImageView)node;
						view.setFitHeight(GP_CELL_SIZE);
						view.setFitWidth(GP_CELL_SIZE);
						view.setImage(defender.getImage());
						System.out.println("Dropped " + defender.toString() + " into " + defender.getRow() + " " + defender.getCol());
					}
				}
			} else { // Remove defender tower
				int r = indexToRemove[0];
				int c = indexToRemove[1];
				for (Node node: children) {
					if (GridPane.getRowIndex(node) == r && GridPane.getColumnIndex(node) == c) {
						// Place
						ImageView view = (ImageView)node;

						view.setFitHeight(GP_CELL_SIZE);
						view.setFitWidth(GP_CELL_SIZE);
						view.setImage(new Image("file:assets/placement-square.png", GP_CELL_SIZE, GP_CELL_SIZE, false, false));
						System.out.println("Deleted " + defender.toString() + " in " + defender.getRow() + " " + defender.getCol());
					}
				}
				removeToggled = false;
				indexToRemove = new int[] {0, 0};
			}
			
		} else if (arg instanceof String) {
			String reason = (String)arg;
			if (reason.equals("cost")) {
				// Display "Not enough funds"
				String toastMsg = "Not enough funds!";
				int toastMsgTime = 1000; //3.5 seconds
				int fadeInTime = 150; //0.5 seconds
				int fadeOutTime= 150; //0.5 seconds
				Toast.makeText(primaryStage, toastMsg, toastMsgTime, fadeInTime, fadeOutTime);
			} else if (reason.equals("taken")) {
				// Display "Tile taken
				String toastMsg = "Tile is already taken!";
				int toastMsgTime = 1000; //3.5 seconds
				int fadeInTime = 150; //0.5 seconds
				int fadeOutTime= 150; //0.5 seconds
				Toast.makeText(primaryStage, toastMsg, toastMsgTime, fadeInTime, fadeOutTime);
			}
		}	
		
		// Update bank amount after each update
		bankAmount.setText(String.valueOf(model.getSpacebucks()));
		
		// controller.isGameOver();
	}
	
	public void setupStartMenu() {
		startBorderPane = new BorderPane();
		
		// Create Title Banner
		ImageView titleBanner = new ImageView();
		titleBanner.setImage(new Image(TITLE_GRAPHIC));
		
		// Create VBox of Utility Button (Info, ?, Start)
		VBox buttonBox = createStartMenuButtonBox();
		
		// Create ImageViews for Astronaut & Alien
		ImageView astronautImageView = new ImageView();
		astronautImageView.setImage(new Image(DefenderTower.STARTRELL_CLUGGINS_GIF, ASTRO_WIDTH, ASTRO_HEIGHT, false, false));
		
		ImageView alienImageView = new ImageView();
		alienImageView.setImage(new Image("file:assets/alien-sample.png", ALIEN_WIDTH, ALIEN_HEIGHT, false, false));
		
		// Create & Set background for the border pane
		Image bgImage = new Image("file:assets/space-gif.gif", SCENE_WIDTH, SCENE_HEIGHT, false, false);
		BackgroundSize bSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true);
	    Background borderPaneBackground = new Background(new BackgroundImage(bgImage,
	            BackgroundRepeat.NO_REPEAT,
	            BackgroundRepeat.NO_REPEAT,
	            BackgroundPosition.CENTER,
	            bSize));
	    startBorderPane.setBackground(borderPaneBackground);
	    
	    // Placement and alignments of UI Components
	    startBorderPane.setTop(titleBanner);
	    startBorderPane.setLeft(astronautImageView);
	    startBorderPane.setRight(alienImageView);
		startBorderPane.setCenter(buttonBox);
		
		BorderPane.setAlignment(titleBanner,        Pos.CENTER);
		BorderPane.setAlignment(astronautImageView, Pos.CENTER);
		BorderPane.setAlignment(alienImageView,     Pos.CENTER);
		BorderPane.setAlignment(buttonBox,          Pos.CENTER);
		
		BorderPane.setMargin(titleBanner,        new Insets(TITLE_TOP_MARGIN, 0, 0, 0)); // top right bottom left
		BorderPane.setMargin(astronautImageView, new Insets(0, 0, 0, ASTRO_LEFT_MARGIN));
		BorderPane.setMargin(alienImageView,     new Insets(0, ALIEN_RIGHT_MARGIN, 0, 0));
		
		// Add border pane to scene and set the stage scene
		Scene scene = new Scene(startBorderPane, SCENE_WIDTH, SCENE_HEIGHT);
		primaryStage.setScene(scene);
	}
	
	public VBox createStartMenuButtonBox() {
		VBox buttonBox = new VBox(3);
		buttonBox.setAlignment(Pos.CENTER);
		buttonBox.setSpacing(15);
		
		Button infoBtn = new Button("Info");
		infoBtn.setMinHeight(40);
		infoBtn.setMinWidth(100);
		
		//Pulls up the info window to show the user a general overview of game
		//mechanics
		infoBtn.setOnAction( e -> {
			Text infoText = new Text("INSTRUCTIONS\n"
					+ "The objective of this game is to fend of your enemies\n"
					+ "for as long as possible. You'll do this buy accumulating spaceBucks(?)\n"
					+ "and spending them on defenders to protect your galaxy(?)\n"
					+ "from the incoming aliens. THe more expensive a defender is,\n"
					+ "the more useful they probably are. However, use your space-\n"
					+ "bucks wisely!\n\n"
					+ "HOW-TO\n"
					+ "You can place a defender by clicking them from the queue and placing\n"
					+ "them anywhere between the alien spawn station and the last line of defense.\n\n"
					+ "No two defenders can occupy the same tile.\n\n"
					+ "A defender can be removed by clicking and highlighting their tile and\n"
					+ "clicking the remove button.\n\n"
					+ "Hover over a defender in the queue for about 1-2 sec to view specs");
			ScrollPane infoPane = new ScrollPane();
			infoPane.setPadding(new Insets(12,12,12,12));
			infoPane.setPrefHeight(350);
			infoPane.setContent(infoText);
			Scene infoScene = new Scene(infoPane);
			Stage infoStage = new Stage();
			infoStage.setScene(infoScene);
			infoStage.setTitle("Info");
			infoStage.show();
		});
		
		Button tempBtn = new Button("?");
		tempBtn.setMinHeight(40);
		tempBtn.setMinWidth(100);
		tempBtn.setOnAction( e -> {
			System.out.println("TODO: Insert temp functionality");
		});
		
		Button startBtn = new Button("Start");
		startBtn.setMinHeight(60);
		startBtn.setMinWidth(150);
		startBtn.setOnAction( e -> {
			// Switch scene to Game Scene
			setupGameScene();
			primaryStage.show();
		});
		
		buttonBox.getChildren().addAll(infoBtn, tempBtn, startBtn);
		
		return buttonBox;
	}
	
	
	/**
	 * Sets up the game scene containing the actual game
	 * assets and functionality
	 */
	public void setupGameScene() {
		gameBorderPane = new BorderPane();
		gameBorderPane.setPadding(new Insets(10,10,10,10));
		
		// Center Box will contain Progress Bar and GridPane
		VBox centerBox = new VBox(2);
		centerBox.setAlignment(Pos.CENTER);
		
		setupProgressHBox();
		setupGridPane();
		
		centerBox.getChildren().addAll(progressHBox, gridPane);
		
		gameBorderPane.setTop(setupTopMenuBar());
		gameBorderPane.setCenter(centerBox);
		
		Image bgImage = new Image(GAME_BACKGROUND_IMAGE);
		BackgroundSize bSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true);
	    Background borderPaneBackground = new Background(new BackgroundImage(bgImage,
	            BackgroundRepeat.NO_REPEAT,
	            BackgroundRepeat.NO_REPEAT,
	            BackgroundPosition.CENTER,
	            bSize));
	    
	    gameBorderPane.setBackground(borderPaneBackground);
	    
		// Start timeline
		timeline.play();
		
		Scene scene = new Scene(gameBorderPane, SCENE_WIDTH, SCENE_HEIGHT);
		primaryStage.setTitle("Aliens vs. Astronauts");
		primaryStage.setScene(scene);
	}
	
	/**
	 * Sets up Progress Bar for Game Stage
	 * 
	 * This progress bar element will maintain the status of the current
	 * stages, updating accordingly as the game progresses.
	 */
	public void setupProgressHBox() {
		progressHBox = new HBox(2);
		progressHBox.setAlignment(Pos.CENTER);
		
		progressLabel = new Label("Stage 1");
		progressLabel.setFont(new Font("Courier New", 20));
		progressLabel.setStyle("-fx-font-weight: bold;");
		progressLabel.setPadding(new Insets(PROGRESSBAR_TOP_MARGIN, 25, 0, 0));
		
		progressBar = new ProgressBar();
		progressBar.setProgress(0.25);
		progressBar.setPadding(new Insets(PROGRESSBAR_TOP_MARGIN, 0, 0, 0));
		progressBar.setPrefWidth(PROGRESSBAR_WIDTH);
		
		progressHBox.getChildren().addAll(progressLabel, progressBar);
	}
	
	/**
	 * Sets up the GridPane containing a grid of open Tile objects
	 * as well as the extreme home-base/enemy-entry points
	 */
	public void setupGridPane() {
		gridPane = new GridPane();
		gridPane.setHgap(CELL_GAP);
		gridPane.setVgap(CELL_GAP);
		// gridPane.setGridLinesVisible(true);
		gridPane.setAlignment(Pos.CENTER);
		gridPane.setPadding(new Insets(GRIDPANE_TOP_MARGIN, 0, 0, 0));
		
		// TODO: Modify the base images below to be a neutral image representing open slot
		for (int row = 0; row < Controller.ROWS; row++) {
			for (int col = 0; col < Controller.COLS; col++) {
				if (col == 0) {
					gridPane.add(new ImageView(new Image(RAIL_GUN_IMAGE, GP_CELL_SIZE, GP_CELL_SIZE, false, false)), col, row);
				} else if (col == 11){
					gridPane.add(new ImageView(new Image("file:assets/blue-circle.png", GP_CELL_SIZE, GP_CELL_SIZE, false, false)), col, row);
				} else {
					gridPane.add(new ImageView(new Image("file:assets/placement-square.png", GP_CELL_SIZE, GP_CELL_SIZE, false, false)), col, row);
				}
			}
		}
		setupGridPaneDragHandlers();
	}
	
	/**
	 * Adds DragEvent Handlers for each Node in the GridPane
	 * 
	 * To support drag & drop, DragEvent handlers will be added to
	 * each Node to detect when an Object is being dragged OVER that
	 * node and when the Object has been DROPPED into that Node.
	 * Performs the necessary updates to place/reject placement.
	 */
	public void setupGridPaneDragHandlers() {
		List<Node> cells = gridPane.getChildrenUnmodifiable();
		for (int i = 0; i < Controller.ROWS * Controller.COLS; i++) {
			Node target = cells.get(i);
			
//			// TODO: This handler is for debugging purposes only, may remove afterwards
			target.setOnDragOver(e -> {
				
				try {
					if (e.getDragboard().hasImage()) {
						e.acceptTransferModes(TransferMode.ANY);
					}

					int row = GridPane.getRowIndex(target);
					int col = GridPane.getColumnIndex(target);
					//System.out.println("Dragging " + selectedTower.toString() + " over " + row + "," + col);
					e.consume();
				} catch (NullPointerException ex) {
					// Silences errors when dragging over HGaps & VGaps
				}
				
			});
			
			target.setOnDragDropped( e -> {
				e.acceptTransferModes(TransferMode.ANY);
				
				Dragboard db = e.getDragboard();
				if (db.hasImage()) {
					int row = GridPane.getRowIndex(target);
					int col = GridPane.getColumnIndex(target);
					if (!removeToggled) {
						// Place tower
						db.clear();
						controller.placeCharacter(selectedTower, row, col);
					} else {
						indexToRemove = new int[]{row, col};
						DefenderTower towerToRemove = model.getDefenderAt(row, col);
						System.out.println("Removing " + towerToRemove.toString() + " from " + row + "," + col);
						controller.removeTower(towerToRemove, row, col);
					}
				}
				e.setDropCompleted(true);
				e.consume();			
			});
		}
	}
	
	/**
	 * Sets up top navigation bar containing the defenders that
	 * can be placed onto the board.
	 */
	public VBox setupTopMenuBar() {
		// Create Menu Bar to hold a Utility Bar & Defender Bar
		VBox menuBar = new VBox(2);
		
		// Utility Bar contains: Fast-Forward & Pause Buttons
		HBox utilityBar = createUtilityBar();
		
		// Defender Bar contains:
		// Currency Card containing up-to-date bank amount
		// Defender Cards of placeable sprites
		// Remove Button to remove a Tower from the field
		HBox defenderBar = createDefenderBar();
		
		menuBar.getChildren().addAll(utilityBar, defenderBar);
		return menuBar;
	}
	
	/**
	 * Creates the Utility Bar
	 * 
	 * The Utility Bar contains Buttons that are useful to the player
	 * during gameplay such as fast-forwarding and pausing the game.
	 * 
	 * @return Generated HBox Object
	 */
	public HBox createUtilityBar() {
		HBox utilityBar = new HBox(3);
		utilityBar.setAlignment(Pos.TOP_RIGHT);
		
		// Buttons for utiliy bar with generated handlers
		Button fastForwardBtn = new Button(">>");
		Button pauseBtn = new Button("Pause");
		
		// Handlers
		fastForwardBtn.setOnAction( e -> {
			// TODO Implement Fast Forward functionality
			System.out.println("Fast forward button pressed");
		});
		
		pauseBtn.setOnAction( e -> {
			// TODO: Implement pause functionality
			System.out.println("Pause button pressed");
		});
		
		utilityBar.getChildren().addAll(fastForwardBtn, pauseBtn);
		
		return utilityBar;
	}
	
	/**
	 * Creates Defender Bar
	 * 
	 * Defender Bar contains the Defender Cards that the player can select
	 * when dragging and dropping onto the field. Also contains a Currency
	 * Card updating the user on their current bank amount. Also includes 
	 * a remove button that allows the player to remove a tower from the field.
	 * 
	 * @return Generated HBox Object 
	 */
	public HBox createDefenderBar() {
		HBox defenderBar = new HBox(4);
		defenderBar.setAlignment(Pos.CENTER_LEFT);
		defenderBar.setPadding(new Insets(0, 0, 0, MENUBAR_LEFT_PADDING));
		defenderBar.setSpacing(15);
		
		// First add currency card
		VBox currencyCard = new VBox(2);
		currencyCard.setAlignment(Pos.CENTER);
		currencyCard.setPadding(new Insets(5, 5, 0, 5));
		currencyCard.setEffect(new DropShadow(20, Color.BLACK));
		currencyCard.setStyle("-fx-background-color: gainsboro;" + 
				"-fx-background-radius: 6;" + 
				"-fx-border-style: solid inside;" + 
				"-fx-border-width: 2;" + 
				"-fx-border-radius: 5;" + 
				"-fx-border-color: limegreen;");
		
		ImageView imageView = new ImageView();
		imageView.setImage(new Image(SPACEBUCKS_IMAGE, DefenderTower.SPRITE_WIDTH * 1.5, DefenderTower.SPRITE_HEIGHT * 1.25, false, false));
		
		bankAmount = new Label("0"); // Start with empty bank
		bankAmount.setFont(new Font("Courier New", 16));
		bankAmount.setStyle("-fx-font-weight: bold;");
		bankAmount.setTextFill(Color.GREEN);
		bankAmount.setPadding(new Insets(8, 0, 5, 0));
		
		currencyCard.getChildren().addAll(imageView, bankAmount);
		
		// Now Generate HBox to store the Defender Selections
		HBox selectionBar = new HBox(7);
		selectionBar.setAlignment(Pos.CENTER);
		selectionBar.setPadding(new Insets(DEFENDERS_TOP_PADDING, 0, DEFENDERS_BOTTOM_PADDING, 0));
		
		// Add 6 Buttons as placeholder for tower items
		for (int i = 0; i < NUM_TOWERS; i++) {
			DefenderTower defender = defenderTowers[i];
			
			// Create VBox containing defender image and cost label
			VBox vbox = setupDefenderCard(defender);
			
			selectionBar.getChildren().add(vbox);
		}
		
		// Add button to act as the removal
		Button removeBtn = new Button("Remove");
		removeBtn.setMinWidth(100);
		removeBtn.setMaxWidth(100);
		removeBtn.setMinHeight(50);
		removeBtn.setMaxHeight(50);
		
		removeBtn.setOnDragDetected( e -> {
			removeToggled = true;
			
			// Allow Transfer Mode when drag initially detected
			Dragboard rm = removeBtn.startDragAndDrop(TransferMode.ANY);
			ClipboardContent content = new ClipboardContent();
			content.putImage(new Image("file:assets/removeX.jpg"));
			rm.setContent(content);
			e.consume();
			
			System.out.println("Remove button pressed");
		});
		
		defenderBar.getChildren().addAll(currencyCard, selectionBar, removeBtn);
		return defenderBar;
	}
	
	/**
	 * Method to setup a DefenderTower Card that provides the user with 
	 * a UI element they can interact with to select and place onto the 
	 * game board.
	 * 
	 * @param defender DefenderTower object needing a generated card
	 * @return Generated VBox containing defender image and cost label
	 */
	public VBox setupDefenderCard(DefenderTower defender) {
		VBox vbox = new VBox(2);
		vbox.setAlignment(Pos.CENTER);
		vbox.setPadding(new Insets(5, 5, 0, 5));
		vbox.setEffect(new DropShadow(20, Color.BLACK));
		vbox.setStyle("-fx-background-color: gainsboro;" + 
				"-fx-background-radius: 6;" + 
				"-fx-border-style: solid inside;" + 
				"-fx-border-width: 2;" + 
				"-fx-border-radius: 5;" + 
				"-fx-border-color: darkturquoise;");
		
		// Extract image to add to ImageView
		ImageView imageView = new ImageView();
		imageView.setImage(defender.getImage());
		
		// Handler when drag is initially detected
		imageView.setOnDragDetected(e -> {
			selectedTower = defender;
			
			// Allow Transfer Mode when drag initially detected
			Dragboard db = imageView.startDragAndDrop(TransferMode.ANY);

			// Set the avatars image as the draggable item
			ClipboardContent content = new ClipboardContent();
			content.putImage(defender.getImage());
			db.setContent(content);

			//System.out.println("Drag detected for " + defender.toString());
			e.consume();
		});
		
		Label costLabel = new Label(String.valueOf(defender.getCost()));
		costLabel.setFont(new Font("Courier New", 16));
		costLabel.setStyle("-fx-font-weight: bold;");
		costLabel.setTextFill(Color.RED);
		costLabel.setPadding(new Insets(8, 0, 5, 0));
		
		// Add to VBox
		vbox.getChildren().addAll(imageView, costLabel);
		
		//Sets the hover info card for the defender
		Tooltip tooltip = new Tooltip(defender.toString());
		Tooltip.install(vbox, tooltip);
		
		return vbox;
	}

}

