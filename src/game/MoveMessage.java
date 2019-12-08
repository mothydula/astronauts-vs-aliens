/**
 * @author Adrian Bao
 * @author Trey Bryant
 * @author Mauricio Herrera
 * @author Tim Lukau
 * 
 * CSC 335 - Object Oriented Programming and Design
 * 
 * Title: Astronauts vs Aliens
 * 
 * File: MoveMessage.java
 * 
 * Description: MoveMessage Object stores contents and information
 * of a particular identity in order to streamline the updating of 
 * the View. This class is meant to streamline the handling of all
 * aspects of the game with specified IDs listed below.
 */

package game;

import ammo.Ammo;
import characters.BoardCharacter;

public class MoveMessage {	
	// Class constants
	public static final int INSUFFICIENT_FUNDS = 0;
	public static final int INVALID_MOVE = 1;
	public static final int VALID_MOVE = 2;
	public static final int BULLET_PLACEMENT = 3;
	public static final int BULLET_REMOVAL = 4;
	public static final int GAME_OVER = 5;
	public static final int GAME_WON = 6;
	
	private int type;
	private BoardCharacter character;
	private int row;
	private int col;
	private boolean remove;
	private Ammo bullet;
	
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
	}
	
	/**
	 * Public mutator for the bullet attribute
	 * @param bullet Ammo object to be set
	 */
	public void setBullet(Ammo bullet) {
		this.bullet = bullet;
	}
	
	/**
	 * Public accessor for the bullet attribute
	 * @return Ammo object bullet attribute
	 */
	public Ammo getBullet() {
		return this.bullet;
	}
	
	/**
	 * Public accessor for the type attribute
	 * @return integer type attribute
	 */
	public int getType() {
		return type;
	}
	
	/**
	 * Public accessor for the character attribute
	 * @return BoardCharacter object character attribute
	 */
	public BoardCharacter getCharacter() {
		return character;
	}
	
	/**
	 * Public accessor for the row attribute
	 * @return integer row value
	 */
	public int getRow() {
		return row;
	}
	
	/**
	 * Public accessor for the col attribute
	 * @return integer col value
	 */
	public int getCol() {
		return col;
	}
	
	/**
	 * Method to check if the current message is signifying
	 * a removal of some sort
	 * @return boolean value of the remove attribute
	 */
	public boolean isRemove() {
		return remove;
	}
}
