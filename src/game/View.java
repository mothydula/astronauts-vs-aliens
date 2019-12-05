package game;

import java.io.*;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;
import ammo.Ammo;
import characters.Astronauts.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.Group;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import characters.Aliens.Enemy;

public class View extends Application implements Observer{
	
	// General constants
	private final int NUM_TOWERS = 9;
	
	// View-specific constants
	private final int BOARD_OFFSET = 310;
	private final int ROW_OFFSET = 82;
	public static final int COLUMN_OFFSET = 170;
	
	
	private final int SCENE_WIDTH = 1300;
	private final int SCENE_HEIGHT = 800;
	public static final int GP_CELL_SIZE = 80;
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
	
	private final String STARTER_BACKGROUND_IMAGE = "file:assets/general/space-gif.gif";
	private final String GAME_BACKGROUND_IMAGE 	= "file:assets/general/stage-one-background.png";
	private final String TITLE_GRAPHIC 			= "file:assets/general/game-title.png";
	private final String SPACEBUCKS_IMAGE	 	= "file:assets/general/spacebucks-image.png";
	private final String RAIL_GUN_IMAGE 		= "file:assets/general/rail-gun.png";
	private final String RAIL_GUN_GIF 			= "file:assets/general/rail-gun.gif";
	private final String PLACEMENT_SQUARE_IMAGE = "file:assets/general/placement-square.png";
	private final String ALIEN_STARTER_IMAGE 	= "file:assets/aliens/grunt-walk.gif";
	private final String ASTRONAUT_STARTER_IMAGE = DefenderTower.STARTRELL_CLUGGINS_GIF;
	private final String BLUE_CIRCLE			= "file:assets/general/blue-circle.png";
	private final String REMOVE_X_IMAGE			= "file:assets/general/removeX.jpg";

	// fields for the music files
	private final String INTRO_MUSIC			= "assets/general/introMusic.mp3";
	private final String IN_GAME_MUSIC			= "assets/general/inGameMusic.mp3";

	private final int ALIEN_RANDOM_OFFSET = 200;

	
	// Class fields
	private Model model;
	private Controller controller;
	private DefenderTower[] defenderTowers;
	private DefenderTower selectedTower;
	
	private Stage primaryStage;
	private Group mainGroup;
	private BorderPane startBorderPane;
	private BorderPane gameBorderPane;
	private boolean paused;
	
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
	
	// music player fields
	private MediaPlayer musicPlayer;
	private boolean isIntro; // is only true during the intro screen
	
	private StackPane[][] defendersGrid;

