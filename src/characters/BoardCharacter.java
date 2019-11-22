package characters;

import javafx.scene.image.Image;

public class BoardCharacter {
	// Class fields
	private int health;
	private int attackSpeed;
	private int damage;
	private Image sprite;
	
	// Constructor
	public BoardCharacter(int health, int attackSpeed, int damage) {
		this.health = health;
		this.attackSpeed = attackSpeed;
		this.damage = damage;
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
	
	protected Image getImage() {
		return sprite;
	}

}
