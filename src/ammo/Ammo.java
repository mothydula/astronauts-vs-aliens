package ammo;

import characters.Astronauts.DefenderTower;
import javafx.scene.image.Image;

public class Ammo {
	private int damage;
	private int speed;
	private Image sprite;
	
	protected static final int SPRITE_WIDTH = 35;
	protected static final int SPRITE_HEIGHT = 35;
	//AstroJoe Ammo Image
	public static final String ASTROJOE_AMMO_SPRITE = 
	
	public Ammo (DefenderTower dt, Image sprite) {
		this.speed = dt.getAttackSpeed();
		this.damage = dt.getDamage();
		this.sprite = sprite;
	}
	
	
}
