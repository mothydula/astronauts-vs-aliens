package game;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import characters.Astronauts.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
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
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class View extends Application implements Observer{
	
	// General constants
	private final int ROWS = 6;
	private final int COLS = 12;
	private final int NUM_CHARACTERS = 9;
	
	// View-specific constants
	private final int SCENE_WIDTH = 1300;
	private final int SCENE_HEIGHT = 800;
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
	
	private final String GAME_BACKGROUND_IMAGE 	= "file:assets/moon-background.png";
	private final String TITLE_GRAPHIC 			= "file:assets/game-title.png";
	private final String SPACEBUCKS_IMAGE	 	= "file:assets/spacebucks-image.png";
	
	// Class fields
	private Model model;
	private Controller controller;
	private List<DefenderTower> defenderTowers;
	
	private Stage primaryStage;
	private GridPane gridPane;
	private BorderPane startBorderPane;
	private BorderPane gameBorderPane;
	
	// Attributes so that we can update as the game progresses
	private HBox progressHBox;
	private ProgressBar progressBar;
	private Label progressLabel;
	private Label bankAmount;

	public View() {
		model = new Model();
		controller = new Controller();
		model.addObserver(this);
		defenderTowers = new ArrayList<DefenderTower>();
		defenderTowers.add(new AstroJoe());
		defenderTowers.add(new Asteroid());
		defenderTowers.add(new ExplosiveAstroJoe());
		defenderTowers.add(new StartrellCluggins());
		defenderTowers.add(new Tars());
		defenderTowers.add(new MoonZeus());
		defenderTowers.add(new MillenniumFalcon());
//		defenderTowers.add(new SpacebucksPrinter());
//		defenderTowers.add(new SpacebucksFactory());
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
		// TODO Auto-generated method stub
		
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
		astronautImageView.setImage(new Image(DefenderTower.ASTRO_JOE_GIF, ASTRO_WIDTH, ASTRO_HEIGHT, false, false));
		
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
		infoBtn.setOnAction( e -> {
			System.out.println("TODO: Insert info functionality");
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
		gridPane.setGridLinesVisible(true);
		gridPane.setAlignment(Pos.CENTER);
		gridPane.setPadding(new Insets(GRIDPANE_TOP_MARGIN, 0, 0, 0));
		
		// TODO: Modify the base images below to be a neutral image representing open slot
		for (int row = 0; row < ROWS; row++) {
			for (int col = 0; col < COLS; col++) {
				if (col == 0) {
					gridPane.add(new ImageView(new Image("file:assets/red-circle.jpg", 70, 70, false, false)), col, row);
				} else if (col == 11){
					gridPane.add(new ImageView(new Image("file:assets/blue-circle.png", 70, 70, false, false)), col, row);
				} else {
					gridPane.add(new ImageView(new Image("file:assets/green-circle.png", 70, 70, false, false)), col, row);
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
		for (int i = 0; i < ROWS * COLS; i++) {
			Node target = cells.get(i);
			target.setOnDragOver(e -> {
				
				try {
					if (e.getDragboard().hasImage()) {
						e.acceptTransferModes(TransferMode.ANY);
					}

					int row = GridPane.getRowIndex(target);
					int col = GridPane.getColumnIndex(target);
					System.out.println("Dragging over " + row + "," + col);
					e.consume();
				} catch (NullPointerException ex) {
					// Silences errors when dragging over HGaps & VGaps
				}
				
			});
			
			target.setOnDragDropped( e -> {
				e.acceptTransferModes(TransferMode.ANY);
				
				Dragboard db = e.getDragboard();
				if (db.hasImage()) {
					// controller.placeTower() logic
					int row = GridPane.getRowIndex(target);
					int col = GridPane.getColumnIndex(target);
					
					ImageView view = (ImageView)target;
					view.setImage(db.getImage());
					
					System.out.println("Dropping into " + row + "," + col);
					
					// TODO: Insert call to controller to place Defender onto board
					// if (controller.verifyPlacement(defender) == -1) {
					// 	// controller.updateBoard(erroneous_placement);
					// } else {
					// 	// controller.placeDefender(defender); [ensure validation] 
					// }
				}
				e.setDropCompleted(true);
				System.out.println("Drop complete");
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
		
		bankAmount = new Label("BANK");
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
		for (int i = 0; i < 7; i++) {
			DefenderTower defender = defenderTowers.get(i);
			
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
		
		removeBtn.setOnAction( e -> {
			// TODO: Implement removal functionality here
			// controller.removeTower()
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
			// Allow Transfer Mode when drag initially detected
			Dragboard db = imageView.startDragAndDrop(TransferMode.ANY);

			// Set the avatars image as the draggable item
			ClipboardContent content = new ClipboardContent();
			content.putImage(defender.getImage());
			db.setContent(content);

			System.out.println("Drag detected");
			e.consume();
		});
		
		Label costLabel = new Label(String.valueOf(defender.getCost()));
		costLabel.setFont(new Font("Courier New", 16));
		costLabel.setStyle("-fx-font-weight: bold;");
		costLabel.setTextFill(Color.RED);
		costLabel.setPadding(new Insets(8, 0, 5, 0));
		
		// Add to VBox
		vbox.getChildren().addAll(imageView, costLabel);
		return vbox;
	}

}

