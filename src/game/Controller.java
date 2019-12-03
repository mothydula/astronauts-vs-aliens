package game;


import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import characters.BoardCharacter;
import characters.Aliens.LittleGreenMen;
import characters.Astronauts.DefenderTower;
import characters.IncomeTowers.IncomeTower;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.util.Duration;

public class Controller {
	// Class fields
	public static final int ROWS = 6;
	public static final int COLS = 12;
	private Model model;
	private Timer timer;
	private static final long FRAME_TIME = 16l;
	private static final int STAGE_ONE_ALIENS = 5;
	private static final int STAGE_TWO_ALIENS = 10;
	private static final int STAGE_THREE_ALIENS = 20;
	
	// Constructor
	public Controller(Model model) {
		this.model = model;
	}
	
	// Methods
	public void initialize() {
		generateAliens();
		startTimer();
	}
	
	private void generateAliens() {
		Random rand = new Random();
		int stage = model.getStage();
		
		if (stage == 1) {
			for (int i = 0; i < STAGE_ONE_ALIENS; i++) {
				LittleGreenMen alien = new LittleGreenMen();
				int row = rand.nextInt(ROWS);
				int col = COLS - 1;
				alien.setRow(row);
				alien.setCol(col);
				
				model.placeAlien(alien, row, col);
			}
		} else if (stage == 2) {
			
		} else if (stage == 3) {
			
		}
	}
	
	private void startTimer() {
		this.timer = new Timer();
		TimerTask task = new TimerTask() {
			public void run() {
				Platform.runLater(() -> animate());
			}
		};
		timer.schedule(task, 0, FRAME_TIME);
	}
	
	private void animate() {
		
	}
	
	
	public void purchaseTower() {
		
	}
	
	public void placeCharacter(BoardCharacter character, int row, int col) {
		int currBank = model.getSpacebucks();
		
		// Check if the tile is empty
		if (model.isEmpty(row, col)) {
			
			// Check if user can afford the tower
			if ((character instanceof DefenderTower) && (currBank >= ((DefenderTower)character).getCost())) {
				
				// Check if currency tower
				if (character instanceof IncomeTower) {
					// Add a timeline for currency generation for this specific income tower
					IncomeTower incomeTower = (IncomeTower)character;
					Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(incomeTower.getTimeline()), e -> {
						model.depositSpacebucks(incomeTower.getDepositAmount());
					}));
					timeline.setCycleCount(Timeline.INDEFINITE);
					timeline.play();
				}
				model.placeCharacter(character, row, col);
			} else {
				model.notifyInsufficientFunds(); // not enough funds
			}
		} else {
			model.notifyInvalidPlacement(); // tile is taken
		}
	}
	
	public void depositSpacebucks(int amount) {
		model.depositSpacebucks(amount);
	}
	
	public void removeTower(DefenderTower towerToRemove, int row, int col) {
		if (model.isEmpty(row, col)) {
			// model.notifyInvalidRemoval();
		} else {
			model.removeTower(towerToRemove, row, col);
		}
	}
	
	public void collectSpaceBucks() {
		// TODO: implement
	}
}
