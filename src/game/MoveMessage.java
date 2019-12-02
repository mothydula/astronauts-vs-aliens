package game;

import characters.Astronauts.DefenderTower;

public class MoveMessage {	
	// Class constants
	public static final int INSUFFICIENT_FUNDS = 0;
	public static final int INVALID_MOVE = 1;
	public static final int VALID_MOVE = 2;
	
	private int type;
	private DefenderTower tower;
	private int row;
	private int col;
	private boolean remove;
	
	// Constructor
	public MoveMessage(int type) {
		this.type = type;
	}
	
	public MoveMessage(int type, DefenderTower tower, int row, int col, boolean remove) {
		this.type = type;
		this.tower = tower;
		this.row = row;
		this.col = col;
		this.remove = remove;
	}
	
	public int getType() {
		return type;
	}
	
	public DefenderTower getTower() {
		return tower;
	}
	
	public int getRow() {
		return row;
	}
	
	public int getCol() {
		return col;
	}
	
	public boolean isRemove() {
		return remove;
	}
}
