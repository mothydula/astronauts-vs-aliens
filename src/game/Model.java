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
	
	public Tile[][] getBoard() {
		return board;
	}
	
	public int getSpaceBucks() {
		return bank;
	}
	
	public void placeTower(DefenderTower defender, int row, int col) {
		if (board[row][col].getCharacter() != null) {
			System.out.println("This tile wasn't empty");
		}
		defender.setRow(row);
		defender.setCol(col);
		board[row][col].placeCharacter(defender);
		setChanged();
		notifyObservers(defender); // TODO: Handle successful placement
	}
	
	public void depositSpaceBucks(int amount) {
		bank += amount;
	}
}
