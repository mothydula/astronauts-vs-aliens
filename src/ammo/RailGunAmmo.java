package ammo;

import characters.Astronauts.AstroJoe;
import characters.Astronauts.RailGun;
import javafx.scene.image.Image;

/**
 * AstroJoe's Ammo class
 * */
public class RailGunAmmo extends Ammo {
	
	/**Constructor
	 * */
	public RailGunAmmo(RailGun railGun) {
		super(railGun, new Image(RAIL_GUN_AMMO_SPRITE, SPRITE_WIDTH, SPRITE_HEIGHT, false, false));
	}
}
