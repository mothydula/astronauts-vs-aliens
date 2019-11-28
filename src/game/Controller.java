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
		// TODO: Insert bounds checking
		model.placeTower(defender, row, col);
	}
	
	public void removeTower() {
		
	}
	
	public void collectSpaceBucks() {
		
	}
}