	public View() {
		Platform.setImplicitExit(true);  // If all windows are closed, exit program.
		paused = false;
		defendersGrid = new StackPane[Controller.ROWS][Controller.COLS];
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

		isIntro = true;
		removeToggled = false;
		indexToRemove = new int[]{0, 0};
		
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
		    @Override
		    public void handle(WindowEvent t) {
		        Platform.exit();
		        System.exit(0);
		    }
		});
		
		// Setup Start menu & show to begin the game
		setupStartMenu();
		this.primaryStage.show();
	}
	
	@Override
	public void update(Observable o, Object arg) {
		if (arg instanceof MoveMessage) {
			MoveMessage message = (MoveMessage) arg;
			
			switch(message.getType()) {
				case MoveMessage.VALID_MOVE:
					if (message.isRemove()) {
						if (message.getCharacter() instanceof DefenderTower) {
							mainGroup.getChildren().remove(defendersGrid[message.getRow()][message.getCol()]);
						} else {
							mainGroup.getChildren().remove(((Enemy) message.getCharacter()).getStackPane());
						}
						removeToggled = false;
					} else {
						if (message.getCharacter() instanceof DefenderTower) {
							StackPane characterPane = new StackPane();
							characterPane.setMaxSize(GP_CELL_SIZE, GP_CELL_SIZE);
							characterPane.setTranslateY(BOARD_OFFSET + (message.getRow() * ROW_OFFSET));
							characterPane.setTranslateX((GP_CELL_SIZE * message.getCol()) + COLUMN_OFFSET);

							Image towerImage = message.getCharacter().getImage();
							ImageView towerImageView = new ImageView(towerImage);
							characterPane.getChildren().add(towerImageView);
							mainGroup.getChildren().add(characterPane);
							defendersGrid[message.getRow()][message.getCol()] = characterPane;
						} else {
							Random rand = new Random();
							StackPane alienPane = ((Enemy) message.getCharacter()).getStackPane();
							alienPane.setMaxSize(GP_CELL_SIZE, GP_CELL_SIZE);
							alienPane.setTranslateY(BOARD_OFFSET + (message.getRow() * ROW_OFFSET));
							alienPane.setTranslateX((GP_CELL_SIZE * message.getCol()) + COLUMN_OFFSET + rand.nextInt(ALIEN_RANDOM_OFFSET));
//							alienPane.setStyle("-fx-border-color: black");
//							Platform.runLater(() -> mainGroup.getChildren().add(alienPane));
							mainGroup.getChildren().add(alienPane);
						}
					}
					break;
				case MoveMessage.INVALID_MOVE:
					// Display "Tile taken
					String toastMsgInvalid = "Tile is already taken!";
					int toastMsgTimeInvalid = 1000; //3.5 seconds
					int fadeInTimeInvalid = 150; //0.5 seconds
					int fadeOutTimeInvalid = 150; //0.5 seconds
					Toast.makeText(primaryStage, toastMsgInvalid, 
							toastMsgTimeInvalid, fadeInTimeInvalid, fadeOutTimeInvalid);
					break;
				case MoveMessage.INSUFFICIENT_FUNDS:
					// Display "Not enough funds"
					String toastMsg = "Not enough funds!";
					int toastMsgTime = 1000; //3.5 seconds
					int fadeInTime = 150; //0.5 seconds
					int fadeOutTime= 150; //0.5 seconds
					Toast.makeText(primaryStage, toastMsg, toastMsgTime, fadeInTime, fadeOutTime);
					break;

				case MoveMessage.BULLET_PLACEMENT:
					Ammo bullet = message.getBullet();
					StackPane bulletPane = bullet.getStackPane();
					
					bulletPane.setMaxSize(GP_CELL_SIZE, GP_CELL_SIZE);
					bulletPane.setTranslateY(BOARD_OFFSET + (bullet.getRow() * ROW_OFFSET) + (ROW_OFFSET/3));
					bulletPane.setTranslateX((GP_CELL_SIZE * bullet.getCol()) + COLUMN_OFFSET + (GP_CELL_SIZE / 2));

					mainGroup.getChildren().add(bulletPane);
				case MoveMessage.BULLET_REMOVAL:
					mainGroup.getChildren().remove(message.getBullet().getStackPane());
			}
		}	
//		
		// Update bank amount after each update
		bankAmount.setText(String.valueOf(model.getSpacebucks()));
//		
//		// TODO: controller.isGameOver();
	}
	
	public void music()
	{
		Media song;
		String resource = null;
	    if (isIntro) {
			System.out.println("trying");
			resource = new File(INTRO_MUSIC).toURI().toString();
		} else {
			resource = new File(IN_GAME_MUSIC).toURI().toString();
		}

		musicPlayer = new MediaPlayer(new Media(resource));
		musicPlayer.setOnEndOfMedia(new Runnable() {
	        @Override
	        public void run() {
	            musicPlayer.seek(Duration.ZERO);
	            musicPlayer.play();
	        }
	    }); 
		
	    musicPlayer.play();
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
		astronautImageView.setImage(new Image(ASTRONAUT_STARTER_IMAGE, ASTRO_WIDTH, ASTRO_HEIGHT, false, false));
		
		ImageView alienImageView = new ImageView();
		alienImageView.setImage(new Image(ALIEN_STARTER_IMAGE, ALIEN_WIDTH, ALIEN_HEIGHT, false, false));
		
		// Create & Set background for the border pane
		Image bgImage = new Image(STARTER_BACKGROUND_IMAGE, SCENE_WIDTH, SCENE_HEIGHT, false, false);
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
		music(); // starts intro music
		primaryStage.setScene(scene);
	}
	
	@SuppressWarnings("deprecation")
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
			VBox infoVBox = new VBox(8);
			infoVBox.setPadding(new Insets(50,12,12,12));
			Button returntoMenu = new Button("Close");
			infoVBox.getChildren().add(infoPane);
			infoVBox.getChildren().add(returntoMenu);
			infoVBox.setAlignment(Pos.CENTER);
			Scene infoScene = new Scene(infoVBox);
			Stage infoStage = new Stage();
			returntoMenu.setOnAction(ba->{
				infoStage.close();
			});
			infoStage.setScene(infoScene);
			infoStage.setTitle("Info");
			
			//Removes the minimize, maximize and close buttons
			infoStage.initStyle(StageStyle.UNDECORATED);
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
			isIntro = false;
			musicPlayer.stop();
			music(); // starts in game music
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
		mainGroup = new Group();

		Image bgImage = new Image(GAME_BACKGROUND_IMAGE);

	    ImageView bgImageView = new ImageView(bgImage);
	    bgImageView.setFitHeight(SCENE_HEIGHT);
	    bgImageView.setFitWidth(SCENE_WIDTH);
	    mainGroup.getChildren().add(bgImageView);
		mainGroup.getChildren().add(setupTopMenuBar());
		setupGrid();
		setupGridHandler();
	    
		
		Scene scene = new Scene(mainGroup, SCENE_WIDTH, SCENE_HEIGHT);
		primaryStage.setTitle("Aliens vs. Astronauts");
		primaryStage.setResizable(false);
		primaryStage.setScene(scene);
		
		
		controller.initialize();
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
	public void setupGrid() {
		Image square = new Image(PLACEMENT_SQUARE_IMAGE, GP_CELL_SIZE, GP_CELL_SIZE, false, false);
		Image gunImage = new Image(RAIL_GUN_IMAGE, GP_CELL_SIZE, GP_CELL_SIZE, false, false);
		
		for (int row = 0; row < Controller.ROWS; row ++) {
			for (int col = 0; col < Controller.COLS; col++) {
				if (col == 0) {
					StackPane gunStackPane = new StackPane();
//					gunStackPane.setStyle("-fx-border-color: black");
					gunStackPane.setTranslateY(BOARD_OFFSET + (row * ROW_OFFSET));
					gunStackPane.setTranslateX(COLUMN_OFFSET);
					gunStackPane.setMaxSize(GP_CELL_SIZE, GP_CELL_SIZE);
					ImageView gunImageView = new ImageView(gunImage);
					gunStackPane.getChildren().add(gunImageView);
					mainGroup.getChildren().add(gunStackPane);
					
					defendersGrid[row][col] = gunStackPane;
				} else if (col <= Controller.COLS - 1) {
					StackPane tempStackPane = new StackPane();
//					tempStackPane.setStyle("-fx-border-color: black");
					tempStackPane.setTranslateY(BOARD_OFFSET + (row * ROW_OFFSET));
					tempStackPane.setTranslateX((GP_CELL_SIZE * col) + COLUMN_OFFSET);
					tempStackPane.setMaxSize(GP_CELL_SIZE, GP_CELL_SIZE);

					ImageView squareImageView = new ImageView(square);
					tempStackPane.getChildren().add(squareImageView);
					mainGroup.getChildren().add(tempStackPane);
				}
			}
		}
		
	}
	
	
	private void setupGridHandler() {
		mainGroup.setOnDragOver(e -> {
			e.acceptTransferModes(TransferMode.ANY);
			e.consume();
		});
		
		mainGroup.setOnDragDropped(e -> {
			e.acceptTransferModes(TransferMode.ANY);
			Dragboard db = e.getDragboard();
			if (db.hasImage()) {
				int col = -1;
				int row = -1;
				if (e.getX() >= COLUMN_OFFSET && e.getX() <= (GP_CELL_SIZE * Controller.COLS) + COLUMN_OFFSET) {
					col = (int)(e.getX() - COLUMN_OFFSET) / GP_CELL_SIZE;
				}
				if (e.getY() >= BOARD_OFFSET && e.getY() <= SCENE_HEIGHT) {
					row = (int)(e.getY() - BOARD_OFFSET) / GP_CELL_SIZE;
				}
				
				if (col != -1 && row != -1) {
					if (!removeToggled) {
						// Place tower
						db.clear();
						controller.placeCharacter(selectedTower, row, col);
						
					} else {
						indexToRemove = new int[]{row, col};
						DefenderTower towerToRemove = model.getDefenderAt(row, col);
						if (towerToRemove != null) {
							controller.removeTower(towerToRemove, row, col);
						} else {
							removeToggled = false;
						}
					}
				}
				e.setDropCompleted(true);
				e.consume();
			}
		});
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
		utilityBar.setTranslateX(COLUMN_OFFSET);
		utilityBar.setTranslateY(ROW_OFFSET);
		
		// Buttons for utiliy bar with generated handlers
		Button fastForwardBtn = new Button(">>");
		Button pauseBtn = new Button("Pause");
		
		// Handlers
		fastForwardBtn.setOnAction( e -> {
			controller.increaseSpeed();
			fastForwardBtn.setText(controller.getSpeedMultiplier() + "X");
		});
		
		pauseBtn.setOnAction( e -> {
			if (paused) {				// TODO: create pause menu modal
				controller.resume();
				pauseBtn.setText("Pause");
				paused = false;
			} else {
				controller.pause();
				paused = true;
				pauseBtn.setText("Resume");
			}
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
			content.putImage(new Image(REMOVE_X_IMAGE));
			rm.setContent(content);
			e.consume();
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