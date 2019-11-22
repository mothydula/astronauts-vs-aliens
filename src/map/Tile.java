package map;

import characters.BoardCharacter;

public class Tile {
	// Class fields
	private boolean isOccupied;
	private BoardCharacter characterAtTile;
	
	// Constructor
	public Tile() {
		
	}
	
	// Methods
	public boolean isOccupied() {
		return isOccupied;
	}
	
	public BoardCharacter getCharacter() {
		return characterAtTile;
	}
	
	public void placeCharacter(BoardCharacter character) {
		this.characterAtTile = character;
	}

}
