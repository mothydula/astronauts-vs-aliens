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
		return "Health: \t\t" + String.valueOf(this.health) +
				"\nAttack Spd: \t" + String.valueOf(this.attackSpeed) +
				"\nDamage: \t\t" + String.valueOf(this.damage);
	}
	
}
