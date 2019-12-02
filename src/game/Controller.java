package game;


import characters.BoardCharacter;
import characters.Astronauts.DefenderTower;
import characters.IncomeTowers.IncomeTower;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class Controller {
	// Class fields
	public static final int ROWS = 6;
	public static final int COLS = 12;
	private Model model;
	
	// Constructor
	public Controller(Model model) {
		this.model = model;
	}
	
	// Methods
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
				model.placeTower(character, row, col);
			} else {
				model.notifyInvalidPlacement("cost"); // not enough funds
			}
		} else {
			model.notifyInvalidPlacement("taken"); // tile is taken
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
