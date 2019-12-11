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
 * File: BoardCharacter.java
 * 
 * Description: This class is the parent class of all aliens and defenders alike. It contains common fields
 * like row and column (that indicate where the character is on the board) as well as health
 * which enables characters to take damage (until health reaches 0). This class also contains an
 * Image field which stores the sprite associated with each character. Enemy (the alien class) and
 * DefenderTower (defender class) both inherit from this class.
 */

package characters;

import javafx.scene.image.Image;

public class BoardCharacter {
	
	// Constants
	public static final int SPRITE_WIDTH = 70;
	public static final int SPRITE_HEIGHT = 70;
	
	// Class fields
	private int health;
	private int attackSpeed;
	private int damage;
	protected Image sprite;
	private int row;
	private int col;
	
	// Constructor
	protected BoardCharacter(int health, int attackSpeed, int damage, Image sprite) {
		this.health = health;
		this.attackSpeed = attackSpeed;
		this.damage = damage;
		this.sprite = sprite;
		this.row = -1;
		this.col = -1;
	}
	
	/**
	 * Utility method that determines if the current Character
	 * has their health depleted.
	 * @return boolean result for the health check
	 */
	public boolean isDead() {
		return this.health <= 0;
	}
	
	/**
	 * Public mutator method for the heath attrbibute. Decreases
	 * by a specified amount, based on the character that is attacking.
	 * @param amount
	 */
	public void decreaseHealth(int amount) {
		this.health -= amount;
	}
	
	/**
	 * Public accesor method for the health attribute
	 * @return int health attribute
	 */
	public int getHealth() {
		return health;
	}
	
	/**
	 * Public mutator method for the health attribute. Simply
	 * sets the health to the specified amount.
	 * @param health int health to set this BoardCharacter's health
	 */
	public void setHealth(int health) {
		this.health = health;
	}
	
	/**
	 * Public accesor method for the attackSpeed attribute
	 * @return int attackSpeed attribute
	 */
	public int getAttackSpeed() {
		return attackSpeed;
	}
	
	
	/**
	 * Public mutator for the attackSpeed attribute
	 * @param attackSpeed int attackSpeed to be set
	 */
	public void setAttackSpeed(int attackSpeed) {
		this.attackSpeed = attackSpeed;
	}
	
	/**
	 * Public accessor method for the damage attribute
	 * @return int damage attribute
	 */
	public int getDamage() {
		return damage;
	}
	
	/**
	 * Public mutator method for the damage attribute
	 * @param damage int damage to be set
	 */
	public void setDamage(int damage) {
		this.damage = damage;
	}
	
	/**
	 * Public mutator method for the sprite attribute
	 * @param sprite Image object to be set
	 */
	protected void setImage(Image sprite) {
		this.sprite = sprite;
	}
	
	/**
	 * Public accessor method for the sprite attribute
	 * @return Image Object sprite attribute
	 */
	public Image getImage() {
		return sprite;
	}

	/**
	 * Public accessor of the row attribute, where the Character
	 * is currently positioned on the board.
	 * @return row position of the current BoardCharacter
	 */
	public int getRow() {
		return row;
	}
	
	/**
	 * Public mutator of the row attribute.
	 * @param row int row value to be set/updated
	 */
	public void setRow(int row) {
		this.row = row;
	}
	
	/**
	 * Public accessor of the col attribute, where the Character
	 * is currently positioned on the board.
	 * @return col position of the current BoardCharacter
	 */
	public int getCol() {
		return col;
	}
	
	/**
	 * Public mutator of the col attribute.
	 * @param col int col value to be set/updated
	 */
	public void setCol(int col) {
		this.col = col;
	}
	
	/**
	 * Utility method that generates a String representation of a 
	 * BoardCharacter's stats. Utilized/present to the player in the View.
	 * @return
	 */
	public String infoCard() {
		return "Health: \t\t" + String.valueOf(this.health) +
				"\nAttack Spd: \t" + String.valueOf(this.attackSpeed) +
				"\nDamage: \t\t" + String.valueOf(this.damage);
	}
	
}
