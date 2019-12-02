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
	
	public void placeCharacter(BoardCharacter character, int row, int col) {
		// Adjust bank amount
		if(character instanceof DefenderTower) {
			bank -= ((DefenderTower)character).getCost();
		}
		
		// Place character
		character.setRow(row);
		character.setCol(col);
		board[row][col].placeCharacter(character);
		
		// Notify Observers
		setChanged();
		notifyObservers(character); // TODO: Handle successful placement
	}
	
	public void depositSpacebucks(int amount) {
		bank += amount;
		setChanged();
		notifyObservers();
	}
	
	public void notifyInvalidPlacement(String reason) {
		setChanged();
		notifyObservers(reason);
	}

	public void removeTower(DefenderTower towerToRemove, int row, int col) {
		// adjusts bank amount
		bank += DefenderTower.REFUND_MULTIPLIER * towerToRemove.getCost();
		
		board[row][col] = new Tile(null);
		setChanged();
		notifyObservers(towerToRemove);
	}

	public DefenderTower getDefenderAt(int row, int col) {
		return (DefenderTower) board[row][col].getCharacter();
	}
}
