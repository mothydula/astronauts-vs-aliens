package ammo;

import characters.Astronauts.DefenderTower;
import javafx.scene.image.Image;
/**
 * Super class for all ammo
 * */
public class Ammo {
	private int damage;
	private int speed;
	private Image sprite;
	
	protected static final int SPRITE_WIDTH = 35;
	protected static final int SPRITE_HEIGHT = 35;
	//AstroJoe Ammo Image
	public static final String ASTROJOE_AMMO_SPRITE = "file:assets/ammo/astro-joe-ammo.png";
	
	//Explosive AstroJoe Ammo Image
	public static final String EXPLOSIVE_ASTROJOE_AMMO_SPRITE = "file:assets/ammo/explosive-astro-joe-ammo.png";
	
	//MoonZeus Ammo Image
	public static final String MOON_ZEUS_AMMO_SPRITE = "file:assets/ammo/moon-zeus-ammo.png";
	
	//Startrell Cluggins Ammo Image
	public static final String STARTRELL_CLUGGINS_AMMO_SPRITE = "file:assets/ammo/startrell-cluggins-ammo.png";
	
	//TARS Ammo Image
	public static final String TARS_AMMO_SPRITE = "file:assets/ammo/tars-ammo.png";
	
	
	public Ammo (DefenderTower dt, Image sprite) {
		this.speed = dt.getAttackSpeed();
		this.damage = dt.getDamage();
		this.sprite = sprite;
	}
	
	
}
