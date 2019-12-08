/**
 * @author Adrian Bao
 * @author Trey Bryant
 * @author Mauricio Herrera
 * @author Tim Lukau
 * 
 * CSC 335 - Object Oriented Programming and Design
 * 
 * Title: Astronauts vs Aliens
 * 
 * File: View.java
 * 
 * Description: View that contains the User Interface and JavaFX
 * GUI components that provide the front end that the user interacts
 * with throughout the duration of the game. View Observes the modal
 * for any changes and updates accordingly when notified.
 */

package game;

import java.io.*;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;
import java.util.Set;

import ammo.Ammo;
import characters.Astronauts.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
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
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import characters.Aliens.Enemy;
import characters.Aliens.*;

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
	private final int TITLE_TOP_MARGIN = 75;
	private final int PROGRESSBAR_TOP_MARGIN = 50;
	private final int PROGRESSBAR_WIDTH = 500;
	private final int DEFENDERS_TOP_PADDING = 10;
	private final int DEFENDERS_BOTTOM_PADDING = 10;
	private final int MENUBAR_LEFT_PADDING = 20;
	
	
	// Start Menu constants
	private final int ASTRO_WIDTH = 300; // Start menu sprite
	private final int ASTRO_HEIGHT = 300; // Start menu sprite
	private final int ASTRO_LEFT_MARGIN = 50;
	private final int ALIEN_WIDTH = 250; // Start menu sprite
	private final int ALIEN_HEIGHT = 250; // Start menu sprite
	private final int ALIEN_RIGHT_MARGIN = 50;
	private final int MAP_SELECTION_WIDTH = 175;
	private final int MAP_SELECTION_HEIGHT = 80;
	
	private final String STARTER_BACKGROUND_IMAGE 	= "file:assets/general/space-gif.gif";
	private final String GAMEOVER_BACKGROUND_IMAGE  = "file:assets/general/game-over-background.png";
	private final String GAMEOVER_TITLE_IMAGE		= "file:assets/general/game-over-title.png";
	private final String WINNING_BACKGROUND_IMAGE	= "file:assets/general/winning-background.png";
	private final String STAGEONE_BACKGROUND_IMAGE 	= "file:assets/general/stage-one-background.png";
	private final String WINNING_TITLE_IMAGE		= "file:assets/general/winning-title.png";
	private final String STAGETWO_BACKGROUND_IMAGE 	= "file:assets/general/stage-two-background.png";
	private final String STAGETHREE_BACKGROUND_IMAGE 	= "file:assets/general/stage-three-background.png";
	private final String TITLE_GRAPHIC 				= "file:assets/general/game-title.png";
	private final String SPACEBUCKS_IMAGE	 		= "file:assets/general/spacebucks-image.png";
	private final String PLACEMENT_SQUARE_IMAGE 	= "file:assets/general/placement-square.png";
	private final String ACID_POOL_IMAGE			= "file:assets/general/acid-pool.png";
	private final String ASTRONAUT_STARTER_IMAGE 	= DefenderTower.STARTRELL_CLUGGINS_GIF;
	private final String REMOVE_X_IMAGE				= "file:assets/general/removeX.jpg";
	
	private Image PLACEMENT_SQUARE = new Image(PLACEMENT_SQUARE_IMAGE, GP_CELL_SIZE, GP_CELL_SIZE, false, false);
	private Image ACID_POOL = new Image(ACID_POOL_IMAGE, GP_CELL_SIZE, GP_CELL_SIZE, false, false);

	// fields for the music files
	private final String INTRO_MUSIC			= "assets/sounds/introMusic.mp3";
	private final String IN_GAME_MUSIC			= "assets/sounds/inGameMusic.mp3";

	private final int ALIEN_RANDOM_OFFSET = 200;

	
	// Class fields
	private Model model;
	private Controller controller;
	private DefenderTower[] defenderTowers;
	private DefenderTower selectedTower;
	
	private Stage primaryStage;
	private Group mainGroup;
	private BorderPane startBorderPane;
	private boolean paused;
	private int selectedMap = Controller.STAGE_ONE_ID;
	
	// Attributes so that we can update as the game progresses
	private HBox progressHBox;
	private ProgressBar progressBar;
	private Label progressLabel;
	private Label bankAmount;
	
	// notifies update that defender is being removed, not placed.
	private boolean removeToggled;
	
	// music player fields
	private MediaPlayer musicPlayer;
	private boolean isIntro; // is only true during the intro screen
	
	private StackPane[][] defendersGrid;

	public View() {
		Platform.setImplicitExit(true);  // If all windows are closed, exit program.
		initializeNewView();
	}
	
	/**
	 * Method that initializes a new View object with fresh
	 * attributes to allow reusability if user wishes to start
	 * a new game.
	 */
	public void initializeNewView() {
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
	}

	/**
	 * Method called upon launching the Viw. Will provide
	 * the user with a STart Menu to begin the Game.
	 */
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

	/**
	 * Method to execute updates to the View according to 
	 * the changes observed on the Model.
	 */
	@Override
	public void update(Observable o, Object arg) {
		if (arg instanceof MoveMessage) {
			MoveMessage message = (MoveMessage) arg;
			
			switch(message.getType()) {
				case MoveMessage.VALID_MOVE:
					performMove(message);
					break;
				case MoveMessage.INVALID_MOVE:
					// Display "Tile taken
					String toastMsgInvalid = "Tile is already taken!";
					int toastMsgTimeInvalid = 1000; 
					int fadeInTimeInvalid = 150; 
					int fadeOutTimeInvalid = 150; 
					Toast.makeText(primaryStage, toastMsgInvalid, 
							toastMsgTimeInvalid, fadeInTimeInvalid, fadeOutTimeInvalid, Color.RED);
					break;
				case MoveMessage.INSUFFICIENT_FUNDS:
					// Display "Not enough funds"
					String toastMsg = "Not enough funds!";
					int toastMsgTime = 1000; 
					int fadeInTime = 150; 
					int fadeOutTime= 150; 
					Toast.makeText(primaryStage, toastMsg, toastMsgTime, fadeInTime, fadeOutTime, Color.RED);
					break;

				case MoveMessage.BULLET_PLACEMENT:
					Ammo bullet = message.getBullet();
					StackPane bulletPane = bullet.getStackPane();
					
					//bulletPane.setStyle("-fx-border-color: black");
					bulletPane.setMaxSize(GP_CELL_SIZE, GP_CELL_SIZE);
					bulletPane.setTranslateY(BOARD_OFFSET + (bullet.getRow() * ROW_OFFSET) + (ROW_OFFSET/3));
					bulletPane.setTranslateX((GP_CELL_SIZE * bullet.getCol()) + COLUMN_OFFSET + (GP_CELL_SIZE * 0.7));

					mainGroup.getChildren().add(bulletPane);
					bullet.playBulletNoise();
					break;
				case MoveMessage.BULLET_REMOVAL:
					mainGroup.getChildren().remove(message.getBullet().getStackPane());
					break;
				case MoveMessage.GAME_OVER:
					triggerModal(GAMEOVER_TITLE_IMAGE, GAMEOVER_BACKGROUND_IMAGE, "Better luck next time!");
					break;
				case MoveMessage.GAME_WON:
					triggerModal(WINNING_TITLE_IMAGE, WINNING_BACKGROUND_IMAGE, "Congratulations!");
					break;
			}
		} else if (arg instanceof String) {
			String waveMsg = ((String)arg);
			int waveMsgTime = 2000; 
			int fadeInTime = 150; 
			int fadeOutTime = 150; 
			Toast.makeText(primaryStage, waveMsg, waveMsgTime, fadeInTime, fadeOutTime, Color.FORESTGREEN);
		}
		
		// Update bank amount after each update
		bankAmount.setText(String.valueOf(model.getSpacebucks()));
		
		// Check if the game is over
		controller.isGameOver();
		
	}
	
	/**
	 * Executes movement on the board
	 * 
	 * This method is responsible for all movement on the board, including
	 * tower placement, tower removal and alien/ammo movement.
	 *  
	 * @param message MoveMessage object containing information on requested move
	 */
	public void performMove(MoveMessage message) {
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
				mainGroup.getChildren().add(alienPane);
			}
		}
	}
	
	/**
	 * Places a bullet onto the View (to initiate Ammo animation)
	 * 
	 * @param message MoveMessage object containing information on requested placement
	 */
	public void placeBullet(MoveMessage message) {
		Ammo bullet = message.getBullet();
		StackPane bulletPane = bullet.getStackPane();
		bulletPane.setMaxSize(GP_CELL_SIZE, GP_CELL_SIZE);
		bulletPane.setTranslateY(BOARD_OFFSET + (bullet.getRow() * ROW_OFFSET) + (ROW_OFFSET/3));
		bulletPane.setTranslateX((GP_CELL_SIZE * bullet.getCol()) + COLUMN_OFFSET + (GP_CELL_SIZE * 0.7));
		mainGroup.getChildren().add(bulletPane);
	}
	
	/**
	 * Method that provides user with Game Over modal when detected
	 * 
	 * User will be presented a Modal that will stop the progress of the
	 * game and allow the user to return to the MainMenu.
	 */
	public void triggerModal(String titleImage, String backgroundImage, String message) {
		musicPlayer.stop();
		
		Stage modal = new Stage();
		modal.initModality(Modality.APPLICATION_MODAL);
		modal.initOwner(primaryStage);
		modal.setTitle("Astronauts vs Aliens");
		
		BorderPane gameOverPane = new BorderPane();
		gameOverPane.setPadding(new Insets(10, 10, 10, 10));
		
		Image bgImage = new Image(backgroundImage, 350, 300, false, false);
		BackgroundSize bSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true);
	    Background borderPaneBackground = new Background(new BackgroundImage(bgImage,
	            BackgroundRepeat.NO_REPEAT,
	            BackgroundRepeat.NO_REPEAT,
	            BackgroundPosition.CENTER,
	            bSize));
	    gameOverPane.setBackground(borderPaneBackground);
		
		Image gameOverTitle = new Image(titleImage, 250, 50, false, false);
		ImageView gameOverView = new ImageView(gameOverTitle);
		
		VBox centerBox = new VBox(2);
		centerBox.setAlignment(Pos.CENTER);
		ImageView astronautImageView = new ImageView();
		astronautImageView.setImage(new Image(ASTRONAUT_STARTER_IMAGE, ASTRO_WIDTH / 2, ASTRO_HEIGHT / 2, false, false));
		Text text = new Text(message);
		text.setFont(Font.font("Courier New", FontWeight.BOLD, 20));
		text.setFill(Color.WHITE);
		centerBox.getChildren().addAll(astronautImageView, text);
		
		Button exitBtn = new Button("Main Menu");
		exitBtn.setAlignment(Pos.CENTER);
		
		exitBtn.setOnAction( e -> {
			Platform.runLater(() -> {
				modal.close();
			});
		});
		
		gameOverPane.setTop(gameOverView);
		gameOverPane.setCenter(centerBox);
		gameOverPane.setBottom(exitBtn);
		
		BorderPane.setAlignment(gameOverView, Pos.CENTER);
		BorderPane.setAlignment(centerBox, Pos.CENTER);
		BorderPane.setAlignment(exitBtn, Pos.CENTER);
		
		Scene scene = new Scene(gameOverPane, 350, 350);
		modal.setScene(scene);
		modal.showAndWait();
		
		// Setup for new game
		initializeNewView();
		musicPlayer.stop();
		setupStartMenu();
	}
	
	/**
	 * Generates a music player to play specific music 
	 * depending on the current state of the game
	 */
	public void music()	{
		// TODO: after game music?? sad for when you lose
		String resource = null;
	    if (isIntro) {
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
	
	/**
	 * Generates the Start Menu
	 * 
	 * This menu allows the user to view the theme of the game and
	 * provides them with different options of playing the game.
	 */
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
		
		ImageView alienImageView = new LittleGreenMen().getWalkView();
		alienImageView.setFitWidth(ALIEN_WIDTH);
		alienImageView.setFitHeight(ALIEN_HEIGHT);
		
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
		BorderPane.setAlignment(astronautImageView, Pos.CENTER_LEFT);
		BorderPane.setAlignment(alienImageView,     Pos.CENTER_RIGHT);
		BorderPane.setAlignment(buttonBox,          Pos.CENTER);
		
		BorderPane.setMargin(titleBanner,        new Insets(TITLE_TOP_MARGIN, 0, 0, 0)); // top right bottom left
		BorderPane.setMargin(astronautImageView, new Insets(0, 0, 0, ASTRO_LEFT_MARGIN));
		BorderPane.setMargin(alienImageView,     new Insets(0, ALIEN_RIGHT_MARGIN, 0, 0));
		
		// Add border pane to scene and set the stage scene
		Scene scene = new Scene(startBorderPane, SCENE_WIDTH, SCENE_HEIGHT);
		music(); // starts intro music
		primaryStage.setScene(scene);
	}
	
	/**
	 * Generates Button Box held in the center of the Start Menu
	 * 
	 * This component stores the various buttons that are provided
	 * within the Start Menu to allow the user to view information
	 * about the game, select a game mode, and begin the game.
	 * 
	 * @return generated VBox object with the associated Buttons
	 */
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
			Text infoText = new Text("INSTRUCTIONS:\n"
					+ "The objective of this game is to fend of your enemies\n"
					+ "for as long as possible. You'll do this by accumulating SpaceBucks\n"
					+ "and spending them on defenders to protect your galaxy\n"
					+ "from the incoming aliens. The more expensive a defender is,\n"
					+ "the more useful they probably are. However, use your Space-\n"
					+ "Bucks wisely!\n\n"
					+ "HOW-TO:\n"
					+ "You can place a defender by dragging them from the queue and placing\n"
					+ "them anywhere between the alien spawn station and the last line of defense.\n\n"
					+ "No two defenders can occupy the same tile.\n\n"
					+ "A defender can be removed by dragging and dropping the remove button 'X'\n"
					+ "on the defender'.\n\n"
					+ "Hover over a defender in the queue for about 1-2 seconds to view specs");
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

		VBox mapPicker = new VBox(2);
		mapPicker.setAlignment(Pos.CENTER);
		Text mapText = new Text("Select Map");
		mapText.setFont(Font.font("Courier New", FontWeight.BOLD, 26));
		mapText.setFill(Color.WHITE);
		HBox mapDisplay = createMapSelectionBar();
		mapPicker.getChildren().addAll(mapText, mapDisplay);
		
		Button startBtn = new Button("Start");
		startBtn.setMinHeight(40);
		startBtn.setMinWidth(100);
		startBtn.setOnAction( e -> {
			// Switch scene to Game Scene
			setupGameScene();
			isIntro = false;
			musicPlayer.stop();
			music(); // starts in game music
			primaryStage.show();
		});
		
		HBox bottomBox = new HBox(infoBtn, startBtn);
		bottomBox.setAlignment(Pos.CENTER);
		bottomBox.setPadding(new Insets(10, 10, 10, 10));
		HBox.setMargin(infoBtn, new Insets(10, 10, 10, 10));
		HBox.setMargin(bottomBox, new Insets(10, 10, 10, 10));
		
		buttonBox.getChildren().addAll(mapPicker, bottomBox);
		buttonBox.setMaxWidth(600);
		buttonBox.setMaxHeight(200);
		
		buttonBox.setStyle(
				"-fx-background-color: rgba(220, 220, 220, 0.5);" + 
				"-fx-background-radius: 6;" + 
				"-fx-border-style: solid inside;" + 
				"-fx-border-width: 2;" + 
				"-fx-border-radius: 5;" + 
				"-fx-border-color: black;");
		
		return buttonBox;
	}
	
	/**
	 * Creates the Map Selection Bar
	 * 
	 * User is given the option of selecting from three different 
	 * themed maps with growing difficulty.
	 * 
	 * @return Geneates HBox containing Map Selector Bar
	 */
	public HBox createMapSelectionBar() {
		
		HBox mapSelector = new HBox(3);
		mapSelector.setAlignment(Pos.CENTER);
		
		// Create VBox containing ImageView and checkbox for each stage
		VBox mapOneBox = new VBox(2);
		mapOneBox.setAlignment(Pos.CENTER);
		ImageView mapOneView = new ImageView(new Image(STAGEONE_BACKGROUND_IMAGE));
		mapOneView.setFitWidth(MAP_SELECTION_WIDTH);
		mapOneView.setFitHeight(MAP_SELECTION_HEIGHT);
		CheckBox mapOneCheckBox = new CheckBox("The Canyon");
		mapOneCheckBox.setStyle("-fx-text-fill: rgba(7, 92, 197, 1.0);");
		mapOneCheckBox.setFont(Font.font("Courier New", FontWeight.BOLD, 16));
		mapOneBox.getChildren().addAll(mapOneView, mapOneCheckBox);

		VBox mapTwoBox = new VBox(2);
		mapTwoBox.setAlignment(Pos.CENTER);
		ImageView mapTwoView = new ImageView(new Image(STAGETWO_BACKGROUND_IMAGE));
		mapTwoView.setFitWidth(MAP_SELECTION_WIDTH);
		mapTwoView.setFitHeight(MAP_SELECTION_HEIGHT);
		CheckBox mapTwoCheckBox = new CheckBox("Sky Bridge");
		mapTwoCheckBox.setStyle("-fx-text-fill: rgba(7, 92, 197, 1.0);");
		mapTwoCheckBox.setFont(Font.font("Courier New", FontWeight.BOLD, 16));
		mapTwoBox.getChildren().addAll(mapTwoView, mapTwoCheckBox);

		VBox mapThreeBox = new VBox(2);
		mapThreeBox.setAlignment(Pos.CENTER);
		ImageView mapThreeView = new ImageView(new Image(STAGETHREE_BACKGROUND_IMAGE));
		mapThreeView.setFitWidth(MAP_SELECTION_WIDTH);
		mapThreeView.setFitHeight(MAP_SELECTION_HEIGHT);
		CheckBox mapThreeCheckBox = new CheckBox("The Nest");
		mapThreeCheckBox.setStyle("-fx-text-fill: rgba(7, 92, 197, 1.0);");
		mapThreeCheckBox.setFont(Font.font("Courier New", FontWeight.BOLD, 16));
		mapThreeBox.getChildren().addAll(mapThreeView, mapThreeCheckBox);

		mapOneCheckBox.setSelected(true); // Default selected map
		
		mapOneBox.setStyle(
				"-fx-background-color: rgba(220, 220, 220, 0.85);" + 
				"-fx-background-radius: 6;" + 
				"-fx-border-style: solid inside;" + 
				"-fx-border-width: 1;" + 
				"-fx-border-radius: 5;" + 
				"-fx-border-color: white;");
		
		mapTwoBox.setStyle(
				"-fx-background-color: rgba(220, 220, 220, 0.85);" + 
				"-fx-background-radius: 6;" + 
				"-fx-border-style: solid inside;" + 
				"-fx-border-width: 1;" + 
				"-fx-border-radius: 5;" + 
				"-fx-border-color: white;");
		
		mapThreeBox.setStyle(
				"-fx-background-color: rgba(220, 220, 220, 0.85);" + 
				"-fx-background-radius: 6;" + 
				"-fx-border-style: solid inside;" + 
				"-fx-border-width: 1;" + 
				"-fx-border-radius: 5;" + 
				"-fx-border-color: white;");
		
		mapOneCheckBox.setOnAction( e -> {
			if (mapOneCheckBox.isSelected()) {
				mapOneCheckBox.setSelected(true);
				mapTwoCheckBox.setSelected(false);
				mapThreeCheckBox.setSelected(false);
				// Set Map One as currently selected
				selectedMap = Controller.STAGE_ONE_ID;
			} else {
				if (mapTwoCheckBox.isSelected() || mapThreeCheckBox.isSelected()) {
					mapOneCheckBox.setSelected(false);
				} else {
					mapOneCheckBox.setSelected(true);
				}
			}
		});
		
		mapTwoCheckBox.setOnAction(e -> {
			if (mapTwoCheckBox.isSelected()) {
				mapOneCheckBox.setSelected(false);
				mapTwoCheckBox.setSelected(true);
				mapThreeCheckBox.setSelected(false);
				// Set Map One as currently selected
				selectedMap = Controller.STAGE_TWO_ID;
			} else {
				mapTwoCheckBox.setSelected(false);
				if (mapTwoCheckBox.isSelected() || mapThreeCheckBox.isSelected()) {
					mapOneCheckBox.setSelected(false);
				} else {
					mapOneCheckBox.setSelected(true);
				}
			}
		});

		mapThreeCheckBox.setOnAction(e -> {
			if (mapThreeCheckBox.isSelected()) {
				mapOneCheckBox.setSelected(false);
				mapTwoCheckBox.setSelected(false);
				mapThreeCheckBox.setSelected(true);
				// Set Map One as currently selected
				selectedMap = Controller.STAGE_THREE_ID;
			} else {
				mapThreeCheckBox.setSelected(false);
				if (mapTwoCheckBox.isSelected() || mapThreeCheckBox.isSelected()) {
					mapOneCheckBox.setSelected(false);
				} else {
					mapOneCheckBox.setSelected(true);
				}
			}
		});
		
		mapSelector.getChildren().addAll(mapOneBox, mapTwoBox, mapThreeBox);
		return mapSelector;
	}
	
	/**
	 * Sets up the game scene containing the actual game
	 * assets and functionality
	 */
	public void setupGameScene() {
		mainGroup = new Group();

		Image bgImage = new Image(STAGEONE_BACKGROUND_IMAGE); // Default
		if (selectedMap == Controller.STAGE_TWO_ID) {
			bgImage = new Image(STAGETWO_BACKGROUND_IMAGE);
			controller.assignMap(Controller.STAGE_TWO_ID);
		} else if (selectedMap == Controller.STAGE_THREE_ID){
			bgImage = new Image(STAGETHREE_BACKGROUND_IMAGE);
			controller.assignMap(Controller.STAGE_THREE_ID);
		} else {
			controller.assignMap(Controller.STAGE_ONE_ID);
		}
		

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
		Map<Integer, Set<Integer>> rTiles = controller.getRestrictedTiles();
		for (int row = 0; row < Controller.ROWS; row ++) {
			for (int col = 0; col < Controller.COLS; col++) {
				if (!(rTiles.containsKey(row) && rTiles.get(row).contains(col))) {
					StackPane tempStackPane = new StackPane();
					tempStackPane.setTranslateY(BOARD_OFFSET + (row * ROW_OFFSET));
					tempStackPane.setTranslateX((GP_CELL_SIZE * col) + COLUMN_OFFSET);
					tempStackPane.setMaxSize(GP_CELL_SIZE, GP_CELL_SIZE);
					if (col > 0) {
						ImageView squareImageView = new ImageView(PLACEMENT_SQUARE);
						tempStackPane.getChildren().add(squareImageView);
					}
					mainGroup.getChildren().add(tempStackPane);
				} else {
					StackPane tempStackPane = new StackPane();
					tempStackPane.setTranslateY(BOARD_OFFSET + (row * ROW_OFFSET));
					tempStackPane.setTranslateX((GP_CELL_SIZE * col) + COLUMN_OFFSET);
					tempStackPane.setMaxSize(GP_CELL_SIZE, GP_CELL_SIZE);
					ImageView squareImageView = new ImageView(ACID_POOL);
					tempStackPane.getChildren().add(squareImageView);
					mainGroup.getChildren().add(tempStackPane);
				}
			}
		}
	}
	
	/**
	 * Sets up drag & drop handlers for the Grid
	 * 
	 * Creates both a dragOver and dragDropped event handler
	 * to handle placement/removal of towers on the grid.
	 */
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
						controller.placeCharacter(generateTowerFromSelected(), row, col);
						
					} else {
						DefenderTower towerToRemove = model.getDefenderAt(row, col);
						if (towerToRemove != null) {
							controller.removeTower(towerToRemove, row, col);
						}
						removeToggled = false;
					}
				}
				e.setDropCompleted(true);
				e.consume();
			}
		});
	}
	
	/**
	 * Creates new instantiation of objects
	 * 
	 * This method was created to ensure that the Object that is
	 * being placed is independent and will not interfere with other
	 * references.
	 * @return
	 */
	private DefenderTower generateTowerFromSelected() {
		DefenderTower tower = null;
		if (selectedTower instanceof Asteroid) {
			tower = new Asteroid();
		} else if (selectedTower instanceof AstroJoe) {
			tower = new AstroJoe();
		} else if (selectedTower instanceof ExplosiveAstroJoe) {
			tower = new ExplosiveAstroJoe();
		} else if (selectedTower instanceof MillenniumFalcon) {
			tower = new MillenniumFalcon();
		} else if (selectedTower instanceof MoneyBush) {
			tower = new MoneyBush();
		} else if (selectedTower instanceof MoneyTree) {
			tower = new MoneyTree();
		} else if (selectedTower instanceof MoonZeus) {
			tower = new MoonZeus();
		} else if (selectedTower instanceof StartrellCluggins) {
			tower = new StartrellCluggins();
		} else if (selectedTower instanceof Tars) {
			tower = new Tars();
		}
		
		return tower;
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
			if (!paused) {
				controller.increaseSpeed();
				fastForwardBtn.setText(controller.getSpeedMultiplier() + "X");
			}
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