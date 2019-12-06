package game;

import java.util.ArrayList;
import java.util.List;

import ammo.Ammo;
import characters.BoardCharacter;
import characters.Aliens.Enemy;

public class MoveMessage {	
	// Class constants
	public static final int INSUFFICIENT_FUNDS = 0;
	public static final int INVALID_MOVE = 1;
	public static final int VALID_MOVE = 2;
	public static final int BULLET_PLACEMENT = 3;
	public static final int BULLET_REMOVAL = 4;
	public static final int ACTIVATE_RAILGUN = 5;
	
	private int type;
	private BoardCharacter character;
	private int row;
	private int col;
	private boolean remove;
	private Ammo bullet;
	private List<Enemy> aliens;
	
	// Constructor
	public MoveMessage(int type) {
		this.type = type;
	}
	
	public MoveMessage(int type, BoardCharacter character, int row, int col, boolean remove) {
		this.type = type;
		this.character = character;
		this.row = row;
		this.col = col;
		this.remove = remove;
		this.aliens = new ArrayList<Enemy>();
	}
	
	public void setBullet(Ammo bullet) {
		this.bullet = bullet;
	}
	
	public Ammo getBullet() {
		return this.bullet;
	}
	
	public int getType() {
		return type;
	}
	
	public BoardCharacter getCharacter() {
		return character;
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
	
	public void setAliens(List<Enemy> aliens) {
		this.aliens = aliens;
	}
	
	public List<Enemy> getAliens() {
		return aliens;
	}
}
