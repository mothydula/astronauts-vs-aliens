package characters;

import javafx.scene.image.Image;

/*
 * This class is the parent class of all aliens and defenders alike. It contains common fields
 * like row and column (that indicate where the character is on the board) as well as health
 * which enables characters to take damage (until health reaches 0). This class also contains an
 * Image field which stores the sprite associated with each character. Enemy (the alien class) and
 * DefenderTower (defender class) both inherit from this class.
 */
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
	
	// Methods
	public boolean isDead() {
		return this.health <= 0;
	}
	
	public void decreaseHealth(int amount) {
		this.health -= amount;
	}
	
	public int getHealth() {
		return health;
	}
	
	public void setHealth(int health) {
		this.health = health;
	}
	
	public int getAttackSpeed() {
		return attackSpeed;
	}
	
	public void setAttackSpeed(int attackSpeed) {
		this.attackSpeed = attackSpeed;
	}
	
	public int getDamage() {
		return damage;
	}
	
	public void setDamage(int damage) {
		this.damage = damage;
	}
	
	protected void setImage(Image sprite) {
		this.sprite = sprite;
	}
	
	public Image getImage() {
		return sprite;
	}

	public int getRow() {
		return row;
	}
	
	public void setRow(int row) {
		this.row = row;
	}
	
	public int getCol() {
		return col;
	}
	
	public void setCol(int col) {
		this.col = col;
	}
	
	public String infoCard() {
		return "Health: " + String.valueOf(this.health) +
				"\nAttack Spd:" + String.valueOf(this.attackSpeed) +
				"\nDamage: " + String.valueOf(this.damage);
	}
	
}
