package game;

import java.util.Observable;
import characters.*;
import characters.Astronauts.DefenderTower;
import javafx.scene.image.Image;
import map.Tile;

public class Model extends Observable {
	// Class fields
	private Tile[][] board;
	private int bank;
	
	// Constructor
	public Model () {
		board = new Tile[Controller.ROWS][Controller.COLS];
		initializeBoard();
	}
	
	// Methods
	public void initializeBoard() {
		for (int row = 0; row < Controller.ROWS; row++) {
			for (int col = 0; col < Controller.COLS; col++) {
				board[row][col] = new Tile(null); // Initialize empty tile
			}
		}
	}
	
	public boolean isEmpty(int row, int col) {
		return board[row][col].isEmpty();
	}
	
	public Tile[][] getBoard() {
		return board;
	}
	
	public int getSpacebucks() {
		return bank;
	}
	
	public void placeTower(DefenderTower defender, int row, int col) {
		// Adjust bank amount
		bank -= defender.getCost();
		
		// Place character
		defender.setRow(row);
		defender.setCol(col);
		board[row][col].placeCharacter(defender);
		
		// Notifiy Observers
		setChanged();
		notifyObservers(defender); // TODO: Handle successful placement
	}
	
	public void depositSpacebucks(int amount) {
		bank += amount;
		setChanged();
		notifyObservers((Integer)bank); // Pass new bank amount to update UI
	}
	
	public void notifyInvalidPlacement(String reason) {
		setChanged();
		notifyObservers(reason);
	}
}
