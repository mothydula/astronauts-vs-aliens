package characters;

import javafx.scene.image.Image;

public class BoardCharacter {
	// Class fields
	private int health;
	private int attackSpeed;
	private int damage;
	private Image sprite;
	
	// Constructor
	protected BoardCharacter(int health, int attackSpeed, int damage, Image sprite) {
		this.health = health;
		this.attackSpeed = attackSpeed;
		this.damage = damage;
		this.sprite = sprite;
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

}
