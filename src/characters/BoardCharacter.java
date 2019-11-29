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
	private Image sprite;
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
	
}
