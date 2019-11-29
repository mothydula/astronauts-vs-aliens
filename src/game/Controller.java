package game;

import characters.Astronauts.DefenderTower;

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
	
	public void placeTower(DefenderTower defender, int row, int col) {
		int currBank = model.getSpacebucks();
		
		// Check if the tile is empty
		if (model.isEmpty(row, col)) {
			// Check if user can afford the tower
			if (currBank >= defender.getCost()) {
				model.placeTower(defender, row, col);
			} else {
				// TODO: Cannot afford tower, notify user
				model.notifyInvalidPlacement("cost");
			}
		} else {
			// TODO: Tile is taken, notify user
			model.notifyInvalidPlacement("taken");
		}
	}
	
	public void depositSpacebucks(int amount) {
		model.depositSpacebucks(amount);
	}
	
	public void removeTower() {
		
	}
	
	public void collectSpaceBucks() {
		
	}
}
