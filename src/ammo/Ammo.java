package ammo;

import characters.Astronauts.DefenderTower;
import javafx.scene.image.Image;

public class Ammo {
	private int damage;
	private int speed;
	private Image sprite;
	public Ammo (DefenderTower dt, Image sprite) {
		this.speed = dt.getAttackSpeed();
		this.damage = dt.getDamage();
		this.sprite = sprite;
	}
	
	
}
