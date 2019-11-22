package game;

import java.util.Observable;
import characters.*;

public class Model extends Observable {
	// Class fields
	private BoardCharacter[] board;
	private int bank;
	
	// Constructor
	public Model () {
		
	}
	
	// Methods
	public BoardCharacter[] getBoard() {
		return board;
	}
	
	public int getSpaceBucks() {
		return bank;
	}
	
	public void placeTower(DefenderTower tower) {
		
	}
	
	public void updateBoard() {
		
	}
	
	public void depositSpaceBucks(int amount) {
		bank += amount;
	}
}
