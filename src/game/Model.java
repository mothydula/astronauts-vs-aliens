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
	private int enemyWave;
	
	// Constructor
	public Model () {
		enemyWave = 5;
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
		if (character instanceof DefenderTower) {
			bank -= ((DefenderTower)character).getCost();
		}
		
		// Place character
		character.setRow(row);
		character.setCol(col);
		board[row][col].placeCharacter(character);
		
		// Creating message to send to view
		MoveMessage message = new MoveMessage(MoveMessage.VALID_MOVE, (DefenderTower)character, row, col, false);
		
		// Notify Observers
		setChanged();
		notifyObservers(message); // TODO: Handle successful placement
	}
	
	public void depositSpacebucks(int amount) {
		bank += amount;
		setChanged();
		notifyObservers();
	}
	
	public void notifyInvalidPlacement() {
		MoveMessage message = new MoveMessage(MoveMessage.INVALID_MOVE);
		setChanged();
		notifyObservers(message);
	}
	
	public void notifyInsufficientFunds() {
		MoveMessage message = new MoveMessage(MoveMessage.INSUFFICIENT_FUNDS);
		setChanged();
		notifyObservers(message);
	}

	public void removeTower(DefenderTower towerToRemove, int row, int col) {
		// adjusts bank amount
		bank += DefenderTower.REFUND_MULTIPLIER * towerToRemove.getCost();
		
		board[row][col] = new Tile(null);
		
		// Creating message to send to view
		MoveMessage message = new MoveMessage(MoveMessage.VALID_MOVE, towerToRemove, row, col, true);
		
		setChanged();
		notifyObservers(message);
	}

	public DefenderTower getDefenderAt(int row, int col) {
		return (DefenderTower) board[row][col].getCharacter();
	}
}
